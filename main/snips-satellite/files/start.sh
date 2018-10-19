#!/bin/bash

pulseaudio -D
/usr/bin/supervisord -c /etc/supervisor/conf.d/services.conf