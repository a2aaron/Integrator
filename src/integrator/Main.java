package integrator;

public class Main {

    public static void main(String[] args) {
        Function cubic = new Function("x");
        TrapezoidalSum integrator = new TrapezoidalSum();
        
        
        // Example of integration with a different number of intervals..
        for (int i = 90000; i < 100000; i += 1) {
            double result = integrator.Integrate(-3, 2, i, cubic);
            System.out.println("number of intervals: " + i + " | value: " + (result));
        }
        // The integral of x^3 from -3 to 2 is exactly -16.25
    }
}
