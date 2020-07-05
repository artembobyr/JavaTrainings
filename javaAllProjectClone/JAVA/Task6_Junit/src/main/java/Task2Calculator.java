import java.util.ArrayList;

public class Task2Calculator {
    public double addTwoNumbers(String str) {
        String[] numbers = str.split(",");
        double sum = 0;
        if (numbers.length > 2) {
            throw new RuntimeException("not more than 2 numbers allowed");
        } else {
            {
                for (String s : numbers) {
                    if (!s.isEmpty())
                        sum += Double.parseDouble(s);
                }
            }
        }
        return sum;
    }

    public double addUnknownAmount(String str) {
        if(str.startsWith("//")){
            str = str.substring(2);
        }
        String[] numbers = str.split("[,\n;]");
        ArrayList<Double> negativeNumbers = new ArrayList<Double>();
        double sum = 0;
        for (String s : numbers) {
            if (!s.isEmpty()) {
                double number = Double.parseDouble(s);
                if(number>1000){
                    number = 0;
                }
                else if (number < 0) {
                    negativeNumbers.add(number);
                } else {
                    sum += number;
                }
            }
        }
        if(negativeNumbers.size() > 0){
            throw new RuntimeException("negatives not allowed" + negativeNumbers.toString());
        }
        return sum;
    }public double addAnyDelimeter (String str) {

        char[] numbers = str.toCharArray();

        double sum = 0;
        for (char c :numbers) {
            if(c > 47 && c < 58){
                double number = Double.parseDouble(c+ "");
                sum+=number;
            }
        }
        return sum;
    }
}
