package sms;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        DBOperations dbOperations = new DBOperations();
        dbOperations.fetchData();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = null;
        try {
            birthDate = new Date(dateFormat.parse("2000-01-15").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        dbOperations.insertStudentData("Shafa", "Female", birthDate, "01927584783");
//
//        dbOperations.fetchData();

        System.out.println();
        dbOperations.fetchStudentDataWithCourses(); // Join Operation
    }
}
