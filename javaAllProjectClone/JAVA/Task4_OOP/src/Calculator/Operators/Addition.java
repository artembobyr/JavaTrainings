package Calculator.Operators;

public class Addition extends SuperOperators {

    public Addition(double first_number, double second_number) {
        super(first_number, second_number);
    }

    public double add(){
        double result = getFirst_number() + getSecond_number();
        return result;
    }
}
