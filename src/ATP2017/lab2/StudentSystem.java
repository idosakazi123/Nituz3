package ATP2017.lab2;

import ATP2017.lab2.Article.Diligent;
import ATP2017.lab2.Article.NoArticle;
import ATP2017.lab2.Article.Regular;
import ATP2017.lab2.Article.Researcher;
import ATP2017.lab2.Exam.ExamExcellence;
import ATP2017.lab2.Exam.ExamFail;
import ATP2017.lab2.Exam.ExamPass;
import ATP2017.lab2.Exam.PreExam;
import ATP2017.lab2.Forum.ForumActive;
import ATP2017.lab2.Forum.ForumNearlyActive;
import ATP2017.lab2.Forum.ForumNoActive;
import ATP2017.lab2.Question.QuestionAnswer;
import ATP2017.lab2.Question.QuestionIdle;

public class StudentSystem implements IstudentSystem {

    Istate diligent;
    Istate noArticle;
    Istate regular;
    Istate researcher;
    Istate examExcellence;
    Istate examFail;
    Istate examPass;
    Istate preExam;
    Istate forumActive;
    Istate forumNearlyActive;
    Istate forumNoActive;
    Istate questionAnswer;
    Istate questionIdle;


    Istate state;

    public StudentSystem() {
        diligent = new Diligent(this);
        noArticle = new NoArticle(this);
        regular = new Regular(this);
        researcher = new Researcher(this);
        examExcellence = new ExamExcellence(this);
        examFail = new ExamFail(this);
        examPass = new ExamPass(this);
        preExam = new PreExam(this);
        forumActive = new ForumActive(this);
        forumNearlyActive = new ForumNearlyActive(this);
        forumNoActive = new ForumNoActive(this);
        questionAnswer = new QuestionAnswer(this);
        questionIdle = new QuestionIdle(this);
    }

    public void setState(Istate newState){
        this.state = state;
    }

    public Istate getState(){
        return state;
    }


    @Override
    public void exam(int score) {

    }

    @Override
    public void read() {

    }

    @Override
    public void post(String text) {

    }

    @Override
    public void answer() {

    }

    @Override
    public void next_week() {

    }

    @Override
    public void status(String text) {

    }
}
