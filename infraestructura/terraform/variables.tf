variable "amis" {
  default = "ami-098e42ae54c764c35"
}

variable "autoscaling_group_min_size" {
  default = 1
}
variable "autoscaling_group_max_size" {
  default = 3
}
variable "instance_type" {
  default = "t2.micro"
}

variable "key_name" {
  default = "sshkey"
}
variable "public_key" {
  default = "sshkey.pub"
}

# Defining Private Key
variable "private_key" {
  default = "sshkey.pem"
}
variable "rds_instance_identifier" {
  default = "c5g10db0621"
}
variable "database_name" {
  default = "db_prod"
}
variable "database_password" {
  default = "grupo10.2022"
}
variable "database_user" {
  default = "grupo10"
}
variable "certificate_arn" {
  default = "arn:aws:acm:us-west-2:145504712931:certificate/96c9cc47-fec7-48d8-afed-52c1b8fb8e6c"
}
variable "route53_hosted_zone_name" {
  default = "api.bookingdh.xyz"
}

variable "allowed_cidr_blocks" {
  default = ["0.0.0.0/0"]
}

variable "route53_zone_id" {
  default = "Z096030214DPHDTUYRX4W"
}

# Name of domain
variable "domain" {
  default = "bookingdh.xyz"
  
}

variable "s3_bucket_name" {
  default = "s3-grupo10"
}

variable "environment" {
  default = "c5g10-0621"
}
