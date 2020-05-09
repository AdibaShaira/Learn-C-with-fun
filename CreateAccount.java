import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class CreateAccount implements Runnable{
    NetworkUtil NS;
    Information in;
    HashMap<String, NetworkUtil> clientList;
    Thread t;
    CreateAccount(NetworkUtil n, Information i, HashMap <String, NetworkUtil> clientList){
        NS=n;
        in=i;
        this.clientList=clientList;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
//        while (true){
//            if(NS.s.isClosed() && NS.s.isClosed()){
//                break;
//            }
            try {
                BufferedReader fr= new BufferedReader(new FileReader(in.Name+".txt"));
                NS.write("0");
            } catch (FileNotFoundException e) {
                File fw= new File(in.Name+".txt");
                PrintWriter bw= null;
                try {
                    bw = new PrintWriter(fw);
                    bw.println(in.Name);
                    //bw.println(in.email);
                    bw.println(in.password);
                    //bw.println(in.mode);
                    bw.close();
                    NS.write("1");

                    // new ModeCheck(in,NS,clientList).t.join();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }


            }

            try{
                File file = new File(in.Name + "Score.txt");
                FileOutputStream fileOut = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);
                LinkedList<Integer> levelMarks = new LinkedList<>();

                levelMarks.add(0); //for Basic
                levelMarks.add(0);
                levelMarks.add(-1);//for Loop
                levelMarks.add(-1);
                levelMarks.add(-1);//for Array
                levelMarks.add(-1);
                levelMarks.add(-1);//for Condition
                levelMarks.add(-1);
                levelMarks.add(-1);//for IO
                levelMarks.add(-1);

                objectOutputStream.writeObject(levelMarks);

                if( fileOut!= null ) {
                    fileOut.close();
                }
                if( objectOutputStream != null ) {
                    objectOutputStream.close();
                }
                System.out.println("Marks & Level Initialized");
            } catch (Exception e) {
                System.out.println("Error in create account scoreFile : " + e.getMessage());
            }
        }
//    }
}
