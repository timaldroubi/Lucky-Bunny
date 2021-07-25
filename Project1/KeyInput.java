import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
public class KeyInput extends KeyAdapter{

    private Handler handler;
    private boolean[] keyDown = new boolean[2];
    
    public KeyInput(Handler handler){
        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        //System.out.println(key);
        //loops through all objects in handler till finds one with matching id to move
        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            
             if(temp.getId() == ID.Player){
                //if(key == KeyEvent.VK_UP) temp.setVelY(-5);
                //if(key == KeyEvent.VK_DOWN) temp.setVelY(5);
                int velTemp = (int)( 4 + .03 * Speed.SPEED);
                if(key == KeyEvent.VK_RIGHT) { temp.setVelX(velTemp);  keyDown[0] = true; }
                if(key == KeyEvent.VK_LEFT) { temp.setVelX(-1*velTemp); keyDown[1] = true; }
                else{
                    ;
                } 
            }
        } 

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            
             if(temp.getId() == ID.Player){
                //if(key == KeyEvent.VK_UP) temp.setVelY(0);
                //if(key == KeyEvent.VK_DOWN) temp.setVelY(0);
                if(key == KeyEvent.VK_RIGHT) keyDown[0] = false; //temp.setVelX(0);
                if(key == KeyEvent.VK_LEFT) keyDown[1] = false; //temp.setVelX(0);
                else{
                    ;
                } 

                if(!keyDown[0] && !keyDown[1]){
                    temp.setVelX(0);
                }
            }
        } 
    }
}
