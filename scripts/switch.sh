#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    echo 'set \$service_url http://127.0.0.1:${IDLE_PORT};' | sudo tee /etc/nginx/conf.d/service-url.inc
    # 엔진엑스가 변경할 프록시 주소 생성 홑따옴표 사용하지 않을시 $service_url의 변수를 찾으려 하게 됨
    # sudo ~~~ // 앞에서 넘어온 문장을 service-url에 덮어쓴다
    echo "> 엔진엑스 reload"
    sudo service nginx reload
    # restart와 달리 끊기지 않고 재로딩을 한다. 외부 설정파일만 불러오기 때문에 가능
}