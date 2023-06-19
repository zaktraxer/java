package Home5.service.iterator;

import Home5.model.StudentClass;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentClassGroupIterator implements Iterator<StudentClass> {

    private final List<StudentClass> studentClasses;
    private int position;

    public StudentClassGroupIterator(List<StudentClass> studentClasses) {
        this.studentClasses = studentClasses;
    }

    @Override
    public boolean hasNext() {
        return position < studentClasses.size();
    }

    @Override
    public StudentClass next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return studentClasses.get(position++);
    }

    @Override
    public void remove() {
        studentClasses.remove(position - 1);
    }
}
