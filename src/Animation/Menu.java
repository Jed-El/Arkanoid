/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*
* @param <T> the object type used.
*/
public interface Menu<T> extends Animation {
    /**
     * The method is in charge of setting a the "stop" value (for sub-menu).
     *
     * @param b a given boolean value.
     */
     void setStop(boolean b);
    /**
    * The method is in charge of setting a message explain this option (for sub-menu).
    *
    * @param m a given message.
    */
    void setMessage(String m);
    /**
    * The method is in charge of finding the message explain this option (for sub-menu).
    *
    * @return the message String.
    */
    String getMessage();
    /**
    * The method is in charge of adding a selection choice to the menu.
    *
    * @param key to press.
    * @param message a String to explain the option.
    * @param returnVal the option value.
    */
   void addSelection(String key, String message, T returnVal);
   /**
   * The method is in charge of adding a sub-Menu choice to the menu.
   *
   * @param key to press.
   * @param message a String to explain the option.
   * @param subMenu the sub-Menu.
   */
   void addSubMenu(String key, String message, Menu<T> subMenu);
   /**
   * The method is in charge of setting the menu to run.
   * It's checks every key status and set this.stop to false.
   */
   void setMenu();
   /**
   * The method is in charge of finding the chosen option.
   *
   * @return the selected option.
   */
   T getStatus();
}