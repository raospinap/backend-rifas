FROM eclipse-temurin:17-jdk-alpine

# Crear directorio de trabajo
WORKDIR /app

# Copiar pom.xml y descargar dependencias primero
COPY pom.xml ./
RUN ./mvnw dependency:go-offline

# Copiar el resto del proyecto y construir
COPY . ./
RUN ./mvnw clean package -DskipTests

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "target/rifa-interactiva-0.0.1-SNAPSHOT.jar"]
