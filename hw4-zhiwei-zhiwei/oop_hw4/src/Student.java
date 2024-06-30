public class Student implements IGetClassroom, ILog{
    public void register_for_class() {
        System.out.println("Student Josephine is preparing to regiser for a new course that she likes");
        System.out.println("Student is registering for the class");
    }

    public void pay_tuition() {
        System.out.println("Seems like this education thing is awfully costly...time to pay tuition again!");
    }

    @Override
    public void get_classroom() {
        System.out.println("I'm five minutes late, where the heck is the classroom???");
    }

    public void get_my_grade() {
        System.out.println("Course is over.  Whew.  What's my grade???");
    }

    public void drop_class_from_my_schedule() {
        System.out.println("A student dropped this course");
    }
}
