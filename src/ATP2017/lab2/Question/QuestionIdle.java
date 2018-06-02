package ATP2017.lab2.Question;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class QuestionIdle implements Istate{

    StudentSystem studentSystem;

    public QuestionIdle(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void enter() {
        studentSystem.setCurrentStateQuestionnaire(this);
        System.out.println("enter "+ this.toString() + " state");
    }

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
