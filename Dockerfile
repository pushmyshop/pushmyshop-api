#FROM openjdk:8-jdk-alpine
FROM fedora:26

RUN dnf update -y
RUN dnf -y install dnf-plugins-core
RUN dnf config-manager --add-repo https://download.docker.com/linux/fedora/docker-ce.repo
RUN dnf config-manager --set-enabled docker-ce-edge
RUN dnf install -y java docker-ce
RUN curl --silent --location https://rpm.nodesource.com/setup_8.x | bash -
RUN dnf install -y nodejs
RUN dnf install -y git
RUN npm install -g --allow-root yo
RUN npm install -g https://github.com/pushmyshop/generator-pushmyshop.git

VOLUME /tmp
ADD target/pushmyshop-api-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
