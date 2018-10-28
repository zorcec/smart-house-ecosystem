FROM openjdk:8u181-jdk-slim-stretch

ARG HOSTID=1001

ENV GRADLE_VERSION 4.3.1
ENV GRADLE_HOME /usr/lib/gradle/gradle-${GRADLE_VERSION}
ENV PATH ${PATH}:${GRADLE_HOME}/bin

RUN apt-get update

# GRADLE
RUN mkdir /usr/lib/gradle /app

WORKDIR /usr/lib/gradle
RUN set -x \
  && apt-get install -y wget \
  && wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
  && unzip gradle-${GRADLE_VERSION}-bin.zip \
  && rm gradle-${GRADLE_VERSION}-bin.zip

# mount directories
RUN mkdir /gradle
VOLUME /gradle

WORKDIR /gradle

CMD ["echo", "java-base is ready!"]