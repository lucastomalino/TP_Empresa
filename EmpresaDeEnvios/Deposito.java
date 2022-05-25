package EmpresaDeEnvios;

import java.util.LinkedList;

public class Deposito {
	private boolean esRefrigerado;
	private LinkedList<Paquete> paquetes;
	private int capacidadMaxima;

	public Deposito(boolean esRefrigerado_, LinkedList<Paquete> paquetes_,
			int capacidadMaxima_) {
		esRefrigerado = esRefrigerado_;
		paquetes = paquetes_;
		capacidadMaxima = capacidadMaxima_; // Lo envia la empresa al crear el deposito
	}

	public Deposito(boolean esRefrigerado_,
			int capacidadMaxima_) {
		esRefrigerado = esRefrigerado_;
		paquetes = new LinkedList<Paquete>();
		capacidadMaxima = capacidadMaxima_; // Lo envia la empresa al crear el deposito
	}

	/* ----------- Getters and Setters ----------- */
	public boolean getesRefrigerado() {
		return esRefrigerado;
	}

	public void setEsRefrigerado(boolean esRefrigerado) {
		this.esRefrigerado = esRefrigerado;
	}

	public LinkedList<Paquete> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(LinkedList<Paquete> paquetes) {
		this.paquetes = paquetes;
	}

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}
	/* ---------------------------------------------- */

	/* ------- Metodos ------- */
	public boolean agregarPaquete(Paquete paquete) {
		boolean necesitaFrio = paquete.getNecesitaFrio() == getesRefrigerado();
		boolean hayEspacio = getCapacidadMaxima() - paquete.getVolumen() >= 0;
		if (necesitaFrio && hayEspacio) {
			paquetes.add(paquete);
			return true;
		}
		return false;
	}

	public Paquete quitarPaquete(Paquete paquete) {
		return new Paquete();
	}

}
