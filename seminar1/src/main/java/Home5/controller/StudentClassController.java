package Home5.controller;

import Home5.model.Student;
import Home5.model.StudentClass;
import Home5.model.Teacher;
import Home5.service.team.TeamService;

import java.util.List;

public class StudentClassController implements TeamController<StudentClass> {

    public TeamService<StudentClass> studentClassService;

    public StudentClassController(TeamService<StudentClass> studentClassService) {
        this.studentClassService = studentClassService;
    }

    @Override
    public void create(String teamName) {
        studentClassService.create(teamName);
    }

    @Override
    public void createAndPopulate(String teamName) {
        studentClassService.createAndPopulate(teamName);
    }

    @Override
    public void populateStudentClassById(Long id) {
        studentClassService.populateStudentClassById(id);
    }

    @Override
    public void load() {
        studentClassService.load();
    }

    @Override
    public List<StudentClass> getAllTeamsSortedById() {
        return studentClassService.getAllTeamsSortedById();
    }

    @Override
    public List<StudentClass> getAllTeamsSortedByTeamName() {
        return studentClassService.getAllTeamsSortedByTeamName();
    }

    @Override
    public void remove(String teamName) {
        studentClassService.remove(teamName);
    }

    @Override
    public List<StudentClass> getAll() {
        return studentClassService.getAll();
    }

    @Override
    public StudentClass getStudentClassById(Long id) {
        return studentClassService.getStudentClassById(id);
    }

    @Override
    public void addTeacher(Long teacherId, Long studentClassId) {
        studentClassService.addTeacher(teacherId, studentClassId);
    }

    @Override
    public void addTeacher(String teacherName, Long studentClassId) {
        studentClassService.addTeacher(teacherName, studentClassId);
    }

    @Override
    public void addStudent(Long studentId, Long studentClassId) {
        studentClassService.addStudent(studentId, studentClassId);
    }

    @Override
    public void addStudent(String studentName, Long studentClassId) {
        studentClassService.addStudent(studentName, studentClassId);
    }

    @Override
    public void setStudentClassTeacherLimitById(Long id, int limit) {
        studentClassService.setStudentClassTeacherLimitById(id, limit);
    }

    @Override
    public Long getStudentClassIdByUserName(String fullName) {
        return studentClassService.getStudentClassIdByUserName(fullName);
    }


    public List<Student> getStudentClassStudentsList(StudentClass studentClass) {
        return studentClassService.getStudentClassStudentsList(studentClass);
    }

    public List<Teacher> getStudentClassTeachersList(StudentClass studentClass) {
        return studentClassService.getStudentClassTeachersList(studentClass);
    }

}