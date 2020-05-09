
import javafx.scene.control.RadioButton;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;

public class QuizPart implements Runnable {

    HashMap<String, NetworkUtil> clientList;
    NetworkUtil NS;
    Thread t;
    String catagory;
    Questionpage q;
    ArrayList<String> RightAnswers = new ArrayList<>();

    ArrayList<String> QuestionString = new ArrayList<>();
    ArrayList<String> OptionString = new ArrayList<>();
    Scanner Q = null, A = null;
    int cntA = 50;
    int cntQ = 10;

    QuizPart(String s, HashMap<String, NetworkUtil> clientList, NetworkUtil NS) {
        catagory = s;
        this.clientList = clientList;
        this.NS = NS;
        t = new Thread(this);
        t.start();
    }

    public Questionpage getQ() {
        return q;
    }

    public void run() {
        while (true) {
            if (NS.s.isClosed()) {
                break;
            }
            if(cntA<=0 && cntQ<=0) break;
            try {
                Q = new Scanner(new File(catagory + "Ques.txt"));
                A = new Scanner(new File(catagory + "Ans.txt"));
                System.out.println("Eije file open er part tuku " + catagory);
            } catch (Exception e) {
                System.out.println("Error");
            }

            while (Q.hasNextLine() && cntQ > 0) {
                cntQ--;
                String qstn = Q.nextLine();
                QuestionString.add(qstn);
                System.out.println("Question : " + qstn);
            }
            int i = 1;

            while (A.hasNextLine() && cntA > 0) {
                cntA--;
                if (i % 5 == 0) {
                    String s = A.nextLine();
                    RightAnswers.add(s);
                    System.out.println("Answer : " + s);
                } else {
                    String s = A.nextLine();
                    OptionString.add(s);
                    System.out.println("Option : " + s);
                }
                i++;
            }

            q = new Questionpage(QuestionString, RightAnswers, OptionString);
            Q.close();
            A.close();
            NS.write(q);
            //String next = NS.read().toString();



















        }
    }

}
