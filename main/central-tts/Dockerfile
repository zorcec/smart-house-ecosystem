FROM common_java-base:latest AS amd64

ENV INSTALLATION_PATH /usr/src/app
ENV LIBTTSPICO_VERSION=1.0+git20130326-8

# Install PICOTTS
RUN	mkdir /temp && \
	apk add --no-cache wget dpkg && \
	wget http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-data_${LIBTTSPICO_VERSION}_all.deb -O /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
	wget http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico0_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb -O /temp/libttspico0_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb && \
	wget http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-utils_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb -O /temp/libttspico-utils_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb && \
	dpkg -i /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
	dpkg -i /temp/libttspico0_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb && \
	dpkg -i /temp/libttspico-utils_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb

# Clone src
RUN git clone https://github.com/zorcec/central-tts.git $INSTALLATION_PATH

# Compile
WORKDIR $INSTALLATION_PATH
RUN gradle compile
RUN gradle --stop

# Copy configuration
RUN mkdir -p /config $INSTALLATION_PATH/build/libs/resources
VOLUME /config

COPY /files/* /files/
COPY /config/supervisor/* /etc/supervisor/conf.d/

WORKDIR $INSTALLATION_PATH/build/libs/

CMD ["bash", "/files/start.sh"]