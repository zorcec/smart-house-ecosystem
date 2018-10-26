FROM debian:stretch-slim

RUN apt-get update

# Install nodejs
RUN apt-get install -y wget gnupg2
RUN wget -qO- https://deb.nodesource.com/setup_8.x | bash -
RUN apt-get install -y nodejs

# Install sam
RUN npm install -g snips-sam
COPY /files/* /files

# Cleanup    
RUN apt-get clean

CMD ["bash", "/files/start.sh"]
