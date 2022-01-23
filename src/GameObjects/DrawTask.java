import biuoop.DrawSurface;

/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface DrawTask extends Sprite {
    /**
    * The method is in charge of drawing the block.
    *
    * @param r the block Rectangle to draw.
    * @param s a surface to draw on.
    */
    void draw(Rectangle r, DrawSurface s);
    /**
    * The method is in charge of drawing the object on the given DrawSurface.
    *
    * @param d a surface to draw on.
    */
    void drawOn(DrawSurface d);
    /**
    * The method is in charge of notifying the sprite that time has passed.
    *
    * @param dt the amount of seconds passed since the last call.
    */
    void timePassed(double dt);
}
