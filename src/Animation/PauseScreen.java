import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class PauseScreen implements Animation {
   /**
   * The method is in charge of doing one frame of the animation.
   *
   * @param d a surface to draw on.
   * @param dt the amount of seconds passed since the last call.
   */
   public void doOneFrame(DrawSurface d, double dt) {
      d.setColor(java.awt.Color.BLACK);
      d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
   }
   /**
   * The method is in charge of checking if the animation should stop.
   *
   * @return true if yes, and false if not.
   */
   public boolean shouldStop() {
      return false;
   }
}