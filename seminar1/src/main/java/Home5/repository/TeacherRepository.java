package Home5.repository;

import Home5.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements UserRepository<Teacher> {
    private static TeacherRepository INSTANCE;

    public static TeacherRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeacherRepository();
        }
        return INSTANCE;
    }

    private final List<Teacher> teachers;

    public TeacherRepository() {
        teachers = new ArrayList<>();
    }

    @Override
    public List<Teacher> getAll() {
        return teachers;
    }

    @Override
    public void add(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void remove(String name) {
        for (Teacher teacher : teachers) {
            if (teacher.getFullName().equals(name)) {
                teachers.remove(teacher);
                return;
            }
        }
    }

    @Override
    public Long getMaxId() {
        Long maxId = 0L;
        for (Teacher teacher : teachers) {
            if (teacher.getId() > maxId) {
                maxId = teacher.getId();
            }
        }
        return maxId;
    }

}