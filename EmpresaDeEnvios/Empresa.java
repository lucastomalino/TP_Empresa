package EmpresaDeEnvios;


    import java.util.HashSet;
	import java.util.LinkedList;

public class Empresa {
	private String CUIT;
	private String NOMBRE;
	private int capacidadDeposito;

	// Objetos que maneja la empresa:
	private LinkedList<Viaje> destinos; // Un viaje puede ser igual a otro pero se le asigna otro transporte
	private LinkedList<Deposito> depositos;
	private HashSet<Transporte> transportes;
	
	public Empresa(String cuit, String nombre, int capacidad) {
		CUIT = cuit;
		NOMBRE = nombre;
		capacidadDeposito = capacidad; // La capacidad no puede ser negativa. cant de Paquetes
		
		// Agregar depositos.
		Deposito d1 = new Deposito(true, capacidad); // Deposito frio
		Deposito d2 = new Deposito(false, capacidad); // Deposito no frio
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
		return destinos;
	}

	public void setViajes(LinkedList<Viaje> viajes) {
		destinos = viajes;
	}

	public HashSet<Transporte> getTransportes() {
		return transportes;
	}

	public void setTransportes(HashSet<Transporte> transportes) {
		this.transportes = transportes;
	}
	
	/*---------------------------------------*/

	// Metodos de Test
	public void agregarDestino(String destino, int km) {
		destinos.add(new Viaje(destino,km)); 	// Agrega un viaje a la linkedlist destinos
	}

	public void agregarTrailer(String matricula, double cargaMax,
	double capacidad, boolean tieneRefrigeracion,
	double costoKm, double segCarga) {
		Transporte tc = new TrailerComun(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm);
		tc.setSeguroDeCarga(segCarga); // Consultar
		transportes.add(tc); // Agrega el trailer al hashSet de transportes
	}

	public void agregarMegaTrailer(String matricula, double cargaMax,
	double capacidad, boolean tieneRefrigeracion,
	double costoKm, double segCarga, double costoFijo,
	double costoComida) {
		Transporte mt = new MegaTrailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm,costoFijo,costoComida);
		mt.setSeguroDeCarga(segCarga);
		transportes.add(mt); // Agrega el trailer al hashSet de transportes
	}

	public void agregarFlete(String matricula, double cargaMax, double capacidad,
	double costoKm, int cantAcompaniantes,
	double costoPorAcompaniante) {
		Transporte f = new Flete(matricula, cargaMax, capacidad, costoKm, cantAcompaniantes, costoPorAcompaniante);
		transportes.add(f);
	}

	public void asignarDestino(String matricula, String destino) {
		Viaje vAux = new Viaje("1",-10);
		for(Viaje v: destinos){
			if(v.getDestino().equals(destino)){
				vAux = v;
				break;
			}
		}
		if(!vAux.getDestino().equals(destino)){
			throw new RuntimeException("El destino no fue agregado");
		}else{
			for(Transporte t: transportes){
				if(t.getId().equals(matricula) && t.getDestino() == null){
					t.setDestino(destino);
					t.setkmArecorrer(vAux.getDistancia());
					return;
				}
			}
		}
		throw new RuntimeException("No se encontro transporte disponible");
	}

	public boolean incorporarPaquete(String destino, double peso, double volumen,
 	boolean necesitaRefrigeracion){
		Paquete p = new Paquete(destino, peso, volumen, necesitaRefrigeracion);
		return depositos.get(0).agregarPaquete(p) || 
			   depositos.get(1).agregarPaquete(p); 
			   /* Si necesita frio se agrega al deposito[0] sino al deposito[1],
			    va a retornar en false únicamente si no hay espacio en el deposito determinado. */
 }
	public double cargarTransporte(String matricula) {
		
		return 0;
	}

	public void iniciarViaje(String matricula) {
		for(Transporte t: transportes){
			boolean tieneMercaderia = t.getCargaMaxima() > 0;
			boolean noEstaEnViaje = !t.estaEnViaje();
			boolean mismaMatricula = t.getId().equals(matricula);
			if(mismaMatricula){
				if(tieneMercaderia && noEstaEnViaje){
					t.setEnViaje(true);
				}else{ 
					throw new RuntimeException("No se puede iniciar el viaje");       
				}
			}	
	}}

	public void finalizarViaje(String matricula) {
		for (Transporte t : transportes) {
			boolean noEstaEnViaje = !t.estaEnViaje();	
			boolean mismaMatricula = t.getId().equals(matricula);
			if (mismaMatricula){
				if(noEstaEnViaje){
					throw new RuntimeException("El transporte se encuentra en viaje");
				}else{
					t.setEnViaje(false); t.setDestino(null); t.setCargaMaxima(0);
				}
			}
		}
	}

	public double obtenerCostoViaje(String matricula) {
		for (Transporte t : transportes) {
			boolean noEstaEnViaje = !t.estaEnViaje();	
			boolean mismaMatricula = t.getId().equals(matricula);
			if(mismaMatricula){
				if(noEstaEnViaje){
					throw new RuntimeException("No esta en viaje");
				} else{
					return t.calcularCosto();
				}
			} 
		}
		return 0;	
	}
	public String obtenerTransporteIgual(String matricula){
		return "";
	}
	
	/*---------------------------------------*/

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
		+ e.obtenerCostoViaje("AA333XQ"));
		System.out.println(e.toString());
		e.finalizarViaje("AA333XQ");
		System.out.println(e.toString());		
	}



	
}

