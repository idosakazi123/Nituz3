package ATP2017.lab2.Question;

import ATP2017.lab2.StudentSystem;
import ATP2017.lab2.Istate;

public class QuestionAnswer implements Istate {

    StudentSystem studentSystem;

    public QuestionAnswer(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void doAction() {
        java.lang.System.out.println("student is in Question Answer");
        studentSystem.setState(this);
    }


    public String toString(){
        return "Question Answer";
    }
}
