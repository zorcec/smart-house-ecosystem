FROM common_java-base-debian:latest

RUN apt-get update

# Copy configuration
RUN mkdir -p /config
VOLUME /config

COPY /files/* /files/
COPY /config/supervisor/* /etc/supervisor/conf.d/

CMD ["bash", "/files/start.sh"]