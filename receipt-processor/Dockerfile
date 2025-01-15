FROM openjdk:23.0.1-jre-slim

WORKDIR /app

COPY target/receipt-processor-0.0.1-SNAPSHOT.jar /app/receiptprocessor.jar

CMD ["java", "-jar", "receiptprocessor.jar"]
