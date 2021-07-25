import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


import java.awt.Font;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private Random r = new Random();

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(game.gameState == Game.STATE.Menu){
            //if play button pressed
            if (mouseOver(mx, my, 210, 150, 200, 64)) {

              /*  for(int a = 0; a < handler.object.size(); a++){
                    GameObject delete = handler.object.get(a);
                    handler.removeObject(delete);
                    System.out.println("deleted");
                } */

                handler.object.clear();

                 game.gameState = Game.STATE.Game;
                Food.setFTime(0);
                Rock.setRTime(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT - 90, ID.Player, handler, game));
                handler.addObject(new Rock(Game.WIDTH / 2 - 32, 32, ID.Rock, handler, 0));
                handler.addObject(new Food(Game.WIDTH - 200, 32, ID.Food, handler, 0));
                HUD.HEALTH = 100;
                Speed.SPEED = 1;
            }
            //if quit button pressed
            if (mouseOver(mx, my, 210, 350, 200, 64)) {
                System.exit(0);
            }
            //if help button pressed
            if (mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = Game.STATE.Help;
            }
        }
            //if back button for help is pressed
        if (game.gameState == Game.STATE.Help){
            if(mouseOver(mx, my,210, 150, 200, 64)){
                    game.gameState = Game.STATE.Menu;
            }
        }
        //if game over
        if(game.gameState == Game.STATE.Over){
            //if click menu
            if(mouseOver(mx, my, 210, 300, 200, 64)){
                game.gameState = Game.STATE.Menu;
               /*  game.gameState = Game.STATE.Game;
                Food.setFTime(0);
                Rock.setRTime(0);
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT - 90, ID.Player, handler, game));
                handler.addObject(new Rock(Game.WIDTH / 2 - 32, 32, ID.Rock, handler, 0));
                handler.addObject(new Food(Game.WIDTH - 200, 32, ID.Food, handler, 0)); */
            }
            //want to add a retry button but not sure how to reset the game
        }

    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);

        if(game.gameState == Game.STATE.Menu){
            
            Color clr1 = new Color(11, 57, 164);
            g.setColor(clr1);
            g.setFont(fnt);
            //g.setColor(Color.pink);
            g.drawString("Menu", 240, 70);

           

            g.setColor(Color.blue);
            g.fillRect(210, 150, 200, 64);
            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);

            g.setColor(Color.blue);
            g.fillRect(210, 250, 200, 64);
            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);

            g.setColor(Color.blue);
            g.fillRect(210, 350, 200, 64);
            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Play", 270, 190);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Help", 270, 290);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("Quit", 270, 390);
        }
        //if help buttuon is pressed
        else if(game.gameState == Game.STATE.Help){
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Back", 270, 190);
        }
        else if(game.gameState == Game.STATE.Over){
            g.setColor(Color.white);
            g.setFont(fnt);
            g.drawString("Game Over", 175, 250);


            g.setFont(fnt2);
            g.setColor(Color.blue);
            g.fillRect(210, 300, 200, 64);
            g.setColor(Color.white);
            g.drawRect(210, 300, 200, 64);
            g.drawString("Menu", 270, 340);
        }
    }
}
