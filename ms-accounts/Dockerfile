# === Build stage ===
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /workspace

# Copiamos POMs primero para cachear dependencias
COPY pom.xml .
COPY shared-kernel/pom.xml shared-kernel/pom.xml
COPY ms-customers/pom.xml ms-customers/pom.xml
COPY ms-accounts/pom.xml ms-accounts/pom.xml

# Pre-descarga de dependencias (sin compilar fuentes)
RUN mvn -q -e -B -DskipTests dependency:go-offline

# Copiamos el código fuente del proyecto
COPY . .

# Empaquetamos solo el módulo ms-accounts
RUN mvn -q -e -B -DskipTests -pl ms-accounts -am clean package

# === Runtime stage ===
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copiamos el jar compilado
COPY --from=build /workspace/ms-accounts/target/ms-accounts-*.jar /app/app.jar

# Puerto del servicio
EXPOSE 8081

ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
