# msv-carro
Microservicio encargado de gestionar el carro de compras.

## Descripción del Proyecto

Este proyecto es una tienda en línea que implementa un carrito de compras con persistencia temporal utilizando Redis como base de datos de clave-valor. Los artículos añadidos al carrito se mantienen almacenados de forma temporal sin necesidad de iniciar sesión. Una vez que el usuario se autentica, los artículos del carrito se pueden transferir a una base de datos persistente.

## Tecnologías Utilizadas

- **Spring Boot**: Framework para el desarrollo del backend.
- **Redis**: Base de datos de clave-valor en memoria para almacenar temporalmente los datos del carrito.
- **Java 17**: Lenguaje de programación utilizado.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

## ¿Qué es Redis?
![Logo de Redis](https://www.albertcoronado.com/wp-content/uploads/2016/06/redis_logo.png)

Redis es una base de datos de estructura de datos en memoria que se puede utilizar como caché, cola de mensajes, y almacenamiento temporal. Es rápido y eficiente para manejar datos temporales que no necesitan persistir en un disco duro, como los carritos de compras de usuarios no autenticados.

En este proyecto, Redis se utiliza para almacenar los ítems del carrito de compras de los usuarios que aún no han iniciado sesión, permitiendo así una persistencia temporal sin necesidad de una cuenta.

## Instalación de Redis

### En Linux/MacOS

```bash
sudo apt-get install redis-server  # Para Ubuntu/Debian
brew install redis  # Para MacOS
```
## Comandos para iniciar Redis

### Inicia el servidor Redis
```
redis-server
```
Para verificar que Redis está funcionando correctamente, ejecuta
```
redis-cli ping
```
La respuesta debería ser PONG, indicando que el servidor Redis está activo.

## Cómo arrancar el proyecto
### 1. Clonar el repositorio:
```
git clone <git clone https://github.com/software2uis/msv-carro.git>
```
### 2. Configurar Redis:
```
spring.redis.host=localhost
spring.redis.port=6379
```
### 3. Compilar y arrancar la aplicación:
Usa Maven para compilar el proyecto:
```
mvn clean install
```
Luego arranca el servicio:
```
mvn spring-boot:run

```








