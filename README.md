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
Con estos comandos podras poner en marcha el proyecto para probarlo

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
## JSON para probar la api
Aca copia estos ejemplos en postman usando los metodos correspondiente de guardar para producto y categoria  

Primero ingresamos categorias:
```
[
    {"categoria_id": 1,
    "descripcion": "Alimentos"
    },
    {"categoria_id": 2,
    "descripcion": "Electronico"
    },
    {"categoria_id": 3,
    "descripcion": "Aseo"
    }
]
```
Ahora los productos:
```
[
  { 
    "nombre": "Quinoa Orgánica", 
    "stock": 10, 
    "precio": 2000.0, 
    "categoria": { 
      "categoriaId": 1 
    }
  },
  { 
    "nombre": "Hamburguesa de Lentejas", 
    "stock": 15, 
    "precio": 1500.0, 
    "categoria": { 
      "categoriaId": 1 
    }
  },
  { 
    "nombre": "Aguacate Orgánico", 
    "stock": 5, 
    "precio": 500.0, 
    "categoria": { 
      "categoriaId": 1 
    }
  },
  { 
    "nombre": "Té Verde Ecológico", 
    "stock": 50, 
    "precio": 100.0, 
    "categoria": { 
      "categoriaId": 1 
    }
  },
  { 
    "nombre": "Tableta Solar Recargable", 
    "stock": 50, 
    "precio": 267000.0, 
    "categoria": { 
      "categoriaId": 2 
    }
  },
  { 
    "nombre": "Auriculares Eco-Friendly", 
    "stock": 50, 
    "precio": 167000.0, 
    "categoria": { 
      "categoriaId": 2 
    }
  },
  { 
    "nombre": "Smartphone Reciclado", 
    "stock": 50, 
    "precio": 567000.0, 
    "categoria": { 
      "categoriaId": 2 
    }
  },
  { 
    "nombre": "Escoba de Cerdas Naturales", 
    "stock": 50, 
    "precio": 3000.0, 
    "categoria": { 
      "categoriaId": 3 
    }
  },
  { 
    "nombre": "Cepillo de Dientes de Bambú", 
    "stock": 50, 
    "precio": 1500.0, 
    "categoria": { 
      "categoriaId": 3 
    }
  },
  { 
    "nombre": "Jabón Natural de Aceite de Oliva", 
    "stock": 50, 
    "precio": 500.0, 
    "categoria": { 
      "categoriaId": 3 
    }
  }
]
```
Listo ahora puedes probar los distintos metodos disponibles con estos productos de ejemplo
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
Esto debería indicar que tienes la versión 21 de cada uno, si no es así tendrás que descargalos.

Para compilar el proyecto ejecutas el siguiente comando dentro del catálogo

Linux:
```
mvn clean package
```

Windows:
```
.\mvnw clean package
```
Una vez compilado el proyecto podemos crear el docker, para esto tendremos iniciar docker,
en windows se puede iniciar en docker desktop

en linux:
```
systemctl start docker
```

Ahora dentro del proyecto tenemos un archivo llamado `Dockerfile` el cual contiene las 
especificaciones que le indican a docker como crear la imagen  
para comenzar con la creacion del docker ejecutamos el siguiente comando:
```
docker build -t catalogo-app .
```
Este comando creara la imagen del docker con nuestra aplicacion  
Ahora para correr la aplicacion y ver si esta funciona podemos correr esta imagen de docker
```
docker run -p 8080:8080 catalogo-app
```
La app deberia comenzara correr en el puerto 8080 en localhost

Ahora que tenemos ya la imagen de docker funcionando podemos guardarla de la siguiente forma
ejecutaremos este comando:
```
docker save -o catalogo-app.tar catalogo-app
```
Y con esto deberiamos terminar con un archivo catalogo-app.tar dentro de nuestro proyecto  

Para usarlo tendremos que cargarlo en docker esto se puede hacer con:
```
docker load -i catalogo-app.tar
```
Luego podemos comprobar si la cargo con:
```
docker images
```
Y por ultimo correr esta imagen
```
docker run -d --name catalogo-app -p 8080:8080 catalogo-app
```
