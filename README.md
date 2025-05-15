# Microservicio Ecomarket para manejar producto

Este microservicio se encarga de manipular los productos y sus categorias, tiene distintos metodos disponible para usar, usando el protocolo http.

## Tecnologias usadas
- [x] Java 21
- [x] Oracle SQL
- [x] Maven 3.9.9
- [x] Docker 27.5.1

## Como correr el proyecto
Dentro de la carpeta catalogo vas ejecutar el siguiente comando

Linux:
```
mvn spring-boot:run
```
Windows:
```
.\mvnw spring-boot:run
```
con estos comandos podras poner en marcha el proyecto para probarlo

## Metodos disponibles
Puedes ver todos los metodos de producto disponibles ingresando a: `http://localhost:8080/api/v1/producto`  
Puedes ver todos los metodos de categoria disponibles ingresando a: `http://localhost:8080/api/v1/categoria`  

Lista completa de metodos de productos:  
- GET    /api/v1/producto/buscarProductoNombre/{nombre} 
- GET    /api/v1/producto/buscarProductoId/{id} 
- GET    /api/v1/producto/buscarPorCategoria/{categoria}  
- GET    /api/v1/producto/mostrarProductos  
- GET    /api/v1/producto/buscarProductoPrecio/{minValue}/{maxValue} 
- GET    /api/v1/producto/masCaro  
- GET    /api/v1/producto/masBarato  
- DELETE /api/v1/producto/eliminarPorId/{id}  
- POST   /api/v1/producto/guardar

Lista completa de metodos de categoria:
	
- POST /api/v1/categoria/guardar
- GET api/v1/categoria/mostrarCategoria

la URL deberia verse de esta forma: `http://localhost:8080/api/v1/producto/mostrarProductos`

## Como compilar el proyecto en docker
Para compilar proyecto primero debes asegurar de tener las versiones correctas de java y javac
para revisar esto puedes ejecutar los siguientes comandos en una terminal  

Version de java
```Terminal
java --version
```

Version de javac
```Terminal
javac --version
``` 
esto debería indicar que tienes la versión 21 de cada uno, si no es así tendrás que descargalos.

para compilar el proyecto ejecutras el siguiente comando dentro del catálogo

Linux:
```
mvn clean package
```

Windows:
```
.\mvnw clean package
```
