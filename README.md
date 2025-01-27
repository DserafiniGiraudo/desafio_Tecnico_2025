# desafio_Tecnico_2025

Para desarrollar utilicé Visual Studio Code.
Faltó realizar:
. test en todos los microservicios.
. msvc-costos -> punto 4 (Consultar el camino con costo mínimo entre dos puntos de venta A y B).
    Con mas tiempo y conocimientos implementaría o una biblioteca que maneje grafos o dijkstra. Por desconocimiento y tiempo no lo desarrollé.
. Pasar los microservicios a docker.
    Con mas tiempo hubiese armado el docker-compose para poner mysql ahi,los servicios y la dependencia de msvc-acreditaciones con msvc-puntos-ventas, y el dockerfile en cada       microservicio para poder armar la imagen. 

Para poder correr el aplicativo se debe levantar el eureka-server, luego los microservicios de punto venta, costos y acreditaciones. 
revisar en los .yml/.properties si se debe ajustar el password, Es necesario tener mysql instalado en la pc(esto lo hubiese evitado si pasaba todo a docker, creaba un contenedor que tenga eso y hacia la coneccion a el mismo).

Ante la falta de test, las pruebas las realicé con la herramienta de postman pegandole a cada endpoint y controlando que devuelva lo esperado. No pude implementar tolerancia a fallos ni ningun patron de microservicios.
