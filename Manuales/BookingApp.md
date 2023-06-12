# BookingWebApp
Para el proyecto de final de grado me he decidido por hacer BookingWebApp, un servicio Web basado en SpringBoot pensado para utilizarse como aplicación de escritorio en la que se accede a una web de oficina para poder reservar horas en los puestos de trabajo disponibles. Para obtener los datos, he creado una API REST que puede ser utilizada desde cualquier herramienta, lo que significa que la aplicación puede ser utilizada sin necesidad del sitio web.;  y si se hiciese otro programa que necesitase acceder a los datos de la misma Base de Datos, no hace falta configurar nada, se puede usar la misma API.

Para instalar el servicio y alojarlo o desarrollarlo, puedes consultar las otras guías disponibles en el proyecto.

Este documento se centra en los detalles del programa, las tecnologías utilizadas, la razón por la que se eligió este proyecto, las dificultades encontradas y una explicación del código.

# ¿Por qué este proyecto?
Las primeras 2 semanas de prácticas en guadaltel solo estuvimos viendo cursos de OpenWebinars, muchos de estos cursos se centraban en Maven, Jakarta, SpringBoot, Hibernate y API's. Basandome en estos cursos pensé que lo más adecuado era hacer un proyecto relacionado con estas tecnologías.

Además, en uno de los proyectos en los que trabajé, se usaba una API REST como tecnología principal, así que me ha resultado muy útil para desenvolverme en mis prácticas.

Si hubiese podido comenzar el proyecto más tarde, habría optado por desarrollar una aplicación con una tecnología más novedosa e interesante de mi experieancia en Guadaltel.


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

Se ha implementado algunos sistemas de seguridad, como encriptación de contraseñas, guardado de contraseñas en la BD encriptadas, no guardar información comprometida en memoria, protocolo CORS, comprobación de SQL injection, sesion HTML, mapeo y url's internas ocultas, ...


## Algunas imágenes de la Aplicación

![Captura de pantalla 2023-06-12 203627](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/afeb3185-b4ad-4480-aa99-0ff9888b39ea)


![Captura de pantalla 2023-06-12 203709](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/97e6bf77-5fee-4d33-9cd6-02d3dd39c92e)


![Captura de pantalla 2023-06-12 203757](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/d8f1f981-3e54-41c5-9126-ec6001272660)


![Captura de pantalla 2023-06-12 203834](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/8e210eae-54d1-4ff5-84a5-1a37193a511a)


![Captura de pantalla 2023-06-12 203944](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/b37671fa-a6cd-4a16-8515-47efba0d8ccf)


# Tecnologías usadas:
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

### Convertir en Aplicación de Escritorio
Esta fue lo último que hice en el proyecto antes de la documentación. El problema es que ya no me quedaba tiempo, asi que no pude hacerlo completamente. Tenia planeado usar una herramienta como [Electron](https://www.electronjs.org/), pero la falta de tiempo y que estas herramientas no son tan inmediatas como creía hizo que usase directamente la extensión de Google Chrome. Esto me ha servido y funciona correctamente, el problema es que para esta aplicación se necesita una dirección de hosteo específica y que no cambie; como tuve que abandonar el servidor E2C de AWS, he tenido que usar mi propio portatil/cualquier-pc como servidor donde hostear la web y solo en una red local; como la IP de estas máquinas se genera de manera automática en cada red, no se puede tener la aplicación ya preparada para descargar, sin embargo, al poder crearse de manera inmediata, se puede hacer en  el mismo momento.
  


# Ejemplo uso y explicación API REST

Vamos a explicar al reservar una hora en un puesto como funcionaría por dentro la API REST para ese funcionamiento:

Ya sea usando la página Web o la API REST por separado, podemos hacernos una idea general usando el Swagger de Booking [/swagger-ui/index.html]: 
![Captura de pantalla 2023-06-12 204722](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/447141aa-3d8a-4b40-8ee4-ad63f44bbd44)

Como podemos ver, para reservar una hora en un puesto debemos hacer una petición POST con el cuerpo JSON que aparece (puesto,hora,nombre usuario) al enlace [host/puestos/reservar], la API nos devolverá un HTTP 200 si todo ha salido bién.

Veamos como funciona esa función de la API más en detalle:


Cuando escribimos ese enlace en cualquier lado, SpringBoot lo detecta y se va a esta operación dentro de controller/PuestoController.java:

![Captura de pantalla 2023-06-12 205241](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/22270682-8cd4-40e6-a874-65add4b30735)


Esta clase, necesita como parámetro una clase DatosReserva, esta clase es el cuerpo JSON como clase JAVA:

![Captura de pantalla 2023-06-12 210705](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/39fc9c8c-9817-4ab9-aa49-e8f74a392663)


Como podemos ver, cuando se activa la operación, lo primero que se hace es llamar al repositorio de los puestos y usa el método personalizado autogenerado getPuestoByid(int id) con el ID del cuerpo JSON para obtener el puesto de la Base de datos automáticamente como clase Java (Como usamos JPA y Spring, no tenemos que escribir el método, spring detecta cual es  la clase ID y autogenera el contenido):

![Captura de pantalla 2023-06-12 205650](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/b7764c42-5d5f-4de5-824d-84afb7469583)


Al obtener los datos comprueba si el puesto existe; si no existe, nos devuelve un HTTP.NOT_FOUND, si existe, continua.

Luego, coge el atributo del puesto donde se guardan las reservas, este atributo se guarda en java como una string, para guardarlo fácilmente, pero en realidad es un objeto JSON. Convertimos las reservas en una Array de JSON con horas y cogemos la que se ha pedido.

La hora es un objeto JSON con dos claves, un int con la hora y un campo detalle, donde se guarda el nombre del usuario que ha reservado el puesto. En esa hora se actualiza el campo detalle para guarda el nombre del usuario pasado en el cuerpo JSON:

![Captura de pantalla 2023-06-12 211612](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/15ac65ff-1269-4d7f-9443-5f0c727079e3)


Una vez actualizado el JSON, se crea un puesto igual al obtenido, pero con el JSON de horario nuevo; se borra el anterior de la base de datos usando el repositorio y se sube el nuevo, simulando así una actualización.

A continuación, el controlador muestra por consola (Si la hubiera) y por el log de Log4j2 creado que se ha hecho una reserva y el nombre del usuario, el puesto y la hora:

![Captura de pantalla 2023-06-12 212127](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/963c89d6-e5ea-4905-9120-f6d2526988ec)


Por último, se devuelve al navegador / herramienta que hayamos usado un HTTP OK (200), indicando que la operación se ha completado correctamente:


![Captura de pantalla 2023-06-12 212732](https://github.com/DanielMartinPerezdeLeon/ProyectoFG/assets/114756164/98bfe44b-bf21-4568-8ac2-3a3d800aed15)

