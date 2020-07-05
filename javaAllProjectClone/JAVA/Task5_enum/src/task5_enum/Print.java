package task5_enum;

import task5_enum.enums.subjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Print {
    public void getPrint() throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("H.dd.MM.yyyy");
        Student student1 = new Student("Ivan", "Ivanov");
        Student student2 = new Student("Petr", "Petrov");
        Course courseOfStudent1 = new Course("J2EE Developer", subjects.getDuration(subjects.STRUCTS_FRAMEWORK.getDuration(), subjects.ТЕХНОЛОГИЯ_JAVA_SERVLETS.getDuration()), dateFormat.parse("07.02.10.2018"));
        Course courseOfStudent2 = new Course("Java Developer", subjects.getDuration(subjects.БИБЛИОТЕКА_JFC_SWING.getDuration(), subjects.ОБЗОР_ТЕХНОЛОГИЙ_JAVA.getDuration(), subjects.ТЕХНОЛОГИЯ_JDBC.getDuration()), dateFormat.parse("09.07.10.2018"));
        Curriculum curriculum1 = new Curriculum(student1, courseOfStudent1);
        Curriculum curriculum2 = new Curriculum(student2, courseOfStudent2);

        System.out.println("Input 1 (full version) or another char");
        char c = sc.next().charAt(0);
        switch (c) {
            case '1':
                System.out.println("First student");
                System.out.println(curriculum1);
                courseOfStudent1.getEstimatedDate();
                System.out.println("\nSecond student");
                System.out.println(curriculum2);
                courseOfStudent2.getEstimatedDate();
                break;
            default:
                System.out.print("First student - ");
                System.out.println(student1.getLastName() + " (" + courseOfStudent1.getNameOfCourse() + ") ");
                courseOfStudent1.getEstimatedDate();
                System.out.print("\nSecond student - ");
                System.out.println(student2.getLastName() + " (" + courseOfStudent2.getNameOfCourse() + ") ");
                courseOfStudent2.getEstimatedDate();
        }
    }
}
