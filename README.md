# API Granja: control de alimentación

## 1. Descripción general

API REST desarrollada con Spring Boot que permite gestionar el catálogo de animales, alimentos y nutrientes de una granja. Registra los animales con su especie, los alimentos disponibles y los nutrientes que aporta cada uno. Sienta la base para controlar la alimentación del ganado y, en una etapa posterior, analizar qué dieta da mejores resultados.

Proyecto de Implementación de Sistemas, integrado con el modelo de base de datos de la materia Diseño de bases de datos relacionales (UNAM, FCA, Licenciatura en Informática, grupo 2491, semestre 2025-2).

## 2. Integrantes

- Bobadilla Gómez Miguel Ángel
- Castellanos Marín Noemí
- Castro Villalpando José Emmanuel
- Galicia Romahn Santiago
- Lechuga Arellano Leonardo Gael
- Pacheco Gálvez Gabriela
- Salas Loaeza Itzel Guadalupe

## 3. Tabla de requerimientos (primeros 5 requerimientos a cubrir)

| ID | Nombre del Requerimiento | Descripción del Sistema | Herramienta / Concepto |
|----|--------------------------|-------------------------|------------------------|
| REQ-01 | **Gestión de Entidades (CRUD)** | El sistema debe exponer endpoints REST que permitan crear, leer, actualizar y eliminar registros de los catálogos de Animales, Alimentos y Nutrientes. | *Spring Web* |
| REQ-02 | **Conexión a Base de Datos** | El sistema debe conectarse a una base de datos relacional para persistir la información y mapear las tablas del diseño a clases de Java. | *Spring Data JPA* + PostgreSQL |
| REQ-03 | **Validación de Reglas de Negocio** | El sistema debe verificar que los datos ingresados (JSON) sean correctos antes de guardarlos, por ejemplo que la edad y el peso del animal sean mayores a cero. | *Spring Boot Validation* |
| REQ-04 | **Documentación de la API** | El sistema debe autogenerar una interfaz interactiva donde se consulten y prueben los endpoints. | *Springdoc OpenAPI / Swagger* |
| REQ-05 | **Registro de Eventos (Logs)** | El sistema debe registrar en consola las peticiones entrantes, las consultas SQL y los errores o excepciones. | *SLF4J / Logback* |

## 4. Funcionalidades que Spring Boot puede resolver

| Funcionalidad | Paquete |
|---|---|
| Conexión a BD y persistencia | Spring Data JPA + PostgreSQL |
| Validaciones | Spring Validation |
| Documentación de la API | SpringDoc OpenAPI (Swagger) |
| Logs | SLF4J / Logback |
| Manejo de errores | `@RestControllerAdvice` |
| Autenticación y seguridad (futuro) | Spring Security (JWT) |
| Migraciones de BD (futuro) | Flyway |
| Pruebas (futuro) | Spring Boot Test, JUnit |
| Monitoreo (futuro) | Spring Actuator |

## 5. Entidades y endpoints

| Entidad | Endpoints |
|---|---|
| Nutriente | `GET/POST /nutrientes`, `PUT/DELETE /nutrientes/{id}` |
| Alimento | `GET/POST /alimentos`, `PUT/DELETE /alimentos/{id}` |
| Alimento ↔ Nutriente | `GET /alimentos/{id}/nutrientes`, `POST/DELETE /alimentos/{id}/nutrientes/{nutrienteId}` |
| Animal | `GET/POST /animales`, `PUT/DELETE /animales/{id}` |
| Especie (catálogo de apoyo) | `GET/POST /especies`, `PUT/DELETE /especies/{id}` |

Documentación interactiva: `/swagger-ui.html`

## 6. Cómo ejecutar

1. Abrir el repositorio en un Codespace o Dev Container (Java 21 + PostgreSQL con host `db`).
2. Crear la base de datos:
   ```sql
   CREATE USER granja WITH PASSWORD 'granja';
   CREATE DATABASE granja OWNER granja;
   ```
3. Ejecutar `./mvnw spring-boot:run`.
4. Abrir `http://localhost:8080/swagger-ui.html`.

## 7. Estructura del repositorio

```
.devcontainer/      configuración del entorno (Java 21 + PostgreSQL)
src/main/java/...   model, repo, controladores, manejo de errores, logs
pom.xml
```

## 8. Trabajo futuro (Fase 2)

Dietas, tomas, cantidades por alimento e historial de dietas por animal, para comparar qué dieta da mejores resultados.