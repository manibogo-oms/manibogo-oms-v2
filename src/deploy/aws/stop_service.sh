#!/bin/bash

EVENT_NAME="ApplicationStop"
SERVICE_NAME="manibogo-v2.service"

echo "[$EVENT_NAME] $SERVICE_NAME 을 중지합니다."
/usr/bin/systemctl stop $SERVICE_NAME

if /usr/bin/systemctl is-active --quiet $SERVICE_NAME; then
        echo "[$EVENT_NAME] $SERVICE_NAME이 아직 실행중입니다. 강제 종료를 시도합니다."
        /usr/bin/systemctl kill $SERVICE_NAME || true
fi 