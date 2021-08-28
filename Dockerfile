FROM openjdk:11
WORKDIR /tmp
COPY ./out/production/csvSort ./
COPY ./out/production/csvSort/main/resources ./src/main/resources
ENTRYPOINT ["java", "main.java.Main", "NAME"]