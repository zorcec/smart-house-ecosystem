#!/usr/bin/env bash

docker-compose -f ../common/docker-compose.yaml build java-base-debian snips-base
docker-compose build
