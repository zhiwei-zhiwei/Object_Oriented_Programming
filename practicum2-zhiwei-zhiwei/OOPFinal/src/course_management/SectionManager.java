package course_management;

import dao.SectionDAO;
import interfaces.ISectionAddManagement;
import interfaces.ISectionStatus;

import java.sql.SQLException;

public class SectionManager implements ISectionAddManagement, ISectionStatus {

    private static SectionManager instance;
    private SectionDAO sectionDAO;

    private SectionManager() {
        sectionDAO = new SectionDAO();
    }

    public static SectionManager getInstance() {
        if (instance == null) {
            instance = new SectionManager();
        }
        return instance;
    }
    @Override
    public void addSection(Lab lab) {

    }

    @Override
    public void addSectionWithCapacity(Lab lab) {

    }

    @Override
    public void openedSection() {

    }

    @Override
    public void getAllSection() {

    }

    @Override
    public void getSectionWithSections() {

    }

    public int getIdByNum(String sectionId) throws SQLException {
        return 0;
    }

    public int getIdByName(String sectionName) throws SQLException {
        return 0;
    }

    public int getIdBySection(Lab lab) throws SQLException {
        return 0;
    }

    public void deleteSectionByNum(String sectionNum) {

    }

    public void deleteSectionByName(String sectionName) {

    }

    public void deleteSectionByCourse(Lab lab) {

    }

    public boolean isSectionFill(Lab lab) {
        return false;
    }
}
