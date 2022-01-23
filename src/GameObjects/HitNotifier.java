/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface HitNotifier {
   /** Add hl as a listener to hit events.
   *
   * @param hl the new listener.
   */
   void addHitListener(HitListener hl);
   /**
   * Remove hl from the list of listeners to hit events.
   *
   * @param hl the listener to remove.
   */
   void removeHitListener(HitListener hl);
}