import java.awt.Graphics;
import java.awt.Color;

public class Speed {
    private static int count = 0;
    public static int SPEED = 1;

    public static void tick(){
        count += 1;
        if(SPEED > 1 && count == 120){
           
            SPEED -= SPEED/20 + 1;
            count = 0;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(15, 40, 200, 16);
        g.setColor(Color.yellow);
        g.fillRect(15, 40, SPEED * 2, 16);
        g.setColor(Color.yellow);
        g.drawRect(15, 40, 200, 16);
    }
}
