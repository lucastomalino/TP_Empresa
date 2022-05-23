package EmpresaDeEnvios;

public class MegaTrailer extends Camion{
	private double costoFijo;
	
	public MegaTrailer(int id_, int cargaMaxima_, int capacidadMaxima_, boolean refrigerado_,
			double costoPorKm_, double costoFijo_) {
		super(id_, cargaMaxima_, capacidadMaxima_, refrigerado_, costoPorKm_);
		super.setviajesMayorA500(true);
		costoFijo = costoFijo_;
	}
	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

}
