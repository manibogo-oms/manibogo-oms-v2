#!/bin/bash

EVENT_NAME="ApplicationStart"
SERVICE_NAME="manibogo-v2.service"

echo "[$EVENT_NAME] $SERVICE_NAME을 실행합니다."
systemctl start $SERVICE_NAME
