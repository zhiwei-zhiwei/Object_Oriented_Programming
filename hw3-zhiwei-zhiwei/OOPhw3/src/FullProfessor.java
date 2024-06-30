public class FullProfessor extends Instructor{
    @Override
    public String scheduleTeachingLoad(int instructorId) {
        return "Teaching Load is Scheduled";
    }

    public String becomeDepartmentChairperson(int instructorId) {
        return "Dame.";
    }
}
