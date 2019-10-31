FROM openjdk:8 AS builder
COPY . /permissioningtoggles
WORKDIR /permissioningtoggles
COPY ./sentinel/linux_jre_and_runtime_libs/libhasp_linux_x86_64_demo.so ./sentinel/linux_jre_and_runtime_libs/libHASPJava_x86_64.so /usr/lib/permissioningtoggles/
RUN tar -xzf ./sentinel/runtime/aksusbd-7.81.1.tar.gz -C ./sentinel/runtime/ && cd ./sentinel/runtime/aksusbd-7.81.1
RUN ./gradlew assemble

FROM openjdk:8
COPY --from=builder  /permissioningtoggles/sentinel/runtime/aksusbd-7.81.1 .
RUN ./dinst
COPY --from=builder /usr/lib/permissioningtoggles/ /usr/lib/permissioningtoggles/
RUN ls -lahrt /usr/lib/permissioningtoggles/
COPY --from=builder /permissioningtoggles/build/libs/permissioningtoggles-0.0.1-SNAPSHOT.jar /permissioningtoggles-0.0.1-SNAPSHOT.jar
ENV LD_LIBRARY_PATH /usr/lib/permissioningtoggles
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/permissioningtoggles-0.0.1-SNAPSHOT.jar"]