#!/bin/bash

# Install Nginx
sudo yum update -y
sudo yum install -y httpd wget
sudo yum install -y mysql
sudo systemctl start httpd
sudo systemctl enable httpd
sudo systemctl is-enabled httpd
sudo usermod -a -G apache ec2-user
sudo chown -R ec2-user:apache /var/www
sudo chmod 2775 /var/www && find /var/www -type d -exec sudo chmod 2775 {} \;
find /var/www -type f -exec sudo chmod 0664 {} \;
sudo sed -i 's/AllowOverride None/AllowOverride All/g' /etc/httpd/conf/httpd.conf

#Download S3 Bucket
aws s3 cp s3://${s3_bucket_name}/ /opt/api/ --region=${region} --no-sign-request --recursive --exclude "*" --include "build*.zip"
unzip -o /opt/api/build.zip -d /var/www/html/
cat << EOF > /var/www/html/.htaccess
<IfModule mod_rewrite.c>
  RewriteEngine On
  RewriteBase /
  RewriteRule ^index\.html$ - [L]
  RewriteCond RRR{REQUEST_FILENAME} !-f
  RewriteCond RRR{REQUEST_FILENAME} !-d
  RewriteCond RRR{REQUEST_FILENAME} !-l
  RewriteRule . /index.html [L]
</IfModule>
EOF

sudo sed -i 's/RRR/%/g' /var/www/html/.htaccess
sudo systemctl is-enabled httpd
sudo systemctl start httpd && sudo systemctl enable httpd
sudo systemctl restart httpd.service
