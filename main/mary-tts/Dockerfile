FROM debian:stretch-slim

ENV MARYTTS_INSTALLATION_PATH /usr/marrytts
ENV MARYTTS_VERSION v5.2

RUN apt-get update

# Install MaryTTS
RUN apt-get install -f -y git openjdk-8-jdk maven
RUN git clone https://github.com/marytts/marytts.git $MARYTTS_INSTALLATION_PATH
WORKDIR $MARYTTS_INSTALLATION_PATH
RUN git checkout $MARYTTS_VERSION
RUN mvn package

# Cleanup    
RUN apt-get clean

EXPOSE 59125
CMD ["./gradlew", "run"]
