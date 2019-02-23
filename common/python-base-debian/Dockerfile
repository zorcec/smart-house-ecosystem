FROM debian:stretch

ENV ARCH amd64
ENV LANG C.UTF-8

RUN apt-get update && apt-get install -y supervisor git python3 python3-pip
RUN pip3 install --upgrade pip setuptools

RUN apt-get update && apt-get install -y \
    sox \
    alsa-utils \
    libasound2 \
    libasound2-plugins \
    pulseaudio \
    pulseaudio-utils \
    --no-install-recommends

COPY /common-config/pulse/* /etc/pulse/

CMD ["echo", "python-base is ready!"]