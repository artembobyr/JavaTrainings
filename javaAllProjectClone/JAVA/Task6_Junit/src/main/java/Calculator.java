public class Calculator {
    public double addition(double x, double y) {
        double result = 0;
        result = x + y;
        //System.out.println(result);
        return result;
    }

    public double subrtaction(double x, double y) {
        double result = 0;
        result = x - y;
        System.out.println(result);
        return result;
    }

    public double multiplication(double x, double y) {
        double result = 0;
        result = x * y;
        System.out.println(result);
        return result;
    }

    public double division(double x, double y) {
        double result = 0;
        try {
            if (y == 0) {
                throw new ArithmeticException();
            } else {
                result = x / y;
                System.out.println(result);
            }
        } catch (ArithmeticException e) {
            System.out.println("Divide on zero");
        }

        return result;
    }
}
