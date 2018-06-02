package ATP2017.lab2.Article;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class Regular implements Istate {

    StudentSystem studentSystem;

    public Regular(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void doAction() {

    }
}
