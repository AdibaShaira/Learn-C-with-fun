import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Server {
    ServerSocket ss;
    static int num=0;

    static HashMap <String,NetworkUtil> clientList;
    Server(int port) throws IOException, InterruptedException {
        ss=new ServerSocket(port);
        System.out.println("server has started");
        clientList = new HashMap<>();
        while(true){
            Socket s=ss.accept();
            System.out.println("client has joined");
            NetworkUtil ns= new NetworkUtil(s);
            new LogInCheck(ns,clientList,++num);
        }
    }

    public static void main(String[] args) throws Exception {
        new Server(33333);
    }

}
