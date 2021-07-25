import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = -6112428091888191314L; // serialVer

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private HUD hud;
    private Speed speed;
    private Random r;
    private Menu menu;
    // instanciate handler class
    private Handler handler;

    public enum STATE {
        Menu,
        Game,
        Help,
        Over
    };

    public STATE gameState =  STATE.Menu;

    public Game() {
       
        handler = new Handler(); 
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Time to build a game!", this);

        hud = new HUD();
        speed = new Speed();
       
        
            r = new Random();

        if(gameState == STATE.Game){
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT -90, ID.Player, handler, this));
           // for(int x = 0; x < 3; x++){
                handler.addObject(new Rock(WIDTH/2 -32, 32, ID.Rock, handler, 0));
           // }
            handler.addObject(new Food(WIDTH -200, 32, ID.Food, handler, 0));
        }
            
        

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 100000000;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            ;
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
        if(gameState == STATE.Game){
            hud.tick();
            speed.tick();
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.Over){
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResource("backgroundSmall.jpg"));
        } catch (IOException e) {
            System.out.println("Exception!");
        }


        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(image, 0, 0, null);

        handler.render(g);

        if(gameState == STATE.Game){
            hud.render(g);
            speed.render(g);
        }else if(gameState == STATE.Menu|| gameState == STATE.Help || gameState == STATE.Over){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }
}
