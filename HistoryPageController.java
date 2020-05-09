import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class HistoryPageController {
    private NetworkUtil NS = Main.ns;
    int[] score=new int[10];
    @FXML
    Label Basic;
    @FXML
    Label Loop;

    @FXML
    Label Array;
    @FXML
    Label Condition;
    @FXML
    Label IOsystem;
    @FXML
    public void initialize(){
        Basic.setText ("BASIC:"+Main.levelMarks.get(0));
        Basic.setText ("BASIC:"+Main.levelMarks.get(0)+Main.levelMarks.get(1));
        Loop.setText ("BASIC:"+Main.levelMarks.get(2));
        Loop.setText ("BASIC:"+Main.levelMarks.get(2)+Main.levelMarks.get(3));
        Array.setText ("BASIC:"+Main.levelMarks.get(4));
        Array.setText ("BASIC:"+Main.levelMarks.get(4)+Main.levelMarks.get(5));
        Condition.setText ("BASIC:"+Main.levelMarks.get(6));
        Condition.setText ("BASIC:"+Main.levelMarks.get(6)+Main.levelMarks.get(7));
        IOsystem.setText ("BASIC:"+Main.levelMarks.get(8));
        IOsystem.setText ("BASIC:"+Main.levelMarks.get(8)+Main.levelMarks.get(9));

    }
    @FXML
    public void BackAction() throws IOException{
        NetworkUtil NS = Main.ns;
        Main.LogInSuccess ();
        //


    }



}
