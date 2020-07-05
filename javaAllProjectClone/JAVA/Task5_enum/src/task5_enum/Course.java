package task5_enum;

import java.util.Date;

public class Course {

    private final String nameOfCourse;
    private final Date startDate;
    private final int duration;
    private Date endDate;
    private final Date now = new Date();
    private static final int GET_TIME_IN_HOUR = 1000 * 60 * 60;


    public Course(String nameOfCourse, int duration, Date startDate) {
        this.nameOfCourse = nameOfCourse;
        this.duration = duration;
        this.startDate = startDate;
        endDate = getEndDate();
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public Date getEndDate() {
        Date tempStart = new Date();
        tempStart.setTime(startDate.getTime());
        int tempDuration = duration;
        while (tempDuration != 0) {
            tempStart.setTime(tempStart.getTime() + GET_TIME_IN_HOUR);
            if (tempStart.getHours() <= 18 && tempStart.getHours() >= 10) {
                tempDuration--;
            }
        }
        endDate = tempStart;
        return endDate;
    }

    public void getEstimatedDate() {
        if (endDate.getTime() > now.getTime()) {
            System.out.println("Training is not completed");
            System.out.print(Math.abs(now.getTime() - endDate.getTime()) / GET_TIME_IN_HOUR / 24 + " days ");
            System.out.println("and " + Math.abs(now.getTime() - endDate.getTime()) / GET_TIME_IN_HOUR % 24 + " hours to end");
        } else {
            System.out.println("Training is completed");
            System.out.print(Math.abs(now.getTime() - endDate.getTime()) / GET_TIME_IN_HOUR / 24 + " days ");
            System.out.println("and " + Math.abs(now.getTime() - endDate.getTime()) / GET_TIME_IN_HOUR % 24 + " hours after end");
        }
    }

    @Override
    public String toString() {
        return "Course: " +
                "nameOfCourse='" + nameOfCourse + '\'' +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", endDate=" + endDate;
    }
}
