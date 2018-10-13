FROM debian:buster-slim

RUN apt-get update
RUN apt-get install -y dirmngr ca-certificates apt-transport-https && \
    bash -c  'echo "deb https://debian.snips.ai/stretch stable main" > /etc/apt/sources.list.d/snips.list' && \
    apt-key adv --keyserver pgp.mit.edu --recv-keys F727C778CCB0A455 && \
    apt-get update

RUN apt-get install -y snips-platform-voice

RUN mkdir -p /config && ln -s /etc/snips.toml
VOLUME /config

CMD tail -f /dev/null