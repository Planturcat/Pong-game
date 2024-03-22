import javax.swing.JFrame;
import java.awt.*;

public class MainMenue extends JFrame implements Runnable {
    public Graphics2D g2;
    public KL keyListener = new KL();
    public Text StartGame,exitGame,Title;
    public ML mouseListener = new ML();
    public boolean isRunning=true;
    public MainMenue(){
    this.setSize(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.setTitle(Constant.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.Title=new Text("PONG",new Font("Times New Roman",Font.BOLD,80),Constant.SCREEN_WIDTH/2.0-50,Constant.SCREEN_HEIGHT/2.0,Color.BLACK);
        this.StartGame=new Text("START GAME",new Font("Times New Roman",Font.BOLD,60),Constant.SCREEN_WIDTH/2.0-200,Constant.SCREEN_HEIGHT/2.0+60,Color.BLACK);
        this.exitGame=new Text("EXIT GAME",new Font("Times New Roman",Font.BOLD,40),Constant.SCREEN_WIDTH/2.0-35,Constant.SCREEN_HEIGHT/2.0+120,Color.BLACK);
    g2 = (Graphics2D) this.getGraphics();
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    Constant.TOOL_BAR_HEIGHT=this.getInsets().top;
        g2.fillRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);


}

public void update(double dt) {

    Image dbImage = createImage(getWidth(),getHeight());
    Graphics dbg =dbImage.getGraphics();
    this.draw(dbg);
    g2.drawImage(dbImage,0,0,this);
    System.out.println(mouseListener.getMouseX() +","+mouseListener.getMouseY()+" "+mouseListener.isPressed);
  if(mouseListener.getMouseX()>StartGame.x &&
          mouseListener.getMouseX()<StartGame.width+StartGame.x &&
       mouseListener.getMouseY()>StartGame.y-StartGame.height/2 &&
  mouseListener.getMouseY()<StartGame.height/2+StartGame.y){
      StartGame.color =new Color(14, 78, 3);

      if(mouseListener.isMousePressed()){
       Main.changeState(1);
      }
  } else if (mouseListener.getMouseX()>exitGame.x &&
          mouseListener.getMouseX()<exitGame.width+exitGame.x &&
          mouseListener.getMouseY()>exitGame.y-exitGame.height/2 &&
          mouseListener.getMouseY()<exitGame.height/2+exitGame.y) {
      exitGame.color =new Color(129, 5, 5);
      if(mouseListener.isMousePressed()){
          Main.changeState(2);
      }
      
  }else{
      StartGame.color=Color.BLACK;
      exitGame.color=Color.BLACK;
  }
}
public void draw(Graphics g){

    Graphics2D  g2=(Graphics2D)g;
    g2.setColor(Color.LIGHT_GRAY);
    g2.fillRect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
    Title.draw(g2);
 StartGame.draw(g2);
exitGame.draw(g2);
}

public void stop(){
        isRunning=false;
}
public void run(){
    double lastFrameTime = 0.0;
    while (isRunning) {
        //do whatever
        double time = Time.getTime();
        double deltaTime = time - lastFrameTime;
        lastFrameTime = time;

        update(deltaTime);


    }
        this.dispose();
return;
}

}
