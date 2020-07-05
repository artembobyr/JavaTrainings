package Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Dublicates {
    public void getDublicates(ArrayList<String> list) {
        list
                .stream()
                .filter(i -> Collections.frequency(list, i) > 1)
                .distinct()
                .sorted((o1, o2) -> +Integer.compare(o1.length(), o2.length()))
                .map(n -> new StringBuilder(n.toUpperCase()).reverse())
                .limit(3)
                .forEach(n -> System.out.println("Word = " + n + ", length = " + n.length()));
    }
}
