FROM maven AS build
COPY . /wordcountserver
RUN cd /wordcountserver && mvn clean package

FROM openjdk:8
COPY --from=build /wordcountserver/target/wordcountserver-1.0-SNAPSHOT.jar /usr/local/lib/wordcountserver.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/wordcountserver.jar"]