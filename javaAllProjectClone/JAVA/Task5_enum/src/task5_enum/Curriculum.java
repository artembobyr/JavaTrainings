package task5_enum;

public class Curriculum {
    private final Student student;
    private final Course course;

    public Curriculum(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "Curriculum:\n" + student + "\n" + course;
    }
}

