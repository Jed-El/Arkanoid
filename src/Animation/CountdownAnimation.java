import biuoop.DrawSurface;
/**
* The CountdownAnimation will display the given gameScreen,
* for numOfSeconds seconds, and on top of them it will show
* a countdown from countFrom back to 1, where each number will
* appear on the screen for (numOfSeconds / countFrom) secods, before
* it is replaced with the next one.
*
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class CountdownAnimation implements Animation {
   private int count;
   private double seconds;
   private double secondsForNum;
   private SpriteCollection screen;
   private boolean stop;
   /**
    * Constructor for EndScreen.
    *
    * @param numOfSeconds a number of seconds for the count.
    * @param countFrom a number to count down from.
    * @param gameScreen a SpriteCollection of the level, to draw in the background.
    */
   public CountdownAnimation(double numOfSeconds,
                             int countFrom,
                             SpriteCollection gameScreen) {
      this.seconds = numOfSeconds;
      this.count = countFrom;
      this.secondsForNum = (numOfSeconds / countFrom);
      this.screen = gameScreen;
      this.stop = false;
   }
   /**
   * The method is in charge of doing one frame of the animation.
   *
   * @param d a surface to draw on.
   * @param dt the amount of seconds passed since the last call.
   */
   public void doOneFrame(DrawSurface d, double dt) {
       this.screen.drawAllOn(d);
       d.setColor(java.awt.Color.RED);
       d.drawText(400, d.getHeight() / 2, Integer.toString(this.count), 200);
       this.seconds = this.seconds - dt;
       this.count = (int) (this.seconds / this.secondsForNum) + 1;
       if (this.seconds <= 0) {
          this.stop = true;
       }
   }
   /**
   * The method is in charge of checking if the animation should stop.
   *
   * @return true if yes, and false if not.
   */
   public boolean shouldStop() {
       return this.stop;
   }
}