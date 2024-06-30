import course_management.*;
import dao.*;
import db.*;

import java.sql.*;

public class Main{
    public static void main(String[] args) throws SQLException, CapacityException {
        Locations courseLocation = new Locations("JCL", "10000");
        Schedule courseSchedule = new Schedule("Mon", "1:00", "2:00");
        Course course = new Course("11111","OOP", "Section 1");
//        course.addCoursesToDb(course);

        Course newCourse1 = new Course("22222", "OOP", "Section 1");
        newCourse1.setCourseCapacity(10);
//        newCourse1.addCoursesWithCapacityToDb(newCourse1);
        course.setLocation(courseLocation);
        course.setSchedules(courseSchedule);
        System.out.println(course.getSchedules());
        System.out.println(course.getCourseCapacity());
        course.setCourseCapacity(100);
        course.setCourseCapacity(5);
        System.out.println(course.getCourseCapacity());
        Student s1 = new Student("1", "1");
        Student s2 = new Student("1", "1");
        Student s3 = new Student("1", "1");
        Student s4 = new Student("1", "1");
        Student s5 = new Student("1", "1");
        course.enrolledStudents(s1);
        course.enrolledStudents(s2);
        course.enrolledStudents(s3);
        course.enrolledStudents(s4);
        course.enrolledStudents(s5);
        Student s6 = new Student("1", "1");
        try{
            course.enrolledStudents(s6);
        } catch (Exception e){
            System.out.println(e);
        }

        System.out.println(course.getStatus());

//        course.currentAllCourses();

        System.out.println("---");
//        course.currentCoursesWithSections();
        System.out.println("-------");
//        System.out.println(course.getCourseIdByNum("12345"));
//        course.deleteACourseByNum("12345");

        System.out.println("==========SINGLETON TESTS==========");
        CourseManager courseManager = CourseManager.getInstance();
        Course newCourse = new Course("33333", "TEST", "Section 001");
//        courseManager.addCourse(newCourse);
        courseManager.getAllCourses();
//        courseManager

        System.out.println("------++++++++++"+courseManager.getIdByNum("33333"));
        System.out.println(courseManager.isCourseFill(newCourse));
        courseManager.courseLessThan5Enrolled();
//        System.out.println();
        courseManager.getCourseSection("Advanced Mathematics");






        System.out.println("==========DATABASE TESTS==========");
        CourseDAO courseDAO = new CourseDAO();
//        courseDAO.addCourse("12345", "OOP");
        courseDAO.getAllCourses();
//        System.out.println(courseDAO.getAllCourses());

        System.out.println(courseDAO.getCourseIdByCourseNum("CSCI101"));
        System.out.println(courseDAO.getCourseIdByCourseName("Principles of Physics"));

        courseDAO.getALLCourseWithLab();
        courseDAO.getCourseLocationAndScheduleByCourseID(2);

        int courseID = courseDAO.getCourseIdByCourseName("OOP");
//        courseDAO.assignCourseLocation(courseID, "JCL", "111");
//        courseDAO.assignCourseSchedule(courseID, "Firday", "17:30", "20:30");
        System.out.println("-------");
        SectionDAO sectionDAO = new SectionDAO();
        int section_id = sectionDAO.getSectionIdByCourseNum("S101");
        System.out.println(section_id);
//        sectionDAO.assignSectionLocation(section_id, "JCL", "112");
//        sectionDAO.assignSectionSchedule(section_id, "Wednesday","9:00", "12:00");
        System.out.println("--");
        sectionDAO.getLabLocationAndScheduleByLabID(section_id);

        System.out.println(courseDAO.getCourseCapacityByCourseName("Organic Chemistry"));

        System.out.println(courseDAO.isCourseFull("Organic Chemistry"));

        courseDAO.coursesLessThan5Enrolled();
//        courseDAO.removeCourseLessThan5Enrolled();

        courseDAO.getCoursesLab("Advanced Mathematics");
        courseDAO.getAvailableCourseLab("Advanced Mathematics");



        StudentDAO studentDAO = new StudentDAO();

        System.out.println(studentDAO.validUsername("student"));
        System.out.println(studentDAO.getUserPassword("student"));

        Student student = new Student("12345", "student");
        System.out.println(student.isUserNameValid(student.getName()));

//        boolean successfullyEnrolled = studentDAO.enrollInCourse("MATH201","Advanced Mathematics", 1,"001");
//        System.out.println(successfullyEnrolled);

        System.out.println(studentDAO.countEnrolledCourses(1));

        AccountAuth accountAuth = new AccountAuth();
        System.out.println(accountAuth.isStudentUserNameValid(student.getName()));
        String inputPassword = "hello";
        System.out.println(accountAuth.isStudentPasswordMatches("student", inputPassword));

    }
}