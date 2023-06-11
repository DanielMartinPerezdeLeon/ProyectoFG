# BookingWebApp
Para el proyecto de final de grado me he decidido por hacer BookingWebApp, un servicio Web hecho con SpringBoot pensado para utilizarse como Aplicación de escritorio (explicado más abajo) en la que se simula el acceso a la web de una oficia para poder reservar
horas en los puestos de trabajo de la misma. Para obtener los datos, se ha creado una API REST, la cual se puede usar desde cualquier herramienta, por lo que si se quisiera, se puede hacer uso de la aplicación sin el sitio web;  y si se hiciese
otro programa que necesitase acceder a los datos de la misma Base de Datos, no ahce falta configurar nada, se puede usar la misma API.

Para instalar el servicio para hostearlo o desarrollar, puedes ver las otras guías en el proyecto.

En este PDF solo se habla en detalles del programa, las tecnologías usadas, por qué se ha elegido esta proyecto, dificultades y explicación del código.

# ¿Por qué este proyecto?
Las primeras 2 semanas de prácticas en guadaltel solo estuvimos viendo cursos de OpenWebinars, muchos de estos cursos se centraban en Maven, Jakarta, SpringBoot, Hibernate y API's; así que con esos cursos pensé que lo más adecuado era hacer un proyecto relacionado.

Después, en uno de los proyectos en los que trabajé se usaba una API REST como tecnología principal, así que me ha venido muy bien para manejarme en las prácticas.

Si hubiese podido empezar el proyecto más tarde hubiera hecho otro proyecto con una tecnología mas novedosa e interesante con lo aprendido en mi puesto en Guadaltel (Implementación de Inteligencia Artificial
para deteccion y cálculo de propiedades de seismos usando datos en tiempo real).


# Explicación de la aplicación
Con BookingWebApp se puede acceder a un servicio web alojada en un servidor tomcat.
En este servicio podremos, en un principio, registrarnos en la aplicación o iniciar sesion:
- Si nos registramos, no podremos acceder dentro hasta que un manager/admin nos permita el acceso
- Si iniciamos sesión, podremos acceder a la página principal, además podremos acceder a diferentes páginas según nuestro rol de permisos:
  - Si somos usuarios, solo podremos acceder a reservar horarios de equipos.
  - Si somos manager, tambíen podremos acceder a otras dos páginas, una para gestionar usuarios y otra para gestionar los equipos.
  - Si somos administradores, además, podremos acceder a la página de administración, en la que podremos borrar los horarios, borrar un puesto o borrar un usuario.

Los datos que la aplicación de la base de datos se obtienen mediante una API REST, de uso universal, lo que nos permite la implantación rápida en otros servicios. Podremos ver como usar la API REST accediendo al Swagger del proyecto,
host/swagger-ui/index.html

La aplicación guarda una vez al día, a las 23:59 (de la hora donde esté alojada) los puestos y sus horarios y después los reinicia. Se puede acceder en cualquier momento a esos datos usando la API REST.



# Detalles de la aplicación

![DiagramaBookingBIen drawio](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/5c74c312-1ec9-4272-b638-88348412c674)

Está construida con SpringBoot, que se encarga de crear el pom del proyecto y te crea automáticamente Beans de Spring sin tener que configurarlos, básicamente se usa para poder crear proyectos Spring teniendo que configurar lo más mínimo posible.

La aplicación ha sido dividida en los siguientes paquetes:
  - Configuration: Configuraciones de la aplicación, al final solo queda la de OpenApi
  - Controller: Controladores para la API de mapeo y la REST API.
  - Entity: Entidades de la base de datos y de los datos de los JSON.
  - Repository: Repositorios para la descarga y subida automática de datos de la Base de Datos.
  - Scheduler: Acción diaria para guardar los datos de cada día.
  - DailySaves: Donde se guardan los datos de cada día.
  - Static: Imágenes de la aplicación y los JS
  - Templates: Las plantillas de thymeleaf para generar los html dinámicamente. 
  - application.properties: archivo de configuración de spring.

La gracia es que la mayoría de datos que se piden a la base de datos es mediante una API REST que se puede usar desde cualquier lado, si se accede al Swagger de la aplicación 

El metodo en el que funciona es usando SpringBoot, que ejecuta automáticamente el servidor tomcat con la aplicación alojada, la aplicación se conectará con una base de datos mySQL donde recibir y guardar información y
usará dos APIS, una REST para leer/es los datos recibidos como JSON y poder modificarlos facil y rápidamente; y otra API para las redirecciones y métodos como iniciar sesion/registrarse.

Para la creación dinámica de los html se usa thymeleaf, un motor de creación de html que usa java, los datos que usa thymeleaf también vienen de la API REST. Para acceder a la API dentro de las páginas creadas se usa JS.

Con Spring MVC se guarda los datos minimos necesarios entre página y página (como el nombre de usuario) y los datos de sesión html, que dura 15 minutos.

Se usa repositorios JPA para transformar directamente los datos de la BD en clases java, además lo hace persistente.

Se usa OpenAPi para generar y configurar Swagger-UI, que explica detalladamente como usar la API REST.

Los usos más peligrosos de la API REST (como borrar un usuario) están protegidas usando un protocolo CORS configurado para que solo puedan ser usadas con métodos POST/Delete desde la misma aplicación y se debe usar un cuerpo JSON en específico.

Se ha implementado un acción programada a las 11:59 que guarda los datos del día y los reinicia, borrando todas las horas reservadas.

El front-end se ha hecho con Bootstrap.

La aplicación ofrece un log completo que describe detalladamente que ocurre en la aplicación.




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
  Eclipse esta muy bien para controlar con mucho detalle proyectos grandes y facilita mucho algunas cosas; pero funciona FATAL, se cuelga cada rato, utiliza muchísimos recursos y no es nada fácil de aprender a usar. Creo que eclipse ha hecho que el desarrollo del proyecto sea mucho más lento. Si tuviese que empezar desde el principio, usaría IntellIJ, que es mucho mejor.
  
### Spring
  Spring esta muy bien y encuentras muchísima información, todo sale suele funcionar siempre; pero como una cosa no te funcione, pasas horas (literalmente) intentando cambiarlo, al final la solución suele ser dar un rodeo e implementar algo de forma manual. Por ejemplo, he estado muchísimo intentando implementar Spring-Security, me rendí y tuve que hacer la seguridad de la aplicación de forma manual.
  
### Amazon Web Service
  Conseguí usar AWS e incluso hostear la aplicación dentro de un servidor, pero el uso gratuíto es horrible, te dan CPU de sobra, pero0 apenas te dan unos kilobites de red, lo que hace imposible usar la aplicación en ella. Además, conseguí alojar la base de datos en un servicio de bases de datos de AWS, pero no te avisan en ningún lado que ese servicio cuesta dinero, casi me cobran 40€ sin avisar.
  
### Docker
  Cuando tuve que abandonar AWS, decidí usar un docker para automatizar el hosteo de la aplicación, pero no funcionaba usando una red de docker, porque Spring odía las redes de docker, he intentado de todo, pero ha sido imposible, para poder hacer el docker he tenido que liarla, la historia más detallada está en la sección docker en el manual de desarrollo.
  


# Explicación API REST en la creación de uno de los html

