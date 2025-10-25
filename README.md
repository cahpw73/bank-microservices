# 🏦 Devsu Banking Microservices

Proyecto técnico desarrollado para la prueba práctica de **Devsu**, utilizando arquitectura de **microservicios**, **Spring Boot 3.5.6**, **Java 21**, y enfoque de **Clean Architecture**.

Cada microservicio cumple una responsabilidad clara y se comunica entre sí aplicando buenas prácticas de diseño, modularidad y mantenibilidad.

---

## 📘 Descripción General

Este repositorio está organizado como un **Maven Multi‑Module**, lo que permite mantener una arquitectura limpia, modular y escalable:

| Módulo | Descripción |
|--------|----------------|
| **shared-kernel** | Contiene clases compartidas entre los microservicios (excepciones, DTOs, utilitarios, etc.) |
| **ms-customers** | Gestiona Personas y Clientes. CRUD completo. |
| **ms-accounts** | Gestiona Cuentas Bancarias y Movimientos. Genera reportes. |

Cada módulo puede compilarse y ejecutarse de manera independiente o todos juntos desde el proyecto raíz.

---

## 📂 Estructura del Proyecto

```
bank-microservices/
├─ pom.xml                       # POM principal (packaging=pom)
│
├─ shared-kernel/                # Módulo compartido (JAR)
│  ├─ pom.xml
│  └─ src/main/java/com/devsu/xp/bank/shared_kernel/
│
├─ ms-customers/                 # Microservicio de clientes/personas
│  ├─ pom.xml
│  ├─ src/main/java/com/devsu/xp/bank/ms_customers/
│  └─ src/main/resources/application.yml
│
├─ ms-accounts/                  # Microservicio de cuentas/movimientos
│  ├─ pom.xml
│  ├─ src/main/java/com/devsu/xp/bank/ms_accounts/
│  └─ src/main/resources/application.yml
│
└─ infra/                        # Scripts externos (Postman, Docker, SQL)
   └─ postman/                   # Colección y environment de Postman
```

---

## 🧰 Tecnologías Implementadas

| Componente | Tecnología |
|------------|-------------|
| Lenguaje | Java 21 |
| Framework | Spring Boot 3.5.6 |
| Arquitectura | Clean Architecture + DDD |
| Build System | Maven Multi‑Module |
| API Docs | SpringDoc OpenAPI 2 |
| Validaciones | Jakarta Bean Validation |
| Mapeos | MapStruct |
| Logging | SLF4J + Spring Boot Logging |
| DB | MySQL 8.4 |
| Contenedores | Docker & Compose |
| API Testing | Postman |

---

## ⚙️ Requisitos Previos

- Java 21 instalado
- Maven 3.9+
- Docker Desktop (opcional para despliegue)
- Postman

> **Tip:** Puedes ejecutar localmente o con Docker Compose.

---

## ▶️ Ejecución Local

Desde la raíz del proyecto:

### 1️⃣ Compilar todo

```bash
./mvnw clean install
```

### 2️⃣ Ejecutar Microservicios

#### **ms-customers (Puerto 8080)**

```bash
./mvnw -pl ms-customers spring-boot:run
```

Health check:

```
GET http://localhost:8080/actuator/health
```

#### **ms-accounts (Puerto 8081)**

```bash
./mvnw -pl ms-accounts spring-boot:run
```

Health check:

```
GET http://localhost:8081/actuator/health
```

---

## 🐳 Ejecución con Docker

Desde la raíz del proyecto:

```bash
docker compose build
docker compose up -d
```

Servicios disponibles:

| Servicio | URL |
|----------|------|
| MySQL | localhost:3307 |
| ms-customers | http://localhost:8080 |
| ms-accounts | http://localhost:8081 |

---

## 🧪 Pruebas con Postman

En `infra/postman` encontrarás:

- Colección de endpoints
- Environment con variables dinámicas

Importar → Ejecutar en este orden recomendado:

1. Crear Cliente
2. Crear Cuenta
3. Registrar Movimientos (+ / -)
4. Consultar Movimientos
5. Generar Reporte

---

## 📜 Funcionalidades Cubiertas

✅ CRUD de Clientes  
✅ CRUD de Cuentas  
✅ Movimientos (depósitos y retiros con validación de saldo)  
✅ Cálculo de saldo disponible  
✅ Reporte por fechas y cliente  
✅ Comunicación ms-accounts → ms-customers vía REST  

---

## 👨‍💻 Autor

Desarrollado por **Christian Alba Herrera**  
⭐ Prueba Técnica Devsu – Microservicios Bancarios

---

¿Mejoras futuras? Observabilidad (Logs estructurados, Tracing), Mensajería (RabbitMQ), Seguridad (JWT) y CI/CD.

