package EmpresaDeEnvios;

import java.util.LinkedList;

public abstract class Transporte {
	private String id; // Codigo de identificacion del camion
	private double cargaMaxima; // Representa el peso maxima que puede cargar
	private double cargaActual;
	private double capacidadMaxima; // Representa el espacio maximo disponible
	private double capacidadActual;
	private boolean refrigerado;
	private double costoPorKm;
	private boolean enViaje; // Representa si el transporte esta en viaje o no
	private LinkedList<Paquete> paquetes = new LinkedList<Paquete>(); // Representa los paquetes que hay en el transporte
	private String destino; // Representa el destino asignado al transporte
	private double distancia;

	public Transporte(String id_, double cargaMaxima_, double capacidadMaxima_, boolean refrigerado_,
			double costoPorKm_) {
		id = id_;
		cargaMaxima = cargaMaxima_;
		capacidadMaxima = capacidadMaxima_;
		refrigerado = refrigerado_;
		costoPorKm = costoPorKm_;
		setEnViaje(false); // En principio estan parados
		setDestino(null);
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

	public double getCapacidadActual() {
		return capacidadActual;
	}

	public void setCapacidadActual(double capacidadActual) {
		this.capacidadActual = capacidadActual;
	}

	public double getCargaMaxima() {
		return cargaMaxima;
	}

	public double getCargaActual() {
		return cargaActual;
	}

	public void setCargaMaxima(double cargaMaxima) {
		this.cargaMaxima = cargaMaxima;
	}

	public void setCargaActual(double carga) {
		this.cargaActual = carga;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public boolean agregarPaquete(Paquete paquete) {
		if (paquete == null) {
			return false;
		}
		if (paquete.getPeso() + cargaActual > cargaMaxima) {
			return false;
		}
		if (paquete.getVolumen() + capacidadActual > capacidadMaxima) {
			return false;
		}
		paquetes.add(paquete);
		cargaActual += paquete.getPeso();
		capacidadActual += paquete.getVolumen();
		return true;
	}

	public void vaciarCarga() {
		this.setCargaActual(0);
	}

	abstract public double calcularCosto();

	public void setkmArecorrer(double distancia) {
		this.distancia = distancia;
	}

	public double getkmArecorrer() {
		return this.distancia;
	}
	
	@Override
	public String toString(){
	String id = this.id;
	String transporte = "";
	if(this instanceof MegaTrailer){
		transporte = "MegaTrailer";
	}
	if(this instanceof TrailerComun){
		transporte = "TrailerComun";
	}
	if(this instanceof Flete){
		transporte = "Flete";
	}
	
	double cargaMaxima = this.cargaMaxima;
	double capacidadMaxima = this.capacidadMaxima;
	double cargaActual = this.cargaActual;
	double capacidadActual = this.capacidadActual;
	boolean enViaje = this.enViaje;
	double costoPorKm = this.costoPorKm;
	String destino = this.destino;
	double distancia = this.distancia;
	String frio = this.isRefrigerado() ? "Sí" : "No";
	
	String mostrar =  transporte + "\nID: " + id + ", Destino: " + destino + ", Distancia: " + distancia	+ ", Tiene frío: " + frio + "\n"
									+ "Capacidad máxima: " + capacidadMaxima + ", Capacidad actual: " + capacidadActual + "\n" 
									+ "Carga maxima: " + cargaMaxima + ", Carga actual: " + cargaActual + "\n"
									+ "En viaje: " + enViaje + ", Costo por KM: " + costoPorKm + ".\n";
	String paquetes = "";
	for(Paquete p: this.paquetes){
		paquetes = paquetes + "\n\t" + p.toString();
		}
	mostrar += paquetes;
	return mostrar;
	}

@Override
public boolean equals(Object o){
	if(o == null){
		return false;
	}
	Transporte other = null;
	if(this instanceof TrailerComun && o instanceof TrailerComun){
		other = (Transporte) o;
		return this.destino.equals(other.getDestino()) && this.cargaActual == other.getCargaActual();
	}
	if(this instanceof MegaTrailer && o instanceof MegaTrailer){
		other = (Transporte) o;
		return this.destino.equals(other.getDestino()) && this.cargaActual == other.getCargaActual();

	}
	if(this instanceof Flete && o instanceof Flete){
		other = (Transporte) o;
		return this.destino.equals(other.getDestino()) && this.cargaActual == other.getCargaActual();
	}
	return false;
}





}
