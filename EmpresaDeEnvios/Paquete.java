package EmpresaDeEnvios;

public class Paquete {
	private int peso;
	private int volumen;
	private String destino;
	private boolean necesitaFrio;
	public Paquete(int peso_, int volumen_, String destino_, boolean necesitaFrio_) {
		setPeso(peso_);
		setVolumen(volumen_);
		setDestino(destino_);
		setNecesitaFrio(necesitaFrio_);
	}
	public Paquete() {}
	
	/* ---------- Getters and Setters ---------- */
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public boolean isNecesitaFrio() {
		return necesitaFrio;
	}
	public void setNecesitaFrio(boolean necesitaFrio) {
		this.necesitaFrio = necesitaFrio;
	}
	/* -------------------------------------------- */
	
	
}
