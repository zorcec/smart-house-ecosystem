FROM openjdk:8u181-jdk-alpine3.8 as amd64

ARG HOSTID=1001

ENV GRADLE_VERSION 4.3.1
ENV GRADLE_HOME /usr/lib/gradle/gradle-${GRADLE_VERSION}
ENV PATH ${PATH}:${GRADLE_HOME}/bin

# GRADLE
RUN mkdir /usr/lib/gradle /app

WORKDIR /usr/lib/gradle
RUN set -x \
  && apk add --no-cache wget \
  && wget --quiet https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
  && unzip gradle-${GRADLE_VERSION}-bin.zip \
  && rm gradle-${GRADLE_VERSION}-bin.zip \
  && apk del wget

# OTHER BASH TOOLS
RUN set -x && apk add --no-cache bash git supervisor

# mount directories
RUN mkdir /gradle
VOLUME /gradle

WORKDIR /gradle

CMD ["echo", "java-base is ready!"]

FROM arm32v6/openjdk:8u181-jdk-alpine3.8 AS armhf