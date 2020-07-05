package Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Length {
    public void getLength(ArrayList<String> list) {
        list
                .stream()
                .sorted((o1, o2) -> -Integer.compare(o1.length(), o2.length()))
                .limit(3)
                .forEach(n -> System.out.println("Word = " + n + ", length = " + n.length()));
    }
}