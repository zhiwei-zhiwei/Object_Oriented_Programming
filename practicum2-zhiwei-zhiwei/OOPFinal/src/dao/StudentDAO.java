package dao;

import db.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    DatabaseHelper connect_db = new DatabaseHelper("jdbc:mysql://localhost:3306/CourseManagement", "root", "Cczw123890//");

    public int validUsername(String userName) {
        String sql = "SELECT id FROM user where userName = ?";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getUserPassword(String userName){
        String sql = "SELECT userPassword FROM user where userName = ?";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("userPassword");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean enrollInCourse(String courseNum, String courseName, Integer userId, String sectionNum) {
        String sql = "INSERT INTO courseEnrollments (courseNum, courseName, userId, sectionNum) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseNum);
            pstmt.setString(2, courseName);
            if (userId != null) {
                pstmt.setInt(3, userId);
            } else {
                pstmt.setNull(3, java.sql.Types.INTEGER); // Assuming userId is an INTEGER in the DB
            }
            pstmt.setString(4, sectionNum);

            int affectedRows = pstmt.executeUpdate();

            // Check if the insert was successful
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countEnrolledCourses(int userId) {
        String sql = "SELECT COUNT(*) AS courseCount FROM courseEnrollments WHERE userId = ?";

        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("courseCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if the user is not enrolled in any courses or an error occurs
    }

}
