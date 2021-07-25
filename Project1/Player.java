import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.image.BufferStrategy;
import javax.imageio.ImageIO;

public class Player extends GameObject{

    private Game game;
    Random r = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler, Game game){
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }

    public void tick(){
        x += velX;
        y += velY;
        if(x >= Game.WIDTH|| x  <= 0){
            x = (int)Math.abs(x - Game.WIDTH);
        }

        collision();
    }

    public void render(Graphics g) {
        
       // g.setColor(Color.white);
        //g.fillOval(x, y, 32, 32); 

        //rendering of the bunny
         BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResource("bunny.png"));
        } catch (IOException e) {
            System.out.println("Exception!");
        }

        g.drawImage(image, x, y-40, null); 
        
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ID.Rock){
                if(getBounds().intersects(temp.getBounds())){
                    //what happens if there is a collision
                    if(HUD.HEALTH <= 25){
                        //make the game end
                        HUD.HEALTH -= 25;
                        System.out.println("Game Over");
                        //go to game over screen
                        game.gameState = Game.STATE.Over;
                        //stop game the game
                        for(int x = 0; x <handler.object.size(); x++){
                            GameObject temps = handler.object.get(x);
                            if(temps.getId() == ID.Rock || temps.getId() == ID.Food|| temps.getId() == ID.Player){
                                handler.removeObject(temps);
                            }
                        }

                        //Food.setFTime(0);
                        //Rock.setRTime(0);


                    }
                    HUD.HEALTH -= 25;
                    handler.removeObject(temp);
                }
                
            }
            if(temp.getId() == ID.Food){
                if(getBounds().intersects(temp.getBounds())){
                    //what happens if there is a collision
                    if(Speed.SPEED < 100){
                        Speed.SPEED += 1;
                        handler.removeObject(temp);
                    }
                    if(HUD.HEALTH < 100){
                         HUD.HEALTH += 1;
                         handler.removeObject(temp);
                    }
                    else{
                        handler.removeObject(temp);
                    }
                }
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y-40, 28, 59);
    }
}
