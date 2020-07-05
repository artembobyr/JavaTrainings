package lesson3.task3;

import java.util.Scanner;

public class Task3_main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Input first number");
            double firstNumber = sc.nextDouble();
            System.out.println("Input second number");
            double secondNumber = sc.nextDouble();
            System.out.println("Input operator \n'+' - add\n'-' - subtract\n'*' - multiplication\n'/' - dividion");
            char chooseOperator = sc.next().charAt(0);
            double result = 0;
            switch (chooseOperator) {
                case '+':
                    result = Calculator.addition(firstNumber, secondNumber);
                    break;
                case '-':
                    result = Calculator.subrtaction(firstNumber, secondNumber);
                    break;
                case '*':
                    result = Calculator.multiplication(firstNumber, secondNumber);
                    break;
                case '/':
                    result = Calculator.division(firstNumber, secondNumber);
                    break;
                default:
                    System.out.println("You input wrong character");
                    break;
            }
            System.out.println("Result = " + result);
        }
    }
}
