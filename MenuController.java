
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {

    private Main main;
    ObservableList<String> Topic = FXCollections.observableArrayList("Basic", "I/O", "Loop", "Array", "fucntions", "String", "Pointer");
    NetworkUtil NS = Main.ns;
    private int ans;
    private String mode;
    @FXML
    private Button basic;
    @FXML
    private Button loop;
    @FXML
    private Button array;
    @FXML
    private Button condition;
    @FXML
    private Button io;
    @FXML
    private Button logout;
    @FXML
    private Button start;

    @FXML

    public void LogOutAction() throws IOException {
        NS = Main.ns;
        NS.write("logOut");
        Main.StartPage();
    }

    @FXML

    public void basicAction() throws IOException {
        NS = Main.ns;
        System.out.println("Basic");
        Main.setNumOfQuestion(10);
        Main.setExamMode("Basic");
        NS.write("Basic");
        Main.PracticeQuestion();
    }


    @FXML

    public void loopAction() throws IOException
    {

        NS = Main.ns;
        ans=Main.levelMarks.get(1);
        if(ans>=8){
            System.out.println("Loop");
            Main.setNumOfQuestion(10);
            Main.setExamMode("Loop");
            NS.write("Loop");
            Main.PracticeQuestion();}
        else
        {
            Alert alert=new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText ("PASS BASIC LEVEL FIRST");
            alert.setTitle ("LEVEL LOCKED");
            // alert.setContentText ("");
            alert.showAndWait ();
            System.out.println ("level basic failed");
        }
    }

    @FXML

    public void arrayAction() throws IOException
    {
        NS = Main.ns;
        ans=Main.levelMarks.get(3);
        if(ans>=8){
            System.out.println("Array");
            Main.setNumOfQuestion(10);
            Main.setExamMode("Array");
            NS.write("Array");
            Main.PracticeQuestion();}
        else
        {
            Alert alert=new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText ("PASS LOOP LEVEL FIRST");
            alert.setTitle ("LEVEL LOCKED");
            // alert.setContentText ("");
            alert.showAndWait ();
            System.out.println ("level basic failed");
        }

    }

    @FXML

    public void conditionAction() throws IOException
    {
        NS = Main.ns;
        ans=Main.levelMarks.get(5);
        if(ans>=8){
            System.out.println("Condition");
            Main.setNumOfQuestion(10);
            Main.setExamMode("Condition");
            NS.write("Condition");
            Main.PracticeQuestion();}

        else
        {
            Alert alert=new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("PASS ARRAY LEVEL FIRST");
            alert.setHeaderText ("LEVEL LOCKED");
            // alert.setContentText ("");
            alert.showAndWait ();
            System.out.println ("level basic failed");
        }

    }

    @FXML

    public void ioAction() throws IOException
    {
        NS = Main.ns;
        ans=Main.levelMarks.get(7);
        if(ans>=8){
            System.out.println("InputOutput");
            Main.setNumOfQuestion(10);
            Main.setExamMode("InputOutput");
            NS.write("InputOutput");
            Main.PracticeQuestion();}
        else
        {
            Alert alert=new Alert (Alert.AlertType.ERROR);
            alert.setTitle ("PASS CONDITION LEVEL FIRST");
            alert.setHeaderText ("LEVEL LOCKED");
            // alert.setContentText ("");
            alert.showAndWait ();
            System.out.println ("level basic failed");

        }
    }
}