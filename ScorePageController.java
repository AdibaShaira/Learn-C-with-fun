
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ScorePageController {

    public Score s = new Score();
    private NetworkUtil NS = Main.ns;

    private int Scorefile1 = 0;
    private int Scorefile2 = 0;
    private int Scorefile3 = 0;
    private int ans;
    private String mode;
    public Score score;
    private String examMode;
    private String user;

    public Score getS() {
        return s;
    }
    @FXML
    Label UserName;
    @FXML
    Label Mode;

    @FXML
    Label Score;
    @FXML
    Button FinishButton;
    @FXML
    Button LogOut;
    @FXML
    Label history;

    public void initialize() {
        mode = Main.getExamMode();
        ans = Main.getCorrectAns();
        UserName.setText("Name: " + Main.getName());
        // long time= (Main.getEndTime()-Main.getStartTime())/1000;
        // Time.setText("Time Taken: "+String.valueOf(time)+" s");
        Mode.setText("Exam Mode: " + Main.getExamMode ());
        //NumberOfQuestions.setText("Number Of Questions: "+Main.getNumOfQuestion());
        Score.setText("Correct Answers: " + Main.getCorrectAns());
        if(mode.equals ("Basic"))
        { history.setText ("HighScore:"+Main.levelMarks.get(1));}
        if(mode.equals ("Loop"))
        { history.setText ("HighScore:"+Main.levelMarks.get(3));}
        if(mode.equals ("Array"))
        { history.setText ("HighScore:"+Main.levelMarks.get(5));}
        if(mode.equals ("Condition"))
        { history.setText ("HighScore:"+Main.levelMarks.get(7));}
        if(mode.equals ("InputOutput"))
        { history.setText ("HighScore:"+Main.levelMarks.get(9));}


    }

    @FXML

    public void FinishAction() throws IOException {
        //user = Main.getName();
        NS = Main.ns;
        //NS.write(ans);
        //System.out.println("exam sesh " + ans);
        //new Submit(s, NS);
        Main.HomePage();
    }

    @FXML

    public void LogOutAction() throws IOException {
        Main.StartPage();
    }

}
