public class Main {
    public static int state=0;
    public static Thread mainThread;
    public static MainMenue main;
    public static Window window;
    public static void main(String[] args) {
        //window Window = new window();
         main = new MainMenue();

        mainThread = new Thread(main);
        mainThread.start();
    }
    public static void changeState (int newState){
    if(newState==1 && state==0){
        main.stop();
        window=new Window();
        mainThread=new Thread(window);
        mainThread.start();
    } else if (newState==0&&state==1) {
        window.stop();
        main= new MainMenue();
        mainThread=new Thread(main);
        mainThread.start();
    } else if (newState==2) {
        if(window!=null)
            window.stop();
        if(main!=null)
            main.stop();
    }
        state=newState;
    }
}