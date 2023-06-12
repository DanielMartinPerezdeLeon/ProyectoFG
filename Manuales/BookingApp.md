# BookingWebApp
Para el proyecto de final de grado me he decidido por hacer BookingWebApp, un servicio Web basado en SpringBoot pensado para utilizarse como aplicación de escritorio en la que se accede a una web de oficina para poder reservar horas en los puestos de trabajo disponibles. Para obtener los datos, he creado una API REST que puede ser utilizada desde cualquier herramienta, lo que significa que la aplicación puede ser utilizada sin necesidad del sitio web.;  y si se hiciese otro programa que necesitase acceder a los datos de la misma Base de Datos, no hace falta configurar nada, se puede usar la misma API.

Para instalar el servicio y alojarlo o desarrollarlo, puedes consultar las otras guías disponibles en el proyecto.

Este documento se centra en los detalles del programa, las tecnologías utilizadas, la razón por la que se eligió este proyecto, las dificultades encontradas y una explicación del código.

# ¿Por qué este proyecto?
Las primeras 2 semanas de prácticas en guadaltel solo estuvimos viendo cursos de OpenWebinars, muchos de estos cursos se centraban en Maven, Jakarta, SpringBoot, Hibernate y API's. Basandome en estos cursos pensé que lo más adecuado era hacer un proyecto relacionado con estas tecnologías.

Además, en uno de los proyectos en los que trabajé, se usaba una API REST como tecnología principal, así que me ha resultado muy útil para desenvolverme en mis prácticas.

Si hubiese podido comenzar el proyecto más tarde, habría optado por desarrollar una aplicación con una tecnología más novedosa e interesante de mi experieancia en Guadalter, como la implementación de Inteligencia Artificial para detección y cálculo de propiedades de sismos utilizando datos en tiempo real).


# Explicación de la aplicación
BookingWebApp se permite acceder a un servicio web alojado en un servidor tomcat.

En este servicio inicialmente podremos registrarnos en la aplicación o iniciar sesion:

- Si nos registramos, no podremos acceder hasta que un manager/admin nos permita el acceso.
- Si iniciamos sesión, podremos acceder a la página principal, además podremos acceder a diferentes funciones según nuestro rol de permisos:
  - Si somos usuarios, solo podremos acceder a la reserva de horarios en los equipos disponibles.
  - Si somos manager, tambíen podremos acceder a otras dos páginas, una para gestionar usuarios y otra para gestionar los equipos.
  - Si somos administradores, además, podremos acceder a la página de administración, en la que podremos borrar los horarios, borrar un puesto o borrar un usuario.

Los datos de la aplicación se obtienen de una base de datos mediante una API REST, que es de uso universal, lo que permite una fácil integración en otros servicios. Para obtener más información sobre cómo utilizar la API REST, se puede acceder al Swagger del proyecto a través de la siguiente URL: host/swagger-ui/index.html.

La aplicación guarda los datos de los puestos y sus horarios una vez al día a las 23:59 (hora del servidor) y luego los reinicia. Los datos pueden ser accedidos en cualquier momento mediante la API REST.



# Detalles de la aplicación

![DiagramaBookingBIen drawio](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/5c74c312-1ec9-4272-b638-88348412c674)

La aplicación ha sido construida utilizando Spring Boot, que se encarga de crear el archivo POM del proyecto y genera automáticamente los beans de Spring sin necesidad de configuración adicional. Esto permite crear proyectos de Spring con una configuración mínima.

La aplicación se ha dividido en los siguientes paquetes:
  - Configuration: Configuraciones de la aplicación, solo ha quedado la de OpenAPI.
  - Controller: Controladores para la API de mapeo y la REST API.
  - Entity: Entidades de la base de datos y de los cuerpos usados en los JSON.
  - Repository: Repositorios para la descarga y subida automática de datos de la Base de Datos.
  - Scheduler: Contiene la acción programada diaria para guardar los datos de cada día.
  - DailySaves: Donde se guardan los datos de cada día.
  - Static: Imágenes de la aplicación y los JS que se usa en los html.
  - Templates: Las plantillas de thymeleaf para generar los HTML dinámicamente. 
  - application.properties: Archivo de configuración de spring.

La mayoría de los datos obtenidos de la base de datos se obtienen a través de una API REST que puede ser utilizada desde cualquier lugar. Se puede acceder al Swagger de la aplicación para obtener información detallada sobre cómo utilizar la API. El uso de una API REST permite que se pueda acceder a los servicios de la web sin necesidad de acceder a la misma.

La aplicación funciona mediante Spring Boot, que automáticamente ejecuta el servidor Tomcat con la aplicación alojada. La aplicación se conecta a una base de datos MySQL para recibir y almacenar información, y utiliza dos APIs: una API REST para leer y modificar los datos en formato JSON, y otra API para las redirecciones y métodos como inicio de sesión y registro.

Para la generación dinámica de archivos HTML, se utiliza Thymeleaf, un motor de creación de HTML que utiliza Java. Los datos utilizados por Thymeleaf también provienen de la API REST. Para acceder a la API dentro de las páginas creadas, se utiliza JavaScript.

Spring MVC se utiliza para almacenar los datos mínimos necesarios entre páginas, como el nombre de usuario, y los datos de sesión HTML, que tienen una duración de 15 minutos.

Se utilizan los repositorios JPA para transformar directamente los datos de la base de datos en clases Java e incluye persistencia.

Se utiliza OpenAPI para generar y configurar Swagger-UI, que proporciona una descripción detallada de cómo utilizar la API REST.

Se han implementado medidas de seguridad para las acciones más peligrosas de la API REST, como la eliminación de un usuario. Estas acciones están protegidas mediante un protocolo CORS configurado para permitir solo solicitudes POST y DELETE desde la propia aplicación, y requieren un cuerpo JSON específico.

Se ha programado una tarea automática a las 11:59 que guarda los datos del día y los reinicia, eliminando todas las horas reservadas.

El proyecto se centra más en el back-end de la aplicación que en el front-end, por lo que se ha usado Bootstrap para crear el diseño de los HTML.


La aplicación ofrece un log completo de log4j2 que describe detalladamente todo lo que ocurre en la aplicación.




#Tecnologías usadas:
  - Java 17
  - Maven
  - Spring
  - SpringBoot
  - HTML5
  - JavaScript
  - Hibernate
  - MySQL
  - Docker
  - AWS(E2C)
  - Thymeleaf
  - Visual Studio Code
  - Bootstrap
  - OpenApi - Swagger - SwaggerUI
  - Java Persistence Api
  - CSS
  - JSON
  - Spring Session 
  - Spring MVC
  - Lombok - Slf4j
  - Bash
  - Tomcat
  - Sonarlint
  - Markdown
  - Git-GitKraken-GitHub
  - JavaScript


# Dificultades encontradas

### Eclipse
Eclipse es una herramienta muy completa para controlar proyectos grandes y facilita muchas tareas, pero tiene un rendimiento deficiente. Se bloquea con frecuencia, consume muchos recursos y no es fácil de aprender a utilizar. Si tuviera que empezar desde el principio, utilizaría IntelliJ, que considero que es una opción mejor.
  
### Spring
Spring es una tecnología muy útil y se encuentra mucha información disponible. En general, funciona bien y la mayoría de las veces las cosas funcionan como se esperan. Sin embargo, cuando algo no funciona, puede llevar muchas horas intentar solucionarlo, no habrá información en internet util para solucionarlo y no habrá documentación oficial sobre el caso; la mayoría de veces hay que trabajar implementando manualmente servicios. Uno de estos caso, la implementación de Spring Security falla y, tras mucho tiempo perdido intentando solucionarlo, finalmente tuve que abordar la seguridad de la aplicación de forma manual.
  
### Amazon Web Service
 Logré utilizar AWS y alojar la aplicación en un servidor E2C, pero la opción gratuita tiene limitaciones significativas. Aunque proporcionan suficiente capacidad de procesamiento (CPU), el límite de ancho de banda es muy bajo, apenas unos KiloBytes, lo que hace que sea prácticamente imposible utilizar la aplicación en esa plataforma. Además, conseguí alojar la base de datos en un servicio de bases de datos de AWS (RDS), no se me advirtió que se aplicaban cargos económicos, lo que resultó en un cargo que al final conseguí evitar de 40€. En ningún momento se avisa de si vas a usar un servicio de pago, he tenido que abandonar el uso de AWS para evitar más cobros inesperados.
  
### Docker
Cuando decidí abandonar AWS, opté por utilizar Docker para automatizar el hosting de la aplicación. Sin embargo, tuve dificultades al intentar usar una red de Docker, ya que Spring tiene problemas con las redes de Docker. Intenté muchas soluciones, pero fue imposible hacer que funcionara correctamente. Para poder utilizar Docker, tuve que encontrar una solución alternativa. Puedes encontrar más detalles sobre esto en la sección de Docker del manual de desarrollo.
  


# Explicación API REST en la creación de uno de los html

