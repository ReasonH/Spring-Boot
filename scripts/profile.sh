#!/usr/bin/env bash

# 쉬고 있는 profile 찾기: real1이 사용 중이면 real2가 쉬고 있고, 반대면 real1이 쉬고있음

function find_idle_profile() {
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
    # 응답 코드

    if [ ${RESPONSE_CODE} -GE 400 ] # 400보다 크면 (즉, 40X/50X 에러 모두 포함)
    then
      CURRENT_PROFILE=real2
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi

    echo "${IDLE_PROFILE}"
    # bash 스크립트는 반환 기능이 없기 때문에 echo로 클라이언트에서 값을 얻는다
}

# 쉬고 있는 profile의 port 찾기
function find_idle_port() {
      IDLE_PROFILE=$(find_idle_profile)

      if [ ${IDLE_PROFILE} == real1 ]
      then
        echo "8081"
      else
        echo "8082"
      fi
}