FROM common_java-base:latest

RUN apt-get update

# Install supervisor
RUN apt-get install -y supervisor

# Copy configuration
RUN mkdir -p /config /gradle-common
VOLUME /config /gradle-common

COPY /files/* /files/
COPY /config/supervisor/* /etc/supervisor/conf.d/

# Cleanup    
# RUN apt-get clean autoremove

CMD ["bash", "/files/start.sh"]