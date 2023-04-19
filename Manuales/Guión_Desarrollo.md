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

## En el IDE (Eclipse)

- Creamos un proyecto maven en un nuevo workspace sin arquetipo

- En el proyecto > propiedades > Java Compiler indicarle que usamos un jdk17 y la ruta de ese jdk

- Vamos a usar Spring, para ellos añadimos las dependencias en el pom.xml de nuestro proyecto.

- Abrimos una terminal proyecto > show in > terminal

- Ahora comprobamos que todo esté bien instalado con un `mvn clean compile package`.

- He añadido la dependencia de Hibernate

- He creado la el archivo de configuracion de Hibernate en resources (paso configuracion)
    ```
    <hibernate-configuration>
   <session-factory name="">
    <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="hibernate.connection.url">jdbc:mysql://localhost/BDbooking</property>
    <property name="hibernate.default_schema">BDbooking</property>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">password</property>
    <property name="hibernate.show_sql">true</property>
    <property name="hibernate.format_sql">true</property>
    <property name="hibernate.hbm2ddl.auto">create</property>
   </session-factory>
  </hibernate-configuration>
    ```  
- Vamos a poner facets al proyecto para que todo sea mas facil: project > properties > project facets > convert > (Solo le he añadido el de Web)


- Es un buen inicio para ir subiendo los archivos al github:
  
