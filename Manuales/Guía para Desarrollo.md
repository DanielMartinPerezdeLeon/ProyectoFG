Aquí se explica como y donde abrir el proyecto para ver/desarrollar su código, siguiendo el mismo entorno que se ha usado para el desarrollo original
En principio, deberúa funcionar en cualquier IDE para Java, pero lo explicaré para eclipse en linux, ya que ahí es donde lo he desarrollado.

# EQUIPO
Originalmente se ha usado una máquina virtual en VMware con una distribución de la ultima versión de mintmate.

# IDE's
Se ha usado [eclipse](https://www.eclipse.org/downloads/packages/installer) con los siguientes plugins (muchos no son necesarios pero ayudan muchisimo):
  - Eclipse Enterprise Java and Web Developer Tool (Permite usar Java/Jajarta EE en Eclipse)
  - Eclipse m2e (Soporte de maven en Eclipse)
  - Eclipse Web Developer Tools (Herramientas de desarrollo de html, js, css, JSON,...)
  - Eclipse XML tools
  - Hibernate Tools (Automatiza la configuración de hibernate)  
  - SonarLint (Para calidad de código/ detección de bugs)
  - Spring Tools (Para automatizar usar SpringBoot)
  - Wild Web Developer (Amplía el Web Developer Tools)

También se ha usado [Visual Studio Code](https://code.visualstudio.com/) con el plugin de JS y Docker

# JAVA 17
Para el desarrollo ha sido necesario java 17, yo he usado openjdk-17
```console
sudo apt-get install openjdk-17-jre
```
# Maven
Para el desarrollo ha sido necesario tener maven instalado
```console
sudo apt-get install maven
```
Y recuerda que todo cambio que hagas vas a tener que usar -mvn clean install package

# Docker 
Para acceder facilmente al proyecto sin tener que usar IDE se ha usado [docker](https://docs.docker.com/engine/install/ubuntu/)

He subido a Docker Hub una imagen que permite abrir el servicio sin más, si te pregunta como funciona la imagen:
- He abierto un contenedor de docker con un ubuntu 22.04 ligero (1 gb)
- He instalado en ese contenedor mysql, le he cambiado la contraseña al root y he creado el servidor con las tablas que puedes encontrar en el proyecto
- He copiado dentro el jar del proyecto (También he metido el proyecto entero, lo que hace la imagen mas pesada, pero me venía bien)
- He hecho un commit de la ese contenedor
- He creado un dockerfile con este código:
 ```dockerfile
    FROM damarpele/booking:prueba

    EXPOSE 8080
    EXPOSE 3306


    ENTRYPOINT ["./run_booking.sh"]
```
- Ese archivo ./run_booking.sh lo he creado yo y es así:
 ```console 
    #!/bin/bash

    service mysql restart

    while /etc/init.d/mysql status | grep -q "MySQL is stopped)"; do
        echo "Waiting for MySQL to restart..."
        sleep 1
    done

    echo "MySQL is active"
    echo "Executing Booking..."

    java -jar booking_webapp-0.0.1-SNAPSHOT.jar 
```
- Si te preguntas por que he liado todo esto en vez de usar Docker Compose, lo he usado, creeme, pero SpringBoot odia las redes de docker, la unica solución era poder usar localhost o AWS, asi que he tenido que meter la base de datos y la web en mismo contenedor


# MySQL
El servicio necesita acceder a una Base de Datos SQL llamada BookingDB con las tablas que aparecen en la carpeta BD del proyecto, yo he instalado [mysql](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04-es), pero he creado la base de
datos usando [WorkBench](https://linuxhint.com/installing_mysql_workbench_ubuntu/) y conectándome al puerto 3306 como root, la contraseña la he cambiado a password

En el docker, se usa la misma BD, la imagen conecta directamente el puerto 3306, así que mantenlo libre, para conectarte puedes usar:
- puerto: 3306
- Base de datos: bookingDB
- host: localhost (127.0.0.0)
- usuario: root
- password: password

Esta configuracion aperece en application.properties, si vas a cambiar la BD recuerda configurar este archivo, pero las tablas de la BD deben ser las mismas


