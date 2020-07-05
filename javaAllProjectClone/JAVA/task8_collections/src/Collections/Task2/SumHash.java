package Collections.Task2;

import java.util.Objects;

public class SumHash {
    private final String firstFourthNumbers;

    public SumHash(String firstFourthNumbers) {
        this.firstFourthNumbers = firstFourthNumbers;
    }

    private int sumHashCode() {
        int sum = 0;
        for (int i = 0; i < firstFourthNumbers.length(); i++) {
            if (i < 4)
                sum += Integer.parseInt(firstFourthNumbers.substring(i, i + 1));
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof SumHash))return false;
        SumHash sumHash = (SumHash) o;
        return Objects.equals(firstFourthNumbers, sumHash.firstFourthNumbers);
    }

    @Override
    public int hashCode() {
        return sumHashCode();
    }

    @Override
    public String toString() {
        return firstFourthNumbers;
    }
}
