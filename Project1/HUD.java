import java.awt.Graphics;
import java.awt.Color;

public class HUD {
    
    public static int HEALTH = 100;

    private static int greenVal = 255;

    public static void tick(){
       // HEALTH  = Game.clamp(HEALTH, 0, 100);
        //greenVal = Game.clamp(greenVal, 0, 255);
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenVal, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.green);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Health", 230, 30);
        g.drawString("Hunger", 230, 52);

    }
}
