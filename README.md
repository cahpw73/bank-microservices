# 🏦 Bank Microservices

Proyecto técnico desarrollado como parte de una prueba práctica para **Devsu**, que implementa una arquitectura basada en **microservicios con Spring Boot 3.5.6 y Java 21**.

Cada microservicio está diseñado para cumplir responsabilidades específicas dentro de un sistema bancario simplificado, aplicando **buenas prácticas**, **arquitectura limpia** y principios de **modularidad**.

---

## 📘 Descripción del proyecto

El proyecto se estructura como un **Maven Multimodule**, compuesto por:

- **`shared-kernel`**  
  Módulo común con DTOs, excepciones y clases compartidas entre los microservicios.

- **`ms-customers`**  
  Microservicio encargado de la gestión de **personas y clientes**, incluyendo operaciones CRUD sobre la entidad `Cliente`.

- **`ms-accounts`**  
  Microservicio encargado de la gestión de **cuentas y movimientos**, permitiendo registrar depósitos, retiros y generar reportes.

Cada módulo se compila y ejecuta de forma independiente, pero comparten configuración y dependencias gestionadas desde el **proyecto padre (`bank-microservices`)**.

---

## 🧱 Estructura de carpetas

```
bank-microservices/
├─ pom.xml                     # POM principal (packaging=pom)
│
├─ shared-kernel/              # Módulo compartido (jar)
│  ├─ pom.xml
│  └─ src/main/java/com/devsu/xp/bank/shared/
│
├─ ms-customers/               # Microservicio de clientes/personas
│  ├─ pom.xml
│  ├─ src/main/java/com/devsu/xp/bank/customers/
│  └─ src/main/resources/application.yml
│
├─ ms-accounts/                # Microservicio de cuentas/movimientos
│  ├─ pom.xml
│  ├─ src/main/java/com/devsu/xp/bank/accounts/
│  └─ src/main/resources/application.yml
│
└─ infra/                      # (opcional) Docker, Postman, scripts SQL
   ├─ docker/
   └─ postman/
```

---

## ⚙️ Requisitos

- **Java 21**
- **Maven 3.9+**
- **VS Code o IntelliJ IDEA**
- **Docker (opcional)** para futuras integraciones
- **Postman** (para pruebas REST)

---

## ▶️ Cómo ejecutar los microservicios

Asegúrate de estar en la carpeta raíz del proyecto:

```bash
cd bank-microservices
```

### 🔹 Compilar todo el proyecto

```bash
mvnw.cmd clean install
```

### 🔹 Ejecutar `ms-customers`

```bash
mvnw.cmd -pl ms-customers spring-boot:run
```

El servicio se levantará por defecto en `http://localhost:8080`

Health check:

```
GET http://localhost:8080/actuator/health
```

### 🔹 Ejecutar `ms-accounts`

```bash
mvnw.cmd -pl ms-accounts spring-boot:run
```

Por defecto en `http://localhost:8081` _(puedes ajustar el puerto en `application.yml`)_

Health check:

```
GET http://localhost:8081/actuator/health
```

---

## 🧩 Tecnologías principales

| Componente        | Tecnología                  |
| ----------------- | --------------------------- |
| Lenguaje          | Java 21                     |
| Framework         | Spring Boot 3.5.6           |
| Build System      | Apache Maven (multimódulo)  |
| Documentación API | SpringDoc OpenAPI           |
| Validaciones      | Jakarta Bean Validation     |
| Logging           | Spring Boot Starter Logging |
| Librerías comunes | Lombok, MapStruct           |
| IDE recomendado   | VS Code o IntelliJ          |

---

## 👨‍💻 Autor

**Desarrollado por:** Christian Rene Alba Herrera
**Tecnologías:** Java • Spring Boot • Docker • Maven  
**Empresa:** Devsu (Prueba Técnica)
