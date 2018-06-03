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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentSystem implements IstudentSystem {
    public int bonus;
    public int week;
    public int article;
    public Istate diligent;
    public Istate noArticle;
    public Istate regular;
    public Istate researcher;
    public Istate examExcellence;
    public Istate examFail;
    public Istate examPass;
    public Istate preExam;
    public Istate forumActive;
    public Istate forumNearlyActive;
    public Istate forumNoActive;
    public Istate questionAnswer;
    public Istate questionIdle;


    private Istate currentStateQuestionnaire;
    private Istate currentStateArticle;
    private Istate currentStateForum;
    private Istate currentStateExam;

    Thread questionnaireThread;
    Thread ArticleThread;
    Thread forumThread;
    Thread examThread;

    public StudentSystem() {
        bonus = 0;
        week = 1;
        article =0;

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

        currentStateQuestionnaire = this.questionIdle;
        currentStateArticle = this.noArticle;
        currentStateForum = this.forumNoActive;
        currentStateExam = this.preExam;



        questionnaireThread = new Thread(() -> currentStateQuestionnaire.enter());
        ArticleThread = new Thread(() -> currentStateArticle.enter() );
        forumThread = new Thread(() -> currentStateForum.enter() );
        examThread = new Thread(() -> currentStateExam.enter());

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(questionnaireThread);
        executor.execute(ArticleThread);
        executor.execute(forumThread);
        executor.execute(examThread);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            try{
                String command = reader.readLine();

                if(command.startsWith("/exam ")){
                    int score = Integer.parseInt(command.substring(6));
                    exam(score);
                }
                else if(command.equals("/read")){
                    read();
                }
                else if(command.startsWith("/post ")){
                    String text = new String(command.substring(6));
                    post(text);
                }
                else if(command.equals("/answer")){
                    answer();
                }
                else if(command.equals("/next_week")){
                    next_week();
                }
                else if(command.startsWith("/status ")){
                    String text = new String(command.substring(8));
                    status(text);
                }
                else{
                    throw new Exception("command is not valid, try again");
                }
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }

        //reader.close();
        //executor.shutdown();
    }

    public void setCurrentStateQuestionnaire(Istate newState){
        this.currentStateQuestionnaire = newState;
    }

    public Istate getCurrentStateQuestionnaire(){
        return this.currentStateQuestionnaire;
    }

    public void setCurrentStateArticle(Istate newState){
        this.currentStateArticle = newState;
    }

    public Istate getCurrentStateArticle(){
        return this.currentStateArticle;
    }

    public void setCurrentStateForum(Istate newState){
        this.currentStateForum = newState;
    }

    public Istate getCurrentStateForum(){
        return this.currentStateForum;
    }

    public void setCurrentStateExam(Istate newState){
        this.currentStateExam = newState;
    }

    public Istate getCurrentStateExam(){
        return this.currentStateExam;
    }

    @Override
    public void exam(int score) {
        // exam region
        if(currentStateExam.toString().equals("PreExam") && !currentStateArticle.toString().equals("NoArticle")){
            if(score<56 ){
                examFail.enter();
            }else if((56<score+bonus) && (score+bonus<90) ){
                examPass.enter();
            }else if(score+bonus >=90){
                examExcellence.enter();
            }
        }
    }

    @Override
    public void read() {
        article++;
        // article region
        if(currentStateArticle.toString().equals("NoArticle")){
            regular.enter();
        }else if(currentStateArticle.toString().equals("Regular")&& article < 3){
            regular.enter();
        }
        else if(currentStateArticle.toString().equals("Regular")&& article == 3){
            diligent.enter();
        }else if(currentStateArticle.toString().equals("Diligent")&& article < 8){
            diligent.enter();
        }else if(currentStateArticle.toString().equals("Diligent")&& article == 8){
            researcher.enter();
        }else if((currentStateArticle.toString().equals("Researcher") || currentStateArticle.toString().equals("Diligent"))&& article < 10){
            researcher.enter();
        }
    }

    @Override
    public void post(String text) {
        // forum region
        if(currentStateForum.toString().equals("ForumNoActive")){
            forumNearlyActive.enter();
        }else if(currentStateForum.toString().equals("ForumNearlyActive")){
            forumActive.enter();
        }else if(currentStateForum.toString().equals("ForumActive")){
            forumActive.enter();
        }
    }

    @Override
    public void answer() {
        // questionnaire region
        if(currentStateQuestionnaire.toString().equals("QuestionAnswer")){
            bonus++;
            questionIdle.enter();
        }
    }

    @Override
    public void next_week() {
        week++;
        if(week <13){
            // check if the threads is not insert to other region
            // questionnaire region
            if(currentStateQuestionnaire.toString().equals("QuestionIdle") || currentStateQuestionnaire.toString().equals("QuestionAnswer")){
                questionAnswer.enter();
            }

            // forum region

            if(currentStateForum.toString().equals("ForumNoActive") || currentStateForum.toString().equals("ForumNearlyActive") || currentStateForum.toString().equals("ForumActive")) {
                forumNoActive.enter();
            }
        }



    }

    @Override
    public void status(String text) {
        StringBuilder sb = new StringBuilder(text);
        //add Article region status
        if(currentStateArticle.toString().equals("Diligent")){
            sb.insert(0,"*");
            sb.append("*");
        }
        else if(currentStateArticle.toString().equals("Researcher")){
            sb.insert(0,"**");
            sb.append("**");
        }

        //add Forum region status
        if(currentStateForum.toString().equals("ForumActive")){
            sb.append("(active)");
        }

        //add Article Exam status
        if(currentStateExam.toString().equals("ExamPass")){
            sb.append(":-)");
        }
        else if(currentStateExam.toString().equals("ExamExcellence")){
            sb.insert(0,"^^^");
            sb.append("^^^");
        }

        System.out.println(sb.toString());

    }
}
