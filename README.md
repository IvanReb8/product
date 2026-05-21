# README de prueba técnica

Este readme contiene las instrucciones para los dos proyectos Java Spring Boot independientes:

- `inventory` — servicio de inventario
- `product` — servicio de productos que consume `inventory` mediante Feign

## Requisitos

- Java 17+ instalado
- Maven o Maven Wrapper (`mvnw` / `mvnw.cmd`)
- Git

> En Windows use `.mvnw` y en Linux/macOS use `./mvnw`.

## Descarga desde GitHub

Clonar los dos proyectos desde sus repositorios GitHub con las siguientes urls.

```bash
inventory: git clone https://github.com/IvanReb8/inventory.git 
product: git clone https://github.com/IvanReb8/product.git 
```

## Estructura del proyecto

- `inventory/`
  - `src/main/java/...` — código del servicio de inventario
  - `src/main/resources/application.properties` — configuración de Spring Boot
  - `server.port=8080`

- `product/`
  - `src/main/java/...` — código del servicio de productos
  - `src/main/resources/application.properties` — configuración de Spring Boot
  - `server.port=8082`

## Puertos

- `inventory`: `http://localhost:8080`
- `product`: `http://localhost:8082`

## Ejecutar los proyectos

### 1. Iniciar `inventory`

Abra un terminal y ejecute:

```powershell
cd inventory
.\mvnw spring-boot:run -DskipTests
```

### 2. Iniciar `product`

Abra un segundo terminal y ejecute:

```powershell
cd product
.\mvnw spring-boot:run -DskipTests
```

> El servicio `product` consulta a `inventory` para obtener stock. Por eso se recomienda arrancar `inventory` primero.

## Comprobación rápida

### Inventario

```bash
curl -i http://localhost:8080/api/v1/inventory/EXT-003
```

Esperado:

```json
{"productId":"EXT-003","stock":5}
```

### Productos

```bash
curl -i "http://localhost:8082/api/v1/products/search?query=Mac"
```

Esperado:

- `inventoryStatus` debe ser `AVAILABLE`
- `stock` debe tener valor `5` para `EXT-003`

## Notas adicionales

- `product` usa Feign para llamar a `inventory`.
- Si `inventory` no está disponible, `product` retornará el producto sin stock (`stock: null`) porque la excepción de Feign se captura internamente.
- Si desea depurar, puede iniciar cualquiera de los servicios con argumentos JVM de debug.

## Problemas comunes

- Si `8080` o `8082` ya están ocupados, cambie `server.port` en el `application.properties` correspondiente.
- Si el proyecto no arranca, asegúrese de usar Java 17 o superior.
