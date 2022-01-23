/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;
   /**
    * Constructor for ScoreTrackingListener.
    *
    * @param scoreCounter for score count.
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }
   /** This method is called whenever the beingHit object is hit.
   *
   * @param beingHit is the Block that's being hit.
   * @param hitter is the Ball that's doing the hitting.
   */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
       if (beingHit.getHitPoints() == 0) {
           this.currentScore.increase(10);
       }
    }
 }
