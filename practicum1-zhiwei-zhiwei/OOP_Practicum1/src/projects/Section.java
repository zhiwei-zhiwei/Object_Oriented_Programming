package projects;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private String sectionNumber;
    private String timeSlot;
    private String location;
    private List<Student> enrolledStudents;
    private int capacity;
    private String status;
    private Faculty faculty;
    private Classroom classroom;

    public Section(String sectionNumber, int capacity, String status) {
        this.sectionNumber = sectionNumber;
        this.capacity = capacity;
        this.status = status;
        this.enrolledStudents = new ArrayList<>();
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void enrollStudent(Student student) throws CapacityException {
        if (enrolledStudents.size() >= capacity) {
            this.status = "Closed";
            throw new CapacityException("Out of section capacity");
        }
        enrolledStudents.add(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

