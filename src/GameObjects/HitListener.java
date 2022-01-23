/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface HitListener {
   /** This method is called whenever the beingHit object is hit.
   *
   * @param beingHit is the Block that's being hit.
   * @param hitter is the Ball that's doing the hitting.
   */
   void hitEvent(Block beingHit, Ball hitter);
}