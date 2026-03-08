# Foro Hub API

API REST desarrollada con Spring Boot como solución al desafío **Foro Hub** de **Alura**.

Esta aplicación permite gestionar tópicos de un foro mediante operaciones CRUD y autenticación de usuarios utilizando JWT(JSON Web Token).

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (Auth0)
- MySQL
- Flyway
- Maven

## Funcionalidades 

La API permite:

- Autenticación de usuarios
- Registrar nuevos tópicos
- Listar tópicos
- Actualizar tópicos
- Cerrar tópicos
- Eliminar tópicos

---

# Autenticación

### Endpoint

POST /login

### Body de la solicitud

```json
{
  "login": "usuario",
  "clave": "password"
}
```

### Respuesta

```json
{
"token": "jwt_token_generado"
}
```

### Uso del token

Una vez obtenido el token, debe enviarse en el header de cada solicitud protegida.
```bash
Authorization: Bearer TU_TOKEN
```
Ejemplo: 
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

#Endpoints

### Tópicos

| Método | Endpoint | Descripción |
|------|------|------|
| POST | /topicos | Crear nuevo tópico |
| GET | /topicos | Listar tópicos |
| GET | /topicos/{id} | Obtener tópico por id |
| PUT | /topicos/{id} | Actualizar tópico |
| PATCH | /topicos/{id}/cerrar | Cerrar un tópico |
| DELETE | /topicos/{id} | Eliminar tópico |

⚠️ Todos los endpoints requieren autenticación mediante JWT.

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio

```bash
git clone https://github.com/marioventos/foro-hub-alura.git
```

2. Configurar la base de datos
  Editar el archivo:
  application.properties

  Configurar:
  - URL de la base de datos
  - usuario
  - contraseña

3. Ejecutar el proyecto

```bash
mvn spring-boot:run
```
La API estará disponible en:
http://localhost:8080

## Autor

Proyecto desarrollado por **Mario Vento** como parte del programa de formación de **Alura**.
