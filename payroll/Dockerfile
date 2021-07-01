FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/folha-de-pagamento-0.0.1-SNAPSHOT.jar hr-payroll.jar
ENTRYPOINT ["java","-jar","/hr-payroll.jar"]