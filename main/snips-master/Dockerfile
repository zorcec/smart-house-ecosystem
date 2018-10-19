FROM common_snips-base:latest

ARG HOSTUID=1000
ARG HOSTGID=1000

RUN apt-get update

# Install and configure supervisor
RUN apt-get install -y supervisor
COPY config/supervisor/* /etc/supervisor/conf.d/
RUN chown -R $HOSTUID:$HOSTGID /etc/supervisor/conf.d/
RUN adduser --disabled-password --gecos $HOSTUID service_user

COPY /files/* /files
COPY /config/snips.toml /etc/snips.toml
    
# Cleanup    
RUN apt-get clean

CMD ["bash", "/files/start.sh"]