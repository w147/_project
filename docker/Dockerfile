FROM node:10.23-alpine
RUN echo "deb http://mirrors.163.com/debian/ stretch main non-free contrib \
\ndeb http://mirrors.163.com/debian/ stretch-updates main non-free contrib \
\ndeb http://mirrors.163.com/debian/ stretch-backports main non-free contrib \
\ndeb-src http://mirrors.163.com/debian/ stretch main non-free contrib \
\ndeb-src http://mirrors.163.com/debian/ stretch-updates main non-free contrib \
\ndeb-src http://mirrors.163.com/debian/ stretch-backports main non-free contrib \
\ndeb http://mirrors.163.com/debian-security/ stretch/updates main non-free contrib \
\ndeb-src http://mirrors.163.com/debian-security/ stretch/updates main non-free contrib" > /etc/apt/sources.list
ENV TZ Asia/Shanghai
ENV LANG=C.UTF-8
RUN npm install cnpm -g --registry=https://registry.npm.taobao.org
