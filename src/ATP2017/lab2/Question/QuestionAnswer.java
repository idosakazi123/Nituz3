package ATP2017.lab2.Question;

import ATP2017.lab2.StudentSystem;
import ATP2017.lab2.Istate;

public class QuestionAnswer implements Istate {

    StudentSystem studentSystem;

    public QuestionAnswer(StudentSystem newStudentSystem) {
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
