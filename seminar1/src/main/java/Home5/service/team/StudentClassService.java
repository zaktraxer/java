package Home5.service.team;

import Home5.model.Student;
import Home5.model.StudentClass;
import Home5.model.Teacher;
import Home5.model.Team;
import Home5.repository.StudentClassRepository;
import Home5.repository.StudentRepository;
import Home5.repository.TeacherRepository;
import Home5.repository.TeamRepository;
import Home5.service.comparator.StudentClassComparator;
import Home5.service.datareader.StudentClassesLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class StudentClassService implements TeamService<StudentClass>{
    private final Random random;

    private final TeamRepository<StudentClass> studentClassRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentClassService() {
        this.random = new Random();
        this.studentClassRepository = StudentClassRepository.getInstance();
        this.studentRepository = StudentRepository.getInstance();
        this.teacherRepository = TeacherRepository.getInstance();
    }

    @Override
    public void create(String teamName) {
        for (StudentClass studentClass : studentClassRepository.getAll()) {
            if (studentClass.getTeamName().equals(teamName)) {
                System.out.println("(createStudentClass:)'" + teamName + "' <- запись уже существует");
                return;
            }
        }
        Long id = studentClassRepository.getMaxId() + 1;
        int maxStudents = 30;
        int maxTeachers = 1;
        StudentClass studentClass = new StudentClass(id, teamName, maxStudents, maxTeachers,
                new ArrayList<>(), new ArrayList<>());
        studentClassRepository.add(studentClass);
    }

    @Override
    public void createAndPopulate(String teamName) {
        create(teamName);
        populateStudentClassById(getStudentClassByName(teamName).getId());
    }

    @Override
    public void populateStudentClassById(Long id) {
        StudentClass studentClass = getStudentClassById(id);
        int freeTeacherSlots = studentClass.getMaxTeachers() - studentClass.getTeacherRoster().size();
        int freeStudentSlots = studentClass.getMaxStudents() - studentClass.getStudentRoster().size();

        if (freeTeacherSlots > 0 && getAvailableTeachers().size() > 0) {
            do {
                addTeacher(getAvailableTeachers().get(random.nextInt(getAvailableTeachers().size())).getId(), id);
                freeTeacherSlots = studentClass.getMaxTeachers() - studentClass.getTeacherRoster().size();
            } while (freeTeacherSlots > 0 && getAvailableTeachers().size() > 0);
        }
        if (freeStudentSlots > 0 && getAvailableStudents().size() > 0) {
            do {
                addStudent(
                        getAvailableStudents().
                                get(random.nextInt(getAvailableStudents().size())).getId(), id);
                freeStudentSlots = studentClass.getMaxStudents() - studentClass.getStudentRoster().size();
            } while (freeStudentSlots > 0 && getAvailableStudents().size() > 0);
        }
    }


    @Override
    public void load() {
        try {
            StudentClassesLoader classLoader = new StudentClassesLoader();
            for (int i = 0, max = classLoader.getData().size(); i < max; i++) {
                create(classLoader.getData().get(i));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentClass> getAll() {
        return studentClassRepository.getAll();
    }

    @Override
    public StudentClass getStudentClassById(Long id) {
        for (StudentClass studentClass : studentClassRepository.getAll()) {
            if (studentClass.getId().equals(id)) {
                return studentClass;
            }
        }
        System.out.println("(DB/StudentGroup:) <- id '" + id + "' не найден");
        return null;
    }

    @Override
    public StudentClass getStudentClassByName(String teamName) {
        for (StudentClass studentClass : studentClassRepository.getAll()) {
            if (studentClass.getTeamName().equals(teamName)) {
                return studentClass;
            }
        }
        System.out.println("(DB/StudentGroup:) <- teamName '" + teamName + "' не найден");
        return null;
    }

    @Override
    public void addTeacher(Long teacherId, Long studentClassId) {
        Teacher teacher = null;
        StudentClass studentClass = null;
        for (Teacher aTeacher : teacherRepository.getAll()) {
            if (aTeacher.getId().equals(teacherId)) {
                teacher = aTeacher;
                break;
            }
        }
        if (teacher == null) {
            System.out.println("(addTeacher:) teacherId '" + teacherId + "' <- запись не найдена");
            return;
        }
        if (teacher.getTeam_id() != null) {
            System.out.println("(addTeacher:) teacherId '" + teacherId + "'-'" + teacher.getFullName()
                    + "' <- уже добавлен в группу '" + teacher.getTeam_id() + "'");
            return;
        }
        for (StudentClass aStudentClass : studentClassRepository.getAll()) {
            if (aStudentClass.getId().equals(studentClassId)) {
                studentClass = aStudentClass;
            }
        }
        if (studentClass == null) {
            System.out.println("(addTeacher:) studentClassId '" + studentClassId + "' <- запись не найдена");
            return;
        }
        if (studentClass.getTeacherRoster().size() >= studentClass.getMaxTeachers()) {
            System.out.println("(addTeacher:) studentClassId '" + studentClassId
                    + "' <- уже имеет максимальное число преподавателей");
            return;
        }
        studentClass.getTeacherRoster().add(teacher);
        teacher.setTeam_id(studentClassId);
    }

    @Override
    public void addTeacher(String teacherName, Long studentClassId) {
        //ToDo
    }

    @Override
    public void addStudent(Long studentId, Long studentClassId) {
        Student student = null;
        StudentClass studentClass = null;
        for (Student aStudent : studentRepository.getAll()) {
            if (aStudent.getId().equals(studentId)) {
                student = aStudent;
                break;
            }
        }
        if (student == null) {
            System.out.println("(addStudent:) studentId '" + studentId + "' <- запись не найдена");
        }
        if (student != null && student.getTeam_id() != null) {
            System.out.println("(addStudent:) studentId '" + studentId + "'-'" + student.getFullName()
                    + "' <- уже добавлен в группу '" + student.getTeam_id() + "'");
            return;
        }
        for (StudentClass aStudentClass : studentClassRepository.getAll()) {
            if (aStudentClass.getId().equals(studentClassId)) {
                studentClass = aStudentClass;
            }
        }
        if (studentClass == null) {
            System.out.println("(addStudent:) studentClassId '" + studentClassId + "' <- запись не найдена");
            return;
        }
        if (studentClass.getStudentRoster().size() >= studentClass.getMaxStudents()) {
            System.out.println("(addStudent:) studentClassId '" + studentClassId
                    + "' <- уже имеет максимальное число студентов");
            return;
        }
        studentClass.getStudentRoster().add(student);
        assert student != null;
        student.setTeam_id(studentClassId);
    }

    @Override
    public void addStudent(String studentName, Long studentClassId) {
        // ToDo
    }

    @Override
    public void setStudentClassTeacherLimitById(Long id, int limit) {
        getStudentClassById(id).setMaxTeachers(limit);
    }

    @Override
    public Long getStudentClassIdByUserName(String fullName) {
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher.getFullName().equals(fullName)) {
                return teacher.getTeam_id();
            }
        }
        for (Student student : studentRepository.getAll()) {
            if (student.getFullName().equals(fullName)) {
                return student.getTeam_id();
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudentClassStudentsList(StudentClass studentClass) {
        List<Student> students = new ArrayList<>();
        for (Student student : studentRepository.getAll()) {
            if (student.getTeam_id() != null && student.getTeam_id().equals(studentClass.getId())) {
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public List<Teacher> getStudentClassTeachersList(StudentClass studentClass) {
        List<Teacher> teachers = new ArrayList<>();
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher.getTeam_id() != null && teacher.getTeam_id().equals(studentClass.getId())) {
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    @Override
    public List<StudentClass> getAllTeamsSortedById() {
        List<StudentClass> studentClasses = studentClassRepository.getAll();
        studentClasses.sort(Comparator.comparing(Team::getId));
        return studentClasses;
    }

    @Override
    public List<StudentClass> getAllTeamsSortedByTeamName() {
        List<StudentClass> studentClasses = studentClassRepository.getAll();
        studentClasses.sort(new StudentClassComparator<>());
        return studentClasses;
    }

    @Override
    public void remove(String teamName) {
        studentClassRepository.remove(teamName);
    }

    public List<Student> getAvailableStudents() {
        List<Student> studentList = new ArrayList<>();
        for (Student student : studentRepository.getAll()) {
            if (student.getTeam_id() == null) {
                studentList.add(student);
            }
        }
        return studentList;
    }

    public List<Teacher> getAvailableTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        for (Teacher teacher : teacherRepository.getAll()) {
            if (teacher.getTeam_id() == null) {
                teacherList.add(teacher);
            }
        }
        return teacherList;
    }

}