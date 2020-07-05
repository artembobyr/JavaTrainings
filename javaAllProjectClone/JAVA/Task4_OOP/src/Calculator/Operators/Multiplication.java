package Calculator.Operators;

public class Multiplication extends SuperOperators {
    public Multiplication(double first_number, double second_number) {
        super(first_number, second_number);
    }

    public double multiply() {
        double result = getFirst_number() * getSecond_number();
        return result;
    }
}
