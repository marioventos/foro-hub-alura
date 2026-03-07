# Foro Hub API

API REST desarrollada con Spring Boot como solución al desafío **Foro Hub** de **Alura**.

Esta aplicación permite gestionar tópicos de un foro mediante operaciones CRUD y autenticación de usuarios utilizando JWT.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT
- MySQL
- Flyway
- Maven

## Funcionalidades 

La API permite:

- Autenticación de usuarios
- Registrar nuevos tópicos
- Listar tópicos
- Actualizar tópicos
- Eliminar tópicos

---

## Endpoints principales

### Autenticación

POST /login

### Tópicos

POST /topicos  
GET /topicos  
PUT /topicos/{id}  
DELETE /topicos/{id}  

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio

```bash
git clone https://github.com/marioventos/foro-hub-alura.git
```

2. Configurar la base de datos en `application.properties`

3. Ejecutar el proyecto

```bash
mvn spring-boot:run
```

## Autor

Proyecto desarrollado por **Mario Vento** como parte del programa de formación de **Alura**.
