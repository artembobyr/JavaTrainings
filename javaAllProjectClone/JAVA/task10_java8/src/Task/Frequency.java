package Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Frequency {

    public void getFrequency(ArrayList<String> list) {
        list
                .stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(2)
                .forEach(entry -> System.out.println("Word = " + entry.getKey() + ", frequency = " + entry.getValue()));
    }
}



