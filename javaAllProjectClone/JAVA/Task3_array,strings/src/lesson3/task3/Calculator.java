package lesson3.task3;

public class Calculator {
    public static double addition(double x, double y) {
        double result = 0;
        result = x + y;
        return result;
    }

    public static double subrtaction(double x, double y) {
        double result = 0;
        result = x - y;
        return result;
    }

    public static double multiplication(double x, double y) {
        double result = 0;
        result = x * y;
        return result;
    }

    public static double division(double x, double y) {
        double result = 0;
        try {
            if (y == 0) {
                throw new ArithmeticException();
            } else {
                result = x / y;
            }
        } catch (ArithmeticException e) {
            System.out.println("No divide by 0");
        }
        return result;
    }
}
