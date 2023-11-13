import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.csvreader.CsvWriter;



public class Biblioteca {
	// Definimos el objeto vacio
	private static final Libro Null = null;
	
	// Definimos a biblioteca como una lista de libros
	private List <Libro> biblio = new ArrayList<Libro>();
	
	// Funcion que agrega un libro a la biblioteca
	public void agregarLibro(Libro newLibro) {
		biblio.add(newLibro);
	}
	
	// Funcion que lista los libros de la biblioteca
	public void mostrarBiblio() {
		if (biblio.isEmpty()) {	//Revisamos si la lista tiene elementos
			System.out.println("La biblioteca esta vacia.");
		}
		else {
			System.out.println("Libros disponibles:");
			for (Libro libro : biblio) {
				libro.mostrarDatosLibro();
				System.out.println();
			}
		}
	}
	
	// Funcion que se encarga de buscar un libto por su titulo.
	public Libro buscarLibro(String titulo) {
		
		
		for (Libro libro : biblio) {
			if (libro.Titulo.toLowerCase().equals(titulo.toLowerCase())    ) {
				return libro;
			}
		}
		return Null;
	}
	
	// Funcion encargada de eliminar un libro de la biblioteca
	public void eliminarLibro(String titulo) {
		biblio.remove(buscarLibro(titulo));
	}
	
	

}
