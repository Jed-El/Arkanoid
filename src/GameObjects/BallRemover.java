/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class BallRemover implements HitListener {
   private GameLevel game;
   private Counter remainingBalls;
   /**
    * Constructor for BallRemover.
    *
    * @param game for GameLevel.
    * @param ballsNum for number of balls Counter.
    */
   public BallRemover(GameLevel game, Counter ballsNum) {
        this.game = game;
        this.remainingBalls = ballsNum;
   }
   /** This method is called whenever the beingHit object is hit.
   * Balls that hitting the bottom of the SurfaceBorders should be removed from the game.
   *
   * @param beingHit is the Block that's being hit.
   * @param hitter is the Ball that's doing the hitting.
   */
   public void hitEvent(Block beingHit, Ball hitter) {
      hitter.removeFromGame(this.game);
      this.remainingBalls.decrease(1);
   }
}
