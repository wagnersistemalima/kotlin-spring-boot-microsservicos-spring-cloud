FROM openjdk:11
VOLUME /tmp
ADD ./build/libs/microcervissos-0.0.1-SNAPSHOT.jar hr-worker.jar
ENTRYPOINT ["java","-jar","/hr-worker.jar"]