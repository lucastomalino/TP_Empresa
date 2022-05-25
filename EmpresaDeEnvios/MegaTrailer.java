package EmpresaDeEnvios;

public class MegaTrailer extends Camion {
	private double costoFijo;
	private double costoComida;

	public MegaTrailer(String id_, double cargaMaxima_, double capacidadMaxima_, boolean refrigerado_,
			double costoPorKm_, double costoFijo_, double costoComida_) {
		super(id_, cargaMaxima_, capacidadMaxima_, refrigerado_, costoPorKm_);
		super.setviajesMayorA500(true);
		costoFijo = costoFijo_;
		costoComida = costoComida_;
	}

	@Override
	public double calcularCosto() {
		return getkmArecorrer() * getCostoPorKM() + costoComida + costoFijo + getSeguroDeCarga();
	}

	// public double getCostoFijo() {
	// return costoFijo;
	// }

	// public void setCostoFijo(double costoFijo) {
	// this.costoFijo = costoFijo;
	// }

}
