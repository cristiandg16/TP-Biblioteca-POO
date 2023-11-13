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
//import javax.swing.JOptionPane;

public class GestionBiblioteca {
	public static void main(String[] args) throws IOException, InterruptedException{
		// Inicializamos la biblioteca de libros
		Biblioteca biblio = new Biblioteca();
		
		// Inicializamos la lista de prestamos
		ListaPrestamos listprestamos = new ListaPrestamos();
		
		
		// Variable de selección
		int seleccion = -1;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Bienvenido a la biblioteca nacional");
		
		while(seleccion != 0) {
			
			
			// Menu de operaciones
			System.out.println("Elija que desea hacer:");
			System.out.println();
			System.out.println("1 - Registrar un nuevo libro.");
			System.out.println("2 - Listar catalago completo.");
			System.out.println("3 - Buscar un libro.");
			System.out.println("4 - Deprecar un libro.");
			System.out.println("5 - Registrar un prestamo.");
			System.out.println("6 - Registrar una devolucion.");
			System.out.println("7 - Listar prestamos activos.");
			System.out.println("8 - Listar prestamos finalizados.");
			System.out.println("9 - Buscar un prestamo.");
			System.out.println("0 - Salir del programa.");
			
			try {
				if (sc.hasNextLine()) {
					seleccion = sc.nextInt();
				}
			} catch (InputMismatchException ex) {
				
				sc.next();
			}
		
			
			
			
			
			
			
			switch(seleccion) {
			
			
			case 1:
				//Registro de un nuevo libro
				Scanner scNuevoLibro = new Scanner(System.in);
				String nombreLibro,autorLibro,isbnLibro,editorialLibro;
				int stockLibro = 0;		
				
				//Ingreso del titulo del nuevo libro
				do{
					System.out.println("Ingrese el titulo:");
					nombreLibro = scNuevoLibro.nextLine();
				}while(nombreLibro.length() <= 0);
								
				// Ingreso del autor del nuevo libro
				do{
					System.out.println("Ingrese el autor:");
					autorLibro = scNuevoLibro.nextLine();
				}while(autorLibro.length() <= 0);
				
				// Ingreso del ISBN del nuevo titulo
				do{
					System.out.println("Ingrese el ISBN:");
					isbnLibro = scNuevoLibro.nextLine();
				}while(isbnLibro.length() <= 0);
				
				// Ingreso de la editorial del nuevo titulo
				do{
					System.out.println("Ingrese la editorial:");
					editorialLibro = scNuevoLibro.nextLine();
				}while(editorialLibro.length() <= 0);
				
				// Ingreso de la cantidad de copias que entraron 
				// No se aceptan valores menores o iguales a 0
				do{
					System.out.println("Ingrese la cantidad de copias:");
					try {
						if(scNuevoLibro.hasNextLine()) {
							stockLibro = scNuevoLibro.nextInt();
						}
					} catch (InputMismatchException ex) {
						System.out.println("Debe ingresar obligatoriamente un numero entero positivo.");
						scNuevoLibro.next();
				}
				
						
					
					
					
				}while(stockLibro <= 0);
				
				
				// Construimos con los datos ingresados el nuevo libro
				Libro newLibro = new Libro(nombreLibro,autorLibro,isbnLibro,editorialLibro,stockLibro,false);			
				
				//	Agregamos el libro a la biblioteca
				biblio.agregarLibro(newLibro);
				
				System.out.println("Libro agregado exitosamente.");
			
				break;
			
			/**************************************************************/
				
			case 2:
				// Listamos los ejemplares que componen a la biblioteca
				// Contemplamos a todos los libros que estan o no prestados.
				biblio.mostrarBiblio();
				break;
			
			/***************************************************************/	
				
			case 3:
				// Buscamos y listamos la información del libro deseado.
				// La busqueda se hace por titulo
				String nombreBusqueda ;
				Scanner scBusquedaLibro = new Scanner(System.in);
				
				// Ingresamos el titulo a buscar
				do{
					System.out.println("Ingrese el titulo del libro a buscar:");
					nombreBusqueda = scBusquedaLibro.nextLine();
				}while(nombreBusqueda.length() <= 0);
				
				// Revisamos si existe un libro con ese nombre
				if(biblio.buscarLibro(nombreBusqueda) == null){
					System.out.println("No se encontraron resultados.");
				}else
					biblio.buscarLibro(nombreBusqueda).mostrarDatosLibro();
					
				System.out.println();
				break;
			
			/*****************************************************************/	
				
			case 4:
				// Eliminamos un libro de la biblioteca.
				// Lo hacemos a traves del titulo del mismo.
				String nombreEliminar ;
				Scanner scEliminarLibro = new Scanner(System.in);
				
				do{
					System.out.println("Ingrese el titulo del libro a borrar:");
					nombreEliminar = scEliminarLibro.nextLine();
				}while(nombreEliminar.length() <= 0);
				
				// Corroboramos que exista el libro en la biblioteca
				if(biblio.buscarLibro(nombreEliminar) == null){
					System.out.println("No se encontraron resultados.");
				}
				// Corroboramos que no tenga prestamos activos
				else if(listprestamos.buscarPrestamoActivoTitulo(nombreEliminar) != null) {
					System.out.println("Existen prestamos activos para este titulo. Cargue las devoluciones correspondientes y vuelva a intentarlo.");
					
				}
				else {
					biblio.eliminarLibro(nombreEliminar);
					System.out.println("Libro eliminado con éxito.");
				}
					
				break;
				
			/*****************************************************************/
			
			case 5:
				// Ingresamos un nuevos prestamo de un libro
				Scanner scNuevoPrestamo = new Scanner(System.in);
				String nombreLibroPrestamo;
				String nombrePrestatario = null;
				int unidadesPrestadas = 0;
				long telPrestatario = 0,docPrestatario = 0;
				Date fechaEntrega = new Date(),fechaDev = new Date();
				
				
				do{
					System.out.println("Ingrese el titulo:");
					nombreLibroPrestamo = scNuevoPrestamo.nextLine();
				}while(nombreLibroPrestamo.length() <= 0);
				
				// Corroboramos si el libro existe en la biblioteca
				if(biblio.buscarLibro(nombreLibroPrestamo) == null){
					System.out.println("No se encontraron resultados.");
				}
				else {
					// Ingreso de nombre y apellido del beneficiario					
					do{
						System.out.println("Ingrese el nombre y apellido del prestatario:");
						nombrePrestatario = scNuevoPrestamo.nextLine();
					}while(nombreLibroPrestamo.length() <= 0);
					
					// Cantidad de unidades a prestar
					do{
						System.out.println("Ingrese la cantidad de copias a prestar:");
						try{
							if(scNuevoPrestamo.hasNextLine()) {
								unidadesPrestadas = scNuevoPrestamo.nextInt();
							}
								
						} catch (InputMismatchException ex) {
								System.out.println("Debe ingresar obligatoriamente un numero entero positivo.");
								scNuevoPrestamo.next();
						}
					
						
					}while((unidadesPrestadas <= 0) || (unidadesPrestadas > biblio.buscarLibro(nombreLibroPrestamo).Stock));
					
					// Ingreso de numero de telefono del beneficiario
					do{
						System.out.println("Ingrese el numero de telefono del prestatario:");
						try {
							if(scNuevoPrestamo.hasNextLine()) {
								telPrestatario = scNuevoPrestamo.nextLong();
							}
						} catch (InputMismatchException ex) {
							System.out.println("El formato del numero no es adecuado.");
							scNuevoPrestamo.next();
					}
						
						
						
					}while(telPrestatario <= 0);
					
					// Ingreso del documento del beneficiario
					do{
						System.out.println("Ingrese el numero de documento del prestatario:");
						try {
							if(scNuevoPrestamo.hasNextLine()) {
								docPrestatario = scNuevoPrestamo.nextLong();
							}
						} catch (InputMismatchException ex) {
								System.out.println("El formato del documento no es adecuado.");
								scNuevoPrestamo.next();
						}
						
						
					}while(docPrestatario <= 0);
					
					System.out.println("Ingrese la fecha de devolucion (dd/mm/aaaa):");
					
					do{
						
						String fechadev = fechaEntrega.toString();
							if(scNuevoPrestamo.hasNextLine()) {
								fechadev = scNuevoPrestamo.nextLine();
							}
							
							SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
							try {
								fechaDev = formato.parse(fechadev);
							} catch (ParseException e) {
								
								
							}
							
						
												
					}while(fechaDev.compareTo(fechaEntrega)<=0);
					
					Prestamo newPrestamo = new Prestamo(biblio.buscarLibro(nombreLibroPrestamo).Titulo ,biblio.buscarLibro(nombreLibroPrestamo).Autor,biblio.buscarLibro(nombreLibroPrestamo).ISBN,biblio.buscarLibro(nombreLibroPrestamo).Editorial,biblio.buscarLibro(nombreLibroPrestamo).Stock,true,nombrePrestatario,unidadesPrestadas,fechaEntrega,fechaDev,telPrestatario,docPrestatario);
					
					biblio.buscarLibro(nombreLibroPrestamo).Prestado = true;
					biblio.buscarLibro(nombreLibroPrestamo).Stock -= unidadesPrestadas;
					
					listprestamos.agregarPrestamo(newPrestamo);
					System.out.println("Prestamo realizado exitosamente.");
					
				}
							
					
				System.out.println();	
				
				break;
			
			/************************************************************/	
				
			case 6:
				// Registramos una devolución de un libro.
				// Esto genera una modificación del campo devuelto.
				
				Scanner scLibroDevuelto = new Scanner(System.in);
				String nombreLibroDevuelto,nombrePrestatarioDevuelto,docPrestatarioDevuelto;
				
				do{
					System.out.println("Ingrese el titulo:");
					nombreLibroDevuelto = scLibroDevuelto.nextLine();
				}while(nombreLibroDevuelto.length() <= 0);
				
				if(listprestamos.buscarPrestamoActivoTitulo(nombreLibroDevuelto) != null) {
					
				
					do{
						System.out.println("Ingrese el nombre del prestatario:");
						nombrePrestatarioDevuelto = scLibroDevuelto.nextLine();
					}while(nombrePrestatarioDevuelto.length() <= 0);
				
				
					do{
						System.out.println("Ingrese el documento del prestatario:");
						docPrestatarioDevuelto = scLibroDevuelto.nextLine();
					}while(docPrestatarioDevuelto.length() <= 0);
				
				// Corroboramos si el prestamo se encuentra activo
					if(listprestamos.buscarPrestamoActivo(nombreLibroDevuelto+nombrePrestatarioDevuelto+docPrestatarioDevuelto) == null){
						System.out.println("No se encontraron resultados.");
					}
					else {
						Prestamo dev = listprestamos.buscarPrestamoActivo(nombreLibroDevuelto+nombrePrestatarioDevuelto+docPrestatarioDevuelto);
						dev.fechaDevolucion = new Date();
						dev.devuelto = true;
					
						biblio.buscarLibro(nombreLibroDevuelto).Stock += dev.unidadesPrestadas;
						biblio.buscarLibro(nombreLibroDevuelto).Prestado = false;
						System.out.println("Devolución realizada exitosamente.");
					
					}
				} else
					System.out.println("No hay prestamos activos para ese titulo");
				
				System.out.println();
				break;
				
			
			/************************************************************/	
			
			case 7:
				// Listamos los prestamos que aun no han sido devueltos.
				listprestamos.mostrarlista_prestamos_activos();
				break;
			
			/************************************************************/	
				
			case 8:
				// Listamos los prestamos que ya fueron devueltos.
				listprestamos.mostrarlista_prestamos_finalizados();
				break;
			/************************************************************/	
				
			case 9:
				// Buscamos la información de un prestamo.
				Scanner scPrestamoBuscar = new Scanner(System.in);
				String nombreLibroBuscar,nombrePrestatarioBuscar,docPrestatarioBuscar;
				
				do{
					System.out.println("Ingrese el titulo:");
					nombreLibroBuscar = scPrestamoBuscar.nextLine();
				}while(nombreLibroBuscar.length() <= 0);
				
				
				do{
					System.out.println("Ingrese el nombre del prestatario:");
					nombrePrestatarioBuscar = scPrestamoBuscar.nextLine();
				}while(nombrePrestatarioBuscar.length() <= 0);
				
			
				
				do{
					System.out.println("Ingrese el documento del prestatario:");
					docPrestatarioBuscar = scPrestamoBuscar.nextLine();
				}while(docPrestatarioBuscar.length() <= 0);
				
			
				
				listprestamos.buscarPrestamoActivo(nombreLibroBuscar+nombrePrestatarioBuscar+docPrestatarioBuscar);
				listprestamos.buscarPrestamoFinalizado(nombreLibroBuscar+nombrePrestatarioBuscar+docPrestatarioBuscar);
				
			case 0:
				break;
				
			default:
				
			System.out.println("Por favor ingrese una opción correcta.");
			break;
			}
		}
		
		sc.close();
		
		
		System.out.println("Gracias por utilizar el programa.");
		
	}
	
	
	
}
