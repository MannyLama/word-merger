FROM maven:3.8.4-openjdk-17

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

RUN echo 'Setting environment settings...'
ENV TZ=Europe/Brussels
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]