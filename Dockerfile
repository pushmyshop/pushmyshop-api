FROM fedora:26
RUN dnf clean packages
RUN dnf update -y
RUN dnf -y install dnf-plugins-core
RUN dnf config-manager --add-repo https://download.docker.com/linux/fedora/docker-ce.repo
RUN dnf makecache 
RUN dnf config-manager --set-enabled docker-ce-edge
RUN dnf install -y java docker-ce
RUN curl --silent --location https://rpm.nodesource.com/setup_8.x | bash -
RUN dnf install -y sudo git nodejs
RUN npm set progress=false && npm config set depth 0 && npm cache clean --force
RUN npm install -g yo --allow-root

RUN groupadd ec2 -g 497
RUN adduser --groups docker,wheel,ftp,ec2 pushmyshop

RUN npm install -g https://github.com/pushmyshop/generator-pushmyshop.git --allow-root
ENV LC_ALL en_US.UTF-8

USER pushmyshop

RUN mkdir -p /home/pushmyshop/.config/configstore/
RUN echo '{"clientId": 732455723580,"optOut": true}'>/home/pushmyshop/.config/configstore/insight-yo.json
VOLUME /tmp
ADD target/pushmyshop-api-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Dfile.encoding=UTF8 -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${PROFILE} -jar /app.jar
