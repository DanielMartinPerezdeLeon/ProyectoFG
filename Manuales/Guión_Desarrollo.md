## Pre

- Lo primero es tener un linux ubuntu 22.04 [imagen aquí](https://ubuntu.com/download/desktop)

- Después descargar e instalar Eclipse [aquí](https://www.eclipse.org/downloads/)

- Lo siguiente que tendriamos que hacer es descargar un jdk17 [aquí](https://download.oracle.com/java/17/latest/jdk-17_linux-aarch64_bin.tar.gz)

- También instalar maven: `sudo apt-get install maven`.

- Tener instalado mysql

- (Preferiblemente tener instalado sql workbench) 

- Crear BD mysql simple
  - Create schema
  - name: BDbooking
  - character set: UTF8
  - Lo demás default.
  
- (Opcional pero ayuda) Instalarse en Eclipse los plugins de spring initializr, maven, sonarlint, tomca
## En el IDE (Eclipse)

- Creamos un proyecto SpringBook starter (Maven) en eclipse. (No he podido guarlas las propiedades)

- He hecho la clase usuario y he empezado con su DAOP y mapping. He cambiado el GetMapping a un PostMapping.

- He arreglado errores que habia y he deployeado la aplicación, consiguiendo que salga en navegador

- Ahora que hemos creado el primer html (JSP) para la web, vamos a meter primefaces, tambien usamos primefaces/designer para diseñar la pagina. En un principio usaremos el diseñador con Bootstrap dark, ya que es lo que he usado antes.

- He ajustado los colores, diseño que quiero, ahora solo copiamos los html y los pegamos a la página

- Es inviable usar primefaces a estas alturas, directamente usaremos boostrap

- He tenido que mover TODOS los paquetes dentro de donde estaba el de SpringApplication, si no, no los detecta al movernos con get/post

- He creado el formulario de inicio de sesion dentro del index (de momento no funciona)

- El iniciar sesion al menos ya comprueba si ese usuario existe(solo por identificacion, vamos a hacer el registro)

- He iniciado la pagina de registro y he mergeado la branch 1 al main

- Importante mencionar que ya no se usa JSP, los html dinámicos los he convertido a Thymeleaf, que es mucho más potente y utilizado actualmente que JSP

- Recordar meter SPRING-DOCKER EN EL POM (https://openwebinars.net/academia/aprende/desplegar-app-spring-boot-docker/)

- https://mdbootstrap.com/ (Muy bien para ver como va a quedar)
