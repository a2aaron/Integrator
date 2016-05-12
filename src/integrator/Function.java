package integrator;
import org.nfunk.jep.JEP;
/**
 * Write a description of class Function here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Function
{
    private String function;
    private JEP parser;
    
    public Function(String function)
    {
        this.function = function;
        
        parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
    }
    
    public double evaluate(double x)
    {
        parser.addVariable("x", x);
        parser.parseExpression(function);
        return parser.getValue();
    }
}
