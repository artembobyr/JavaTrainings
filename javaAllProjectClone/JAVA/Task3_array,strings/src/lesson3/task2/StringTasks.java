package lesson3.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class StringTasks {

    private final ArrayList<String> linesWords = new ArrayList<>();

    public void inputLinesOrWords(int n, boolean lines) {
        Scanner in = new Scanner(System.in);
        System.out.println("Fill lines: ");
        for (int i = 0; i < n; i++) {
            if (lines) {
                linesWords.add(in.nextLine());
            } else {
                linesWords.add(in.next());
            }
        }
    }

    private int lookingForAverage() {
        int average = 0;
        int sum = 0;
        for (String s : linesWords) {
            sum += s.length();
        }
        average = Math.round(sum / linesWords.size());
        return average;
    }

    public void getLengthLines_1() {
        int maxLength = linesWords.get(0).length();
        int minLength = linesWords.get(0).length();
        int indexMax = 0;
        int indexMin = 0;
        for (int i = 0; i < linesWords.size(); i++) {
            if (maxLength < linesWords.get(i).length()) {
                maxLength = linesWords.get(i).length();
                indexMax = i;
            }
            if (minLength > linesWords.get(i).length()) {
                minLength = linesWords.get(i).length();
                indexMin = i;
            }
        }
        System.out.printf("The longest line is '%s' and it contains %d letters \n", linesWords.get(indexMax), maxLength);
        System.out.printf("The shortest line is '%s' and it contains %d letters", linesWords.get(indexMin), minLength);
    }

    public void getNumbersMoreThanAverage_2() {
        int average = lookingForAverage();
        for (int i = 0; i < linesWords.size(); i++) {
            if (linesWords.get(i).length() > average) {
                System.out.printf("The average is '%d'\nMore than average lines are '%s', length = %d\n", average, linesWords.get(i), linesWords.get(i).length());
            }
        }
    }

    public void getNumbersLessThanAverage_3() {
        int average = lookingForAverage();
        for (int i = 0; i < linesWords.size(); i++) {
            if (linesWords.get(i).length() < average) {
                System.out.printf("The average is '%d'\nLess than average lines are '%s', length = %d\n", average, linesWords.get(i), linesWords.get(i).length());
            }
        }
    }

    public void getWordWithMinimalDifferentCharacters_4() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < linesWords.size(); i++) {
            int countOfSame = 0;

            String word = linesWords.get(i);
            for (int j = 0; j < word.length(); j++) {
                for (int k = j + 1; k < word.length(); k++) {
                    if (word.charAt(j) == word.charAt(k)) {
                        countOfSame++;
                    }
                }
                map.put(word, countOfSame);
            }
        }

        int minDublVal = (int) map.values().toArray()[0];
        String minDublKey = (String) map.keySet().toArray()[0];
        for (int i = 0; i < map.entrySet().size(); i++) {
            if (minDublVal > (int) map.values().toArray()[i]) {
                minDublVal = (int) map.values().toArray()[i];
                minDublKey = (String) map.keySet().toArray()[i];
            }
        }
        System.out.println("The word with minimal dublicates is " + minDublKey + ", count of dublicates is " + minDublVal);
    }



    public void getOnlyDifferentCharacters_5() {
        List<String> list = new ArrayList<>();
        String result = "";
        for (int i = 0; i < linesWords.size(); i++) {

            String word = linesWords.get(i);
            for (int j = 0; j < word.length(); j++) {
                if (result.indexOf(word.charAt(j)) == -1) {
                    result += word.charAt(j);
                }
            }
            if (result.length() == word.length()) {
                list.add(result);
            }
            result = "";
        }
        if (list.isEmpty()) {
            System.out.println("no words");
        } else {
            for (String s : list) {
                System.out.println(s);
            }
        }
    }

    public void getOnlyNumbers_6() {
        List<String> newlist = new ArrayList<>();
        for (int i = 0; i < linesWords.size(); i++) {
            String result = "";
            String word = linesWords.get(i);
            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j) >= 48 && word.charAt(j) <= 57) {  //по ascII
                    result += word.charAt(j);
                } else {
                    break;
                }
            }
            if ((word.length() > 0) && (word.length() == result.length())) {
                newlist.add(result);
                result = "";
            }
        }
        if (newlist.size() > 1) {
            System.out.println(newlist.get(1));
        } else {
            System.out.println(newlist.get(0));
        }
    }
}

