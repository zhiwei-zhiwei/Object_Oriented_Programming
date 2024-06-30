package course_management;

import interfaces.*;

import dao.CourseDAO;

import java.sql.SQLException;

public class CourseManager implements ICourseAddManagement, ICourseStatus{

    private static CourseManager instance;
    private CourseDAO courseDAO;

    private CourseManager() {
        courseDAO = new CourseDAO();
    }

    public static CourseManager getInstance() {
        if (instance == null) {
            instance = new CourseManager();
        }
        return instance;
    }


    // Method to add a course remains the same as previously shown
    @Override
    public void addCourse(Course course){
        courseDAO.addCourse(course.getCourseNum(), course.getCourseName(), course.getSectionNum());
    }
    @Override
    public void addCourseWithCapacity(Course course){
        courseDAO.addCourseWithCapacity(course.getCourseNum(), course.getCourseName(), course.getSectionNum(), course.getCourseCapacity());
    }
    @Override
    public void openedCourses() {
        courseDAO.availableCourse();
    }
    @Override
    public void getAllCourses() {
        courseDAO.getAllCourses();
    }
    public void getCoursesWithLabs() {
        // Again, directly call the DAO method and process as needed
        courseDAO.getALLCourseWithLab();
    }
    public int getIdByNum(String courseId) throws SQLException {
        return courseDAO.getCourseIdByCourseNum(courseId);
    }
    public int getIdByName(String courseName) throws SQLException {
        return courseDAO.getCourseIdByCourseName(courseName);
    }
    public int getIdByCourse(Course course) throws  SQLException {
        return courseDAO.getCourseIdByCourseName(course.getCourseName());
    }
    public void deleteCourseByNum(String courseNum) {
        try {
            int courseId = courseDAO.getCourseIdByCourseNum(courseNum);
            if (courseId != -1) {
                courseDAO.deleteCourse(courseId);
                System.out.println("Course successfully deleted.");
            } else {
                System.out.println("Course not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteCourseByName(String courseName) {
        try {
            int courseId = courseDAO.getCourseIdByCourseName(courseName);
            if (courseId != -1) {
                courseDAO.deleteCourse(courseId);
                System.out.println("Course successfully deleted.");
            } else {
                System.out.println("Course not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteCourseByCourse(Course course) {
        try {
            int courseId = courseDAO.getCourseIdByCourseName(course.getCourseName());
            if (courseId != -1) {
                courseDAO.deleteCourse(courseId);
                System.out.println("Course successfully deleted.");
            } else {
                System.out.println("Course not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error occurred.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean isCourseFill(Course course) {
        return courseDAO.isCourseFull(course.getCourseName()) < 0;
    }

    public void courseLessThan5Enrolled() {
        courseDAO.coursesLessThan5Enrolled();
    }

    public void removeCourseLessThan5Enrolled() throws SQLException {
        courseDAO.removeCourseLessThan5Enrolled();
    }

    public void getCourseSection(String courName) {
        courseDAO.getCoursesLab(courName);
    }

    public void getAvailableCourseSection(String courseName) {
        courseDAO.getAvailableCourseLab(courseName);
    }

}
