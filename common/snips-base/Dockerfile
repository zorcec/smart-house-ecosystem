FROM snipsdocker/platform

ENV ARCH amd64
ENV LIBTTSPICO_VERSION=1.0+git20130326-3

RUN apt-get update

RUN mkdir -p /common-config
VOLUME /common-config

# Install PICOTTS
RUN	mkdir /temp && \
    apt-get install -y libpopt0 wget && \
    wget --quiet http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-data_${LIBTTSPICO_VERSION}_all.deb -O /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
    wget --quiet http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico0_${LIBTTSPICO_VERSION}_${ARCH}.deb -O /temp/libttspico0_${LIBTTSPICO_VERSION}_${ARCH}.deb && \
    wget --quiet http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-utils_${LIBTTSPICO_VERSION}_${ARCH}.deb -O /temp/libttspico-utils_${LIBTTSPICO_VERSION}_${ARCH}.deb && \
    dpkg -i /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
    dpkg -i /temp/libttspico0_${LIBTTSPICO_VERSION}_${ARCH}.deb && \
    dpkg -i /temp/libttspico-utils_${LIBTTSPICO_VERSION}_${ARCH}.deb

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
