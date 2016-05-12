package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import integrator.Function;
import integrator.LeftHandRiemannSum;
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
		TrapezoidalSum trapezoid = new TrapezoidalSum(-3 ,2, 10000, cubic);
		
		assertEquals(-16.25, leftHand.Integrate(), EPSILON);
		assertEquals(-16.25, rightHand.Integrate(), EPSILON);
		assertEquals(-16.25, trapezoid.Integrate(), EPSILON);
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
}
