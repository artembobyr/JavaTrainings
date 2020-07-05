package Calculator.Operators;

public class Division extends SuperOperators {

    public Division(double first_number, double second_number) {
        super(first_number, second_number);
    }

    public double divide() {
        double result = 0;
        try {
            if (getSecond_number() == 0) {
                throw new ArithmeticException();
            } else {
                result = getFirst_number() / getSecond_number();
            }
            return result;
        } catch (ArithmeticException e) {
            System.out.println("Divide on 0");
            return 0;
        }
    }
}
