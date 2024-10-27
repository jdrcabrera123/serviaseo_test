# serviaseo

# Prueba Técnica Java Backend

Configuración de Ambiente
1. Instalar Java JDK 17 y JRE 1.8
2. Instalar y confirmar versión actualizada de Maven
3. Configurar variables de entorno para Maven (ver manual de instalación de Maven)
4. si esta trabajando con windows, instalar WAMP o XAMPP para la base de datos MariaDB, Si esta trabajando con Linux, instalar LAMP para la misma base de datos, si ya tiene un servidor de MariaDB o MySQL en alguna otra instancia, debe configurarla en el archivo properties del proyecto
#
Configuración del Proyecto
1. clonar el proyecto
2. Configurar base de datos desde el archivo serviaseo.sql
3. Modificar archivo Properties para configuraciones de base de datos y servidor de correo
4. mediante consola Ejecutar el comando mvn clean package, para descargar las librerias del proyecto
5. para ejecutarlo sin la necesidad de abrir un IDE se puede realizar de 3 maneras

 a. ejecutar mvn spring-boot:run en la carpeta del proyecto
 b. ejecutar mvn clean install para compilar .jar y ejecutar el .jar directamente ya sea desde windows o desde linux, no se requieren mas configuraciones ya que el proyecto maneja un tomcat embebido
 c. Usar el IDE de desarrollo para ejecutar el proyecto en ambiente de desarrollo

#
Modificaciones de codigo
1. para modificar el codigo se recomiendo usar STS o Visual Code Studio
2. para la base de datos se recomiendo usar MySQL Workbench o PHPMyAdmin

#
El proyecto cuenta con documentación de consumo de los servicios, esta se encuentra en la siguiente URL http://localhost:8081/swagger-ui/index.html#/
a esta documentación solo podra acceder una vez iniciado el proyecto

#
Tambien puedes usar el archivo ServiAseo.postman_collection.json para migrar la colección de servicios a tu POSTMAN y poder consumirlos facilmente

