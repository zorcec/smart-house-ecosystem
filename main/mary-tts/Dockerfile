FROM openjdk:8u181-jdk-slim-stretch

ENV MARYTTS_INSTALLATION_PATH /usr/marrytts
ENV MARYTTS_VERSION 5.2
ENV VOICE_NAME voice-cmu-rms

RUN apt-get update

# Install MaryTTS
RUN apt-get install -y git maven
RUN git clone https://github.com/marytts/marytts.git $MARYTTS_INSTALLATION_PATH
WORKDIR $MARYTTS_INSTALLATION_PATH
RUN git checkout v$MARYTTS_VERSION
RUN mvn package

RUN mkdir $MARYTTS_INSTALLATION_PATH/installer
WORKDIR $MARYTTS_INSTALLATION_PATH/installer
RUN git clone https://github.com/marytts/marytts-installer.git $MARYTTS_INSTALLATION_PATH/installer
RUN git checkout v$MARYTTS_VERSION
RUN ./marytts install

# Cleanup
RUN apt-get clean autoremove

EXPOSE 59125
CMD ["marytts", "server", "--info"]
