package EmpresaDeEnvios;

import java.util.LinkedList;

public abstract class Transporte {
	private String id;	// Codigo de identificacion del camion
	private double cargaMaxima; // Representa el peso maxima que puede cargar
	private double capacidadMaxima; // Representa el espacio maximo disponible
	private boolean refrigerado;
	private double costoPorKm;
	private double seguroDeCarga; // Agregado por tema de enunciado
	private boolean enViaje; // Representa si el transporte esta en viaje o no
	private LinkedList<Paquete> paquetes; // Representa los paquetes que hay en el transporte
	private String destino; // Representa el destino asignado al transporte
	private double distancia;

	public Transporte(String id_, double cargaMaxima_, double capacidadMaxima_
					, boolean refrigerado_, double costoPorKm_) {
		id = id_;
		cargaMaxima = cargaMaxima_;
		capacidadMaxima = capacidadMaxima_;
		refrigerado = refrigerado_;
		costoPorKm = costoPorKm_;
		seguroDeCarga = 0; // En principio tienen
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
	public double getCapacidadMaxima() {
		return capacidadMaxima;
	}
	public void setCapacidadMaxima(double capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	public double getCargaMaxima() {
		return cargaMaxima;
	}
	public void setCargaMaxima(double cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public double isSeguroDeCarga() {
		return seguroDeCarga;
	}

	public void setSeguroDeCarga(double segCarga) {
		this.seguroDeCarga = segCarga;
	}

	public LinkedList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(LinkedList<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	public boolean estaEnViaje() {
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
	abstract public double calcularCosto();
	public void setkmArecorrer(double distancia) {
		this.distancia = distancia;
	}
	public double getkmArecorrer(){
		return this.distancia;
	}
}
