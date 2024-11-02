
# 📌 AV Viaje por Colombia

Un proyecto de simulación de vuelos que permite buscar rutas entre diferentes ciudades en Colombia, mostrando detalles del precio, compañía y vuelos intermedios. La aplicación está construida con **Spring Boot** y **JPA** para el backend y **React** para el frontend.

## 📋 Tabla de Contenidos
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Configuración Local](#-configuración-local)
- [Despliegue](#-despliegue)
- [Uso de la Aplicación](#-uso-de-la-aplicación)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Contribuciones](#-contribuciones)

---

## 🚀 Tecnologías Utilizadas
- **Backend**: Java, Spring Boot, Spring JPA, MySQL
- **Frontend**: React, CSS
- **Infraestructura**: Docker (opcional), Nginx (para despliegue)

---

## 📂 Estructura del Proyecto
```
/project-root
│
├── pruebatecnica          # Código fuente del backend en Spring Boot
│   ├── src
│   └── pom.xml            # Dependencias de Maven
│
├── pruebatecnicaFront     # Código fuente del frontend en React
│   ├── public
│   └── src
│   └── package.json       # Dependencias de Node
│
└── README.md              # Documentación
```

---

## 🛠 Configuración Local

### 1. Clonar el Proyecto
```bash
git clone https://github.com/Eliza266/PruebaTecnicaBiteli.git
cd PruebaTecnicaBiteli
```

### 2. Backend (Spring Boot)

#### Prerrequisitos
- **Java 11** o superior
- **MySQL** para la base de datos

#### Configuración de la Base de Datos
1. Crea una base de datos en MySQL:

1. Configura el archivo `application.properties` en el backend (`src/main/resources`):
   ```properties
   
   spring.application.name=pruebatecnica
   spring.profiles.active=pdn
   ```
2. Configura el archivo `application-pnd.properties` en el backend (`src/main/resources`):
   ```properties
   
   spring.application.name=pruebatecnica
   spring.datasource.url=jdbc:mysql://localhost:3306/dbpruebatecnica?createDatabaseIfNotExist=true
   spring.datasource.username=usuarion
   spring.datasource.password=contraseña
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   spring.jpa.show-sql=true
   spring.jpa.hibernate.ddl-auto=create-drop
   ```

3. Compila y ejecuta el backend:
   ```bash
   ./mvnw clean install
   java -jar target/nombre-del-proyecto.jar
   ```

### 3. Frontend (React)


#### Instalación

1. Instala las dependencias:
   ```bash
   npm install
   ```

2. Cambia el endpoint de la API en el frontend (asegúrate de que apunte al backend):
   ```javascript
   const API_URL = "http://localhost:8080/api";
   ```

4. Ejecuta el servidor de desarrollo:
   ```bash
   npm run dev
   ```

---

## 🖥 Uso de la Aplicación

1. Selecciona las ciudades de **origen** y **destino** desde el menú desplegable.
2. Haz clic en el botón **Buscar**.
3. Los resultados de los vuelos se mostrarán en la interfaz, detallando cada vuelo entre ciudades intermedias.

---

## 📌 Endpoints de la API

| Método | Endpoint                     | Descripción                          |
|--------|-------------------------------|--------------------------------------|
| GET    | `/api/cities`                | Lista todas las ciudades disponibles |
| GET    | `/api/find/{origen}/{destino}` | Busca rutas entre dos ciudades       |

---

## 🤝 Contribuciones

¡Contribuciones son bienvenidas! Si tienes sugerencias para mejorar este proyecto, por favor abre un **issue** o envía un **pull request**.

---
