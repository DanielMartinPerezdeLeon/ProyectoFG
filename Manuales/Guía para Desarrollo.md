En esta guía se explica cómo y dónde abrir el proyecto para ver y desarrollar el código, siguiendo el mismo entorno que se ha utilizado para el desarrollo original.

En principio, deberúa funcionar en cualquier IDE para Java, pero lo explicaré para eclipse en linux, ya que ahí es donde lo he desarrollado.

# EJECUTAR EN IDE
Si sigues e instalas todo lo que aparece a continuación en esta guía, podrás hacer cambios y ejecutar rapida y directamente el proyecto en SpringBoot en Eclipse, para ello:

  - Instala Eclipse y descarga los plugins que se mencionan más abajo.
  - Clona el proyecto en tu máquina (instala [git](https://www.digitalocean.com/community/tutorials/how-to-install-git-on-ubuntu-20-04-es))
  - Abre una consola y ejecuta el siguiente comando para clonar el proyecto:
  ```console
    git clone https://github.com/DanielMartinPerezdeLeon/ProyectoFG
  ```
  - Abre Eclipse y selecciona "Importar" > "Proyecto existente de maven" > selecciona la carpeta de "booking_webapp".
  - Instala MySQL y crea la base de datos como se indica más adelante en esta guía.
  - Realiza los cambios necesarios en el proyecto en Eclipse o modifica los archivos "application.properties" o "pom.xml" si es necesario.
  - En una consola (se recomienda usar la consola integrada de Eclipse), ejecuta el siguiente comando:
  ```console
    mvn clean compile install package
  ```
  - Haz clic derecho en el proyecto, selecciona "SpringBoot" > "Ejecutar como aplicación SpringBoot".
  - Se abrirá una consola que mostrará un registro, en caso de que haya algún error, podrás verlo ahí.
  - Si todo va bien, puedes acceder al servicio a través de http://localhost:8080/ .

# EQUIPO
Originalmente se ha utilizado una máquina virtual en VMware con una distribución de la ultima versión de mintmate.

# IDE's
Se ha utilizado [eclipse](https://www.eclipse.org/downloads/packages/installer) con los siguientes plugins (muchos no son necesarios pero ayudan muchisimo):
  - Eclipse Enterprise Java and Web Developer Tool (Permite usar Java/Jajarta EE en Eclipse).
  - Eclipse m2e (Soporte de maven en Eclipse).
  - Eclipse Web Developer Tools (Herramientas de desarrollo de html, js, css, JSON,...).
  - Eclipse XML tools.
  - Hibernate Tools (Automatiza la configuración de hibernate).
  - SonarLint (Para calidad de código/ detección de bugs).
  - Spring Tools (Para automatizar usar SpringBoot).
  - Wild Web Developer (Amplía el Web Developer Tools).

También se ha utilizado [Visual Studio Code](https://code.visualstudio.com/) con el complemento de JS y Docker.

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
Recuerda que cada vez que realices cambios, deberás utilizar el comando [mvn clean install package].

# Docker 
Para acceder facilmente al proyecto sin tener que usar IDE se ha usado [docker](https://docs.docker.com/engine/install/ubuntu/)

He subido a Docker Hub una imagen que permite abrir el servicio sin complicaciones. Si te preguntas cómo funciona la imagen, a continuación se detallan los pasos:
- He abierto un contenedor de docker con un ubuntu 22.04 ligero (1 gb)
- He instalado en ese contenedor MySQL, he cambiado la contraseña al root a 'password' y se ha creado el servidor con las tablas que puedes encontrar en el proyecto.
- He copiado el archivo JAR del proyecto (también se ha incluido el proyecto completo, lo que hace que la imagen sea más pesada, pero era conveniente).
- He hecho un commit del contenedor.
- He creado un dockerfile con este código:
 ```dockerfile
    FROM damarpele/booking:prueba

    EXPOSE 8080
    EXPOSE 3306


    ENTRYPOINT ["./run_booking.sh"]
```
- Ese archivo ./run_booking.sh lo he creado yo y tiene este código:
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
- Si te preguntas por qué se ha utilizado esta configuración en lugar de Docker Compose, se ha intentado usar, creeme, pero SpringBoot tiene problemas con las redes de Docker. La única solución era poder usar "localhost" o AWS, por lo que se ha tenido que incluir la base de datos y la web en el mismo contenedor.


# MySQL
El servicio necesita acceder a una Base de Datos SQL llamada BookingDB con las tablas que aparecen en la carpeta BD del proyecto, yo he instalado [MySQL](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04-es), pero he creado la base de
datos usando [WorkBench](https://linuxhint.com/installing_mysql_workbench_ubuntu/) y conectándome al puerto 3306 como root, la contraseña la he cambiado a password

En el docker, se usa la misma BD, la imagen conecta directamente el puerto 3306, así que mantenlo libre, para conectarte puedes usar esta configuración:
- puerto: 3306
- Base de datos: bookingDB
- host: localhost (127.0.0.0)
- usuario: root
- password: password

Esta configuración aparece en el archivo "application.properties" del proyecto. Si deseas cambiar la base de datos, recuerda configurar este archivo, pero las tablas de la base de datos deben ser las mismas.


