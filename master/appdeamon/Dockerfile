FROM python:3.6

COPY /files/* /src/
RUN pip3 install --no-cache-dir -r /src/requirements_all.txt

# Cleanup    
RUN apt-get clean

WORKDIR /src

VOLUME /src/apps

CMD ["appdaemon", "-c", "/src"]
