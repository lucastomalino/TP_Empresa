package EmpresaDeEnvios;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Empresa {
	private String CUIT;
	private String NOMBRE;
	private int capacidadDeposito;

	// Objetos que maneja la empresa:
	private Set<Viaje> destinos = new HashSet<Viaje>();
	private LinkedList<Deposito> depositos = new LinkedList<Deposito>();
	private HashSet<Transporte> transportes = new HashSet<Transporte>();

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

	public void setCUIT(String cuit) {
		CUIT = cuit;
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

	public HashSet<Viaje> getViajes() {
		return (HashSet<Viaje>) destinos;
	}

	public void setViajes(HashSet<Viaje> viajes) {
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
		for (Viaje v : destinos) {
			if (v.getDestino().equals(destino)) {
				throw new RuntimeException("El destino no se puede repetir");
			}
		}
		destinos.add(new Viaje(destino, km));
	}

	public void agregarTrailer(String matricula, double cargaMax,
			double capacidad, boolean tieneRefrigeracion,
			double costoKm, double segCarga) {
		Transporte tc = new TrailerComun(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm);
		((Camion) tc).setSeguroDeCarga(segCarga);
		transportes.add(tc); // Agrega el trailer al hashSet de transportes
	}

	public void agregarMegaTrailer(String matricula, double cargaMax,
			double capacidad, boolean tieneRefrigeracion,
			double costoKm, double segCarga, double costoFijo,
			double costoComida) {
		Transporte mt = new MegaTrailer(matricula, cargaMax, capacidad, tieneRefrigeracion, costoKm, costoFijo,
				costoComida);
		((Camion) mt).setSeguroDeCarga(segCarga);
		transportes.add(mt); // Agrega el trailer al hashSet de transportes
	}

	public void agregarFlete(String matricula, double cargaMax, double capacidad,
			double costoKm, int cantAcompaniantes,
			double costoPorAcompaniante) {
		Transporte f = new Flete(matricula, cargaMax, capacidad, costoKm, cantAcompaniantes, costoPorAcompaniante);
		transportes.add(f);
	}

	public void asignarDestino(String matricula, String destino) {
		Viaje vAux = new Viaje("1", -10);
		for (Viaje v : destinos) {
			if (v.getDestino().equals(destino)) {
				vAux = v;
				break;
			}
		}
		if (!vAux.getDestino().equals(destino)) {
			throw new RuntimeException("El destino no fue agregado");
		} else {
			for (Transporte t : transportes) {
				if (t.getId().equals(matricula) && t.getDestino() == null) {
					t.setDestino(destino);
					t.setkmArecorrer(vAux.getDistancia());
					return;
				}
			}
		}
		throw new RuntimeException("No se encontro transporte disponible");
	}

	public boolean incorporarPaquete(String destino, double peso, double volumen,
			boolean necesitaRefrigeracion) {
		Paquete p = new Paquete(destino, peso, volumen, necesitaRefrigeracion);
		return depositos.get(0).agregarPaquete(p) ||
				depositos.get(1).agregarPaquete(p);
		/*
		 * Si necesita frio se agrega al deposito[0] sino al deposito[1],
		 * va a retornar en false únicamente si no hay espacio en el deposito
		 * determinado.
		 */
	}

	public double cargarTransporte(String matricula) {
		Transporte transporte = null;
		String destinoTransporte = "";
		Integer tieneFrio = 0;
		double volumenCargado = 0;
		LinkedList<Paquete> paquetesAmeter = new LinkedList<Paquete>();

		/*
		 * Deposito 0 : tiene frio
		 * Deposito 1 : no tiene frio
		 */

		for (Transporte t : transportes) {
			boolean mismaMatricula = t.getId().equals(matricula);
			if (mismaMatricula) {
				/*
				 * Paquete: necesitaFrio - peso - volumen - destino
				 * Transporte: refrigerado - carga - capacidad - destino
				 */
				transporte = t;
				destinoTransporte = transporte.getDestino();
				tieneFrio = transporte.isRefrigerado() ? 0 : 1;
			}
		}
		if (transporte != null) {
			for (Paquete p : depositos.get(tieneFrio).getPaquetes()) {
				if (p.getDestino().equals(destinoTransporte)) {
					boolean entro = transporte.agregarPaquete(p);
					if (entro) {
						volumenCargado += p.getVolumen();
						paquetesAmeter.add(p);
					}

				}
			}

			for (Paquete p : paquetesAmeter) {
				depositos.get(tieneFrio).getPaquetes().remove(p);
			}
		}
		return volumenCargado;
	}

	public void iniciarViaje(String matricula) {
		for (Transporte t : transportes) {
			boolean tieneMercaderia = t.getCargaActual() > 0;
			boolean noEstaEnViaje = !t.estaEnViaje();
			boolean mismaMatricula = t.getId().equals(matricula);
			if (mismaMatricula) {
				if (tieneMercaderia && noEstaEnViaje) {
					t.setEnViaje(true);
					return;
				} else {
					throw new RuntimeException("No se puede iniciar el viaje");
				}
			}
		}
		
		throw new RuntimeException("No esta el transporte");
	}

	public void finalizarViaje(String matricula) {
		for (Transporte t : transportes) {
			boolean noEstaEnViaje = !t.estaEnViaje();
			boolean mismaMatricula = t.getId().equals(matricula);
			if (mismaMatricula) {
				if (noEstaEnViaje) {
					throw new RuntimeException("El transporte se encuentra en viaje");
				} else {
					t.setEnViaje(false);
					t.setDestino(null);
					t.vaciarCarga();
					;
				}
			}
		}
	}

	public double obtenerCostoViaje(String matricula) {
		for (Transporte t : transportes) {
			boolean noEstaEnViaje = !t.estaEnViaje();
			boolean mismaMatricula = t.getId().equals(matricula);
			if (mismaMatricula) {
				if (noEstaEnViaje) {
					throw new RuntimeException("No esta en viaje");
				} else {
					return t.calcularCosto();
				}
			}
		}
		return 0;
	}

	public String obtenerTransporteIgual(String matricula) {
		/*
			Mismo tipo - Misma Carga - Mismo destino
		*/
		Transporte transporte = null;
		Transporte mismoTransporte = null;

		for(Transporte t: transportes){
			if(t.getId().equals(matricula)){
				transporte = t;
			}
		}
		if(transporte != null){
			for(Transporte t2: transportes){
				if(transporte.equals(t2)){
					mismoTransporte = t2;
					return mismoTransporte.getId();
				}
			}
		}	
		return null;
	}
	
	// Metodo toString
	@Override 
	public String toString(){
		return mostrarDepositosYtransportes();
	}

	private String mostrarDepositosYtransportes(){
		StringBuilder mostrar = new StringBuilder("");

		mostrar.append(this.getNOMBRE() + "\n" 
		+ "Cantidad de depositos: " + depositos.size()  + " | Capacidad: " + this.getCapacidadDeposito() + "\n" 
		+ "Cantidad de transportes: " + transportes.size() + "\n\n" 
		+ "------------------Depositos------------------\n");
			
		for(Deposito d: depositos){
			mostrar.append(d.toString() + "\n\n") ;
		}
		
		mostrar.append( "----------------Transportes-------------------\n");
		for(Transporte t: transportes){
			mostrar.append(t.toString() + "\n\n");
		}
		
		return mostrar.toString();
	}

	/*---------------------------------------*/

	public static void main(String[] args) {
		double volumen;
		Empresa e = new Empresa("30112223334", "Expreso Libre", 40000);
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
		System.out.println("Se cargaron " + volumen	+ " metros cubicos en el transp AA333XQ");
		e.iniciarViaje("AA333XQ");
		System.out.println("Costo del viaje:" + e.obtenerCostoViaje("AA333XQ"));
		System.out.println(e.toString());
		e.finalizarViaje("AA333XQ");
		System.out.println(e.toString());


	}

		
}