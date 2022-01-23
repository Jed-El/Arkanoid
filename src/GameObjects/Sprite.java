import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface Sprite {
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