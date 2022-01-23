import biuoop.GUI;
import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class AnimationRunner {
   private GUI gui;
   private int framesPerSecond;
   /**
    * Constructor for AnimationRunner, using a given GUI.
    *
    * @param g a given GUI.
    */
   public AnimationRunner(GUI g) {
      this.gui = g;
   }
   /**
   * The method is in charge of running the Animation loop.
   *
   * @param animation a Animation to run.
   */
   public void run(Animation animation) {
      biuoop.Sleeper sleeper = new biuoop.Sleeper();
      this.framesPerSecond = 60;
      int millisecondsPerFrame = 1000 / this.framesPerSecond;
      while (!animation.shouldStop()) {
         long startTime = System.currentTimeMillis(); // timing
         DrawSurface d = gui.getDrawSurface();
         animation.doOneFrame(d, 1.0 / this.framesPerSecond);
         gui.show(d);
         long usedTime = System.currentTimeMillis() - startTime;
         long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
         if (milliSecondLeftToSleep > 0) {
             sleeper.sleepFor(milliSecondLeftToSleep);
         }
      }
   }
}