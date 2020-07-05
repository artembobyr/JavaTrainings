package Collections.Task2;

import java.util.Objects;

public class LengthHash {
    private final String stringLength;

    public LengthHash(String stringLength) {
        this.stringLength = stringLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof LengthHash))return false;
        LengthHash that = (LengthHash) o;
        return Objects.equals(stringLength, that.stringLength);
    }

    @Override
    public int hashCode() {
        return stringLength.length();
    }

    @Override
    public String toString() {
        return stringLength;
    }
}
