//everything in the game is going to be considered a game object
//inherits all the functions we put in the game

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    //all objects need x and y variable
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    
     /**
      * constructor
      * @param x
      * @param y
      * @param id
      */
    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    //will need to use this within other class like player
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();


    //getters and setters for class
    /**
     * setter for x
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }
    /**
     * setter for y
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * getter for x
     * @return x
     */
    public int getX() {
        return x;
    }
    /**
     * getter for y
     * @return y
     */
    public int getY() {
        return y;
    }
    /**
     * getter for id
     * @return id
     */
    public ID getId() {
        return id;
    }
    /**
     * settert for id
     * @param id
     */
    public void setId(ID id) {
        this.id = id;
    }
    /**
     * getter for x velocity
     * @return velx
     */
    public int getVelX() {
        return velX;
    }
    /**
     * setter for x velocity
     * @param velX
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }
    /**
     * getter for y velocity
     * @return vely
     */
    public int getVelY() {
        return velY;
    }
    /**
     * setter for y velocity
     * @param velY
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }
}
