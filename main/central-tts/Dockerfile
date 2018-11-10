FROM common_java-base:latest

ENV INSTALLATION_PATH /usr/src/app

RUN apt-get update

# Install what is needed
RUN apt-get install -y supervisor git

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

CMD ["bash", "/files/start.sh"]