FROM common_snips-base as amd64

ARG HOSTUID=1000
ARG HOSTGID=1000

RUN apt-get update && \
    apt-get install -y snips-watch

COPY /config/snips.toml /etc/snips.toml

RUN apt-get clean autoremove

CMD ["snips-watch", "-v"]