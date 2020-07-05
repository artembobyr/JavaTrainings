import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;


public class Task2CalculatorTest {

    private Task2Calculator calculator = new Task2Calculator();
    private static final double delta = 0.001;


    public void frame(double operator, double expectedNumber) {
        double actual = operator;
        System.out.println(actual);
        double expected = expectedNumber;
        System.out.println(expected);
        assertEquals(expected, actual, delta);
    }

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
        frame(calculator.addTwoNumbers("1,2"), 1 + 2);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void addStringWithMoreThan2Numbers() {
        frame(calculator.addTwoNumbers("1,2,3"), 0);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void addStringWithNumberAndLetter() {
        frame(calculator.addTwoNumbers("1,X"), 0);
    }

    @Test
    public void addUnknownAmountOfNumbers() {
        frame(calculator.addUnknownAmount("1,3, 5, 7, 11"), 1 + 3 + 5 + 7 + 11);
    }

    @Test
    public void addNewLineBetweenNumbers() {
        frame(calculator.addUnknownAmount("1 \n2,3"), 1 + 2 + 3);
    }

    @Test
    public void addNewDelimeter(){
        frame(calculator.addUnknownAmount("//;\n1;2"), 1 + 2);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void addNegativeNumber(){
        frame(calculator.addUnknownAmount("1,2,-3,4,-5"), 1 + 2 + 4);
    }

    @Test
    public void addIgnoreMoreThan1000(){
        frame(calculator.addUnknownAmount("//;1001\n1;2"), 1 + 2);
    }

    @Test
    public void addDelimeterAnyLength(){
        frame(calculator.addAnyDelimeter("//[***]\n1***2***3"), 1 + 2 + 3);
    }

    @Test
    public void addMultiplyDelimeters(){
        frame(calculator.addAnyDelimeter("//[*&^$][%]\\n1*rg2%3"), 1 + 2 + 3);
    }

}
