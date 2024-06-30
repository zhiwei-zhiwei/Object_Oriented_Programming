package dao;

import course_management.Course;
import db.DatabaseHelper;

import java.sql.*;

public class SectionDAO {

    DatabaseHelper connect_db = new DatabaseHelper("jdbc:mysql://localhost:3306/CourseManagement", "root", "Cczw123890//");

    public void addSectionToCourse(String labNum, int courseId) throws SQLException {
        String sql = "INSERT INTO lab (labNum, course_id) VALUES (?, ?);";
        connect_db.executeUpdateWithParams(sql, labNum, courseId);
    }

    public void assignSectionLocation(int sectionId, String building, String room) throws SQLException {
        String sql = "INSERT INTO sectionLocation (building, room, section_id) VALUES (?, ?, ?);";
        connect_db.executeUpdateWithParams(sql, building, room, sectionId);

    }

    public void assignSectionSchedule(int sectionId, String day, String startTime, String endTime) throws SQLException {
        String sql = "INSERT INTO sectionSchedule (day, startTime, endTime, section_id) VALUES (?, ?, ?, ?);";
        connect_db.executeUpdateWithParams(sql, day, startTime, endTime, sectionId);
    }

    public void getLabLocationAndScheduleByLabID(int sectionId) {
        String sql = """
                SELECT
                    l.id AS LabID,
                    l.labNum AS LabNumber,
                    sl.building AS Building,
                    sl.room AS Room,
                    ss.day AS Day,
                    ss.startTime AS StartTime,
                    ss.endTime AS EndTime
                FROM
                    lab l
                        JOIN sectionLocation sl ON l.id = sl.section_id
                        JOIN sectionSchedule ss ON l.id = ss.section_id
                WHERE
                    l.id = ?;""";
        connect_db.executeSelectQueryWithParams(sql, sectionId);
    }

    public int getSectionIdByCourseNum(String sectionNum) throws SQLException {
        String sql = "SELECT id FROM lab WHERE labNum = ?;";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sectionNum);
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



}
