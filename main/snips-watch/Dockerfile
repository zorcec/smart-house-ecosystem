FROM common_snips-base:latest

ARG HOSTUID=1000
ARG HOSTGID=1000

RUN apt-get update && \
    apt-get install -y snips-watch

COPY /config/snips.toml /etc/snips.toml

CMD ["snips-watch", "-v"]