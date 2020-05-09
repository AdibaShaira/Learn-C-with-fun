import java.io.IOException;

public class LogInSuccessController {
    private Main main;
   // int [] score=new int[10];
    public void LogInSuccessAction() throws IOException{
        NetworkUtil NS = Main.ns;
        NS.write("Logging in Successfully");
        Main.getLevelandMarksfromFile();
        Main.HomePage();
    }

}
