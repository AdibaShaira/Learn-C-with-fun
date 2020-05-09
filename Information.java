
import java.io.Serializable;

public class Information implements Serializable {

    public String Name = null;
    public String password = null;

    Information(String n, String p) {
        Name = n;
        password = p;
    }

    Information() {
    }
}
