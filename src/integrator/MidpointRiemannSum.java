package integrator;

public class MidpointRiemannSum extends GenericIntegrator {
	
	public MidpointRiemannSum() {
		super();
	}
	
	public MidpointRiemannSum(double start, double end, int numberOfIntervals, Function function) {
		super(start, end, numberOfIntervals, function);
	}
	
	public double Integrate(double start, double end, int numberOfIntervals, Function function) {
		return super.Integrate(start, end, numberOfIntervals, function);
	}
	
	public double specificSum(double x, Function function, double deltaX) {
	    return function.evaluate(x + (deltaX / 2)) * deltaX;
	}
}
