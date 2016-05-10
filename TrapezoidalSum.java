 

public class TrapezoidalSum extends GenericIntegrator {
    
    public TrapezoidalSum() {
        super();
    }
    
    public TrapezoidalSum(double start, double end, int numberOfIntervals, Function function) {
        super(start, end, numberOfIntervals, function);
    }
    
    public double Integrate(double start, double end, int numberOfIntervals, Function function) {
        return super.Integrate(start, end, numberOfIntervals, function);
    }
    
    public double specificSum(double x, Function function, double deltaX) {
        return (function.evaluate(x) + function.evaluate(x + deltaX)) * deltaX;
    }
}
