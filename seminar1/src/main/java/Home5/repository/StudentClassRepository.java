package Home5.repository;

import Home5.model.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class StudentClassRepository implements TeamRepository<StudentClass> {
    private static StudentClassRepository INSTANCE;

    public static StudentClassRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentClassRepository();
        }
        return INSTANCE;
    }

    private final List<StudentClass> studentClasses;

    public StudentClassRepository() {
        this.studentClasses = new ArrayList<>();

    }

    @Override
    public List<StudentClass> getAll() {
        return studentClasses;
    }

    @Override
    public void add(StudentClass aStudentClass) {
        studentClasses.add(aStudentClass);
    }

    @Override
    public void remove(String teamName) {
        for (StudentClass studentClass : studentClasses) {
            if (studentClass.getTeamName().equals(teamName)) {
                studentClasses.remove(studentClass);
                return;
            }
        }
    }

    @Override
    public Long getMaxId() {
        Long maxId = 0L;
        for (StudentClass studentClass : studentClasses) {
            if (studentClass.getId() > maxId) {
                maxId = studentClass.getId();
            }
        }
        return maxId;
    }
}