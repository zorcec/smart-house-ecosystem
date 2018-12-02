FROM debian:stretch-slim AS development

ENV ARCH $(dpkg --print-architecture)
ENV LIBTTSPICO_VERSION 1.0+git20130326-3

RUN apt-get update

# Install snips
RUN apt-get install -y dirmngr apt-transport-https ca-certificates && \
    bash -c  'echo "deb https://debian.snips.ai/stretch stable main" > /etc/apt/sources.list.d/snips.list' && \
    apt-key adv --keyserver pgp.mit.edu --recv-keys F727C778CCB0A455
RUN apt-get update && \
    apt-get install -y snips-platform-voice

RUN mkdir -p /common-config
VOLUME /common-config

# Install PICOTTS
RUN	mkdir /temp && \
	apt-get install -y libpopt0 wget && \
	wget http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-data_${LIBTTSPICO_VERSION}_all.deb -O /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
	wget http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico0_${LIBTTSPICO_VERSION}_${ARCH}.deb -O /temp/libttspico0_${LIBTTSPICO_VERSION}_${ARCH}.deb && \
	wget http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-utils_${LIBTTSPICO_VERSION}_${ARCH}.deb -O /temp/libttspico-utils_${LIBTTSPICO_VERSION}_${ARCH}.deb && \
	dpkg -i /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
	dpkg -i /temp/libttspico0_${LIBTTSPICO_VERSION}_${ARCH}.deb && \
	dpkg -i /temp/libttspico-utils_${LIBTTSPICO_VERSION}_${ARCH}.deb

# Install snips TTS
RUN apt-get install -y snips-tts

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

FROM armbuild/debian:jessie AS production

CMD ["echo", "Snips base is ready!"]
