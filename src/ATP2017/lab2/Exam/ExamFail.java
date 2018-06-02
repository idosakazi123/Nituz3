package ATP2017.lab2.Exam;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class ExamFail implements Istate{

    StudentSystem studentSystem;

    public ExamFail(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void enter() {
        studentSystem.setCurrentStateExam(this);
        System.out.println("enter " + this.toString() + " state"  );
    }

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
