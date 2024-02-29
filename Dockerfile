FROM openjdk:8-jdk-alpine

ARG JAR_FILE=lucky-coin-pool-xxl-job-scheduler-0.0.1-SNAPSHOT.jar

WORKDIR /app

COPY lucky-coin-xxl-job-scheduler/target/${JAR_FILE} /app/xxl-job.jar

ENTRYPOINT ["java"]

CMD ["-server", "-Duser.timezone=GMT", "-Xms516M", "-Xmx1024M", "-XX:MetaspaceSize=512M", "-jar", "/app/xxl-job.jar"]