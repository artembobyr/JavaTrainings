package Calculator.Operators;

public class Subtracting extends SuperOperators{
    public Subtracting(double first_number, double second_number) {
        super(first_number, second_number);
    }

    public double subtract(){
        double result = getFirst_number() - getSecond_number();
        return result;
    }
}
