FROM openjdk:11
WORKDIR /tmp
COPY ./out/production/csvSort/ ./
RUN 'pwd'
RUN 'ls'
ENTRYPOINT ["java", "main.java.Main"]