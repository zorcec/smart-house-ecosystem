FROM common_java-base:latest

ENV INSTALLATION_PATH /usr/src/app

RUN apt-get update

# Install what is needed
RUN apt-get install -y supervisor git

# Install PICOTTS
RUN	mkdir /temp && \
	apt-get install -y libpopt0 wget && \
	wget --quiet http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-data_${LIBTTSPICO_VERSION}_all.deb -O /temp/libttspico-data_${LIBTTSPICO_VERSION}_all.deb && \
	wget --quiet http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico0_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb -O /temp/libttspico0_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb && \
	wget --quiet http://ftp.fr.debian.org/debian/pool/non-free/s/svox/libttspico-utils_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb -O /temp/libttspico-utils_${LIBTTSPICO_VERSION}_$(dpkg --print-architecture).deb && \
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

# Cleanup
RUN apt-get clean autoremove

WORKDIR $INSTALLATION_PATH/build/libs/

CMD ["bash", "/files/start.sh"]