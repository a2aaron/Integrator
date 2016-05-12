package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import integrator.Function;
import integrator.LeftHandRiemannSum;
import integrator.MidpointRiemannSum;
import integrator.RightHandRiemannSum;
import integrator.SimpsonsRule;
import integrator.TrapezoidalSum;


public class SimpleIntegratorTests {
	private static final double EPSILON = 0.01;

	@Test
	public void cubicIntergral() {
		//All three integrals should be reasonably close to -16.25
		Function cubic = new Function("x^3");
		LeftHandRiemannSum leftHand = new LeftHandRiemannSum(-3 ,2, 10000, cubic);
		RightHandRiemannSum rightHand = new RightHandRiemannSum(-3 ,2, 10000, cubic);
		MidpointRiemannSum midPoint = new MidpointRiemannSum(-3 ,2, 10000, cubic);
		TrapezoidalSum trapezoid = new TrapezoidalSum(-3 ,2, 10000, cubic);
		
		assertEquals(-16.25, leftHand.Integrate(), EPSILON);
		assertEquals(-16.25, rightHand.Integrate(), EPSILON);
		assertEquals(-16.25, trapezoid.Integrate(), EPSILON);
		assertEquals(-16.25, midPoint.Integrate(), EPSILON);
	}
	
	public void TrapezoidalSumIsAverage() {
		/* Since the LH and RH Riemann Sums are
	 	 * LH: (f(x_0) + f(x_1) ... f(x_n-1)) * deltaX
	   	 * RH: (f(x_1) ... f(x_n-1) + f(x_n)) * deltaX
	 	 * We can sum them, producing
	 	 * ( (f(x_0) + f(x_1)) + (f(x_1) + f(x_2)) ... ) * deltaX
	 	 * Which means that, since the Trapezoidal Sum is 
	 	 * ( (f(x_0) + f(x_1)) + (f(x_1) + f(x_2)) ... ) * deltaX / 2
	 	 * That LH + RH / 2 = Trapezoidal Sum
		 */ 
		Function function = new Function("ln(x)+e^x");
		LeftHandRiemannSum leftHand = new LeftHandRiemannSum(5, 7, 11, function);
		RightHandRiemannSum rightHand = new RightHandRiemannSum(5 ,7, 11, function);
		TrapezoidalSum trapezoid = new TrapezoidalSum(5 ,7, 11, function);

		double average = (leftHand.Integrate() + rightHand.Integrate()) / 2;
		assertEquals(trapezoid.Integrate(), average, EPSILON);
	}
	
	@Test
	public void simpsonsRuleTest() {
		Function cubic = new Function("x^3");
		SimpsonsRule simpsons = new SimpsonsRule(-3 ,2, 10000, cubic);
		assertEquals(-16.25, simpsons.Integrate(), EPSILON);
	}
	
	@Rule
	public ExpectedException simpsonsThrown = ExpectedException.none();
	
	@Test
	public void simpsonsRuleException() {
		Function cubic = new Function("x^3");
		simpsonsThrown.expect(RuntimeException.class);
		SimpsonsRule simpsons = new SimpsonsRule(-3 ,2, 5, cubic);
	}
	
	@Test
	public void simpsonsRuleFewIntervals() {
		Function quartic = new Function("(x^4) + x");
		SimpsonsRule simpsons = new SimpsonsRule(4 ,6, 4, quartic);
		assertEquals(1360.42, simpsons.Integrate(), EPSILON);
	}
	
	@Test
	public void simpsonRuleExact() {
		// Simpson's rule is exactly accurate on cubic equations.
		Function cubic = new Function("2*x^3 - 5*x^2 - 4");
		
		SimpsonsRule simpsons1 = new SimpsonsRule(-3 ,2, 2, cubic);
		SimpsonsRule simpsons2 = new SimpsonsRule(-3 ,2, 4, cubic);
		SimpsonsRule simpsons3 = new SimpsonsRule(-3 ,2, 6, cubic);
		
		double EPSILON = 1.0e-7;
		assertEquals(simpsons1.Integrate(), simpsons2.Integrate(), EPSILON);
		assertEquals(simpsons1.Integrate(), simpsons3.Integrate(), EPSILON);
	}
	
}
