#!/usr/bin/env bash

docker-compose -f ../common/docker-compose.armhf.yaml build java-base snips-base
docker-compose build
