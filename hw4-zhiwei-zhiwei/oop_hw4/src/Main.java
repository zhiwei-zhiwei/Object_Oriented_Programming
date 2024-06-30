/*
For the original ruby code, there is an interface called RegistrarInterface with 11 interface methods.
At the same time, the Student class, Instructor class, and Registrar class implement the method of RegistrarInterface.
However, it is a fact that not every class needs to implement every method of the interface. Reference to the idea
of Interface Segregation Principle (ISP): A client should never be forced to implement an interface that it doesn't
use, or clients shouldn't be forced to depend on methods they do not use.

Meanwhile, in order to ensure the Single Responsibility Principle (SRP), every class, module, or function in a program
should have one responsibility/purpose in a program, it is important to ensure that each interface should have one
responsibility/purpose. There are only two methods that are reusable in this system, which are get_enrollment_numbers and
get_classroom. The rest of the methods are too specific for each class.

As a result, this is how I fix the original ruby code in Java:
I separate the "huge" RegistrarInterface interface into their "small" interfaces (IGetClassroom and IGetEnrollmentNum)by
following the idea of (SRP). These two interfaces have methods that could be used by multiple classes which ensure the
idea of ISP and SRP. Since each interface has its own responsibility and each class will not depend on any methods that
it doesn't use. It makes the whole project structure more clear and readable. Moreover, set a default method log,
since each class needs to call it.
 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.register_for_class();
        student.pay_tuition();
        student.get_classroom();
        student.get_my_grade();
        student.log();
        System.out.println();

        Instructor instructor = new Instructor();
        instructor.get_enrollment_numbers();
        instructor.get_classroom();
        instructor.enter_grades();
        instructor.log();
        System.out.println();

        Registrar registrar = new Registrar();
        registrar.add_class_to_quarter_schedule();
        registrar.set_instructor_for_class();
        registrar.get_enrollment_numbers();
        registrar.log();
        registrar.run_class();
    }
}