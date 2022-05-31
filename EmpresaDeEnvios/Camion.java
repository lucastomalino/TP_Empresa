package EmpresaDeEnvios;

public abstract class Camion extends Transporte {
	private double seguroDeCarga;
	// private boolean viajesMayorA500;

	public Camion(String id_, double cargaMaxima_, double capacidadMaxima_, boolean refrigerado_, double costoPorKm_) {
		super(id_, cargaMaxima_, capacidadMaxima_, refrigerado_, costoPorKm_);
		// viajesMayorA500 = false;
	}

	// public boolean getviajaAmasDe500km() {
	// 	return viajesMayorA500;
	// }

	// void setviajesMayorA500(boolean a) {
	// 	this.viajesMayorA500 = a;
	// }

	public void setSeguroDeCarga(double segCarga) {
		seguroDeCarga = segCarga;
	}

	public double getSeguroDeCarga() {
		return seguroDeCarga;
	}
}
