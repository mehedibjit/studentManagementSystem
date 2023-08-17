package sms;

import java.sql.*;

public class DBOperations {
    DBConnection dbConnection = new DBConnection();
    Connection con = dbConnection.getConnection();

    public void fetchData() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student");

            while (rs.next()) {
                String name = rs.getString("stname");
                String gender = rs.getString("stgender");
                String birthDate = rs.getString("stbirthdate");
                String contact = rs.getString("stcontact");
                System.out.println("Name: " + name + ", Gender: " + gender + ", Birth Date: " + birthDate + ", Contact: "+contact);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void insertStudentData(String name, String gender, Date birthDate, String contact) {
        String insertQuery = "INSERT INTO student (stname, stgender, stbirthdate, stcontact) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, gender);

            java.sql.Date sqlDate = new java.sql.Date(birthDate.getTime());
            preparedStatement.setDate(3, sqlDate);

            preparedStatement.setString(4, contact);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nStudent data inserted successfully.\n");
            } else {
                System.out.println("\nNo student data inserted.\n");
            }
        } catch (SQLException e) {
            System.err.println("\nError inserting student data: " + e.getMessage());
        }
    }
    public void fetchStudentDataWithCourses() {
        try {
            String query = "SELECT s.stname, s.stgender, s.stbirthdate, s.stcontact, c.course_name " +
                    "FROM student s " +
                    "LEFT JOIN courses c " +
                    "ON s.course_id = c.course_id";

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("s.stname");
                String gender = rs.getString("s.stgender");
                String birthDate = rs.getString("s.stbirthdate");
                String contact = rs.getString("s.stcontact");
                String courseName = rs.getString("c.course_name");
                System.out.println("Name: " + name + ", Gender: " + gender + ", Birth Date: " + birthDate + ", Contact: " + contact + ", Course: " + courseName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
