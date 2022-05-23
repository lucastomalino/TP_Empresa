package EmpresaDeEnvios;

public class Flete extends Transporte{
	private int cantAcompaniantes;
	private double costoPorAcompaniante;
	public Flete(int id_, int cargaMaxima_, int capacidadMaxima_, boolean refrigerado_, double costoPorKm_, 
					int cantAcompaniantes_, double costoPorAcompaniante_) {
		super(id_, cargaMaxima_, capacidadMaxima_, refrigerado_, costoPorKm_);
		super.setSeguroDeCarga(false);
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

	
}
