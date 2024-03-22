public class Ball {
    public Rect rect;
    public Rect leftPad, RightPad;
    Text leftScoretext;
    Text RightScoretext;
    private double vy = 10.0;
    private double vx = -150.0;

    public Ball(Rect rect, Rect leftPad, Rect RightPad,Text leftScoretext,Text RightScoretext) {
        this.leftPad = leftPad;
        this.RightPad = RightPad;
        this.rect = rect;
        this.RightScoretext=RightScoretext;
        this.leftScoretext=leftScoretext;

    }
    public boolean intersects(Rect a, Rect b) {
        return (a.x < b.x + b.width &&
                a.x + a.width > b.x &&
                a.y < b.y + b.height &&
                a.y + a.height > b.y);
    }
        public void update(double dt) {
        if (vx < 0) {
            if (intersects(rect,leftPad)
                    //this.rect.x <= this.leftPad.x + this.leftPad.width &&
                    //this.rect.x + this.rect.width >= this.leftPad.x &&
                    //this.rect.y >= this.leftPad.y &&
                    //this.rect.y <= this.leftPad.y + this.leftPad.height)
            ){
                System.out.println("collison with pad happend");
                double theta = calnewValAng(leftPad);
                double newVx= Math.abs((Math.cos(theta))*Constant.BALL_SPEED);
                double newVy=Math.abs(-(Math.sin(theta))*Constant.BALL_SPEED);
                double oldSign=Math.signum(vx);
                this.vx=newVx*(-1.0*oldSign);

               this.vy = newVy;
            } else if (this.rect.x + this.rect.width < this.leftPad.x + this.leftPad.width) {
                System.out.println("player  has lost a point");
                int RightScore=Integer.parseInt(RightScoretext.text);
                RightScore++;
                RightScoretext.text=""+RightScore;
                this.rect.x=Constant.SCREEN_WIDTH/2.0;
                this.rect.y=Constant.SCREEN_HEIGHT/2.0;
                this.vx=10.0;
                this.vy=-150;
                if(RightScore==Constant.SCORE_WIN){
                    System.out.println("you lost booboo");
                Main.changeState(2);}
            }
        }else if (vx > 0) {
                if ( intersects(rect,RightPad)
                        //this.rect.x + this.rect.width >= this.RightPad.x &&
                        //this.rect.x<=this.RightPad.x  +this.RightPad.width&&
                       // this.rect.y >= this.RightPad.y &&
                       // this.rect.y <= this.RightPad.y + this.RightPad.height)
                ){System.out.println("collison with pad happend");
                    double theta = calnewValAng(RightPad);
                    double newVx= Math.abs((Math.cos(theta))*Constant.BALL_SPEED);
                    double newVy=Math.abs((Math.sin(theta))*Constant.BALL_SPEED);
                    double oldSign=Math.signum(vx);
                    this.vx=newVx*(-1.0*oldSign);

                    this.vy = newVy;
                } else if (this.rect.x + this.rect.width >this.RightPad.x +this.RightPad.width) {

                    System.out.println("ai has lost a point");
                    int leftScore=Integer.parseInt(leftScoretext.text);
                    leftScore++;
                    leftScoretext.text=""+leftScore;
                    this.rect.x=Constant.SCREEN_WIDTH/2.0;
                    this.rect.y=Constant.SCREEN_HEIGHT/2.0;
                    this.vx=10.0;
                    this.vy=150;
                    if(leftScore==Constant.SCORE_WIN){
                        System.out.println("you have won");
                    Main.changeState(2);}

                }
            }
            if(vy>=0){
                if(this.rect.y + this.rect.height>Constant.SCREEN_HEIGHT){
                    this.vy*=-1;
                }
            } else if (vy<0) {
                if(this.rect.y <Constant.TOOL_BAR_HEIGHT){
                    this.vy *= -1;
                }

            }

            this.rect.x += vx * dt;
            this.rect.y += vy * dt;
//            if(this.rect.x+this.rect.width< leftPad.x){
//            int RightScore=Integer.parseInt(RightScoretext.text);
//            RightScore++;
//                RightScoretext.text=""+RightScore;
//                this.rect.x=Constant.SCREEN_WIDTH/2.0;
//                this.rect.y=Constant.SCREEN_HEIGHT/2.0;
//                this.vx=20.0;
//                this.vy=-200;
//                if(RightScore==Constant.SCORE_WIN)
//                    System.out.println("you lost booboo");
//                 Main.changeState(2);
//            } else if (this.rect.x>RightPad.x + RightPad.width) {
//                int leftScore=Integer.parseInt(leftScoretext.text);
//                leftScore++;
//                RightScoretext.text=""+leftScore;
//                this.rect.x=Constant.SCREEN_WIDTH/2.0;
//                this.rect.y=Constant.SCREEN_HEIGHT/2.0;
//                this.vx=20.0;
//                this.vy=200;
//                if(leftScore==Constant.SCORE_WIN)
//                    System.out.println("you have won");
//                Main.changeState(2);
//            }
    }
    public double calnewValAng(Rect paddle){
        double relativeIntersectY= (paddle.y +( paddle.height/2))- (this.rect.y+(this.rect.height/2.0));
        double nominalintersectY= relativeIntersectY/ (paddle.height/2.0);
        double theta=nominalintersectY*Constant.MAX_ANGLE;

        return theta;
    }
}