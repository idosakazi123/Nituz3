package ATP2017.lab2.Exam;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class PreExam implements Istate{

    StudentSystem studentSystem;

    public PreExam(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void doAction() {

    }
}
