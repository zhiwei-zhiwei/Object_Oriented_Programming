public class Instructor implements IGetClassroom, IGetEnrollmentNum, ILog{

    @Override
    public void get_enrollment_numbers() {
        System.out.println("Professor Rumplestilskin is preparing to teach a new course");
        System.out.println("Professor is getting the current number of students registered for the course");
    }

    @Override
    public void get_classroom() {
        System.out.println("Professor is wondering where his class is, as he only has five minutes to get there");
    }

    public void enter_grades() {
        System.out.println("Course is over.  Whew.  Now I have to assign grades for my students");
    }
}
