FROM maven AS build
COPY . /midserver
RUN cd /midserver && mvn clean package

FROM openjdk:8
COPY --from=build /midserver/target/midserver-1.0-SNAPSHOT.jar /usr/local/lib/midserver.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/midserver.jar"]