FROM debian:stretch-slim

ENV ARHITECTURE amd64

RUN apt-get update

# Install snips
RUN apt-get install -y dirmngr apt-transport-https ca-certificates && \
    bash -c  'echo "deb https://debian.snips.ai/stretch stable main" > /etc/apt/sources.list.d/snips.list' && \
    apt-key adv --keyserver pgp.mit.edu --recv-keys F727C778CCB0A455 && \
    apt-get update && \
    apt-get install -y snips-platform-voice

# Cleanup    
RUN apt-get clean

RUN mkdir -p /config /files
VOLUME /config
VOLUME /files

COPY /config/snips.toml /etc/snips.toml

CMD ["sh", "/files/start.sh"]
