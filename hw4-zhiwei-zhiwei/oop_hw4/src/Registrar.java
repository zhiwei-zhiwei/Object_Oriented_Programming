public class Registrar implements IGetEnrollmentNum, ILog{
    public void add_class_to_quarter_schedule() {
        System.out.println("Registrar is preparing to create a new course listing");
        System.out.println("Registrar is adding this new course to the course listing for the quarter");
    }

    public void set_instructor_for_class() {
        System.out.println("Registrar is setting the instructor for this course");
    }

    @Override
    public void get_enrollment_numbers() {
        System.out.println("Registrar is getting the current number of students registered for the course");
    }

    public void run_class() {
        System.out.println("The quarter has begun!");
    }

    public void drop_class_from_quarter_schedule() {
        System.out.println("This course looks not popular");
    }
}
