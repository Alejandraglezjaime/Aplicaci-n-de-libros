# LITERATURA

Este proyecto fue desarrollado para comprender Spring Data JPA y PostgreSQL Driver, con el objetivo de integrar la API Gutendex y almacenar los resultados en una base de datos PostgreSQL. 

La API Gutendex es un catálogo de información de más de 70.000 libros presentes en Project Gutenberg (biblioteca en línea y gratuita). *URL API: https://gutendex.com/* 

## DESCRIPCION DEL PROYECTO 

El proyecto incluye las siguientes funcionalidades:

![Imagen menu](https://github.com/Alejandraglezjaime/Aplicaci-n-de-libros/blob/master/Img/menuPrincipal.jpg?raw=true)

1. **Buscar Libro.** En esta etapa realizamos consulta de un libro en específico por su título en la API, con la ayuda de la URL base *“https://gutendex.com/books/"* y retenerá el primer resultado obtenido, pasa posterior guardarlo en la base de datos de PostgreSQL Driver. El libro debe tener los siguientes atributos:

    • Título.
    • Autor.
    • Idiomas.
    • Número de Descargas.
   
2. **Mostrar libros registrados.** Con esta funcionalidad, será posible presentar en la consola un listado de todos los libros que ya fueron buscados, con sus respectivos atributos.
   
3. **Mostrar autores registrados.** Como podemos ver en el sitio web de la API, cada libro tiene datos relacionados con sus autores, en este caso el cuerpo de json recibe una lista de autores por libro, donde cada autor tiene tres características:

    • Nombre.
    • Año de nacimiento.
    • Año de fallecimiento.

La aplicación considera que un libro posee solo un autor, para que las consultas hechas con tal objeto sean más comprensibles y sencillas. En otras palabras, nos quedaremos solo con el primer resultado de autor de la lista de autores recibida.

4. **Mostrar autores vivos en un determinado año.** Además, pensando en los años de nacimiento y fallecimiento, es posible incluso realizar una consulta de autores vivos en un determinado año. 

5. **Buscar Libros por Idioma:** Filtra y muestra por consola los libros según el idioma especificado, es este caso manejamos dos  español (ES) e inglés (ES). En este desafío vamos a considerar que un libro posee solo un idioma, para que las consultas hechas con tal objeto sean más comprensibles y sencillas. En otras palabras, nos quedaremos solo con el primer resultado de idioma de la lista de idiomas recibida.

### EXTRAS 

- [ ]  **Generando estadísticas.** Iniciamos las sugerencias de funcionalidades opcionales recordando la clase DoubleSummaryStatistics, utilizada para obtener datos estadísticos de un objeto Java. Es posible obtener dichos datos ya sea de consultas de la API o base de datos.

- [x]  **Top 10 Libros Más Descargados.** Muestra los 10 libros más descargados basados en criterios definidos por la aplicación. Es posible presentar los datos de los 10 libros más descargados, siendo una consulta directamente hecha en la API o en la base de datos.

- [x] **Buscar autor por nombre.** Si has echado un vistazo al sitio de la API es posible realizar la búsqueda de libro o autor con la consulta hecha con search?. sin embargo, en este caso realiza la consulta por nombre de autor en la base de datos creada para nuestro proyecto.

- [ ] **Listar autores con otras consultas.**  Implementa otras consultas con los atributos de año de nacimiento y fallecimiento de los autores. 

- [ ] **Realizar su front End para facilidad del usuario.** 

#### FUNCIONALIDAD CODIGO .

![Imagen consola funcionando](https://github.com/Alejandraglezjaime/Aplicaci-n-de-libros/blob/master/Img/Funcionamiento.jpg?raw=true)

## PERSISTENCIA DE DATOS 

Para almacenar la información anterior se construyó una base de datos, con tablas y atributos relacionados a nuestros objetos de interés: **Libro** y **Autor.**

Se utilizo la base de datos llamada **PostgreSQL**, una de las bases de datos open source más utilizadas en el mercado. 

Sugerimos la creación de clases de entidad/modelo para Libro y Autor, así como también sus respectivas interfaces de repositorio para manejar inserción y consultas en la base de datos.

Al crear los repositorios de libros y autores, recuerda realizar la conversión de los atributos del libro presentes en el resultado json para un objeto java correspondiente al libro, así quedará más fácil manejar los datos obtenidos en tu proyecto.

[IMPORTANTE] Al insertar un libro en la base también deberás insertar su autor y así mantener una relación entre los dos objetos vía atributo de identificación (o como lo llamamos, el famoso ID).

![Imagen base de datos tabla libros](https://github.com/Alejandraglezjaime/Aplicaci-n-de-libros/blob/master/Img/baseDeDatoslibro.jpg?raw=true)

![Imagen base de datos tabla autores](https://github.com/Alejandraglezjaime/Aplicaci-n-de-libros/blob/master/Img/baseDeDatosAutores.jpg?raw=true)



# AUTOR
#### © Alejandra Gonzalez  