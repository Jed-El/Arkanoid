import java.util.List;
import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class HighScoresAnimation implements Animation {
   private HighScoresTable scoresTable;
   /**
    * Constructor for EndScreen.
    *
    * @param scores a high-scores table.
    */
   public HighScoresAnimation(HighScoresTable scores) {
      this.scoresTable = scores;
   }
   /**
   * The method is in charge of doing one frame of the animation.
   *
   * @param d a surface to draw on.
   * @param dt the amount of seconds passed since the last call.
   */
   public void doOneFrame(DrawSurface d, double dt) {
      d.setColor(java.awt.Color.BLACK);
      int size = this.scoresTable.size(),
              y = d.getHeight() / (size + 1);
      List<ScoreInfo> scores = this.scoresTable.getHighScores();
      for (int i = 0; i < size; i++) {
         ScoreInfo score = scores.get(i);
         d.drawText(100, y * (i + 1), Integer.toString(i + 1) + ". " + score.getName(), 32);
         d.drawText(400, y * (i + 1), Integer.toString(score.getScore()), 32);
      }
   }
   /**
   * The method is in charge of checking if the animation should stop.
   *
   * @return true if yes, and false if not.
   */
   public boolean shouldStop() {
      return false;
   }
}