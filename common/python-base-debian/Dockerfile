FROM debian:stretch

ENV ARCH amd64
ENV LANG C.UTF-8

RUN apt-get update && apt-get install -y supervisor git python3 python3-pip
RUN pip3 install --upgrade pip setuptools

CMD ["echo", "python-base is ready!"]