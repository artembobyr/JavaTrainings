import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class Task2CalculatorTest {
    private final Task2Calculator calculator = new Task2Calculator();
    private static final double delta = 0.001;

    public void frame(double operator, double expectedNumber) {
        double actual = operator;
        double expected = expectedNumber;
        assertEquals(expected, actual, delta);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void addEmptyString() {
        frame(calculator.addTwoNumbers(""), 0);
    }

    @Test
    public void addStringWithOneNumber() {
        frame(calculator.addTwoNumbers("5"), 5);
    }

    @Test
    public void addStringWithTwoNumbers() {
        frame(calculator.addTwoNumbers("1,2"), 3);
    }

    @Test
    public void addStringWithMoreThan2Numbers() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("not more than 2 numbers allowed");
        frame(calculator.addTwoNumbers("1,2,3"), 0);
    }

    @Test
    public void addStringWithNumberAndLetter() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("For input string");
        frame(calculator.addTwoNumbers("1,X"), 0);
    }

    @Test
    public void addUnknownAmountOfNumbers() throws Exception {
        frame(calculator.addUnknownAmount("1,3, 5, 7, 11"), 1 + 3 + 5 + 7 + 11);
    }

    @Test
    public void addNewLineBetweenNumbers() throws Exception {
        frame(calculator.addUnknownAmount("1 \n2,3"), 1 + 2 + 3);
    }

    @Test
    public void addNewDelimeter() throws Exception {
        frame(calculator.addUnknownAmount("//;\n1;2"), 1 + 2);
    }

    @Test
    public void addNegativeNumber() throws Exception {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("negatives not allowed[-3.0, -5.0]");
        frame(calculator.addUnknownAmount("1,2,-3,4,-5"), 1 + 2 + 4);
    }

    @Test
    public void addIgnoreMoreThan1000() throws Exception {
        frame(calculator.addUnknownAmount("//;1001\n1;2"), 1 + 2);
    }

    @Test
    public void addDelimeterAnyLength() throws Exception {
        frame(calculator.addAnyDelimeter("//[***]\n1***2***3"), 1 + 2 + 3);
    }

    @Test
    public void addMultiplyDelimeters() throws Exception {
        frame(calculator.addAnyDelimeter("//[*&^$][%]\\n1*rg2%3"), 1 + 2 + 3);
    }

}
