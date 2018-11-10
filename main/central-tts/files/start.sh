#!/bin/bash

mkdir -p /cache/audio

gradle compile

/usr/bin/supervisord -c /etc/supervisor/conf.d/services.conf