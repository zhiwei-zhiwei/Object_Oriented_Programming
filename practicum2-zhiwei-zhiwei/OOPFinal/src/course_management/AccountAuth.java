package course_management;

import dao.StudentDAO;

public class AccountAuth {
    StudentDAO studentDAO = new StudentDAO();

    public boolean isStudentUserNameValid(String userName) {
        return studentDAO.validUsername(userName) != -1;
    }

    public boolean isStudentPasswordMatches(String userName, String inputPassword){
        return studentDAO.getUserPassword(userName).equals(inputPassword);
    }

}
