#!/bin/bash

pulseaudio -D --system

/usr/bin/supervisord -c /etc/supervisor/conf.d/services.conf