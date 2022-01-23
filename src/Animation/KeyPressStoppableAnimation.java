import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class KeyPressStoppableAnimation implements Animation {
   private KeyboardSensor keyboard;
   private boolean stop;
   private boolean isAlreadyPressed = false;
   private String endKey;
   private Animation animation;
   /**
    * Constructor for KeyPressStoppableAnimation.
    *
    * @param sensor a KeyboardSensor.
    * @param key a specified key to pressed for end.
    * @param ani an animation to run.
    */
   public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation ani) {
       this.keyboard = sensor;
       this.endKey = key;
       this.animation = ani;
       if (this.keyboard.isPressed(this.endKey)) {
          this.isAlreadyPressed = true;
       }
       this.stop = false;
    }
    /**
    * The method is in charge of checking if the animation should stop.
    *
    * @return true if yes, and false if not.
    */
    public boolean shouldStop() {
       return this.stop;
    }
    /**
    * The method is in charge of doing one frame of the animation.
    *
    * @param d a surface to draw on.
    * @param dt the amount of seconds passed since the last call.
    */
    public void doOneFrame(DrawSurface d, double dt) {
        if (!this.keyboard.isPressed(this.endKey) && this.isAlreadyPressed) {
           this.isAlreadyPressed = false;
         }
        if (this.keyboard.isPressed(this.endKey) && !this.isAlreadyPressed) {
           this.stop = true;
        }
        this.animation.doOneFrame(d, dt);
     }
   // think about the implementations of doOneFrame and shouldStop.
}