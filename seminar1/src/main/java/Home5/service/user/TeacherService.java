package Home5.service.user;

import Home5.model.Teacher;
import Home5.model.User;
import Home5.repository.TeacherRepository;
import Home5.repository.UserRepository;
import Home5.service.comparator.UserComparator;
import Home5.service.randomizer.TeacherRandomizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeacherService implements UserService<Teacher> {

    private final UserRepository<Teacher> teacherRepository;
    private final TeacherRandomizer teacherRandomizer;

    public TeacherService() throws IOException {
        this.teacherRepository = TeacherRepository.getInstance();
        this.teacherRandomizer = new TeacherRandomizer();
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber) {
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher.getFullName().equals(fullName)) {
                System.out.println("(createTeacher:)'" + fullName + "' <- запись уже существует");
                return;
            }
        }
        Long id = teacherRepository.getMaxId() + 1;
        Teacher teacher = new Teacher(id, fullName, age, phoneNumber);
        teacherRepository.add(teacher);
    }

    @Override
    public void createRandom(Integer quantity) {
        for (int i = 0; i < quantity; i++) {
            create(teacherRandomizer.getData().get(0),
                    Integer.valueOf(teacherRandomizer.getData().get(1)),
                    teacherRandomizer.getData().get(2)
            );
        }
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.getAll();
    }

    @Override
    public List<Teacher> getAllUsersSortedByFirstName() {
        List<Teacher> teachers = teacherRepository.getAll();
        Collections.sort(teachers);

        return teachers;
    }

    @Override
    public List<Teacher> getAllUsersSortedByLastName() {
        List<Teacher> teachers = teacherRepository.getAll();
        teachers.sort(new UserComparator<>());

        return teachers;
    }

    @Override
    public List<Teacher> getAllUsersSortedByAge() {
        List<Teacher> teachers = teacherRepository.getAll();
        teachers.sort((Comparator.comparing(User::getAge)));

        return teachers;
    }

    @Override
    public List<Teacher> getAllUsersSortedById() {
        List<Teacher> teachers = teacherRepository.getAll();
        teachers.sort((Comparator.comparing(User::getId)));

        return teachers;
    }

    @Override
    public void remove(String fullName) {
        teacherRepository.remove(fullName);
    }

    @Override
    public void edit(String fullName, Integer age, String phoneNumber) {
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher.getFullName().equals(fullName)) {
                System.out.println("(editTeacher:)'" + fullName + "' <- запись найдена");
                System.out.println("(editTeacher:)*поля age и phoneNumber обновлены*");
                Long id = teacher.getId();
                teacherRepository.remove(fullName);
                Teacher editedTeacher = new Teacher(id, fullName, age, phoneNumber);
                teacherRepository.add(editedTeacher);
                return;
            }
        }
        System.out.println("(editTeacher:)'" + fullName + "' <- запись не найдена");
    }

    @Override
    public List<Teacher> getAvailableUsers() {
        List<Teacher> teacherList = new ArrayList<>();
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher.getTeam_id() == null) {
                teacherList.add(teacher);
            }
        }
        return teacherList;
    }
}
