package ATP2017.lab2.Question;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class QuestionIdle implements Istate{

    StudentSystem studentSystem;

    public QuestionIdle(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void doAction() {

    }
}
