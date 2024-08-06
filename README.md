# Challenge Técnico para LAMANSYS
Este es un proyecto de automatizacion de pruebas creado para Lamansys, en el cual se utilizaron las paginas de Gmail y Ebay para la parte web y el servidor de muestra de Petstore para las pruebas de API.

## Tecnologías y Versiones de las Librerías

- **Java**: 11.0.10
- **Maven**: 3.8.5
- **Serenity Screenplay**: 4.1.20
- **JUnit**: 4.13.2
- **RestAssured**: 5.4.0
- **Lombok**: 1.18.34
- **TestNg**: 7.10.2

## Descripción del Proyecto

Este proyecto consiste en la automatización de pruebas de la página web de Ebay y Gmail, utilizando el patrón de diseño screenplay para la organización del código. Para la parte de APIs se utilizó el servidor de pruebas PetStore, haciendo foco en la sección de “Users”, se puede acceder a la información de las apis en la URL https://petstore.swagger.io/#/

Se Crearon 3 clases de Tests para agruparlas de forma organizada, a continuacion se detalla brevemente las clases de pruebas:

- EbayTests: Se realiza una prueba que incluye navegar hacia la pagina ebay.com, realizar una busqueda por el nombre de articulo guitarra electrica, seleccionar el primer resultado de la busqueda e imprimer el valor de dicho producto.


- GmailTests:
  - Prueba 1°: Se realiza una prueba que incluye navegar hacia la pagina gmail.com, realizar un inicio de sesion con una cuenta creada previamente, y se valida el login exitoso mediante el icono de perfil de usuario.
  - Prueba 2°: Se realiza una prueba de login no exitoso, enviando una contraseña incorrecta y validando el mensaje de error.


- PetStoreTests: 
  - Prueba 1°: Se realiza un llamado a la api de PetStore con metodo post para crear un nuevo usuario, luego se hace un get para validar los datos del usuario creado.
  - Prueba 2°: Se crea un nuevo usuario mediante un post, se validan los datos mediante un get, se modifican los datos del usuario mediante un put y se valida que los datos fueron modificados con otro get.  


## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/agustingonzalez93/TechnicalTestLamansys.git  
    ```

2. Configura las dependencias de Maven:
    ```sh
    mvn clean install
    ```


