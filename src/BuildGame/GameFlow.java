import java.util.List;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class GameFlow {
   private AnimationRunner animationRunner;
   private biuoop.KeyboardSensor keyboardSensor;
   private Counter lives;
   private Counter score;
   private List<LevelInformation> levels;
   /**
    * Constructor for GameFlow, using a given AnimationRunner, KeyboardSensor and lives number.
    *
    * @param ar an AnimationRunner.
    * @param ks a KeyboardSensor.
    * @param le an array list of levels to run.
    * @param li a value for lives count.
    */
   public GameFlow(AnimationRunner ar, biuoop.KeyboardSensor ks,
           List<LevelInformation> le, int li) {
       this.animationRunner = ar;
       this.keyboardSensor = ks;
       this.levels = le;
       this.lives = new Counter(li);
       this.score = new Counter(0);
   }
   /**
   * The method is in charge of running the game, by starting the animation loop.
   *
   * @return the score value.
   */
   public int runLevels() {
      for (LevelInformation levelInfo : this.levels) {
         GameLevel level = new GameLevel(levelInfo,
               this.keyboardSensor, this.animationRunner,
               this.lives, this.score);
         Paddle ped = new Paddle(this.keyboardSensor,
               levelInfo.paddleWidth(), levelInfo.paddleSpeed());
         level.initialize();
         ped.addToGame(level);
         while (!level.levelDone()) {
            level.playOneTurn();
            ped.resetPaddle();
            if (this.lives.getValue() <= 0) {
               break;
            }
         }
         if (this.lives.getValue() <= 0) {
             break;
         }
      }
      EndScreen es = new EndScreen(this.lives, this.score);
      KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(
              this.keyboardSensor, KeyboardSensor.SPACE_KEY, es);
      this.animationRunner.run(kpsa);
      return this.score.getValue();
   }
}