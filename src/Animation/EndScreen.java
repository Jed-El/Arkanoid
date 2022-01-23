import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class EndScreen implements Animation {
   private Counter lives;
   private Counter score;
   /**
    * Constructor for EndScreen.
    *
    * @param livesLeft a Counter of lives, to check if the player survived.
    * @param scoreEarned a Counter of score, to check the final score.
    */
   public EndScreen(Counter livesLeft, Counter scoreEarned) {
      this.lives = livesLeft;
      this.score = scoreEarned;
   }
   /**
   * The method is in charge of doing one frame of the animation.
   *
   * @param d a surface to draw on.
   * @param dt the amount of seconds passed since the last call.
   */
   public void doOneFrame(DrawSurface d, double dt) {
      d.setColor(java.awt.Color.BLACK);
      if (this.lives.getValue() > 0) {
         d.drawText(150, d.getHeight() / 2, "You Win! Your score is "
             + Integer.toString(this.score.getValue()), 32);
      } else {
         d.drawText(150, d.getHeight() / 2, "Game Over. Your score is "
             + Integer.toString(this.score.getValue()), 32);
      }
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