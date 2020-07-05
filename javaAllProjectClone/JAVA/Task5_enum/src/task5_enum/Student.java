package task5_enum;

public class Student {
    private final String firstName;
    private final String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'';
    }
}
