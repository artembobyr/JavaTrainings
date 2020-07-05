import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Calculator {
    final static Logger logger = LogManager.getLogger(Calculator.class);

    public static double addition(double x, double y) {
        logger.warn("May be mistake with big numbers");
        double result = 0;
        result = x + y;
        logger.info("Numbers are added");
        return result;
    }

    public static double subrtaction(double x, double y) {
        logger.warn("May be mistake with big numbers");
        double result = 0;
        logger.debug("variable 'result' is '0'");
        result = x - y;
        logger.info("Numbers are subrtacted");
        return result;
    }

    public static double multiplication(double x, double y) {
        logger.warn("May be mistake with big numbers");
        double result = 0;
        result = x * y;
        logger.info("Numbers are multiplicated");
        return result;
    }

    public static double division(double x, double y) {
        logger.warn("May be mistake with big numbers and dividion on zero");
        double result = 0;
        try {
            if (y == 0) {
                throw new ArithmeticException();
            } else {
                result = x / y;
                logger.info("Numbers are divided");
            }
        } catch (ArithmeticException e) {
            logger.error("Divide on zero");
        }
        return result;
    }
}
