/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
   private GameLevel game;
   private Counter remainingBlocks;
   /**
    * Constructor for BlockRemover.
    *
    * @param game for GameLevel.
    * @param removedBlocks for removed block Counter.
    */
   public BlockRemover(GameLevel game, Counter removedBlocks) {
       this.game = game;
       this.remainingBlocks = removedBlocks;
   }
   /** This method is called whenever the beingHit object is hit.
   * Blocks that are hit and reach 0 hit-points should be removed from the game.
   *
   * @param beingHit is the Block that's being hit.
   * @param hitter is the Ball that's doing the hitting.
   */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() == 0) {
           beingHit.removeHitListener(this);
           beingHit.removeFromGame(this.game);
           this.remainingBlocks.decrease(1);
       }
   }
}
