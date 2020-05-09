
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;

public class Main extends Application {

    public static LinkedList<Integer> levelMarks = new LinkedList<>();
    public Socket s;
    public static NetworkUtil ns;
    private static Stage stage;
    static AnchorPane LogInPage, SignUpPage, SignUpSuccessPage, LogInSuccessPage, HomePage,
            AfterExamPage,HistoryPage;
    //static Pane LogInPage;
    private static int numOfQuestion = 10;
    public static int idx;
    //static Pane AfterPracticePage,QuestionAddPage,AfterQuestionAddPage,StatisticsPage;
    private static LogINController login;
    private static String Name, ExamMode;
    private static int CorrectAns;
    
    public static int getNumOfQuestion() {
        return numOfQuestion;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static void setNumOfQuestion(int n) {
        numOfQuestion = n;
    }

    public static void setExamMode(String mode) {
        ExamMode = mode;
    }

    public static String getExamMode() {
        return ExamMode;
    }

    public static int getCorrectAns() {
        return CorrectAns;
    }

    public static void main(String[] args) {
        launch(args);
    }
    MediaPlayer musicplayer; {



        Media mp3MusicFile = new Media(getClass().getResource("POL-doggo-brothers-short.wav").toExternalForm());

        musicplayer = new MediaPlayer(mp3MusicFile);
        musicplayer.setAutoPlay(true);
        musicplayer.setVolume(0.3);   // from 0 to 1

        //***************** loop (repeat) the music  ******************
        musicplayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                musicplayer.seek(Duration.ZERO);
            }
        });
        //*************** end of loop (repeat) the music  **************

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        StartPage();
    }

    //page 1, contains login,sign up button
    public static void StartPage() throws IOException {

        ns = new NetworkUtil("127.0.0.1", 33333);
        System.out.println("client has created");
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("Login1.fxml"));
        LogInPage = loader.load();
        Scene scene = new Scene(LogInPage);
        stage.setScene(scene);
        stage.show();

    }

    //signup page
    public static void SignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SignUpPage.fxml"));
        SignUpPage = loader.load();
        Scene scene = new Scene(SignUpPage);
        stage.setScene(scene);
        stage.show();

    }

    // page 3, if sign in is successful
    public static void SignUpSuccess() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SignUpSuccessful.fxml"));
        SignUpSuccessPage = loader.load();
        Scene scene = new Scene(SignUpSuccessPage);
        stage.setScene(scene);
        stage.show();

    }

    public static void LogInSuccess() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("LoginSuccessful.fxml"));
        LogInSuccessPage = loader.load();
        Scene scene = new Scene(LogInSuccessPage);
        stage.setScene(scene);
        stage.show();

    }

    //switches to the home page
    public static void HomePage() throws IOException {

        System.out.println(getName());
//        getLevelandMarksfromFile();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Menu.fxml"));
        HomePage = loader.load();
        Scene scene = new Scene(HomePage);

        stage.setScene(scene);

        stage.show();
    }
    public static void HistoryPage() throws IOException {

        System.out.println(getName());
//        getLevelandMarksfromFile();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("HistoryPage.fxml"));
        HistoryPage = loader.load();
        Scene scene = new Scene(HistoryPage);

        stage.setScene(scene);

        stage.show();
    }

    public static void CloseProgram() throws IOException {
        ns.close();
        stage.close();
    }

    // practice page
    public static void PracticeQuestion() throws IOException {

        Questionpage q = new Questionpage();
        CorrectAns = 0;
        q = (Questionpage) ns.read();
      
        System.out.println("QPAGE");

        ArrayList<ToggleGroup> toggle = new ArrayList<>();
        ArrayList<Label> QuestionsLabel = new ArrayList<>();
        ArrayList<String> RightAnswers = new ArrayList<>();
        ArrayList<RadioButton> OptionsLabel = new ArrayList<>();
        ArrayList<RadioButton> QuesAnswered = new ArrayList<>();
        ArrayList<String> QuestionString = new ArrayList<>();
        ArrayList<String> OptionString = new ArrayList<>();

        QuestionString = q.QuestionsList;
        RightAnswers = q.RightansList;
        OptionString = q.OptionsList;
        System.out.println(QuestionString.size());
        ArrayList<String> finalQuestionString = QuestionString;
        ArrayList<String> finalOptionString = OptionString;
        ArrayList<String> finalRightAnswers1 = RightAnswers;
        Vector<Integer> Clicked = new Vector<>();

        //in the uper portion of question page
        numOfQuestion = QuestionString.size();
        VBox Box = new VBox(10);
        VBox ques = new VBox(10);
        final ScrollPane sp = new ScrollPane();
        HBox QuestionNumber = new HBox(180);

        Label Number = new Label("Number of Questions : " + QuestionString.size());
        Number.setFont(Font.font("Bodoni MT", 25));
       Label answeredQuestion = new Label("Subject: GRE");
        Number.setFont(Font.font("Bodoni MT", 20));
        answeredQuestion.setFont(Font.font("Bodoni MT", 20));
        QuestionNumber.getChildren().addAll(Number, answeredQuestion);
        QuestionNumber.setAlignment(Pos.BASELINE_LEFT);
        ques.setPadding(new Insets(10));
        Scene scene = new Scene(Box, 640, 480);
        stage.setScene(scene);
        stage.show();
        HBox up = new HBox(70);
        Label practice = new Label("Mode: " + ExamMode);
        practice.setFont(Font.font("Bodoni MT", 25));
        Label name = new Label("Name:" + Name);
        Button Refresh = new Button("Refresh");
        //Label subject= new Label("Subject: "+Subject);
        name.setFont(Font.font("Bodoni MT", 25));
        // subject.setFont(Font.font("Bodoni MT",25));
        up.getChildren().addAll(practice, name, Refresh);
        HBox forButtons = new HBox(450);

        Button Back = new Button("Back");
        Button Show = new Button("Show Answer");
        Button Finish = new Button("Finish");
        forButtons.getChildren().addAll(Show);
        forButtons.getChildren().addAll(Back);
        forButtons.getChildren().addAll(Finish);

        Refresh.setDisable(true);
        //ques.getChildren().add(up);
        // ques.getChildren().addAll(QuestionNumber);
        Box.setPadding(new Insets(10));
        Box.getChildren().addAll(up, QuestionNumber, sp, forButtons);
        VBox.setVgrow(sp, Priority.ALWAYS);

        //here comes the question part
        for (int j = 0; j < QuestionString.size(); j++) {
            Label QuesLine = new Label();
            QuesLine.setText((j + 1) + "." + QuestionString.get(j));

            QuestionsLabel.add(QuesLine);
        }
        for (int j = 0; j < QuestionsLabel.size(); j++) {
            ToggleGroup mygroup = new ToggleGroup();
            toggle.add(mygroup);
        }

        System.out.println(OptionString.size());
        for (int j = 0; j < OptionString.size(); j++) {

            RadioButton option = new RadioButton();
            option.setText(OptionString.get(j));
            System.out.println(option.getText());
            //System.out.println("j "+j);
            System.out.println(j / 4);
            option.setToggleGroup(toggle.get(j / 4));
            OptionsLabel.add(option);
            Clicked.add(j, 0);
        }

        for (int j = 0, k = 0; j < QuestionsLabel.size(); j++, k += 4) {
            ques.getChildren().addAll(QuestionsLabel.get(j));
            ques.getChildren().addAll(OptionsLabel.get(k));
            ques.getChildren().addAll(OptionsLabel.get(k + 1));
            ques.getChildren().addAll(OptionsLabel.get(k + 2));
            ques.getChildren().addAll(OptionsLabel.get(k + 3));
        }

        sp.setVmax(440);
        sp.setPrefSize(440, 150);
        sp.setContent(ques);
        int[] answeredprev = new int[10000];
        int[] answered = new int[QuestionString.size()];
        for (int j = 0; j < answered.length; j++) {
            answered[j] = 0;
        }
        for (int j = 0; j < OptionsLabel.size(); j++) {
            final int jj = j;
            System.out.println("option selection" + jj);
            OptionsLabel.get(jj).setOnAction(e -> {
                System.out.println("option selected" + jj);
                if (OptionsLabel.get(jj).isSelected() && answeredprev[jj / 4] == 0) {
                    answered[0]++;
                    Clicked.remove(jj);
                    Clicked.add(jj, 1);
                    answeredprev[jj / 4] = 1;

                }
                if (!OptionsLabel.get(jj).isSelected()) {
                    answered[0]--;
                    Clicked.remove(jj);
                    Clicked.add(jj, 0);
                }

                answeredQuestion.setText("Answered Questions : " + String.valueOf(answered[0]));
            });
        }
        final boolean[] back = {false};
        Back.setOnAction(e -> {
            // ns.write("back");
            //  back[0]=true;
            try {
                //Main.setName(getName());
                //ns.write(getName());
                //System.out.println("");
                //new LogInCheck(ns,Server.clientList,Server.num);
                Main.HomePage();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        ArrayList<String> finalRightAnswers;
        if (finalRightAnswers1.size() == RightAnswers.size()) {
            finalRightAnswers = RightAnswers;
        } else {
            finalRightAnswers = finalRightAnswers1;
        }

        Show.setOnAction(e -> {
            ns.write("show");
            back[0] = true;
            Date d2 = new Date();
            // EndTime=d2.getTime();
            // System.out.println("e "+EndTime);
            for (int j = 0; j < finalRightAnswers.size(); j++) {
                String answerString = finalRightAnswers.get(j);
                int answer = answerString.charAt(0) - '0' - 1;
                System.out.println(answer);
                OptionsLabel.get(j * 4 + answer).setTextFill(Color.GREEN);
                for (int k = 0; k < 4; k++) {
                    if (k != answer) {
                        if (OptionsLabel.get(j * 4 + k).isSelected()) {
                            OptionsLabel.get(j * 4 + k).setTextFill(Color.RED);
                        }
                    } else if (OptionsLabel.get(j * 4 + k).isSelected()) {
                        CorrectAns++;
                    }
                }
            }

            setMarksandLevel(ExamMode, CorrectAns);
            serializeMarksandLevel();

            answeredQuestion.setText("Correct Answer: " + CorrectAns);
            forButtons.getChildren().remove(Show);
            forButtons.setSpacing(500);
            Back.setDisable(true);
            forButtons.getChildren().addAll(Finish);

        });
        Finish.setOnAction(e -> {
            ns.write("finish");
            try {
                AfterExam();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        // stage.show();

    }

    public static void AfterExam() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ScorePage.fxml"));
        AfterExamPage = loader.load();
        Scene scene = new Scene(AfterExamPage);
        stage.setScene(scene);
        stage.show();
    }

    private static void setMarksandLevel(String mode, int marks) {

        //Basic Loop Array Condition InputOutput

        switch (mode) {
            case "Basic" :
                levelMarks.set(0,marks); //prev score
                if(levelMarks.get(1) < marks) { //highest score
                    levelMarks.set(1,marks);
                }
                System.out.println(mode + "Current : " +levelMarks.get(0) + "Highest : " +levelMarks.get(1));
                break;
            case "Loop" :
                levelMarks.set(2,marks);
                if(levelMarks.get(3) < marks) {
                    levelMarks.set(3,marks);
                }
                System.out.println(mode + "Current : " +levelMarks.get(0) + "Highest : " +levelMarks.get(1));
                break;
            case "Array" :
                levelMarks.set(4,marks);
                if(levelMarks.get(5) < marks) {
                    levelMarks.set(5,marks);
                }
                System.out.println(mode + "Current : " +levelMarks.get(0) + "Highest : " +levelMarks.get(1));
                break;
            case "Condition" :
                levelMarks.set(6,marks);
                if(levelMarks.get(7) < marks) {
                    levelMarks.set(7,marks);
                }
                System.out.println(mode + "Current : " +levelMarks.get(0) + "Highest : " +levelMarks.get(1));
                break;
            case "InputOutput" :
                levelMarks.set(8,marks);
                if(levelMarks.get(9) < marks) {
                    levelMarks.set(9,marks);
                }
                System.out.println(mode + "Current : " +levelMarks.get(0) + "Highest : " +levelMarks.get(1));
                break;
            default:
                System.out.println("Invalid ExamMode");
        }
    }

    private static void serializeMarksandLevel() {
        try {
            FileOutputStream fileOut = new FileOutputStream(getName()+"Score.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);
            objectOutputStream.writeObject(levelMarks);
            if(fileOut!=null) {
                fileOut.close();
            }
            if (objectOutputStream!=null) {
                objectOutputStream.close();
            }
            System.out.println("Scores Saved.");
        } catch (Exception e) {
            System.out.println("Exception Serializing Marks");
        }
    }

    public static void getLevelandMarksfromFile() {
        try {
            FileInputStream fileIn = new FileInputStream(Main.getName() + "Score.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
            Main.levelMarks =  (LinkedList<Integer>) objectInputStream.readObject();
            if (fileIn!=null) {
                fileIn.close();
            }
            if (objectInputStream!=null) {
                objectInputStream.close();
            }
            System.out.println("Saved Scores loaded");
            for(Integer scores : levelMarks) {
                System.out.println(scores);
            }
        } catch (Exception e) {
            System.out.println("Error getting marks and level : " + e.getMessage());
        }
    }
}
