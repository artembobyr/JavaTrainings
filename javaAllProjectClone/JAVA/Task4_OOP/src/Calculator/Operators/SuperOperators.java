package Calculator.Operators;

public class SuperOperators {
    private final double first_number;
    private final double second_number;

    public double getFirst_number() {
        return first_number;
    }

    public double getSecond_number() {
        return second_number;
    }

    protected SuperOperators(double first_number, double second_number) {
        this.first_number = first_number;
        this.second_number = second_number;
    }
}
