package projects;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    private String description;
    private String credit; // depends, some course doesn't have credits
    private String location;
    private int courseCapacity;
    private String timePeriod;
    private List<Student> enrolledStudents;
    private String status;
    private List<Section> sections;
    private Faculty faculty;
    private Classroom classroom;

    public Course(String name, int courseCapacity, String status) {
        this.name = name;
        this.courseCapacity = courseCapacity;
        this.status = status;
        this.sections = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public String getDescription() {
        return description;
    }

    public String getCredit() {
        return credit;
    }

    public String getLocation() {
        return location;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void enrolledStudents(Student student) throws CapacityException {
        if (enrolledStudents.size() >= courseCapacity){
            this.status = "Closed";
            throw new CapacityException("Out of course capacity");
        }
        enrolledStudents.add(student);
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getStatus() {
        return status;
    }

    public void addSection(Section section){
        sections.add(section);
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
