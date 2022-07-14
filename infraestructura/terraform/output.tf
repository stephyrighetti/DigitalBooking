# Define the load balancer DNS name as an output variable.
output "lb_dns_name" {
  value = "${aws_alb.alb.dns_name}"
}

# Define the load balancer DNS name as an output variable.
output "lb_dns_name_api" {
  value = "${aws_alb.alb_api.dns_name}"
}
