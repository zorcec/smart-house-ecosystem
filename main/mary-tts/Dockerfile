FROM openjdk:8u181-jdk-slim-stretch

ENV MARYTTS_INSTALLATION_PATH /usr/marrytts
ENV MARYTTS_VERSION v5.2

RUN apt-get update

# Install MaryTTS
RUN apt-get install -y git maven
RUN git clone https://github.com/marytts/marytts.git $MARYTTS_INSTALLATION_PATH
WORKDIR $MARYTTS_INSTALLATION_PATH
RUN git checkout $MARYTTS_VERSION
RUN mvn package

# Cleanup    
RUN apt-get clean autoremove

EXPOSE 59125
CMD ["/usr/marrytts/target/marytts-5.2/bin/marytts-server", "-Xmx256m"]
