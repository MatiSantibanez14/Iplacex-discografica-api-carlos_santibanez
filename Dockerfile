#Build gradle
FROM gradle:8.7-jdk17-alpine AS builder

WORKDIR /home/gradle/project

# Copiamos el contenido del proyecto al contenedor
COPY . .

# Construimos el JAR de Spring Boot
RUN gradle clean bootJar --no-daemon


#Runtime JDK
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el JAR generado desde el stage anterior
COPY --from=builder /home/gradle/project/build/libs/discografia-1.jar app.jar

# Exponemos el puerto donde corre Spring Boot
EXPOSE 8080

# Comando que ejecuta la app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
