package Collections.Task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Frequency implements Comparator<HashMap.Entry<String, Integer>> {
    private final HashMap<String, Integer> frequencyWords = new HashMap<>();

    public HashMap<String, Integer> getFrequency(ArrayList<String> list) {
        Collections.sort(list);
        Collections.reverse(list);

        //create HashMap key:word, value:count
        for (String word : list) {
            if (!frequencyWords.containsKey(word)) {
                frequencyWords.put(word, 0);
            }
            frequencyWords.put(word, frequencyWords.get(word) + 1);
        }

        //sort HashMap by value
        ArrayList<HashMap.Entry<String, Integer>> tempList = new ArrayList(frequencyWords.entrySet());
        Collections.sort(tempList, new Frequency());

        //output first, second elements
        outputResult(tempList);

        return frequencyWords;
    }

    public ArrayList<HashMap.Entry<String, Integer>> outputResult(ArrayList<HashMap.Entry<String, Integer>> list) {
        ArrayList<HashMap.Entry<String, Integer>> newList = new ArrayList<>();
        for (HashMap.Entry<String, Integer> s : list) {
            newList.add(s);
            System.out.println("word - " + s.getKey() + ", count - " + s.getValue());
            if (newList.size() == 2)
                break;
        }
        return newList;
    }

    public int compare(HashMap.Entry<String, Integer> h1, HashMap.Entry<String, Integer> h2) {
        return h2.getValue().compareTo(h1.getValue());
    }
}
