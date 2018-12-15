FROM snipsdocker/platform

ENV ARCH amd64

RUN apt-get update

RUN mkdir -p /common-config
VOLUME /common-config

# Sound config & test
COPY /files/* /files/
RUN apt-get update && apt-get install -y \
    sox \
    alsa-utils \
    libasound2 \
    libasound2-plugins \
    pulseaudio \
    pulseaudio-utils \
    --no-install-recommends

COPY /common-config/pulse/* /etc/pulse/

# Cleanup
RUN rm -rf /temp
RUN apt-get clean

CMD ["echo", "Snips base is ready!"]
