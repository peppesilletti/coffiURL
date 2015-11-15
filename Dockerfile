FROM java:8-jdk

VOLUME /tmp

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/jre

MAINTAINER Giuseppe Silletti <sillettig@gmail.com>


RUN apt-get update


# Install Redis
RUN apt-get install -y redis-server

#Install Maven
RUN apt-get --no-install-recommends install maven -y

#Add application files to container
ADD coffiURL/ coffiURL/

WORKDIR /coffiURL


#Build project with maven
RUN mvn -DskipTests package

#Copying files in the coffiURL directory
RUN cp /coffiURL/target/coffiURL-1.0.jar /coffiURL && \
    cp -avr /coffiURL/target/lib     /coffiURL
    
#Removing src files
RUN rm -R /coffiURL/src /coffiURL/target /coffiURL/pom.xml

WORKDIR /

# Init script for starting redis and application
RUN touch init.sh && \
	echo '#!/bin/bash' >> init.sh && \
	echo 'redis-server &' >> init.sh && \
	echo 'cd /coffiURL && java -jar coffiURL-1.0.jar init' >> init.sh && \
	chmod 777 init.sh	
	

#Exposing ports for Spring-Boot and Redis Server

EXPOSE 8080 6379 


CMD "/init.sh"
