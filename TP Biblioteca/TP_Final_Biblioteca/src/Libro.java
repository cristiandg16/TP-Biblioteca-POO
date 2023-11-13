
public class Libro {
	public String Titulo,Autor,ISBN,Editorial;
	public int Stock;
	public boolean Prestado;
	
	public Libro(String titulo, String autor, String ISBN,String edit,int stock, boolean prest) {
		this.Titulo = titulo;
		this.Autor = autor;
		this.ISBN = ISBN;
		this.Editorial = edit;
		this.Stock = stock;
		this.Prestado = prest;
		
	}
	
	// Devuelve el titulo del libro
	public String obtenerTitulo() {
		return Titulo;
	}
	
	// Devuelve el autor del libro
	public String obtenerAutor() {
		return Autor;
	}
	
	// Devuelve el ISBN del libro
	public String obtenerISBN() {
		return ISBN;
		
	}
	
	//  Devuelve la editorial del libro
	public String obtenerEditorial() {
		return Editorial;
	}
	
	// Indica la cantidad de copias del libro
	public int obtenerStock() {
		return Stock;
	}
	
	// Indica si esta prestado o no.
	public boolean obtenerEstado() {
		return Prestado;
	}
	
	// Muestra los atributos de un libro
	public void mostrarDatosLibro() {
		System.out.println("Titulo: "+this.Titulo);
		System.out.println("Autor: "+this.Autor);
		System.out.println("ISBN: "+this.ISBN);
		System.out.println("Editorial: "+this.Editorial);
		System.out.println("Stock: "+this.Stock);
		System.out.println("Prestado: "+ this.Prestado);
	}
	
}


