package EmpresaDeEnvios;

public class TrailerComun extends Camion {
	public TrailerComun(String id_, double cargaMax, double capacidad, boolean refrigerado_, double costoPorKm_) {
		super(id_, cargaMax, capacidad, refrigerado_, costoPorKm_);
	}

	@Override
	public double calcularCosto() {
		return getCostoPorKM() * getkmArecorrer() + getSeguroDeCarga();
	}

}
