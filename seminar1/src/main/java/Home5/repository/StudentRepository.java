package Home5.repository;

import Home5.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements UserRepository<Student> {
    private static StudentRepository INSTANCE;

    public static StudentRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StudentRepository();
        }
        return INSTANCE;
    }

    private final List<Student> students;

    public StudentRepository() {
        students = new ArrayList<>();
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public void remove(String fullName) {
        for (Student student : students) {
            if (student.getFullName().equals(fullName)) {
                students.remove(student);
                return;
            }
        }
    }

    @Override
    public Long getMaxId() {
        Long maxId = 0L;
        for (Student student : students) {
            if (student.getId() > maxId) {
                maxId = student.getId();
            }
        }
        return maxId;
    }

}