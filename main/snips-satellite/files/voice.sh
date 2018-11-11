#!/bin/bash

TEXT="$1"
OUTPUT_FILE="$2"

wget "http://central-tts:8081/transform?text=$TEXT&effects=auto-breaths" -O $OUTPUT_FILE || rm -f $OUTPUT_FILE

if [ ! -f $OUTPUT_FILE ]; then
    echo "Problem with central-tts; using on device synthesis: $OUTPUT_FILE"
    pico2wave -w $OUTPUT_FILE $TEXT
fi