#!/usr/bin/env bash

docker-compose -f ../common/docker-compose.armhf.yaml build java-base-debian snips-base
docker-compose build
