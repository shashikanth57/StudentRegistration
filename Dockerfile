FROM nginx:alpine

COPY webpage/registration.html /usr/share/nginx/html/index.html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
