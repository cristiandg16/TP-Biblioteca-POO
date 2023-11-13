
import java.util.ArrayList;
import java.util.List;

public class ListaPrestamos {
	// Definimos el valor de prestamo Null
	private static final Prestamo Null = null;
	
	// Creamos la lista de prestamos
	private List <Prestamo> lista_prestamos = new ArrayList<Prestamo>();
	
	// Funcion encargada de agregar un nuevo prestamo.
	public void agregarPrestamo(Prestamo newPrestamo) {
		lista_prestamos.add(newPrestamo);
	}
	
	// Funcion que lista los prestamos que se encuentran activos
	public void mostrarlista_prestamos_activos() {
		if (lista_prestamos.isEmpty()) {
			System.out.println("No se registran prestamos activos a la fecha.");
		}
		else {
			System.out.println("Prestamos activos:");
			for (Prestamo Prestamo : lista_prestamos) {
				if(Prestamo.devuelto == false) {
					Prestamo.mostrarDatosPrestamo();
				}
				
				
				System.out.println();
			}
		}
		System.out.println();
	}
	
	// Funcion que lista los prestamos ya finalizados
	public void mostrarlista_prestamos_finalizados() {
		
		if (lista_prestamos.isEmpty()) {
			System.out.println("No se registran prestamos.");
		}
		else {
			System.out.println("Prestamos finalizados:");
			for (Prestamo Prestamo : lista_prestamos) {
				if(Prestamo.devuelto == true) {
					Prestamo.mostrarDatosPrestamo();
				}
				
				//System.out.print(Prestamo.obtenerTitulo() + "-" + Prestamo.obtenerAutor() + "-" + Prestamo.obtenerEditorial());
				System.out.println();
			}
		}
		System.out.println();
	}
	
	
	// Funcion que busca un prestamo finalizado
	public Prestamo buscarPrestamoFinalizado(String codigoPrestamo) {
		
		
		for (Prestamo Prestamo : lista_prestamos) {
			if (Prestamo.idPrestamo.toLowerCase().equals(codigoPrestamo.toLowerCase()) && Prestamo.devuelto == true) {
				return Prestamo;
			}
		}
		return Null;
	}
	
	// Funcion que busca un prestamo activo
	public Prestamo buscarPrestamoActivo(String codigoPrestamo) {
		
		
		for (Prestamo Prestamo : lista_prestamos) {
			if (Prestamo.idPrestamo.toLowerCase().equals(codigoPrestamo.toLowerCase()) && Prestamo.devuelto == false ) {
				return Prestamo;
			}
		}
		return Null;
	}
	
	
	// Funcion que buscar un prestamo activo por titulo de libro
	public Prestamo buscarPrestamoActivoTitulo(String titulo) {
	
	
	for (Prestamo Prestamo : lista_prestamos) {
		if (Prestamo.Titulo.toLowerCase().equals(titulo.toLowerCase()) && Prestamo.devuelto == false ) {
			return Prestamo;
		}
	}
	return Null;
}

	// Funcion que elimina un prestamo de la lista de prestamos.
	public void eliminarPrestamo(String codigoPrestamo) {
		lista_prestamos.remove(buscarPrestamoFinalizado(codigoPrestamo));
	}
	
}
