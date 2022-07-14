# Create a VPC to launch our instances into.
resource "aws_vpc" "vpc" {
  cidr_block = "10.0.0.0/16"


  tags = {
    Name = "${var.environment}-vpc"
  }
}

# Create an internet gateway to give our subnet access to the outside world.
resource "aws_internet_gateway" "gateway" {
  vpc_id = "${aws_vpc.vpc.id}"

  tags ={
    Name = "${var.environment}-igw"
  }
}

# Grant the VPC internet access on its main route table.
resource "aws_route" "route" {
  route_table_id         = "${aws_vpc.vpc.main_route_table_id}"
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = "${aws_internet_gateway.gateway.id}"
}

# Create subnets in each availability zone to launch our instances into, each with address blocks within the VPC.
resource "aws_subnet" "main" {
  count                   = "${length(data.aws_availability_zones.available.names)}"
  vpc_id                  = "${aws_vpc.vpc.id}"
  cidr_block              = "10.0.${count.index}.0/24"
  map_public_ip_on_launch = true
  availability_zone       = "${element(data.aws_availability_zones.available.names, count.index)}"

  tags ={
    Name = "${var.environment}-public-${element(data.aws_availability_zones.available.names, count.index)}"
  }
}

# Create a security group in the VPC which our instances will belong to.
resource "aws_security_group" "default" {
  name        = "${var.environment}-sg-default"
  description = "Terraform example security group"
  vpc_id      = "${aws_vpc.vpc.id}"

  # Restrict inboud SSH traffic by IP address.
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = "${var.allowed_cidr_blocks}"
  }

  # Restrict inbound HTTP traffic to the load balancer.
  ingress {
    from_port       = 80
    to_port         = 80
    protocol        = "tcp"
    security_groups = ["${aws_security_group.alb.id}"]
  }

  # Allow outbound internet access.
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.environment}-sg-default"
  }
}

# Create an application load balancer security group.
resource "aws_security_group" "alb" {
  name        = "${var.environment}-sg-alb"
  description = "Terraform load balancer security group"
  vpc_id      = "${aws_vpc.vpc.id}"

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = "${var.allowed_cidr_blocks}"
  }

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = "${var.allowed_cidr_blocks}"
  }

  # Allow all outbound traffic.
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "${var.environment}-sg-alb"
  }
}

# Create a new application load balancer.
resource "aws_alb" "alb" {
  name            = "${var.environment}-alb"
  security_groups = ["${aws_security_group.alb.id}"]
  subnets         = aws_subnet.main.*.id

  tags= {
    Name = "${var.environment}-alb"
  }
}


# Create a new target group for the application load balancer.
resource "aws_alb_target_group" "group" {
  name     = "${var.environment}-alb-target"
  port     = 80
  protocol = "HTTP"
  vpc_id   = "${aws_vpc.vpc.id}"

  stickiness {
    type = "lb_cookie"
  }

  # Alter the destination of the health check to be the login page.
  health_check {
    path = "/"
    port = 80
  }
}

# Create a new application load balancer listener for HTTP.
resource "aws_alb_listener" "listener_http" {
  load_balancer_arn = "${aws_alb.alb.arn}"
  port              = "80"
  protocol          = "HTTP"

  default_action {
    target_group_arn = "${aws_alb_target_group.group.arn}"
    type             = "forward"
  }
}

# Create a new application load balancer listener for HTTPS.
resource "aws_alb_listener" "listener_https" {
  load_balancer_arn = "${aws_alb.alb.arn}"
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = "${var.certificate_arn}"

  default_action {
    target_group_arn = "${aws_alb_target_group.group.arn}"
    type             = "forward"
  }
}

# Define a record set in Route 53 for the load balancer.
resource "aws_route53_record" "www" {
  zone_id = var.route53_zone_id
  name    = var.domain
  type    = "A"

  alias {
    name                   = "${aws_alb.alb.dns_name}"
    zone_id                = "${aws_alb.alb.zone_id}"
    evaluate_target_health = true
  }
}


#API
# Create a new application load balancer API.
resource "aws_alb" "alb_api" {
  name            = "${var.environment}-alb-api"
  security_groups = ["${aws_security_group.alb.id}"]
  subnets         = aws_subnet.main.*.id

  tags= {
    Name = "${var.environment}-alb-api"
  }
}

# Create a new target group for the application load balancer.
resource "aws_alb_target_group" "group_api" {
  name     = "${var.environment}-alb-target-api"
  port     = 80
  protocol = "HTTP"
  vpc_id   = "${aws_vpc.vpc.id}"

  stickiness {
    type = "lb_cookie"
  }

  # Alter the destination of the health check to be the login page.
  health_check {
    path = "/"
    port = 80
  }
}

# Create a new application load balancer listener for HTTP.
resource "aws_alb_listener" "listener_http_api" {
  load_balancer_arn = "${aws_alb.alb_api.arn}"
  port              = "80"
  protocol          = "HTTP"

  default_action {
    target_group_arn = "${aws_alb_target_group.group_api.arn}"
    type             = "forward"
  }
}

# Create a new application load balancer listener for HTTPS.
resource "aws_alb_listener" "listener_https_api" {
  load_balancer_arn = "${aws_alb.alb_api.arn}"
  port              = "443"
  protocol          = "HTTPS"
  ssl_policy        = "ELBSecurityPolicy-2016-08"
  certificate_arn   = "${var.certificate_arn}"

  default_action {
    target_group_arn = "${aws_alb_target_group.group_api.arn}"
    type             = "forward"
  }
}

# Define a record set in Route 53 for the load balancer.
resource "aws_route53_record" "api" {
  zone_id = var.route53_zone_id
  name    = "api.${var.domain}"
  type    = "A"

  alias {
    name                   = "${aws_alb.alb_api.dns_name}"
    zone_id                = "${aws_alb.alb_api.zone_id}"
    evaluate_target_health = true
  }
}
