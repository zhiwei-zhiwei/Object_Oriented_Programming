package testcases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.*;

import static org.junit.jupiter.api.Assertions.*;

public class SectionTest {
    private Section section;

    Faculty faculty = new Faculty("Mark");

    Classroom classroom = new Classroom("JCL 390");

    @BeforeEach
    void setUp() {
        section = new Section("1", 2, "Open");
        section.setTimeSlot("Mon 4:10 PM");
        section.setLocation("Online");
        section.setFaculty(faculty);
        section.setClassroom(classroom);
    }

    @Test
    void testSectionWithSectionNumber() {
        assertEquals("1", section.getSectionNumber());
    }

    @Test
    void testSectionWithCapacity() {
        assertEquals(2, section.getCapacity());
    }

    @Test
    void testSectionWithStatus() {
        assertEquals("Open", section.getStatus());
    }

    @Test
    void testSectionLocationAndTimeLocation() {
        assertEquals("Online", section.getLocation());
        assertEquals("Mon 4:10 PM", section.getTimeSlot());
    }

    @Test
    void testSectionHasFaculty(){
        assertEquals(section.getFaculty().getName(), faculty.getName());
    }

    @Test
    void testSectionHasRoom(){
        assertEquals(section.getClassroom().getRoom(), classroom.getRoom());
    }

    @Test
    void testEnrollStudents() throws CapacityException {
        Student student = new Student("Student A");
        section.enrollStudent(student);
        assertTrue(section.getEnrolledStudents().contains(student));
    }

    @Test
    void testEnrollmentLimitation() throws CapacityException {
        Student student1 = new Student("Student 1");
        Student student2 = new Student("Student 2");
        Student student3 = new Student("Student 3");
        section.enrollStudent(student1);
        section.enrollStudent(student2);
        Exception exception = assertThrows(CapacityException.class, () -> {
            section.enrollStudent(student3);
        });

        String message = "Out of section capacity";
        String returnMessage = exception.getMessage();

        assertEquals(message, returnMessage);
    }
}
