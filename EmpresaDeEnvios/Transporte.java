package EmpresaDeEnvios;

import java.util.LinkedList;

public abstract class Transporte {
	private int id;	// Codigo de identificacion del camion
	private int cargaMaxima; // Representa el peso maxima que puede cargar
	private int capacidadMaxima; // Representa el espacio maximo disponible
	private boolean refrigerado;
	private double costoPorKm;
	private boolean seguroDeCarga; // Agregado por tema de enunciado
	private boolean enViaje; // Representa si el transporte esta en viaje o no
	private LinkedList<Paquete> paquetes; // Representa los paquetes que hay en el transporte
	private String destino; // Representa el destino asignado al transporte

	public Transporte(int id_, int cargaMaxima_, int capacidadMaxima_
					, boolean refrigerado_, double costoPorKm_) {
		id = id_;
		cargaMaxima = cargaMaxima_;
		capacidadMaxima = capacidadMaxima_;
		refrigerado = refrigerado_;
		costoPorKm = costoPorKm_;
		seguroDeCarga = true; // En principio tienen
		LinkedList<Paquete> paquetes = new LinkedList<Paquete>(); // En principio esto a a estar vacio
		setEnViaje(false);   // En principio estan parados		
		setDestino("");
	}
	
	/*------------ Getters and Setters ---------*/
	
	public double getCostoPorKM() {
		return costoPorKm;
	}
	public void setCostoPorKM(double costoPorKM) {
		this.costoPorKm = costoPorKM;
	}
	public boolean isRefrigerado() {
		return refrigerado;
	}
	public void setRefrigerado(boolean refrigerado) {
		this.refrigerado = refrigerado;
	}
	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}
	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	public int getCargaMaxima() {
		return cargaMaxima;
	}
	public void setCargaMaxima(int cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isSeguroDeCarga() {
		return seguroDeCarga;
	}

	public void setSeguroDeCarga(boolean seguroDeCarga) {
		this.seguroDeCarga = seguroDeCarga;
	}

	public LinkedList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(LinkedList<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	public boolean isEnViaje() {
		return enViaje;
	}

	public void setEnViaje(boolean enViaje) {
		this.enViaje = enViaje;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	/* ----------------------------------------------- */
	
	/* ------ Metodos ------ */
	public void agregarPaquete(Paquete paquete){} 
	public void vaciarCarga(){}

	
}
