#!/usr/bin/env bash

docker-compose -f ../common/docker-compose.yaml build snips-base-custom java-base-debian snips-base
docker-compose build
