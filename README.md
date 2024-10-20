# Proyecto de Consulta de Precios - Arquitectura Hexagonal

Este proyecto es una aplicación hecha con **Spring Boot** que sigue los principios de **arquitectura hexagonal**, también conocida como arquitectura de puertos y adaptadores. Su propósito es consultar precios de productos basados en parámetros específicos como la fecha de aplicación, el identificador del producto y el identificador de la cadena.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**
- **H2 Database** (Base de datos en memoria para pruebas)
- **JPA** (Java Persistence API)
- **Lombok** (Para reducción de código boilerplate)
- **JUnit** (Para pruebas unitarias)
- **Maven** (Gestor de dependencias)

## Arquitectura

El proyecto está diseñado bajo los principios de la arquitectura hexagonal. Esto permite que los componentes internos del sistema (como el dominio) 
estén desacoplados de los detalles de la infraestructura y de los adaptadores de entrada/salida.

### Capas Implementadas

- **Dominio**: Contiene la lógica central de la aplicación y las entidades principales.
- **Aplicación**: Servicios que manejan la lógica de negocio.
- **Infraestructura**: Implementa detalles como la persistencia (JPA) y configuración de base de datos.
- **Adaptadores**: Maneja las entradas y salidas, como los controladores que exponen las APIs REST.

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal para la construcción de la aplicación.
- **H2 Database**: Base de datos en memoria para facilitar el testing y la demostración del proyecto.
- **Lombok**: Para reducir el código repetitivo como getters, setters, constructores, etc.
- **Jackson**: Para la serialización/deserialización de objetos Java, con soporte para tipos de datos modernos como `LocalDateTime`.
- **JPA**: Para la gestión de la persistencia en la base de datos.
- **JUnit 5**: Para la creación y ejecución de tests.

## Configuración del Proyecto

### Requisitos

- **Java 17**: Este proyecto utiliza Java 17 como lenguaje base, asegurarse de tener esta versión o superior instalada.
- **Gradle**: Para gestionar las dependencias del proyecto.

### Plugins y Dependencias

Este proyecto está configurado con los siguientes plugins y dependencias en el archivo `build.gradle`:

groovy
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.3.4'
	id 'io.spring.dependency-management' version '1.1.6'
}

group = 'cl.pablovillalobos'
version = '0.0.1-SNAPSHOT'

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.13.4'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
	useJUnitPlatform()
}

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repo.git
  
