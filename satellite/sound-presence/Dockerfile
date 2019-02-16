FROM common_python-base-debian

RUN apt-get update && apt-get install -y python3-tk ffmpeg libasound2-dev

COPY /src/requirements_all.txt /src/requirements_all.txt
RUN pip3 install --no-cache-dir -r /src/requirements_all.txt

# Cleanup
RUN apt-get clean autoremove

RUN mkdir -p /src
VOLUME /src

WORKDIR /src

CMD ["python3", "-m", "audio_meter"]
