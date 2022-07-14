#!/bin/bash

# Install Nginx
sudo yum update -y
sudo yum install -y httpd wget
sudo systemctl start httpd
sudo systemctl enable httpd
sudo systemctl is-enabled httpd
sudo usermod -a -G apache ec2-user
sudo chown -R ec2-user:apache /var/www
sudo chmod 2775 /var/www && find /var/www -type d -exec sudo chmod 2775 {} \;
find /var/www -type f -exec sudo chmod 0664 {} \;
sudo sed -i 's/AllowOverride None/AllowOverride All/g' /etc/httpd/conf/httpd.conf

sudo iptables -I INPUT 1 -p tcp --dport 8080 -j ACCEPT
sudo iptables -I INPUT 1 -p tcp --dport 80 -j ACCEPT
sudo iptables -A PREROUTING -t nat -i eth0 -p tcp --dport 80 -j REDIRECT --to-port 8080


sudo systemctl is-enabled httpd
sudo systemctl start httpd && sudo systemctl enable httpd

# Install Java
sudo yum -y install java-17-amazon-corretto-headless
#api java
mkdir -p /home/ec2-user/api
mkdir -p /home/ec2-user/api/applications
mkdir -p /home/ec2-user/api/scripts/
chown -R ec2-user /home/ec2-user/api
#chown ec2-user:ec2-user /home/ec2-user/api -R
#chmod 755 /home/ec2-user/api
# Copy the Spring Boot application from S3
mkdir /opt/api
aws s3 cp s3://${s3_bucket_name}/ /opt/api/ --region=${region} --no-sign-request --recursive --exclude "*" --include "proyectoIntegrador*.jar"
mv /opt/api/proyectoIntegrador*.jar /home/ec2-user/api/applications/proyectoIntegrador-0.0.1-SNAPSHOT.jar

cat << EOF > /etc/systemd/system/backendApi.service
[Unit]
Description=Java Application as a Service
After=syslog.target

[Service]
User=ec2-user
#change this directory into your workspace
#mkdir workspace
WorkingDirectory=/home/ec2-user/api/applications
#path to the executable bash script which executes the jar file
ExecStart=/bin/bash /home/ec2-user/api/scripts/backendApi-script.sh
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF

cat << EOF > /home/ec2-user/api/scripts/backendApi-script.sh
#!/bin/sh
java -jar /home/ec2-user/api/applications/proyectoIntegrador-0.0.1-SNAPSHOT.jar
EOF
# Automatically start services
systemctl daemon-reload
systemctl enable backendApi.service
systemctl start backendApi

