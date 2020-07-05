package Collections.Task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class Dublicates implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s1.length() - s2.length();
    }

    public ArrayList<String> getDublicates(ArrayList<String> list) {

        //create HashSet with Dublicates
        HashSet<String> duplicates = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            if (duplicates.contains(list.get(i))) {
                continue;
            }
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    duplicates.add(list.get(i));
                    break;
                }
            }
        }
        //sort HashSet by length
        ArrayList<String> tempList = new ArrayList<String>(duplicates);
        Collections.sort(tempList, new Dublicates());

        tempList = returnOnly3Words(tempList);
        tempList = getReverseOrderAndUppercase(tempList);
        outputResult(tempList);

        return tempList;
    }

    public void outputResult(ArrayList<String> list) {
        for (String s : list) {
            System.out.println(s + ", length - " + s.length());
        }
    }

    public ArrayList<String> returnOnly3Words(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }

    public ArrayList<String> getReverseOrderAndUppercase(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (String s : list) {
            newList.add(new StringBuilder(s.toUpperCase()).reverse().toString());
        }
        return newList;
    }
}
