FROM openjdk:11-jre-slim

EXPOSE 8080

ADD build/libs/bookmark-0.0.1-SNAPSHOT.jar bookmark.jar

ENTRYPOINT ["java", "-jar", "bookmark.jar"]