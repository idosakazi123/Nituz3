package ATP2017.lab2.Forum;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class ForumNearlyActive implements Istate{

    StudentSystem studentSystem;

    public ForumNearlyActive(StudentSystem newStudentSystem) {
        studentSystem = newStudentSystem;
    }

    @Override
    public void doAction() {

    }
}
