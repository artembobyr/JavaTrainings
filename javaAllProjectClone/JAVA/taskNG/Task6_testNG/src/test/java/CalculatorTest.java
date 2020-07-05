import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class CalculatorTest {
    private Calculator calculator = new Calculator();
    private static final double delta = 0.001;

    public void frame(double operator, double expectedNumber) {
        double actual = operator;
        double expected = expectedNumber;
        assertEquals(expected, actual, delta);
    }

    @Test
    public void addition() {
        frame(calculator.addition(5, 6), 11);
    }

    @Test
    public void subrtaction() {
        frame(calculator.subrtaction(8, 6), 2);
    }

    @Test
    public void multiplication() {
        frame(calculator.multiplication(5, 6), 30);
    }

    @Test
    public void division() {
        frame(calculator.division(6, 4), 1.5);
    }
}