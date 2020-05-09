
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


public class InformationCheck implements Runnable {

    Information in;
    NetworkUtil NS;
    Thread t;
    HashMap<String, NetworkUtil> clientList;

    InformationCheck(Information in, NetworkUtil ns, HashMap<String, NetworkUtil> clientList) {
        this.in = in;
        NS = ns;
        this.clientList = clientList;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {

            if (NS.s.isClosed()) {
                System.out.println("socket is closed");
                break;
            }

            BufferedReader fr = null;
            try {
                fr = new BufferedReader(new FileReader(in.Name + ".txt"));
                String UserName = fr.readLine();
                String UserPassword = fr.readLine();
                String mode = fr.readLine();

                if (in.Name.equals(UserName) && in.password.equals(UserPassword)) {
                    NS.write(UserName);
                    UserName = NS.read().toString();

                    clientList.put(in.Name, NS);
                    //System.out.println("added in hash map");
                    printHashMap();

                    //new QuestionPart(clientList,NS).t.join();
                    //System.out.println("hellooooooo " + clientList.size() + " " + mode);
                    

                    if (mode != null) {
                        if (mode.equals("exit")) {
                            break;
                        } else if (mode.equals("logOut")) {
                            break;
                        }
                    }
                    new QuestionPart(clientList, NS).t.join();

                } else {
                    NS.write("2");
                    break;
                }
            } catch (FileNotFoundException e) {
                NS.write("0");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (InterruptedException ex) {
                Logger.getLogger(InformationCheck.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void printHashMap() {

        Set set = clientList.entrySet();

        Iterator i = set.iterator();
        System.out.println("Current User--");
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.println(me.getKey() + " : ");
        }

    }
}
