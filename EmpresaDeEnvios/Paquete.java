package EmpresaDeEnvios;

public class Paquete {
	private double peso;
	private double volumen;
	private String destino;
	private boolean necesitaFrio;
	public Paquete(String destino_, double peso_, double volumen_, boolean necesitaFrio_) {
		setPeso(peso_);
		setVolumen(volumen_);
		setDestino(destino_);
		setNecesitaFrio(necesitaFrio_);
	}
	public Paquete() {}
	
	/* ---------- Getters and Setters ---------- */
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso_) {
		this.peso = peso_;
	}
	public double getVolumen() {
		return volumen;
	}
	public void setVolumen(double volumen_) {
		this.volumen = volumen_;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public boolean getNecesitaFrio() {
		return necesitaFrio;
	}
	public void setNecesitaFrio(boolean necesitaFrio) {
		this.necesitaFrio = necesitaFrio;
	}
	/* -------------------------------------------- */
	
	
}
