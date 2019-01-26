FROM common_python-base-debian

ENV INSTALLATION_PATH /usr/src/app

# Uncomment any of the following lines to disable the installation.
ENV INSTALL_TELLSTICK no
ENV INSTALL_OPENALPR no
ENV INSTALL_FFMPEG no
ENV INSTALL_LIBCEC no
ENV INSTALL_SSOCR no
ENV INSTALL_IPERF3 no

# master, dev or version nr.
ENV HOME_ASSISTANT_VERSION 0.86.2

VOLUME /config

RUN mkdir -p /temp
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
RUN apt-get install -y autoconf
# temp workaround for some dependency issues
# RUN pip3 install aiocoap dtlssocket urllib3 requests idna
RUN virtualization/Docker/setup_docker_prereqs

# Install hass component dependencies
RUN pip3 install --no-cache-dir -r requirements_all.txt
RUN pip3 install --no-cache-dir psycopg2 uvloop cchardet cython tensorflow

# Cleanup    
RUN apt-get clean

CMD [ "python3", "-m", "homeassistant", "--config", "/config" ]