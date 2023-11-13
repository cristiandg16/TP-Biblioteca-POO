import java.util.Date;

public class Prestamo extends Libro {
	public String nombrePrestatario,idPrestamo;
	public int unidadesPrestadas;
	public boolean devuelto;
	public Date fechaEntrega,fechaDevolucion;
	public long telefonoPrestatario,documentoPrestatario;
	
	
	public Prestamo(String titulo, String autor, String ISBN, String edit, int stock, boolean prest,String prestatario,int unidades,Date fechaEntrega,Date fechaDev,long telefono,long doc) {
		super(titulo, autor, ISBN, edit, stock, prest);
		this.nombrePrestatario = prestatario;	// Nombre y apellido de quien recibe el prestamo del libro
		this.unidadesPrestadas = unidades;		// Unidades que son prestadas 
		this.fechaEntrega = fechaEntrega;		// Fecha que se realiza el prestamo
		this.fechaDevolucion = fechaDev;		// Fecha que se realiza la devolucion del prestamo
		this.telefonoPrestatario = telefono;	// Numero de telefono de quien recibe el prestamo.
		this.documentoPrestatario = doc;		// Numero de documento de quien recibe el prestamo.
		this.devuelto = false;					// Indica si el prestamo sigue activo o no
		this.idPrestamo = titulo+prestatario+String.valueOf(doc);	// Codigo identificatorio del prestamo
		// TODO Auto-generated constructor stub
	}
	
	// Muestra los atributos de un prestamo
	public void mostrarDatosPrestamo() {
		System.out.println("Titulo: "+this.Titulo);
		System.out.println("Autor: "+this.Autor);
		System.out.println("ISBN: "+this.ISBN);
		System.out.println("Editorial: "+this.Editorial);
		System.out.println("Prestado a: "+ this.nombrePrestatario);
		System.out.println("Unidades Prestadas: "+ this.unidadesPrestadas);
		System.out.println("Fecha Entrega: "+ this.fechaEntrega);
		System.out.println("Fecha Devolucion: "+ this.fechaDevolucion);
		System.out.println("Telefono: "+ this.telefonoPrestatario);
		System.out.println("Documento: "+ this.documentoPrestatario);
		if(this.devuelto == false) {
			System.out.println("Prestamo en curso");
		}else
			System.out.println("Prestamo finalizado");
		System.out.println();
	}
	
}
