# Fixing Project

1. Actualización del nombre del pojo SpotifyArtist a [Artist](./src/main/java/com/example/refactor/model/Artist.java)
1. Implementación patron Builder en las clases del paquete [model](./src/main/java/com/example/refactor/model).
1. Eliminación de `setter` o declación `private` para mantener la inmutabilidad de los POJOs ([dto](./src/main/java/com/example/refactor/dto),[model](./src/main/java/com/example/refactor/model)).
1. Cambio de declaraciones en variables, se eliminan las clases específicas para utilizar Interfaces.
1. Utilización de `Optional`.
1. Utilización de Unit Test para validar el funcionamiento antes y después de los cambios en el código.
1. Se cambia el uso de `JSONObject` por `ObjectMapper`, se trabaja con el supuesto que se ha definido el formato de recepción 
    por parte del provedor ficticio (Spotify).

# Ejecución

1. Ejecutar `mvn clean install`, el comando creará el jar `master-interview-1.0-SNAPSHOT.jar` bajo la carpeta */target*.
1. Correr el jar con el comando `java -jar master-interview-1.0-SNAPSHOT.jar` se puede agregar el path donde se encuentra
el archivo con el json a procesar, en caso de que no se agregue el path del archvio como argumento, el programa solicitará
que se ingrese la ruta del archvio a procesar.
1. En caso de no existir, se enviará mensaje de que el archivo no existe.