FROM common_java-base-debian:latest

ENV INSTALLATION_PATH /usr/src/app

RUN apt-get update

# Clone src
RUN git clone https://github.com/zorcec/pi-gpio.git $INSTALLATION_PATH

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