FROM openjdk:8-jdk-slim
COPY "./target/videogames-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
ARG amazon_aws_accesskey_arg
ARG amazon_aws_secretkey_arg
ENV amazon_aws_accesskey $amazon_aws_accesskey_arg
ENV amazon_aws_secretkey $amazon_aws_secretkey_arg