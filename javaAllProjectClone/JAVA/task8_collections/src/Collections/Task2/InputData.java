package Collections.Task2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InputData {
    /**
     * @return strings by increases the hash code
     */
    public HashMap<Object, String> inputHashMap() {
        HashMap<Object, String> hashMap = new HashMap<>();

        hashMap.put(new SumHash("12345"), "first");
        hashMap.put(new SumHash("2385723895789"), "second");
        hashMap.put(new LengthHash("dhsjkagds"), "second");
        hashMap.put(new LengthHash("2385723895789"), "second");

        return hashMap;
    }

    /**
     * @return strings in order of addition
     */
    public LinkedHashMap<Object, String> inputLinkedHashMap() {
        LinkedHashMap<Object, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(new SumHash("12345"), "second");
        linkedHashMap.put(new SumHash("2385723895789"), "first");
        linkedHashMap.put(new LengthHash("dhsjkagds"), "second");
        linkedHashMap.put(new LengthHash("2385723895789"), "second");
        return linkedHashMap;
    }

    public static void getHash(Map<Object, String> map) {
        for (Map.Entry<Object, String> s : map.entrySet()) {
            System.out.println("Key = " + s.getKey() + ", hash code = " + s.getKey().hashCode());
        }
    }
}
