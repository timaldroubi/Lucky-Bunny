import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class Food extends GameObject {
    Handler handler;
    public int numFood = 0;
    int numRock = 0;
    private static int Ftime = 0;

    public int getnumFood(){
        return numFood;
    }
    public void setnumFood(int n){
        numFood = n;
    }
    public Food(int x, int y, ID id, Handler handler, int time) {
        super(x, y, id);
        velY = 5;
        this.handler = handler;
        this.Ftime = time;
        // Random rand = new Random(600);
    }

    public void tick() {
        collision();
        Ftime += 1;
        y += velY;
        // if (y >= Game.HEIGHT) {
        // Random r = new Random();
        // y = 60;
        // x = r.nextInt(500) +50; cant figure it out right now
        if (y >= Game.HEIGHT) {
            handler.removeObject(this);
            numFood -= 1;
            // handler.addObject(new Rock((int)Math.random()*500, 10, ID.Rock, handler,
            // time));
        }

        // There is probably a way to do like recursive method or something
        // so that it will keep getting faster by itself indefinitely???
        numRock = 0;
        for (int x = 0; x < handler.object.size(); x++) {
            GameObject temps = handler.object.get(x);
            if (temps.getId() == ID.Rock) {
                numRock += 1;
                //System.out.println(numFood);
            }
        }
        //System.out.println(numFood);
        if (numRock == 0) {
            Random r = new Random();
            handler.addObject(new Rock(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Rock, handler, Ftime));
            handler.addObject(new Rock(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Rock, handler, Ftime));
            numFood += 2;
        }

        numFood = 0;
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Food) {
                numFood += 1;
                //System.out.println(numFood);
            }
        }
        //System.out.println(numFood);

        /* if (numFood == 0) {
            System.out.println(numFood);
            Random r = new Random();
            handler.addObject(new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, time));
            handler.addObject(new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, time));
            numFood += 2;
        } */

        if (Ftime % 50 == 0) {

            if (Ftime <= 500) {
                if (numFood <= 5) {
                    Random r = new Random();
                    handler.addObject(
                            new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, Ftime));
                }
            }
            if (Ftime > 500 && Ftime <= 1500) {
                if (numFood <= 10) {
                    // System.out.println("new rock " + time);
                    Random r = new Random();
                    handler.addObject(
                            new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, Ftime));
                }
            }
            if (Ftime > 1500) {
                if (numFood <= 15) {
                    Random r = new Random();
                    handler.addObject(
                            new Food(r.nextInt(550) + 10, (int) (150 * Math.random()) - 125, ID.Food, handler, Ftime));
                }
            }
        }
}



    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(x, y, 16, 16);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Rock) {
                if (getBounds().intersects(temp.getBounds())) {
                    // what happens if there is a collision
                    handler.removeObject(this);
                    numFood -= 1;
                }

            }
        }
    }

    public static void setFTime(int time){
        Ftime = time;
    }
    public static int getFTime(){
        return Ftime;
    }
}
