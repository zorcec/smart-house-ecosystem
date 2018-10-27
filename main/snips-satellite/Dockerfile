FROM common_snips-base:latest

RUN apt-get update

# Install supervisor
RUN apt-get install -y supervisor

# Copy configuration
RUN mkdir -p /config
VOLUME /config

COPY /files/* /files/
COPY /config/snips.toml /etc/snips.toml
COPY /config/supervisor/* /etc/supervisor/conf.d/

# Cleanup    
RUN apt-get clean

CMD ["bash", "/files/start.sh"]