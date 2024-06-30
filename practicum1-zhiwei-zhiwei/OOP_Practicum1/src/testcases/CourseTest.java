package testcases;

import projects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    private Course course;

    Faculty faculty = new Faculty("Mark");

    Classroom classroom = new Classroom("JCL 390");
    @BeforeEach
    void setUp() {
        course = new Course("OOP", 2, "Open");
        course.setDescription("Object-Oriented Programming");
        course.setCredit("1");
        course.setLocation("Online");
        course.setTimePeriod("Mon 4:10 PM");
        course.setFaculty(faculty);
        course.setClassroom(classroom);
    }

    @Test
    void testCourseHasName() {
        assertEquals("OOP", course.getName());
    }

    @Test
    void testCourseHasCapacity() {
        assertEquals(2, course.getCourseCapacity());
    }

    @Test
    void testCourseIsOpen() {
        assertEquals("Open", course.getStatus());
    }

    @Test
    void testCourseHasDescription() {
        assertEquals("Object-Oriented Programming", course.getDescription());
    }

    @Test
    void testCourseTimePeriodAndLocation() {
        assertEquals("Online", course.getLocation());
        assertEquals("Mon 4:10 PM", course.getTimePeriod());
    }

    @Test
    void testCourseHasFaculty(){
        assertEquals(course.getFaculty().getName(), faculty.getName());
    }

    @Test
    void testCourseHasRoom(){
        assertEquals(course.getClassroom().getRoom(), classroom.getRoom());
    }

    @Test
    void testCourseSections() {
        Section section = new Section("1", 2, "Open");
        course.addSection(section);
        assertTrue(course.getSections().contains(section));
    }

    @Test
    void testEnrollStudents() throws CapacityException {
        Student student = new Student("Student A");
        course.enrolledStudents(student);
        assertTrue(course.getEnrolledStudents().contains(student));
    }

    @Test
    void testEnrollmentLimitation() throws CapacityException {
        Student student1 = new Student("Student 1");
        Student student2 = new Student("Student 2");
        Student student3 = new Student("Student 3");
        course.enrolledStudents(student1);
        course.enrolledStudents(student2);
        Exception exception = assertThrows(CapacityException.class, () -> {
            course.enrolledStudents(student3);
        });

        String message = "Out of course capacity";
        String returnMessage = exception.getMessage();

        assertEquals(message, returnMessage);
    }
}
