package ATP2017.lab2.Article;

import ATP2017.lab2.Istate;
import ATP2017.lab2.StudentSystem;

public class NoArticle implements Istate {
    StudentSystem studentSystem;

    public NoArticle(StudentSystem newStudentSystem) {

        studentSystem = newStudentSystem;
    }

    @Override
    public void enter() {
        studentSystem.setCurrentStateArticle(this);
        System.out.println("enter " + this.toString() + " state"  );
    }

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
