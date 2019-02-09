FROM common_python-base-debian

RUN apt-get update && apt-get install -y libasound-dev portaudio19-dev libportaudio2 libportaudiocpp0 ffmpeg

COPY /src/requirements_all.txt /src/requirements_all.txt
RUN pip3 install --no-cache-dir -r /src/requirements_all.txt

# Cleanup
RUN apt-get clean autoremove

RUN mkdir -p /src
VOLUME /src

WORKDIR /src

CMD ["python3", "-m", "audio_meter"]
