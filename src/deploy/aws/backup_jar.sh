#!/bin/bash

EVENT_NAME="ApplicationStart"
SERVICE_NAME="manibogo-v2.service"

echo "[$EVENT_NAME] $SERVICE_NAME JAR 파일을 백업합니다."
mkdir -p /home/ec2-user/backup

mv /home/ec2-user/manibogo-oms-v2-*.jar /home/ec2-user/backup