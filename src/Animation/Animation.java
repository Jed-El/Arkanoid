import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface Animation {
    /**
    * The method is in charge of doing one frame of the animation.
    *
    * @param d a surface to draw on.
    * @param dt the amount of seconds passed since the last call.
    */
   void doOneFrame(DrawSurface d, double dt);
   /**
   * The method is in charge of checking if the animation should stop.
   *
   * @return true if yes, and false if not.
   */
   boolean shouldStop();
}