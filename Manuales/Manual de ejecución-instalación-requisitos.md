# Web-APP booking


# Descripción
  Se trata de un Servicio Web construído con Java 17 y el FrameWork SpringBoot para simular la gestión y reserva de horarios de distintos puestos de trabajo en una oficina.

# Requisitos

## Hardware
  Para poder usar la app-web, debe ser alojada en un servidor/ordenador que cumpla una de estas dos características:

  ### Ordenador con una distribución cualquiera de Linux que cumpla los requisitos mínimos para tener docker:
  - 64-bit kernel and CPU support for virtualization.
  - KVM virtualization support.
  - QEMU must be version 5.2 or newer.
  - Gnome, KDE, or MATE Desktop environment.
  - At least 4 GB of RAM.
  - Enable configuring ID mapping in user namespaces, see File sharing.


  ### Ordenador con una distribución de Windows 10/11 que cumple estos requisitos mínimos:
  - Enable the WSL 2 feature on Windows.
  - 64-bit processor with Second Level Address Translation (SLAT)
  - 4GB system RAM
  - BIOS-level hardware virtualization support must be enabled in the BIOS settings.
  - Download and install the Linux kernel update package.



## Software

  ### Linux:
  - Tener instalado [Docker] (https://docs.docker.com/).
  - Tener y mantener abierto el puerto 8080 del equipo.
  - Tener y mantener abierto el puerto 3306 del equipo.
  - Si se va a acceder desde la misma maquina que se aloja, tener instalado y actualizado el navegador web Google Chrome (preferiblemente), o en su defecto firefox.

  ### Windows 10/11:
  - Tener instalado [Docker Deskpot] (https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe).
  - Instalar en el equipo [WSL2] (https://docs.docker.com/desktop/install/windows-install/).
  - Tener y mantener abierto el puerto 8080 del equipo.
  - Tener y mantener abierto el puerto 3306 del equipo.
  - Si se va a acceder desde la misma maquina que se aloja, tener instalado y actualizado el navegador web Google Chrome (preferiblemente), o en su defecto firefox.



# Guia de instalación
 ### Linux:
  - En una consola, ejecuta los siguientes comandos (quizás necesites permisos de administrados [sudo] antes de cada comando).
  ```console
  docker pull damarpele/booking:latest
  docker run damarpele/booking:latest
  ```
  -Una vez hecho esto, podrás acceder desde ese equipo con http://localhost:8080 ó desde otro equipo en la misma red usando la IP del equipo y el puerto 8080.

  ### Windows 10/11:
  - Abrir Docker Deskpot y esperar a que cargue.
  - Ir a la sección "Images".
  - Haremos click en la barra de busqueda en la parte superior de la ventana o pulsamos Ctrl+k.
  - En el buscador escribimos damarpele/booking, hacemos click en la imagen que nos saldrá, en tag seleccionamos latest y hacemos click en pull.
  - Esperamos a que se descargue la imagen (puede tardar varios minutos).
  - En images ahora nos saldrá la imagen descargada, hacemos click en el botón run:arrow_forward:
  - Nos saldrá una nueva ventana, en la que tendremos que abrir la pestaña  Optional Settings.
  - En esta ventana podemos darle un nombre si queremos a nuestro contenedor, pero lo importante es darle puertos al puerto 8080 y 3306 (preferiblemente esos mismos)
  - Le damos a run, esperamos a que cargue y ya podremos acceder al servicio desde ese equipo con http://localhost:8080 ó desde otro equipo usando la ip de la máquina y el puerto 8080

# Guia de ejecución
Una vez que el servicio esté alojado en Docker, estará listo para ser utilizado a través del puerto 8080 del equipo donde se encuentra el contenedor en ejecución.

Si sospechas que ha ocurrido un error en el contenedor, puedes verificarlo desde Docker Desktop abriendo el contenedor en la sección "Containers" o desde la consola de Linux ejecutando el siguiente comando:


```console
  docker logs [nombre del contenedor]
  ```
También puedes acceder directamente al servicio dentro del contenedor en Docker Desktop en la misma pestaña "Terminal" o desde la consola de Linux ejecutando el siguiente comando:


```console
  docker exec -it [nombre del contenedor] /bin/bash
  ```
