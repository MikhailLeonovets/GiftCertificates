FROM maven:3.8.4-openjdk-17-slim AS maven_build
COPY pom.xml /tmp/
COPY controller /tmp/controller/
COPY repository /tmp/repository/
COPY service /tmp/service/
COPY utility /tmp/utility/
WORKDIR /tmp/
RUN mvn package

FROM tomcat:9-jdk17-openjdk-slim AS tomcat_build
COPY --from=maven_build /tmp/controller/target/controller-1.0-SNAPSHOT.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]