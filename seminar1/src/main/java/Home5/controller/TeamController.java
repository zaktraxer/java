package Home5.controller;

import Home5.model.Student;
import Home5.model.Teacher;
import Home5.model.Team;

import java.util.List;

public interface TeamController<T extends Team> {

    void create(String teamName);

    void createAndPopulate(String teamName);

    void populateStudentClassById(Long id);

    void load();

    List<T> getAllTeamsSortedById();

    List<T> getAllTeamsSortedByTeamName();

    void remove(String teamName);

    List<T> getAll();

    T getStudentClassById(Long id);

    void addTeacher(Long teacherId, Long studentClassId);

    void addTeacher(String teacherName, Long studentClassId);

    void addStudent(Long studentId, Long studentClassId);

    void addStudent(String studentName, Long studentClassId);

    void setStudentClassTeacherLimitById(Long id, int limit);

    Long getStudentClassIdByUserName(String fullName);

    List<Student> getStudentClassStudentsList(T studentClass);

    List<Teacher> getStudentClassTeachersList(T studentClass);
}