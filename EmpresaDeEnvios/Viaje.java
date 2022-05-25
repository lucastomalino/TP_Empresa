package EmpresaDeEnvios;

public class Viaje {
	private String destino;
	private Integer distancia; // distancia > 0;

	public Viaje(String destino_, int distancia_) {
		destino = destino_;
		distancia = distancia_;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	// Metodos auxiliares
	public boolean mismoDestino(String destino) {
		return this.destino.equals(destino);
	}
}
