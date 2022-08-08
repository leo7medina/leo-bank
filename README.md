# leo-bank


API de practica.

### Requerimientos
- Java 8

### Instrucciones
####  Despliegue en docker
#### Construir imagen
docker build --no-cache -t leo7medina/bank .

#### Ejecutar contenedor
docker run --network host --name bank -p 8080:8080 leo7medina/bank:1.0.0