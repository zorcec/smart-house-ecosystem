FROM python:3.6

ENV INSTALLATION_PATH /usr/src/app

# Uncomment any of the following lines to disable the installation.
ENV INSTALL_TELLSTICK no
ENV INSTALL_OPENALPR no
ENV INSTALL_FFMPEG no
ENV INSTALL_LIBCEC no
ENV INSTALL_SSOCR no
ENV INSTALL_IPERF3 no

# master, dev or release version
ENV HOME_ASSISTANT_VERSION dev

VOLUME /config

RUN mkdir -p /temp
RUN apt-get update && apt-get install -y git
RUN git clone https://github.com/home-assistant/home-assistant.git $INSTALLATION_PATH

WORKDIR $INSTALLATION_PATH

RUN if [ "$HOME_ASSISTANT_VERSION" = "master" ]; then \
        git checkout master; \
    elif [ "$HOME_ASSISTANT_VERSION" = "dev" ]; then \
        git checkout dev; \
    else \
        git checkout tags/$HOME_ASSISTANT_VERSION; \
    fi
    

# Build & setup
RUN virtualization/Docker/setup_docker_prereqs

# Install hass component dependencies
RUN pip3 install --no-cache-dir -r requirements_all.txt
RUN pip3 install --no-cache-dir mysqlclient psycopg2 uvloop cchardet cython

# Cleanup    
RUN apt-get clean

CMD [ "python", "-m", "homeassistant", "--config", "/config" ]