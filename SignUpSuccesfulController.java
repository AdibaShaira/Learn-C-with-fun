import java.io.IOException;

public class SignUpSuccesfulController {
    private Main main;
    public void SignInSuccessAction() throws IOException{
        NetworkUtil NS = Main.ns;
        NS.write("Logging in Successfully");
        Main.getLevelandMarksfromFile();
        Main.HomePage();
    }
}
