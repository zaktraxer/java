package Home4.Controller;

import Home4.model.Teacher;
import Home4.service.TeacherService;
import Home4.service.UserService;

import java.util.List;

public class TeacherController implements UserController<Teacher> {

    public UserService<Teacher> teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber) {
        teacherService.create(fullName, age, phoneNumber);
    }

    @Override
    public List<Teacher> getAllUsersSortedByFirstName() {
        return teacherService.getAllUsersSortedByFirstName();
    }

    @Override
    public List<Teacher> getAllUsersSortedByLastName() {
        return teacherService.getAllUsersSortedByLastName();
    }

    @Override
    public List<Teacher> getAllUsersSortedByAge() {
        return teacherService.getAllUsersSortedByAge();
    }

    @Override
    public List<Teacher> getAllUsersSortedById() {
        return teacherService.getAllUsersSortedById();
    }

    @Override
    public boolean remove(String fullName) {
        teacherService.remove(fullName);
        return true;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherService.getAll();
    }

    @Override
    public void edit(String fullName, Integer age, String phoneNumber) {
        teacherService.edit(fullName, age, phoneNumber);
    }


}