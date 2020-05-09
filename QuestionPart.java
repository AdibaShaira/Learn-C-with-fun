import java.util.HashMap;


public class QuestionPart implements Runnable {
    HashMap<String, NetworkUtil> clientList;
    NetworkUtil NS;
    Thread t;

    QuestionPart(HashMap<String, NetworkUtil> clientList, NetworkUtil NS) {
        this.clientList = clientList;
        this.NS = NS;
        t = new Thread (this);
        t.start ();
    }


    @Override
    public void run() {
        while (true) {
            if (NS.s.isClosed ()) {
                break;
            }

            String examMode = NS.read ().toString ();
            System.out.println (examMode + " server ");
            //etotuk run hoi,server e dekh.erpor ki jani hoi,basic ar loop er por.
           if (examMode.equals("Basic")) {
                System.out.println("basic server selected");
                new QuizPart(examMode,clientList, NS);
            }
            else if (examMode.equals("Loop")) {
                System.out.println("Loop server selected");
                new QuizPart(examMode,clientList, NS);
            }
           else if (examMode.equals ("Array")) {
                System.out.println ("array server selected");
                new QuizPart (examMode, clientList, NS);
            } else if (examMode.equals ("Condition")) {
                System.out.println ("condition server selected");
                new QuizPart (examMode, clientList, NS);
            } else if (examMode.equals ("InputOutput")) {
                System.out.println ("io server selected");
                new QuizPart (examMode, clientList, NS);
            } else if (examMode.equals ("logOut")) {

                break;
            }
        }
    }
}