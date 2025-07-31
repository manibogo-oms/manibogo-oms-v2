#!/bin/bash

EVENT_NAME="ApplicationStart"
SERVICE_NAME="manibogo-v2.service"

echo "[$EVENT_NAME] 구성 파일 변경을 위해 3초간 대기합니다."
/usr/bin/sleep 3

echo "[$EVENT_NAME] 대기 완료. $SERVICE_NAME을 실행합니다."
/usr/bin/systemctl restart $SERVICE_NAME
