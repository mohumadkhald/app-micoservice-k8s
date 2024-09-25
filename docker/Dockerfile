FROM eclipse-temurin:17 as builder
WORKDIR application
ARG ARTIFACT_NAME
COPY ${ARTIFACT_NAME}.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract


FROM eclipse-temurin:17
WORKDIR application

ARG EXPOSED_PORT
EXPOSE ${EXPOSED_PORT}

ENV SPRING_PROFILES_ACTIVE docker

COPY --from=builder application/dependencies/ ./

RUN true
COPY --from=builder application/spring-boot-loader/ ./
RUN true
COPY --from=builder application/snapshot-dependencies/ ./
RUN true
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]









#docker push ${REPOSITORY_PREFIX}/config-server
#docker push ${REPOSITORY_PREFIX}/service-registry
#docker push ${REPOSITORY_PREFIX}/gateway
#docker push ${REPOSITORY_PREFIX}/job-service
#docker push ${REPOSITORY_PREFIX}/review-service
#docker push ${REPOSITORY_PREFIX}/company-service
#docker push ${REPOSITORY_PREFIX}/admin-server

