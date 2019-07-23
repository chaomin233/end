#!/bin/bash

cd ..

echo "检查Docker......"
docker -v
if [ $? -eq  1 ]; then
    echo "检查到Docker已安装!"

    img_mvn="maven:3.3.3-jdk-8"                 # docker image of maven
    m2_cache=~/.m2                              # the local maven cache dir
    proj_home=$PWD

    echo "打包swagger.json"
    echo "use docker maven"
    docker run --rm \
       -v $m2_cache:/root/.m2 \
       -v $proj_home:/usr/src/mymaven \
       -w /usr/src/mymaven $img_mvn mvn clean package

    echo "swagger正导入yapi......"
    docker run --rm \
        -v $proj_home/swagger/swagger.json:/home/swagger.json \
        -v $proj_home/swagger/config.json:/home/yapi-import.json \
        -w /home \
        shaowin/yapi-cli sh -c "yapi import"

else
    echo "打包swagger.json"
    echo "use 本地 maven"
    mvn clean package

    echo "swagger正导入yapi......"
    yapi import --config config.json
fi