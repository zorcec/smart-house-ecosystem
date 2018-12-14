#!/bin/bash

TEXT="$1"
OUTPUT_FILE="$2"

wget "http://central-tts:8081/transform?text=$TEXT&effects=auto-breaths" --quiet -O $OUTPUT_FILE || rm -f $OUTPUT_FILE
