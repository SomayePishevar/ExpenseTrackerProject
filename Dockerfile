FROM openjdk:8-jdk-alpine
COPY target/expenseTracker-0.0.1-SNAPSHOT.jar expense-tracker-1.0.0.jar
ENTRYPOINT ["java","-jar","/expense-tracker-1.0.0.jar"]
