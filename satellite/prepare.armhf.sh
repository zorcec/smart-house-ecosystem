#!/usr/bin/env bash

docker-compose -f ../common/docker-compose.armhf.yaml build java-base
docker-compose build
