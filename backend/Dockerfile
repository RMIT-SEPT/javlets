FROM openjdk:8-jre-alpine
ENV APP_ROOT /app
RUN mkdir ${APP_ROOT}
RUN apk --no-cache add ca-certificates
RUN wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-2.29-r0.apk
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-bin-2.29-r0.apk
RUN apk add glibc-2.29-r0.apk glibc-bin-2.29-r0.apk
WORKDIR ${APP_ROOT}
COPY target/*.jar ${APP_ROOT}/javlets*.jar
ENTRYPOINT ["java", "-jar", "javlets*.jar"]