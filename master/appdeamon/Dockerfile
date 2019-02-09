FROM common_python-base-debian

COPY /files/* /src/
RUN pip3 install --no-cache-dir -r /src/requirements_all.txt

# Cleanup    
RUN apt-get clean

WORKDIR /src

VOLUME /src/apps

CMD ["appdaemon", "-c", "/src"]
