FROM debian:stretch-slim

RUN apt-get update

# Install git
RUN apt-get install -y git python-numpy python-pyaudio python-soundfile

# Prepare scripts
RUN git clone https://github.com/snipsco/snips-record-personal-hotword.git /app
WORKDIR /app

# Copy configuration
RUN mkdir -p /hotword /files
COPY /files/* /files
VOLUME /hotword

# Cleanup    
RUN apt-get clean

#CMD ["bash", "/files/start.sh"]
CMD tail -f /dev/null
