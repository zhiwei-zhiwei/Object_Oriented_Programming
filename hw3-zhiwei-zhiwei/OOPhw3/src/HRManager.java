// In the original c# code, the basic class Instructor is an abstract class with two method which is ScheduleTeachingLoad
// and BecomeDepartmentChairperson. The first problem is that the ContractualInstructor class is the subclass of the basic
// class (Instructor) doesn't override all methods of the basic class. It missed the BecomeDepartmentChairperson method.
// However, The original c# code doesn't follow a legitimate object-oriented programming structure which violates the
// Liskov Substitution Principle (LSP). In this case, the only character who can "becomeDepartmentChairperson" is the
// "FullProfessor", so it is not necessary to create a method in the basic class. If so, it is kind of redundant, and
// let all other classes to do unnecessary actions.

// As a result, the revised solution for the original code is that remove the "becomeDepartmentChairperson" method from
// the basic class and then explicit the "becomeDepartmentChairperson" method for the FullProfessor class, since it is
// the only character could become a chairperson. Then based on the original main method, just call
// "becomeDepartmentChairperson" method when the instance class is FullProfessor.
import java.util.ArrayList;
import java.util.List;

public class HRManager {
    public static void main(String[] args) {
        System.out.println("Java version running");
        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(new FullProfessor());
        instructorList.add(new ContractualInstructor());
        instructorList.add(new TeachingAssistant());

        for (Instructor instructor : instructorList) {
            if (instructor instanceof FullProfessor) {
                System.out.println(((FullProfessor)instructor).becomeDepartmentChairperson(1245));
            }
        }
    }
}
