FROM node:20-alpine AS build

WORKDIR /musicando-host

COPY . .

RUN npm install --legacy-peer-deps

RUN npm run build

# Serve Application using Nginx Server
FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /musicando-host/build/ /usr/share/nginx/html
EXPOSE 9090
