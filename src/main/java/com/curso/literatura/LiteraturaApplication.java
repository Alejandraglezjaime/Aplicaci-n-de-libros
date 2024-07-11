package com.curso.literatura;

import com.curso.literatura.principal.Principal;
import com.curso.literatura.repository.AutorRepositorio;
import com.curso.literatura.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {
	@Autowired
	private LibroRepositorio repositoryLibro;

	@Autowired
	private AutorRepositorio repositoryAutor;

	public static void main(String[] args) {

		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryLibro, repositoryAutor);
		principal.muestraMenu();
	}
}
