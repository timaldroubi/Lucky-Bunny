
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas{
    private static final long serialVersionUID = -8255319694373975038L; //serialVer

    /**
     * Constructor for window the game will be played on
     * 
     * @param width
     * @param height
     * @param title
     * @param game
     */
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);//creates window with a border and buttons
        //sets the size of the window
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        //makes the close button shut down the game
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //does not allow the user to resize the frame
        frame.setResizable(false);
        //window starts in middle of screen instead of top left
        frame.setLocationRelativeTo(null);
        //adds the game class into the frame
        frame.add(game);
        //makes the frame visible
        frame.setVisible(true);
        //run the game start method in game class
        game.start();


    }

    
}