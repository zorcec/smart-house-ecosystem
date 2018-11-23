#!/bin/bash

pactl set-default-source 4

/usr/bin/supervisord -c /etc/supervisor/conf.d/services.conf