FROM debian:stretch-slim

ENV ARHITECTURE amd64

RUN apt-get update

# Install snips
RUN apt-get install -y dirmngr apt-transport-https ca-certificates && \
    bash -c  'echo "deb https://debian.snips.ai/stretch stable main" > /etc/apt/sources.list.d/snips.list' && \
    apt-key adv --keyserver pgp.mit.edu --recv-keys F727C778CCB0A455 && \
    apt-get update && \
    apt-get install -y snips-platform-voice

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

COPY /config/pulse/* /etc/pulse/

# Cleanup    
RUN apt-get clean

CMD ["echo", "Snips base is ready!"]
