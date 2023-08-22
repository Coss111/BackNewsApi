# Utiliza una imagen base de .NET Core
FROM
# Copia los archivos del proyecto y restaura las dependencias
COPY target/*.jar app.jar

ENV
# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080
# Comando para iniciar la aplicación cuando el contenedor se ejecute
ENTRYPOINT ["java","-jar","/app.jar"]
