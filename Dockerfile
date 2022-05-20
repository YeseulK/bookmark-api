FROM openjdk:11-jre-slim

EXPOSE 8080

ADD build/libs/bookmark-0.0.1-SNAPSHOT.jar bookmark.jar

ENTRYPOINT ["java", "-XX:MaxRAM=256M", "-Xmx128M", "-Xss256k", "-Xms128M", "-jar", "bookmark.jar"]