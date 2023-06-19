package Home4.service;

import Home4.model.Student;
import Home4.model.User;
import Home4.repository.StudentRepository;
import Home4.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentService implements UserService<Student> {

    private final UserRepository<Student> studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber) {
        for (Student student : studentRepository.getAll()) {
            if (student.getFullName().equals(fullName)) {
                System.out.println("(create:)'"+fullName+ "' <- запись уже существует");
                return;
            }
        }
        Long id = studentRepository.getMaxId() + 1;
        Student student = new Student(id, fullName, age, phoneNumber);
        studentRepository.add(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.getAll();
    }

    @Override
    public List<Student> getAllUsersSortedByFirstName() {
        List<Student> students = studentRepository.getAll();
        Collections.sort(students);

        return students;
    }

    @Override
    public List<Student> getAllUsersSortedByLastName() {
        List<Student> students = studentRepository.getAll();
        students.sort(new UserComparator<>());

        return students;
    }

    @Override
    public List<Student> getAllUsersSortedByAge() {
        List<Student> students = studentRepository.getAll();
        students.sort((Comparator.comparing(User::getAge)));

        return students;
    }

    @Override
    public List<Student> getAllUsersSortedById() {
        List<Student> students = studentRepository.getAll();
        students.sort((Comparator.comparing(User::getId)));

        return students;
    }

    @Override
    public void remove(String fullName) {
        studentRepository.remove(fullName);
    }

    @Override
    public void edit(String fullName, Integer age, String phoneNumber) {
        for (Student student : studentRepository.getAll()) {
            if (student.getFullName().equals(fullName)) {
                System.out.println("(edit:)'"+fullName+ "' <- запись найдена");
                System.out.println("(edit:)*поля age и phoneNumber обновлены*");
                Long id = student.getId();
                studentRepository.remove(fullName);
                Student editedStudent = new Student(id, fullName, age, phoneNumber);
                studentRepository.add(editedStudent);
                return;
            }
        }
        System.out.println("(edit:)'"+fullName+ "' <- запись не найдена");
    }
}