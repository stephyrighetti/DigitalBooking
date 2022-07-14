# Create a new EC2 launch configuration to be used with the autoscaling group.
resource "aws_launch_configuration" "launch_config" {
  name_prefix                 = "${var.environment}-web-instance"
  image_id                    = "${var.amis}"
  instance_type               = "${var.instance_type}"
  key_name                    = "${aws_key_pair.deployer.key_name}"
  security_groups             = ["${aws_security_group.default.id}"]
  associate_public_ip_address = true
  user_data                   = "${data.template_file.provision.rendered}"
  iam_instance_profile        = aws_iam_instance_profile.iam_ip.name

  lifecycle {
    create_before_destroy = true
  }

  depends_on = [
    aws_db_instance.default
  ]
}

# Create the autoscaling group.
resource "aws_autoscaling_group" "autoscaling_group" {
  launch_configuration = "${aws_launch_configuration.launch_config.id}"
  min_size             = "${var.autoscaling_group_min_size}"
  max_size             = "${var.autoscaling_group_max_size}"
  target_group_arns    = ["${aws_alb_target_group.group.arn}"]
  vpc_zone_identifier  = aws_subnet.main.*.id

  tag  {
      key                 = "Name"
      value               = "${var.environment}-autoscaling-group"
      propagate_at_launch = true
  }

  depends_on = [
    aws_launch_configuration.launch_config
  ]
   
  
}
