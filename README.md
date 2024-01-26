# **Taller 1 - Servidor Web para consultar películas**
### *Hecho por Ricardo Pulido Renteria*

En este taller, se desarrolla un servidor web utilizando Java y una API gratuita llamada OMDb API para brindar información sobre películas.
El usuario podrá acceder desde su navegador para realizar la búsqueda por el título que desee y obtener los datos de dicha película en formato JSON.

## **Descarga y ejecución**

Para poder ejecutar este proyecto, el cual se ejecutará en tu ambiente local por fines de desarrollo y pruebas, debes contar con algunos elementos que serán indicados a continuación.


## **Prerequisitos**

La ejecución de este proyecto requiere de:
- `Java (versión 8 o superior)`
- `Maven (3.8.1 o superior)`
- `Conexión a internet`


## **Instalación**

Para poder trabajar con el proyecto, es necesario descargarlo desde GitHub. Para esto puede clonar el repositorio en su máquina o puede descargarlo en formato zip. Luego, puede ejecutarlo desde la terminal de comandos o desde el intérprete de código de su elección (VS Code, IntelliJ, NetBeans, etc).


- **_Ejecución usando terminal de comandos_**
  
  En caso de realizar la ejecución desde la terminal de comandos, se debe realizar lo siguiente:
  1. Acceder al directorio del proyecto usando el comando `cd arep-taller1`.
  2. Una vez dentro del directorio del proyecto, se ejecuta el comando `mvn package` para generar la carpeta _target_.
  3. Desde la terminal, ejecutamos el comando `java -cp .\target\classes edu.escuelaing.arem.ASE.app.RestAPIFacade`.
  4. Listo, el servidor web estará corriendo y verás un mensaje diciendo que está listo para recibir peticiones.


## **Uso**

Para acceder al servicio y pedir la información de la película de tu elección, entra a http://localhost:17000/ en el navegador de tu preferencia para visualizar el formulario. Una vez allí, en el campo de texto se te solicita escribir el nombre de la película que deseas buscar, para obtener los datos de dicha película debes dar clic en el botón "Submit".

Hecho esto, abajo te aparecerá en formato JSON (JavaScript Object Notation) toda la información proporcionada por la API. En caso de que el nombre de la película no se encuentra, sea por errores de digitación al escribir el título o por no encontrarse en la plataforma de búsqueda, se mostrará un mensaje notificando al usuario que su película no fue encontrada y que intente nuevamente.

## **Diseño**

Para este proyecto se manejan 2 clases que son _RestAPIFacade_ y _MovieAPI_, tomando de guía las demás clases encontradas en este proyecto las cuales se realizaron durante la clase.

La clase _RestAPIFacade_ crea un servidor HTTP que realiza llamados a _MovieAPI_ (esta clase implementa una interfaz con el fin de aplicar la inversión de dependencias junto a la segregación de interfaces) donde se requiere de un método con el cual buscar los datos de la película dado su título. _MovieAPI_ utiliza la API gratuita de OMDb API para la obtención de los datos de las películas y maneja un mapa para simular una memoria caché, esto con el fin de no hacer consultas repetitivas a la API fuera de que el apikey que nos brinda OMDb tiene un límite de 1000 consultas al día.

Se manejaron clases diferentes para el servidor y para la API con el fin de brindar flexibilidad para la extensión del proyecto y su posible modificación. En el servidor se cuenta con los elementos necesarios para levantar el servicio y escuchar por el puerto 17000, el cual se puede cambiar solo con modificar este valor que se encuentra en el enumerado llamado _env_.

En caso de quere cambiar el uso de la API, puede crear una nueva clase donde la condición es la implementación de la interfaz _APIQuery_. Esta contiene como contrato la implementación de una función que retorne en formato string el resultado acerca de la película a consultar.

## **Implementación de otro proveedor de servicio**

Tal como se indicó en la sección anterior, se puede crear una nueva clase que implemente la interfaz _APIQuery_. Diseñarla de la forma que requiera con la única condición de mínimo contar con el método de `queryMovie()`, utilizando otra API o manejando el servicio de consulta de la forma deseada.

Una vez hecho esto, para utilizarlo se tendría que cambiar la instancia utilizada por el servidor. Esto en el atributo `movieSearcher` encontrado el inicio de la clase _RestAPIFacade_, este atributo es de tipo APIQuery para confirmar que se pueda utilizar de la forma esperada por el servidor.

## **Construido con**
  - [Git](https://git-scm.com) - Control de versiones
  - [Maven](https://maven.apache.org) - Administrador de dependencias