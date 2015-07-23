FROM    java:openjdk-7u79-jre
ADD 	headless/build/libs/headless-1.0.jar headless.jar
ENTRYPOINT [ "java", "-jar", "headless.jar" ]
EXPOSE 9165 9166