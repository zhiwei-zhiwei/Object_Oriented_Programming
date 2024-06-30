package dao;

import db.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import course_management.*;

public class CourseDAO {

    DatabaseHelper connect_db = new DatabaseHelper("jdbc:mysql://localhost:3306/CourseManagement", "root", "Cczw123890//");

    public void availableCourse() {
        String sql = "SELECT * " +
                "FROM courses " +
                "WHERE currentCourseCapacity < courseCapacity;";
        DatabaseHelper.executeSelectQuery(sql);
    }

    public void addCourse(String courseNum, String courseName, String sectionNum) {
        String sql = "INSERT INTO courses (courseNum, courseName,sectionNum ) VALUES (?, ?, ?);";
        connect_db.executeUpdateWithParams(sql, courseNum, courseName, sectionNum);
    }

    public void addCourseWithCapacity(String courseNum, String courseName, String sectionNum, int capacity) {
        String sql = "INSERT INTO courses (courseNum, courseName, String sectionNum, courseCapacity) VALUES (?, ?, ?, ?);";
        connect_db.executeUpdateWithParams(sql, courseNum, courseName, sectionNum, capacity);
    }

    public void getAllCourses() {
        String sql = "SELECT * FROM courses";
        DatabaseHelper.executeSelectQuery(sql);
    }

    public void getALLCourseWithLab() {
        String sql = "SELECT c.id         AS CourseID,\n" +
                "       c.courseNum  AS CourseNumber,\n" +
                "       c.courseName AS CourseName,\n" +
                "       c.sectionNum AS sectionNum,\n" +
                "       l.labNum     AS LabNum\n" +
                "FROM courses c\n" +
                "         LEFT JOIN\n" +
                "     lab l ON c.id = l.course_id;";
        DatabaseHelper.executeSelectQuery(sql);
    }

    public int getCourseIdByCourseNum(String courseNum) throws SQLException {
        String sql = "SELECT id FROM courses WHERE courseNum = ?;";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseNum);
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

    public int getCourseIdByCourseName(String courseName) throws SQLException {
        String sql = "SELECT id FROM courses WHERE courseName = ?;";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseName);
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

    public int getCourseCapacityByCourseName(String courseName) throws SQLException {
        String sql = "SELECT courseCapacity FROM courses WHERE courseName = ?;";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("courseCapacity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getCourseCapacityByCourseNum(String courseNum) throws SQLException {
        String sql = "SELECT courseCapacity FROM courses WHERE courseNum = ?;";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseNum);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("courseCapacity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void assignCourseLocation(int courseId, String building, String room) throws SQLException {
        String sql = "INSERT INTO courseLocation (building, room, course_id) VALUES (?, ?, ?);";
        connect_db.executeUpdateWithParams(sql, building, room, courseId);

    }

    public void assignCourseSchedule(int courseId, String day, String startTime, String endTime) throws SQLException {
        String sql = "INSERT INTO courseSchedule (day, startTime, endTime, course_id) VALUES (?, ?, ?, ?);";
        connect_db.executeUpdateWithParams(sql, day, startTime, endTime, courseId);
    }

    public void getCourseLocationAndScheduleByCourseID(int courseID) {
        String sql = "SELECT c.id AS CourseID, " +
                "c.courseNum AS CourseNumber, " +
                "c.courseName AS CourseName, " +
                "cl.building AS Building," +
                "cl.room AS Room, " +
                "cs.day AS Day, " +
                "cs.startTime AS StartTime, " +
                "cs.endTime AS EndTime " +
                "FROM courses c " +
                "LEFT JOIN courseLocation cl ON c.id = cl.course_id " +
                "LEFT JOIN courseSchedule cs ON c.id = cs.course_id " +
                "WHERE c.id = ?;";
        DatabaseHelper.executeSelectQueryWithParams(sql, courseID);
    }

    public void deleteCourse(int courseID) throws SQLException {
        Connection conn = null;
        try {
            conn = connect_db.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Define all SQL statements required for deletion
            String[] sqlStatements = {
                    "DELETE\n" +
                            "FROM courseSchedule\n" +
                            "WHERE course_id = ?;\n" +
                            "DELETE\n" +
                            "FROM courseLocation\n" +
                            "WHERE course_id = ?;\n" +
                            "DELETE\n" +
                            "FROM courseStudents\n" +
                            "WHERE course_id = ?;\n" +
                            "DELETE\n" +
                            "FROM courseFaculty\n" +
                            "WHERE course_id = ?;\n" +
                            "DELETE\n" +
                            "FROM sectionSchedule\n" +
                            "WHERE section_id IN (SELECT id FROM lab WHERE course_id = ?);\n" +
                            "DELETE\n" +
                            "FROM sectionLocation\n" +
                            "WHERE section_id IN (SELECT id FROM lab WHERE course_id = ?);\n" +
                            "DELETE\n" +
                            "FROM sectionStudents\n" +
                            "WHERE section_id IN (SELECT id FROM lab WHERE course_id = ?);\n" +
                            "DELETE\n" +
                            "FROM sectionFaculty\n" +
                            "WHERE section_id IN (SELECT id FROM lab WHERE course_id = ?);\n" +
                            "DELETE\n" +
                            "FROM lab\n" +
                            "WHERE course_id = ?;\n" +
                            "DELETE\n" +
                            "FROM courses\n" +
                            "WHERE id = ?;"
            };

            for (String sql : sqlStatements) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, courseID);
                    pstmt.executeUpdate();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    public int isCourseFull(String courseName) {
        String sql = "SELECT courseCapacity - currentCourseCapacity as availableSpace " +
                "FROM courses " +
                "WHERE courseName = ?;";
        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, courseName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("availableSpace");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void coursesLessThan5Enrolled() {
        String sql = """
                SELECT id,
                       courseNum,
                       courseName,
                       courseCapacity,
                       currentCourseCapacity
                FROM courses
                WHERE currentCourseCapacity < 5;""";
        DatabaseHelper.executeSelectQuery(sql);
    }

    public void removeCourseLessThan5Enrolled() throws SQLException {
        Connection conn = null;
        List<Integer> courseIds = new ArrayList<>();

        try {
            conn = connect_db.getConnection();

            // SQL to find courses with currentCourseCapacity less than 5
            String sql = "SELECT id FROM courses WHERE currentCourseCapacity < 5";

            // Execute query and collect course IDs
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    courseIds.add(rs.getInt("id"));
                }
            }

            // Iterate over each course ID and call deleteCourse
            for (int courseId : courseIds) {
                deleteCourse(courseId);  // Call existing deleteCourse function
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void getCoursesLab(String courseName) {
        String sql = """
                SELECT l.id                            AS LabID,
                       l.labNum                        AS LabNumber,
                       l.labCapacity - l.currentLabCapacity AS RemainingSpace,
                       c.id                            AS CourseID,
                       c.courseName                    AS CourseName
                FROM lab l
                         JOIN courses c ON l.course_id = c.id
                WHERE c.courseName = ?;""";
        DatabaseHelper.executeSelectQueryWithParams(sql, courseName);
    }

    public void getAvailableCourseLab(String courseName) {
        String sql = """
                SELECT l.id                             AS LabID,
                       l.labNum                         AS LabNumber,
                       l.labCapacity - l.currentLabCapacity AS RemainingSpace,
                       c.id                             AS CourseID,
                       c.courseName                     AS CourseName
                FROM lab l
                         JOIN courses c ON l.course_id = c.id
                WHERE c.courseName = ?
                  AND l.labCapacity > l.currentLabCapacity;""";
        DatabaseHelper.executeSelectQueryWithParams(sql, courseName);
    }

    public List<String> findScheduleAndLocationConflicts() {
        List<String> conflicts = new ArrayList<>();
        String sql = "SELECT 'Course-Course' AS conflict_type, cs1.course_id AS entity1_id, cs2.course_id AS entity2_id, cs1.day, cs1.startTime, cs1.endTime, cl1.building, cl1.room " +
                "FROM courseSchedule cs1 " +
                "JOIN courseLocation cl1 ON cs1.course_id = cl1.course_id " +
                "JOIN courseSchedule cs2 ON cs1.day = cs2.day " +
                "JOIN courseLocation cl2 ON cs2.course_id = cl2.course_id AND cl1.building = cl2.building AND cl1.room = cl2.room " +
                "WHERE cs1.course_id != cs2.course_id AND " +
                "((cs1.startTime <= cs2.startTime AND cs1.endTime > cs2.startTime) OR " +
                "(cs2.startTime <= cs1.startTime AND cs2.endTime > cs1.startTime)) " +
                "UNION " +
                "SELECT 'Section-Section' AS conflict_type, ss1.section_id AS entity1_id, ss2.section_id AS entity2_id, ss1.day, ss1.startTime, ss1.endTime, sl1.building, sl1.room " +
                "FROM sectionSchedule ss1 " +
                "JOIN sectionLocation sl1 ON ss1.section_id = sl1.section_id " +
                "JOIN sectionSchedule ss2 ON ss1.day = ss2.day " +
                "JOIN sectionLocation sl2 ON ss2.section_id = sl2.section_id AND sl1.building = sl2.building AND sl1.room = sl2.room " +
                "WHERE ss1.section_id != ss2.section_id AND " +
                "((ss1.startTime <= ss2.startTime AND ss1.endTime > ss2.startTime) OR " +
                "(ss2.startTime <= ss1.startTime AND ss2.endTime > ss1.startTime)) " +
                "UNION " +
                "SELECT 'Course-Section' AS conflict_type, cs.course_id AS entity1_id, ss.section_id AS entity2_id, cs.day, cs.startTime, cs.endTime, cl.building, cl.room " +
                "FROM courseSchedule cs " +
                "JOIN courseLocation cl ON cs.course_id = cl.course_id " +
                "JOIN sectionSchedule ss ON cs.day = ss.day " +
                "JOIN sectionLocation sl ON ss.section_id = sl.section_id AND cl.building = sl.building AND cl.room = sl.room " +
                "WHERE " +
                "((cs.startTime <= ss.startTime AND cs.endTime > ss.startTime) OR " +
                "(ss.startTime <= cs.startTime AND ss.endTime > cs.startTime))";

        try (Connection conn = connect_db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String conflictType = rs.getString("conflict_type");
                    int entity1Id = rs.getInt("entity1_id");
                    int entity2Id = rs.getInt("entity2_id");
                    String day = rs.getString("day");
                    String startTime = rs.getString("startTime");
                    String endTime = rs.getString("endTime");
                    String building = rs.getString("building");
                    String room = rs.getString("room");

                    String conflictDetails = String.format("Conflict Type: %s, Entity1 ID: %d, Entity2 ID: %d, Day: %s, Time: %s-%s, Location: %s %s",
                            conflictType, entity1Id, entity2Id, day, startTime, endTime, building, room);
                    conflicts.add(conflictDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conflicts;
    }

    public void printConflicts() {
        List<String> conflicts = findScheduleAndLocationConflicts();
        if (conflicts.isEmpty()) {
            System.out.println("No conflicts found.");
        } else {
            System.out.println("Conflicts found:");
            for (String conflict : conflicts) {
                System.out.println(conflict);
            }
        }
    }


}
