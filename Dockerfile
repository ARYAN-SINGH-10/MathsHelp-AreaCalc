# Stage 1: Compile Java Source
FROM openjdk:21-slim AS build

WORKDIR /app

# Copy the source code and necessary libraries for compilation
COPY src/main/java /app/src
# Add servlet-api for compilation
ADD https://repo1.maven.org/maven2/javax/servlet/javax.servlet-api/3.1.0/javax.servlet-api-3.1.0.jar /app/lib/

# Compile the Java files
RUN mkdir -p /app/build/classes
RUN javac -d /app/build/classes -cp "/app/lib/*" /app/src/*.java

# Stage 2: Deploy to Tomcat
FROM tomcat:9.0-jdk21-temurin-jammy

# Remove default Tomcat apps to keep it clean
RUN rm -rf /usr/local/tomcat/webapps/ROOT \
    && rm -rf /usr/local/tomcat/webapps/docs \
    && rm -rf /usr/local/tomcat/webapps/examples

# Copy the webapp static files (HTML, CSS)
COPY src/main/webapp /usr/local/tomcat/webapps/ROOT

# Copy the compiled classes from the build stage
COPY --from=build /app/build/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

EXPOSE 8080

CMD ["catalina.sh", "run"]
