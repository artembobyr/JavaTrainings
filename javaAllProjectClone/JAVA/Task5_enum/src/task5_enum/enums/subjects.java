package task5_enum.enums;

public enum subjects {
    ТЕХНОЛОГИЯ_JAVA_SERVLETS(24),
    STRUCTS_FRAMEWORK(16),
    ОБЗОР_ТЕХНОЛОГИЙ_JAVA(8),
    БИБЛИОТЕКА_JFC_SWING(16),
    ТЕХНОЛОГИЯ_JDBC(16);

    private final Integer duration;

    subjects(int duration) {
        this.duration = duration;
    }

    public static int getDuration(int... d) {
        int sum = 0;
        for (int i : d)
            sum += i;
        return sum;
    }

    public Integer getDuration() {
        return duration;
    }
}