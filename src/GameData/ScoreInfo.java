import java.io.Serializable;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class ScoreInfo implements Serializable {
   private int score;
   private String name;
   /**
   * Constructor for ScoreInfo.
   *
   * @param name a String with the player name.
   * @param score a number of points.
   */
   public ScoreInfo(String name, int score) {
       this.name = name;
       this.score = score;
   }
   /**
   * The method is in charge of finding the player's name.
   *
   * @return name.
   */
   public String getName() {
       return this.name;
   }
   /**
   * The method is in charge of finding the score value.
   *
   * @return score.
   */
   public int getScore() {
       return this.score;
   }
}