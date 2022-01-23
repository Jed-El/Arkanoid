import java.util.HashMap;
import java.util.Map;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*
* @param <T> the object type used.
*/
public class MenuAnimation<T> implements Menu<T> {
    private Map<String, MenuOption<T>> options;
    private Map<String, Menu<T>> subMenus;
    private KeyboardSensor keyboard;
    private String title;
    private String message;
    private boolean stop;
    private T status;
    /**
     * Constructor for MenuAnimation.
     *
     * @param t the menu title.
     * @param sensor a KeyboardSensor.
     */
    public MenuAnimation(String t, KeyboardSensor sensor) {
        this.title = t;
        this.options = new HashMap<String, MenuOption<T>>();
        this.subMenus = new HashMap<String, Menu<T>>();
        this.keyboard = sensor;
    }
    /**
     * The method is in charge of setting a the "stop" value (for sub-menu).
     *
     * @param b a given boolean value.
     */
     public void setStop(boolean b) {
         this.stop = b;
     }
    /**
     * The method is in charge of setting a message explain this option (for sub-menu).
     *
     * @param m a given message.
     */
     public void setMessage(String m) {
         this.message = m;
     }
     /**
      * The method is in charge of finding the message explain this option (for sub-menu).
      *
      * @return the message String.
      */
      public String getMessage() {
          return this.message;
      }
    /**
    * The method is in charge of adding a selection choice to the menu.
    *
    * @param key to press.
    * @param m a String to explain the option.
    * @param returnVal the return value.
    */
   public void addSelection(String key, String m, T returnVal) {
       options.put(key, new MenuOption<T>(m, returnVal));
   }
   /**
   * The method is in charge of adding a sub-Menu choice to the menu.
   *
   * @param key to press.
   * @param m a String to explain the option.
   * @param subMenu the sub-Menu.
   */
   public void addSubMenu(String key, String m, Menu<T> subMenu) {
       this.subMenus.put(key, subMenu);
   }
   /**
   * The method is in charge of setting the menu to run.
   * It's checks every key status and set this.stop to false.
   */
   public void setMenu() {
       for (String key: this.options.keySet()) {
           options.get(key).setAlreadyPressed(this.keyboard.isPressed(key));
       }
       for (String key: this.subMenus.keySet()) {
           Menu<T> sub = this.subMenus.get(key);
           sub.setStop(true);
       }
       this.stop = false;
   }
   /**
   * The method is in charge of doing one frame of the animation.
   *
   * @param d a surface to draw on.
   * @param dt the amount of seconds passed since the last call.
   */
  public void doOneFrame(DrawSurface d, double dt) {
      d.setColor(java.awt.Color.BLACK);
      for (String key: this.subMenus.keySet()) {
          Menu<T> sub = this.subMenus.get(key);
          if (!sub.shouldStop()) {
              sub.doOneFrame(d, dt);
              if (sub.shouldStop()) {
                 this.status = sub.getStatus();
                 this.stop = true;
              }
              return;

          }
       }
      int size = this.options.size() + this.subMenus.size(),
              y = d.getHeight() / (size + 2);
      d.drawText(100, y, this.title, 50);
      int i = 2;
      for (String key: this.subMenus.keySet()) {
          Menu<T> sub = this.subMenus.get(key);
          d.drawText(100, y * (i++), "Press " + key + " to " + sub.getMessage(), 32);
          if (this.keyboard.isPressed(key)) {
              sub.setMenu();
           }
       }
      for (String key: this.options.keySet()) {
         MenuOption<T> opt = this.options.get(key);
         d.drawText(100, y * (i++), "Press " + key + " to " + opt.getMessage(), 32);
         if (!this.keyboard.isPressed(key)) {
            opt.setAlreadyPressed(false);
         } else if (!opt.checkAlreadyPressed()) {
            this.stop = true;
            this.status = opt.getT();
         }
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
   /**
   * The method is in charge of finding the chosen option.
   *
   * @return the selected option.
   */
   public T getStatus() {
       return this.status;
   }
}