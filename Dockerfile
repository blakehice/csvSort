FROM openjdk:11
COPY ./out/production/csvSort/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "main.Main"]