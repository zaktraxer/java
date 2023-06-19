package Home5.view;

import Home5.controller.UserController;
import Home5.model.Teacher;

import java.util.List;

public class TeacherView implements UserView<Teacher> {

    UserController<Teacher> controller;

    public TeacherView(UserController<Teacher> controller) {
        this.controller = controller;
    }


    @Override
    public void sendOnConsole(String sortType) {
        int separatorLength = 39;
        List<Teacher> teachers = switch (sortType) {
            case SortType.NONE -> controller.getAll();
            case SortType.ID -> controller.getAllUsersSortedById();
            case SortType.FIRSTNAME -> controller.getAllUsersSortedByFirstName();
            case SortType.LASTNAME -> controller.getAllUsersSortedByLastName();
            case SortType.AGE -> controller.getAllUsersSortedByAge();
            default -> null;
        };
        if (teachers == null || teachers.size() < 1) {
            System.out.println("(DB:) преподаватели отсутствуют");
            return;
        }
        System.out.println("-".repeat(separatorLength));
        System.out.println("Преподаватели:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        System.out.println("-".repeat(separatorLength));
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber) {
        controller.create(fullName, age, phoneNumber);
    }

    @Override
    public void createRandom(Integer quantity) {
        controller.createRandom(quantity);
    }

    @Override
    public void remove(String fullName) {
        controller.remove(fullName);
    }

    @Override
    public void edit(String fullName, Integer age, String phoneNumber) {
        controller.edit(fullName, age, phoneNumber);
    }
}