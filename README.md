# **Primer parcial backend - Individual**

---
## 🧬 Introducción del reto

###   Alcance:
Este proyecto consiste en el desarrollo de una API REST utilizando Java Spring Boot, diseñada para detectar si un ser humano es mutante a partir de una secuencia de ADN. La arquitectura implementa un enfoque en capas, incluyendo controladores, servicios y repositorios, garantizando una separación de responsabilidades y una fácil escalabilidad. La API expone el endpoint `/mutant/`, que recibe una secuencia de ADN en formato JSON y devuelve un código HTTP que indica si el ADN es mutante o humano.

Además, el proyecto incluye una base de datos H2 para almacenar los ADN verificados, asegurando que solo se registre una entrada por secuencia. Se implementa un endpoint adicional `/stats` que proporciona estadísticas sobre las verificaciones de ADN, permitiendo un análisis del ratio entre ADN mutante y humano. Se ha diseñado teniendo en cuenta la capacidad de manejar fluctuaciones de tráfico intensas, y se han realizado pruebas automatizadas con una cobertura de código superior al 80%.

## 🛠️ Tecnologías utilizadas

- **Java 17**
- **Gradle** *(Gestor de dependencias)*
- **Spring Boot** *(Framework)*
- **H2** *(Base de datos embebida)*
- **Postman** *(Cliente pruebas de API)*
- **JUnit** *(Pruebas unitarias)*
- **JMeter** *(Pruebas de estrés y performance)*
- **Render** *(Despliegue en la nube de la API)*
- **Docker Desktop** *(Deploy contenedor)*
- **Swagger** *(Documentación interactiva de APIs)*

## 🖥️ Pruebas en Local

1. Descargar o clonar el repositorio: [Examen-Mutante-ML](https://github.com/AgusAstuDev/Examen-Mutante-ML/archive/refs/heads/main.zip)


2. Abrir el proyecto:

<pre><code>Ejecutar MutantApp</code></pre>

3. Si se han descargado correctamente las dependencias con gradle, mostrará:

<pre><code>------------------------------------------- 
API de control de ADNs mutantes funcionando
-------------------------------------------
</code></pre>

4. Hacer pruebas con la API y Swagger:
<pre><code>http://localhost:8080/swagger-ui/index.html
</code></pre>

5. Si se quiere acceder a la DB H2:
<pre><code>http://localhost:8080/h2-console
</code></pre>

## 🖥️ Diagrama de secuencias

Imagen secuencia

## 📊 Cobertura de Código (>80%)

Las pruebas unitarias realizadas con jUnit se han hecho superando el 80% de cobertura. IntelliJ ofrece la posibilidad de poder ver la misma ejecutanto test con cobertura.

## 🧪 Pruebas utilizando Postman como cliente

* ### 🔬 Endpoint GET: <code>/stats</code>

* ### 🔬 Endpoint POST: <code>/mutant</code>

  * #### ADN Humano
<pre><code>{
    "dna": [
        "TTGCGCAGCT",
        "CAGTAAACCT",
        "TTAGAGAGGT",
        "ATTCGGGAAA",
        "CCCAAACTAG",
        "GGGTACTGAA",
        "TTAGAGAGGT",
        "ATTCGGGAAA",
        "TTGCGCAGCT",
        "CAGTAAACCT"
    ]
}
</code></pre>

  * #### ADN Mutante
<pre><code>{
    "dna": [
        "ATGCGA", 
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA", 
        "TCACTG"
    ]
}
</code></pre>

## 🗄️ Base de datos H2 para guardar los ADN's
Se utilizó H2 como base de datos para almacenar todas las secuencias de adn, sin que se repitan.

## 🗄️ Prueba de documentación con Swagger
#### Funcionamiento POST o GET en Swagger


## 🧪 Ejemplos de funcionamiento (Render + Postman)

#### Funcionamiento POST en Render


## 🔨 Pruebas de rendimiento JMeter
Se realizaron pruebas de rendimiento de manera local Tanto POST como GET, se probó desde 100
usuarios por segundo hasta 5000, respondiendo correctamente la aplicación hasta 2099 sin presentar error en la petición.
Cabe destacar que elegí un Ramp-up period de 1 segundo, lo que significa que en tan solo un segundo se recibieron las 2099 peticiones.
En las imagenes que se muestran a continuación se utilizo JMeter (5.6.3)

![jmeterGetT.png](imagenes%2FjmeterGetT.png)
![jmeterGetT2.png](imagenes%2FjmeterGetT2.png)
![jmeterPostT.png](imagenes%2FjmeterPostT.png)

Para más información sobre el reto, la implementación del resto de tecnologias y temas como la eficiencia
y la complejidad cuadrática del algoritmo visitar mi pagina:

<a href="https://thebestdeveloper95.github.io/Documentacion-HTML-Examen-Mercadolibre/" target="_blank">Como afronté el Examen de Mercadolibre</a>

## 🧬 Live test (Render)

- **Swagger** 
- **H2**  
- **Mutant check url (POST)**  
- **Stats url (GET)**  

## 🏆 Desafíos cumplidos:
### Nivel 1: ✓
### Nivel 2: ✓
### Nivel 3: ✓
