FROM tiangolo/node-frontend:10 as build-stage
WORKDIR /app
COPY package*.json /app/
RUN npm install
COPY ./ /app/
RUN npm run build

FROM httpd:2.4
COPY --from=build-stage /app/build/ /usr/local/apache2/htdocs

RUN sed -i \
        -e 's/^#\(Include .*httpd-ssl.conf\)/\1/' \
        -e 's/^#\(LoadModule .*mod_ssl.so\)/\1/' \
        -e 's/^#\(LoadModule .*mod_socache_shmcb.so\)/\1/' \
        conf/httpd.conf

COPY --from=build-stage /app/build/dev.conf /etc/apache2/sites-enabled/dev.conf
COPY --from=build-stage /app/build/ssl.crt /etc/apache2/ssl/ssl.crt
COPY --from=build-stage /app/build/ssl.key /etc/apache2/ssl/ssl.key