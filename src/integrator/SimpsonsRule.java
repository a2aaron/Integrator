package integrator;

public class SimpsonsRule extends GenericIntegrator {
	/*
	 * Simpson's Rule works by taking the integral of
	 */

	public SimpsonsRule() {
		super();
	}

	public SimpsonsRule(double start, double end, int numberOfIntervals, Function function){
		super(start, end, numberOfIntervals, function);
		if (numberOfIntervals % 2 == 1) {
			throw new RuntimeException("Simpson's Rule requires that the number of intervals be even.");
		}
	}
	public double specificSum(double x, Function function, double deltaX) {
		return function.evaluate(x) + 4 * function.evaluate(x + deltaX) + function.evaluate(x + 2 * deltaX);
	}

	@Override
	public double Integrate(double start, double end, int numberOfIntervals, Function function) {
		/* Simpson’s rule is (f(x_0) + 4 * f(x_1) + f(x_2)) * deltaX / 6
		 * Where x_0, x_1, and x_2 are all equally spaced from each other
		 * (x_0 + deltaX = x_1)
		 * If you want more slices, then you can repeat Simpson’s rule at
		 * x_2 -> x_4, x_4 -> x_6, and so on.
		 * Resulting in (f(x_0) + 4 * f(x_1) + 2f(x_2) + 4f(x_3) + 2f(x_4) . . . 
		 * + 2f(x_n-2) + 4f(x_n-1) + f(x_n)) * deltaX / 6
		 */

		if (numberOfIntervals % 2 == 1) {
			// Is this a good idea?
			throw new RuntimeException("Simpson's Rule requires that the number of intervals be even.");			
		}

		double deltaX = (end - start)/numberOfIntervals;
		double total = 0;
		for (double x = start; x < end; x += 2 * deltaX) {
			total += specificSum(x, function, deltaX);
		}
		// TODO: Investigate loss of precision stuff.
		// Do deltaX / 6 here because it reduces loss of precision
		return total * deltaX / 3;
	}
}
