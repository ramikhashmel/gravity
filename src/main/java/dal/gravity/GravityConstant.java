package dal.gravity;

public class GravityConstant implements GravityModel {
	private double g;
	
	public GravityConstant(double g) {
		this.g= g;
	}
	
	@Override
	public double getGravitationalField() {
		return g;
	}
}
