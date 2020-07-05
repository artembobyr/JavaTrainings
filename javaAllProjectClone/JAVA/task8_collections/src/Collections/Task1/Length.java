package Collections.Task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Length implements Comparator<String> {
    public ArrayList<String> getLength(ArrayList<String> list) {
        Collections.sort(list, new Length());
        Collections.reverse((list));
        list = returnOnly3Words(list);
        outputResult(list);
        return list;
    }

    public void outputResult(ArrayList<String> list) {
        for (String s : list) {
            System.out.println(s + " = " + s.length());
        }
    }

    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }

    public ArrayList<String> returnOnly3Words(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
}