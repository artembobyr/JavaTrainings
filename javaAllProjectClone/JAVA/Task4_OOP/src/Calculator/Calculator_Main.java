package Calculator;

import Calculator.Operators.Addition;
import Calculator.Operators.Division;
import Calculator.Operators.Multiplication;
import Calculator.Operators.Subtracting;

import java.util.Scanner;

public class Calculator_Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Input first number");
            double firstNumber = sc.nextDouble();
            System.out.println("Input second number");
            double secondNumber = sc.nextDouble();
            System.out.println("Input operator");
            char chooseOperator = sc.next().charAt(0);
            double result = 0;
            switch (chooseOperator) {
                case '+':
                    result = new Addition(firstNumber, secondNumber).add();
                    break;
                case '-':
                    result = new Subtracting(firstNumber, secondNumber).subtract();
                    break;
                case '*':
                    result = new Multiplication(firstNumber, secondNumber).multiply();
                    break;
                case '/':
                    result = new Division(firstNumber, secondNumber).divide();
                    break;
                default:
                    System.out.println("You input wrong character");
                    break;
            }
            System.out.println("Result = " + result);
        }
    }
}
