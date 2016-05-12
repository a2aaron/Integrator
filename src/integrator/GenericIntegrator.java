package integrator;

public abstract class GenericIntegrator {
	private double start;
	private double end;
	private int numberOfIntervals;

	// Below is a lambda function and represents f(x).
	// Syntax should be:
	// Function function = (x) -> {return x*x*x/3};
	// Replace x*x*x/3 with your own functions.
	private Function function;
	// This is the function used to sum your 
	// partial sums. For example. Left hand Riemann Summation should be
	// deltaSum = (x, function) -> {return function(x) * deltaX}
	
	public GenericIntegrator() { }

	public GenericIntegrator(double start, double end, int numberOfIntervals, Function function) {
		this.start = start;
		this.end = end;
		this.numberOfIntervals = numberOfIntervals;
		this.function = function;
	}

	public double Integrate(){
		return Integrate(start, end, numberOfIntervals, function);
	}

	public double Integrate(double start, double end, int numberOfIntervals) {
		return Integrate(start, end, numberOfIntervals, function);
	}
	
	public double Integrate(double start, double end, int numberOfIntervals, Function function) {
		double deltaX = (end - start)/numberOfIntervals;
		double total = 0;
		for (double x = start; x < end; x += deltaX) {
			total += this.specificSum(x, function, deltaX);
		}
		return total;
	}
	
	public abstract double specificSum(double x, Function function, double deltaX);
}
