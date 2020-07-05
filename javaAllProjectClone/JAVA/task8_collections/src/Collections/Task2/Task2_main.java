package Collections.Task2;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Task2_main {

    public static void main(String[] args) {
        HashMap<Object, String> hashMap;
        hashMap = new InputData().inputHashMap();
        InputData.getHash(hashMap);
        System.out.println();
        LinkedHashMap<Object, String> linkedHashMap;
        linkedHashMap = new InputData().inputLinkedHashMap();
        InputData.getHash(linkedHashMap);
    }
}
