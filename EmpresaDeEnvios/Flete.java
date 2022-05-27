package EmpresaDeEnvios;

public class Flete extends Transporte {
	private int cantAcompaniantes;
	private double costoPorAcompaniante;

	public Flete(String id_, double cargaMaxima_, double capacidadMaxima_, double costoPorKm_,
			int cantAcompaniantes_, double costoPorAcompaniante_) {
		super(id_, cargaMaxima_, capacidadMaxima_, false, costoPorKm_);
		setCantAcompaniantes(cantAcompaniantes_);
		setCostoPorAcompaniante(costoPorAcompaniante_);

	}

	public int getCantAcompaniantes() {
		return cantAcompaniantes;
	}

	public void setCantAcompaniantes(int cantAcompaniantes) {
		this.cantAcompaniantes = cantAcompaniantes;
	}

	public double getCostoPorAcompaniante() {
		return costoPorAcompaniante;
	}

	public void setCostoPorAcompaniante(double costoPorAcompaniante) {
		this.costoPorAcompaniante = costoPorAcompaniante;
	}

	@Override
	public double calcularCosto() {
		return getkmArecorrer() * getCostoPorKM() + cantAcompaniantes * costoPorAcompaniante;
	}

}
