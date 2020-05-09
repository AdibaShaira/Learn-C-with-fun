
public class Submit implements Runnable {
    Score s;
    NetworkUtil NS;
    Thread t;
    Submit(Score s,NetworkUtil ns){
        this.s=s;
        NS=ns;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        Object o= NS.read();
        if(o instanceof Score){
            s=(Score) NS.read();
            System.out.println("score "+s.score);

        }
    }
}
