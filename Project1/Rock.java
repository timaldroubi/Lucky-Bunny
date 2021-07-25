import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Rectangle;

public class Rock extends GameObject{

    int numRocks = 0;
    int numFood = 0;
    Handler handler;
    private static int Rtime;

    public Rock(int x, int y, ID id, Handler handler, int time){
        super(x, y, id);
        this.handler = handler;
        velY = 8;
        this.Rtime = time;
        numRocks += 1;
    }

    public void tick() {
        collision();
        Rtime += 1;
        y += velY;
        if(y >= Game.HEIGHT){
            handler.removeObject(this);
            numRocks -= 1;
           // handler.addObject(new Rock((int)Math.random()*500, 10, ID.Rock, handler, time));
        }

        //There is probably a way to do like recursive method or something
        // so that it will keep getting faster by itself indefinitely???
        
        //level 1
    //    if(time <= 500){
        
        numFood = 0;
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Food) {
                numFood += 1;
                //System.out.println(numFood);
            }
        }
        //System.out.println(numFood);
        if (numFood == 0) {
            System.out.println(numFood);
            Random r = new Random();
            handler.addObject(new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, Rtime));
            handler.addObject(new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, Rtime));
            numFood += 2;
        }
            if(Rtime % 30 == 0){
                for(int i = 0;  i < handler.object.size(); i++){
                    GameObject temp = handler.object.get(i);
                    if(temp.getId() == ID.Rock){
                        numRocks += 1;
                    }
                }
                
                //System.out.println(numRocks);
                if(Rtime <= 500){
                    
                    if(numRocks <=7 && numRocks > 0){
                       // System.out.println("new rock " + time);
                        Random r = new Random();
                        handler.addObject(new Rock(r.nextInt(550)+10, (int)(150*Math.random()) - 125 , ID.Rock, handler, Rtime));//add a new rock at random x position
                    }
                }
                if(Rtime > 500 && Rtime <=1500){
                    if(numRocks <=20){
                      //  System.out.println("new rock " + time);
                        Random r = new Random();
                        handler.addObject(new Rock(r.nextInt(550)+10, (int)(150*Math.random()) - 125 , ID.Rock, handler, Rtime));//add a new rock at random x position
                    }
                }
                if(Rtime > 1500){
                    if(numRocks <=30){
                      //  System.out.println("new rock " + time);
                        Random r = new Random();
                        handler.addObject(new Rock(r.nextInt(550)+10, (int)(150*Math.random()) - 125 , ID.Rock, handler, Rtime));//add a new rock at random x position
                    }
                }
                
            }
     //   }
        //level 2
 /*        Rock tempObj;
        if(time > 500 && time <= 1500){
            Random r2 = new Random();
            if(time % 45 == 0){
                System.out.println("new rock 2! " + time); 
   
                tempObj = new Rock(r2.nextInt(550)+10, (int)(300*Math.random()) - 275, ID.Rock, handler, time);
                handler.addObject(tempObj);
                tempObj.setVelY(7);
            }
        }
        //level 3
        if(time > 1500){
            Random r3 = new Random();
            if(time % 40 == 0){
                tempObj = new Rock(r3.nextInt(550)+10, (int)(300*Math.random()) - 275, ID.Rock, handler, time);
                handler.addObject(tempObj);
                tempObj.setVelY(10);
            }
            
        } */
        
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);

    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, 16, 16);
    }
    
    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ID.Food){
                if(getBounds().intersects(temp.getBounds())){
                    //what happens if there is a collision
                    handler.removeObject(temp);

                }
                
            }
        }
    }

    public static void setRTime(int time){
        Rtime = time;
    }
}
