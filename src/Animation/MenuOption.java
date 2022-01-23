/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*
* @param <T> the object type used.
*/
public class MenuOption<T> {
   private T returnVal;
   private boolean isAlreadyPressed;
   private String message;
   /**
   * Constructor for MenuOption.
   *
   * @param m a String to explain the option.
   * @param rv the return value.
   */
   public MenuOption(String m, T rv) {
       this.returnVal = rv;
       this.message = m;
   }
   /**
   * The method is in charge of finding the message explain this option.
   *
   * @return the message String.
   */
   public String getMessage() {
       return this.message;
   }
   /**
   * The method is in charge of finding the return value for this option.
   *
   * @return the return value.
   */
   public T getT() {
       return this.returnVal;
   }
   /**
   * The method is in charge of checking if the key is already pressed.
   *
   * @return the value from isAlreadyPressed.
   */
   public boolean checkAlreadyPressed() {
       return this.isAlreadyPressed;
   }
   /**
   * The method is in charge of changing isAlreadyPressed value,
   * if the key is not pressed anymore.
   *
   * @param ifPressed a boolean value.
   */
   public void setAlreadyPressed(boolean ifPressed) {
       this.isAlreadyPressed = ifPressed;
   }
}
