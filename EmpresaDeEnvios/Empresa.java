package EmpresaDeEnvios;


    import java.util.HashSet;
	import java.util.LinkedList;

public class Empresa {
	private String CUIT;
	private String NOMBRE;
	private int capacidadDeposito;
	
	public Empresa(String cuit, String nombre, int capacidad) {
		CUIT = cuit;
		NOMBRE = nombre;
		capacidadDeposito = capacidad; // La capacidad no puede ser negativa
		
		// Agregar depositos.
		Deposito d1 = new Deposito(true, capacidad);
		Deposito d2 = new Deposito(false, capacidad);
		depositos.add(d1);
		depositos.add(d2);
		
	}
	
	/* -------- Getters and Setters ----------- */
	public String getCUIT() {
		return CUIT;
	}
	public void setCUIT(String cUIT) {
		CUIT = cUIT;
	}
	public String getNOMBRE() {
		return NOMBRE;
	}
	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}
	public int getCapacidadDeposito() {
		return capacidadDeposito;
	}
	public void setCapacidadDeposito(int capacidadDeposito) {
		this.capacidadDeposito = capacidadDeposito;
	}
	public LinkedList<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(LinkedList<Viaje> viajes) {
		this.viajes = viajes;
	}

	public HashSet<Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(HashSet<Transporte> transportes) {
		this.transportes = transportes;
	}
	
	/*---------------------------------------*/
	
	// Objetos que maneja la empresa:
	private LinkedList<Viaje> viajes; // Un viaje puede ser igual a otro pero se le asigna otro transporte
	private HashSet<Deposito> depositos;
	private HashSet<Transporte> transportes;
	
	// Metodos de la Empresa
	// boolean incorporarUnPaqueteAunDeposito(Paquete paquete) {		
	// 	return true;
	// }
	
	// void agregarTransporte(Transporte transporte) {}
	
	// void asinarDestinoAtransporte(String id, String destino, String cantKm) {}
	
	// int cargarTransporteConMercaderia(String id) {return 0;}
	
	// void iniciarViaje(String id) {}
	
	// void finalizarViaje(String id) {}

	public static void main(String[] args) {
		double volumen;
		Empresa e = new Empresa("30112223334","Expreso Libre", 40000);
		System.out.println(e.toString());
		e.agregarDestino("Rosario", 100);
		e.agregarDestino("Buenos Aires", 400);
		e.agregarDestino("Mar del Plata", 800);
		e.agregarTrailer("AA333XQ", 10000, 60, true, 2, 100);
		e.agregarMegaTrailer("AA444PR", 15000, 100, false, 3, 150, 200, 50);
		e.agregarFlete("AB555MN", 5000, 20, 4, 2, 300);
		e.asignarDestino("AA333XQ", "Buenos Aires");
		e.asignarDestino("AB555MN", "Rosario");
		// paquetes que necesitan frio
		e.incorporarPaquete("Buenos Aires", 100, 2, true);
		e.incorporarPaquete("Buenos Aires", 150, 1, true);
		e.incorporarPaquete("Mar del Plata", 100, 2, true);
		e.incorporarPaquete("Mar del Plata", 150, 1, true);
		e.incorporarPaquete("Rosario", 100, 2, true);
		e.incorporarPaquete("Rosario", 150, 1, true);
		// paquetes que NO necesitan frio
		e.incorporarPaquete("Buenos Aires", 200, 3, false);
		e.incorporarPaquete("Buenos Aires", 400, 4, false);
		e.incorporarPaquete("Mar del Plata", 200, 3, false);
		e.incorporarPaquete("Rosario", 80, 2, false);
		e.incorporarPaquete("Rosario", 250, 2, false);
		volumen = e.cargarTransporte("AA333XQ");
		System.out.println("Se cargaron " + volumen
		+" metros cubicos en el transp AA333XQ");
		e.iniciarViaje("AA333XQ");
		System.out.println("Costo del viaje:"
		+e.obtenerCostoViaje("AA333XQ"));
		System.out.println(e.toString());
		e.finalizarViaje("AA333XQ");
		System.out.println(e.toString());		
	}

	private String obtenerTransporteIgual(String matricula){
		return "";
	}
	private void finalizarViaje(String string) {
	}

	private void iniciarViaje(String string) {
	}

	private String obtenerCostoViaje(String string) {
		return null;
	}

	private double cargarTransporte(String string) {
		return 0;
	}

	private void incorporarPaquete(String string, int i, int j, boolean b) {
	}

	private void asignarDestino(String string, String string2) {
	}

	private void agregarFlete(String string, int i, int j, int k, int l, int m) {
	}

	private void agregarMegaTrailer(String string, int i, int j, boolean b, int k, int l, int m, int n) {
	}

	private void agregarTrailer(String string, int i, int j, boolean b, int k, int l) {
	}

	private void agregarDestino(String string, int i) {
	}
	
}

