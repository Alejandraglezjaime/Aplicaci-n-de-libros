package com.curso.literatura.principal;

import com.curso.literatura.model.*;
import com.curso.literatura.repository.AutorRepositorio;
import com.curso.literatura.repository.LibroRepositorio;
import com.curso.literatura.service.ConsumoAPI;
import com.curso.literatura.service.ConvierteDatos;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepositorio repositorioLibro;
    private AutorRepositorio repositorioAutor;

    private final Scanner teclado = new Scanner(System.in);
    private final ConvierteDatos conversor = new ConvierteDatos();

    public Principal(LibroRepositorio libro, AutorRepositorio autor ){
        this.repositorioLibro = libro;
        this.repositorioAutor = autor;
    }
    public void muestraMenu() {
        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    |***************************************************|
                    
                              🕮  BIENVENIDO A LA LIBRERIA  🕮
                              
                    1.- Buscar libro por título.
                    2.- Mostrar libros registrados.
                    3.- Mostrar autores registrados.
                    4.- Mostrar autores vivos en un determinado año. 
                    5.- Buscar libros por idioma.
                    6.- Top 10 libros más descargados
                    7.- Buscar autor por nombre 
                    
                    0 - Salir
                    
                    |***************************************************|
                    """;
            System.out.println(menu);
            while (!teclado.hasNextInt()) {
                System.out.println("Formato inválido, ingrese un número que esté disponible en el menú!");
                teclado.nextLine();
            } opcion = teclado.nextInt();
            teclado.nextLine();


            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostarAutoresRegistrados();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 6:
                    top10LibrosMasDescargados();
                    break;
                case 7:
                    buscarAutorPorNombre();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Datos buscarDatosLibros() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        String libro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + libro.replace(" ", "+"));
        return conversor.obtenerDatos(json, Datos.class);
    }

    private void buscarLibro() {
        Datos datos = buscarDatosLibros();
        if (!datos.resultados().isEmpty()) {
            DatosLibros datosLibros = datos.resultados().get(0);
            DatosAutor datosAutor = datosLibros.autor().get(0);
            System.out.println("Título: " + datosLibros.titulo());
            System.out.println("Autor: " + datosAutor.nombre());
           Autor autor = new Autor(datosAutor);
            repositorioAutor.save(autor);
            repositorioLibro.save(new Libro(datosLibros, autor ));
        } else {
            System.out.println("El libro buscado no se encuentra. ");
        }
    }

    @Transactional
    private void mostrarLibrosRegistrados() {
        try {
            List<Libro> libros = repositorioLibro.findAll();
            if (libros.isEmpty()) {
                System.out.println("No hay ningún Libro registrado.");
            } else {
                libros.forEach(s -> {
                    System.out.println("Título: " + s.getTitulo());
                    System.out.println("Autor: " + s.getAutor().getNombre());
                    System.out.println("Número de descargas: " + s.getNumeroDeDescargas());
                    System.out.println("Idiomas: " + String.join(", ", s.getIdiomas()));
                    System.out.println();
                });
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al mostrar los libros.");
            e.printStackTrace();
        }
    }


    @Transactional
    private void mostarAutoresRegistrados() {
        try {
            List<Autor> autores = repositorioAutor.findAll();
            if (autores.isEmpty()) {
                System.out.println("No hay ningún autor registrado.");
            } else {
                autores.forEach(e -> {
                    System.out.println("Autor : " + e.getNombre());
                    System.out.println("Fecha de nacimiento : " + e.getFechaDeNacimiento());
                    System.out.println("Fecha de fallecimiento : " +  e.getFechaDeFallecimiento());
                    System.out.println();
                });
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al mostrar la lista de autores.");
            e.printStackTrace();
        }
    }

    private void autoresVivos() {
        System.out.println("Ingrese el año que desea buscar autores que se encuentran vivos ");
        while (!teclado.hasNextInt()) {
            System.out.println("Formato inválido, ingrese un año válido.");
            teclado.nextLine();
        }
        int fechavivo = teclado.nextInt();
        teclado.nextLine();
        String anioString = String.valueOf(fechavivo);

        List<Autor> autoresVivos = repositorioAutor.autorVivoEnDeterminadoAnio(anioString);
        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año especificado.");
        } else {
            System.out.println(" Autores vivos en " + fechavivo );
            autoresVivos.forEach(System.out::println);
        }
    }



    private void buscarLibroPorIdioma() {
        System.out.println("""
                ** IDIOMA **
                
                1.- Español (ES)
                2.- Inglés (EN)
                3.- Regresar al menú principal
                
                Por favor, seleccione idioma a consultar:
                """);
        int opcion = teclado.nextInt();
        teclado.nextLine();
        List<Libro> libros;
        switch (opcion) {
            case 1:
                libros = repositorioLibro.findByIdiomasContains("es");
                if (!libros.isEmpty()) {
                    libros.forEach(System.out::println);
                } else {
                    System.out.println("No hay ningún libro registrado en Español.");
                }
                break;
            case 2:
                libros = repositorioLibro.findByIdiomasContains("en");
                if (!libros.isEmpty()) {
                    libros.forEach(System.out::println);
                } else {
                    System.out.println("No hay ningún libro registrado en Inglés.");
                }
                break;
            case 3:
                muestraMenu();
                break;
            default:
                System.out.println("La opción seleccionada no es válida.");
        }
    }

    private void top10LibrosMasDescargados() {
        String json = consumoApi.obtenerDatos(URL_BASE + "&sort=download_count&order=desc&limit=10");
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        if (!datos.resultados().isEmpty()) {
            System.out.println("----- Top 10 Libros Más Descargados -----");
            for (int i = 0; i < Math.min(10, datos.resultados().size()); i++) {
                DatosLibros datosLibros = datos.resultados().get(i);
                System.out.println("Título: " + datosLibros.titulo());
                System.out.println("Autor: " + datosLibros.autor().get(0).nombre());
                System.out.println("Idioma: " + datosLibros.idiomas().get(0));
                System.out.println("Número de descargas: " + datosLibros.numeroDeDescargas());
                System.out.println("----------------------------------------");
            }
        } else {
            System.out.println("No se encontraron libros en el top 10 de descargas.");
        }
    }

    private void buscarAutorPorNombre(){
        System.out.println("Escribe el nombre autor que deseas buscar : ");
        var nombreAutor = teclado.nextLine();

        List<Autor> buscarAutor = repositorioAutor.autorPorNombre(nombreAutor);
        buscarAutor.forEach(e-> {
                    System.out.println("Autor : " + e.getNombre());
                    System.out.println("Fecha de nacimiento : " + e.getFechaDeNacimiento());
                    System.out.println("Fecha de fallecimiento : " +  e.getFechaDeFallecimiento());
                    System.out.println();
                }  );
    }


}
