import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select the logging level\ne - Error\nw - Warn\ni - Info\nd - Debug\n");
        char chooseLevel = sc.next().charAt(0);
        switch (chooseLevel) {
            case 'e':
                Configurator.setRootLevel(Level.ERROR);
                break;
            case 'w':
                Configurator.setRootLevel(Level.WARN);
                break;
            case 'i':
                Configurator.setRootLevel(Level.INFO);
                break;
            case 'd':
                Configurator.setRootLevel(Level.DEBUG);
                break;
            default:
                Configurator.setRootLevel(Level.ALL);
        }

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
