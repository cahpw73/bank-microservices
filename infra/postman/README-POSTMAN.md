# Postman Collection & Environment - Devsu Banking Microservices

Este archivo README describe cómo importar y utilizar la colección de Postman para probar los microservicios **ms-customers** y **ms-accounts**.

---

## 📂 Contenido recomendado del folder `infra/postman/`

| Archivo                          | Descripción                                                     |
| -------------------------------- | --------------------------------------------------------------- |
| `DEVSU.postman_collection.json`  | Colección con todos los endpoints del reto Devsu                |
| `Devsu.postman_environment.json` | Variables necesarias para ejecutar la colección (IDs dinámicos) |
| `README.md` _(este archivo)_     | Guía de uso                                                     |

> **Nota:** Solo necesitas importar los 2 primeros archivos en Postman.

---

## 🚀 Cómo importar en Postman

1. Abrir **Postman**
2. Clic en **Import**
3. Seleccionar los archivos:
   - `DEVSU.postman_collection.json`
   - `Devsu.postman_environment.json`
4. Ir a la esquina superior derecha y seleccionar el environment **"Devsu"** para activarlo.

---

## 🔧 Variables del Environment

Estas variables permiten reutilizar IDs, se necesita copiar/pegar manualmente.

| Variable      | Ejemplo de Valor                       | Descripción                                        |
| ------------- | -------------------------------------- | -------------------------------------------------- |
| `customer_id` | `c4a18db5-b20f-4aba-afd1-a915628cc18d` | Se debe actualizar manualmente al crear un cliente |
| `account_id`  | `677e377b-58ca-41e2-99f9-28ca800ca7a6` | Se debe actualizar manualmente al crear una cuenta |

---

## ✅ Endpoints incluidos en la colección

### **🔹 ms-customers (http://localhost:8080)**

| Método | Endpoint          | Descripción           |
| ------ | ----------------- | --------------------- |
| POST   | `/customers`      | Crear cliente         |
| GET    | `/customers`      | Listar clientes       |
| GET    | `/customers/{id}` | Buscar cliente por ID |
| PUT    | `/customers/{id}` | Actualizar cliente    |
| DELETE | `/customers/{id}` | Eliminar cliente      |

---

### **🔸 ms-accounts (http://localhost:8081)**

| Método | Endpoint                         | Descripción               |
| ------ | -------------------------------- | ------------------------- |
| POST   | `/accounts`                      | Crear cuenta              |
| GET    | `/accounts/{id}`                 | Obtener cuenta por ID     |
| POST   | `/movements`                     | Registrar depósito/retiro |
| GET    | `/accounts/{id}/movements`       | Listar movimientos        |
| GET    | `/reports?customerId=&from=&to=` | Generar reporte           |

---

## 🧪 Ejecución sugerida (orden de pruebas)

1. Crear un cliente
2. Crear una cuenta usando el `customer_id`
3. Registrar movimientos (positivo = depósito, negativo = retiro)
4. Ver movimientos por cuenta
5. Generar reporte filtrado por fechas

---

## 📎 Notas Importantes

- La colección asume ejecución **local** de los MS.
- Si se ejecuta mediante Docker, verificar hosts/puertos.
- No requiere autenticación para el reto.
