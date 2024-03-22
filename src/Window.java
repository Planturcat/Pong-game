import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
   public Graphics2D g2;
    public KL keyListener = new KL();
    public Rect playerOne;
    public Rect Ai;
    public Rect ballrect;
    public Ball ball;
    public Text LeftScoreText,RightScoreText ,Exit;
    public ML mouseListener = new ML();
    public boolean isRunning=true;

public PlayerController playerController;
public AiCon AiController;
    public Window() {
        this.setSize(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        this.setTitle(Constant.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g2 = (Graphics2D) this.getGraphics();
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        Constant.TOOL_BAR_HEIGHT=this.getInsets().top;
        g2.fillRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);

        LeftScoreText =new Text(0,new Font("Times New Roman",Font.BOLD,Constant.TEXT_SIZE),10,Constant.TEXT_Y);
        RightScoreText=new Text(0,new Font("Times New Roman",Font.BOLD,Constant.TEXT_SIZE),Constant.SCREEN_WIDTH-10-20,Constant.TEXT_Y);
        Exit=new Text("Exit",new Font("TImes new Roman",Font.BOLD,Constant.TEXT_SIZE-20),Constant.SCREEN_WIDTH/2,Constant.TEXT_Y,Color.RED);
        playerOne = new Rect(Constant.HR_PADDING,40,Constant.PADDLE_WIDTH,Constant.PADDLE_HEIGHT,Color.RED);
        playerController= new PlayerController(playerOne,keyListener);
        Ai= new Rect(Constant.SCREEN_WIDTH-Constant.PADDLE_WIDTH-Constant.HR_PADDING,40,Constant.PADDLE_WIDTH,Constant.PADDLE_HEIGHT,Color.BLUE);
        ballrect =new Rect(Constant.SCREEN_WIDTH/2.0,Constant.SCREEN_HEIGHT/2.0,10,10,Color.BLACK);
       ball= new Ball(ballrect,playerOne,Ai,LeftScoreText,RightScoreText);
        AiController = new AiCon(new PlayerController(Ai),ballrect);

    }

    public void update(double dt) {
        //System.out.println("" + dt + " seconds passed since the last frame");
        //System.out.println(1 / dt + "fps");
        Image dbImage = createImage(getWidth(),getHeight());
        Graphics dbg =dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

//Rect rect = new Rect(50,Constant.SCREEN_HEIGHT-100,40,80,Color.WHITE);
        //rect.draw(g2);
        playerController.update(dt);
        AiController.update(dt);
        ball.update(dt);
        if (mouseListener.getMouseX()>Exit.x &&
                mouseListener.getMouseX()<Exit.width+Exit.x &&
                mouseListener.getMouseY()>Exit.y-Exit.height/2 &&
                mouseListener.getMouseY()<Exit.height/2+Exit.y) {
            Exit.color = new Color(255, 0, 0);
            if (mouseListener.isMousePressed()) {
                Main.changeState(0);
            }
        }else{
            Exit.color = new Color(0, 0, 0);
        }
 }
    public void draw(Graphics g){

        Graphics2D  g2=(Graphics2D)g;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
        //Font font = new Font("Times New Roman",Font.ITALIC,20);
        //Text text=new Text("sample",font,100,100);
        //text.draw(g2);
        Exit.draw(g2);
        LeftScoreText.draw(g2);
        RightScoreText.draw(g2);
        playerOne.draw(g2);
        Ai.draw(g2);
        ballrect.draw(g2);

    }
    public void stop(){
      isRunning =false;
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
        }

    }

