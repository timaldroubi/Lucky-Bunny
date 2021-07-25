//class which handles and processes all of the objects
//individually updates and renders all objects

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
