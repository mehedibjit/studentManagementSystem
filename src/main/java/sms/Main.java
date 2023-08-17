package sms;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        DBOperations dbOperations = new DBOperations();
        System.out.println("Printing Student data:");
        dbOperations.fetchStudentData();

        System.out.println("\nPrinting Course Data:");
        dbOperations.fetchCoursesData();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null;
        try {
            birthDate = new Date(dateFormat.parse("2000-01-15").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Inserting New Student Data
        System.out.println("\nInserting New Student Data");
        dbOperations.insertStudentData("Shafa", "Female", birthDate, "01927584783", 2);

        // Checking the student data if new data is added or not
        dbOperations.fetchStudentData();

        System.out.println("\nJoin operation with student and course table");
        dbOperations.fetchStudentDataWithCourses(); // Join Operation
    }
}
