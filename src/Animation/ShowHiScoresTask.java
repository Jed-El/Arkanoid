import java.io.File;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class ShowHiScoresTask implements Task<Void> {
   private File fileName = new File("highscores");
   private AnimationRunner runner;
   private DialogManager dialog;
   private KeyboardSensor keyboard;
   private HighScoresTable scoresTable;
   /**
    * Constructor for ShowHiScoresTask.
    *
    * @param runner an AnimationRunner.
    * @param dm a DialogManager.
    * @param ks a KeyboardSensor.
    */
   public ShowHiScoresTask(AnimationRunner runner, DialogManager dm, KeyboardSensor ks) {
      this.runner = runner;
      this.dialog = dm;
      this.keyboard = ks;
      this.scoresTable = HighScoresTable.loadFromFile(this.fileName);
   }
   /**
   * The method is in charge of adding the new high-score to the table.
   *
   * @param score the the new score.
   */
   public void addToTable(int score) {
       if (this.scoresTable.getRank(score) >= this.scoresTable.size()) {
           return;
       }
       String name = this.dialog.showQuestionDialog("Name", "What is your name?", "");
       System.out.println(name);
       ScoreInfo newScore = new ScoreInfo(name, score);
       this.scoresTable.add(newScore);
       try {
           this.scoresTable.save(this.fileName);
       } catch (Exception e) {
           System.out.println("Failed to save the new high-score");
       }
   }
   /**
   * The method is in charge of running the high-score animation.
   *
   * @return null.
   */
   public Void run() {
      HighScoresAnimation hsa = new HighScoresAnimation(this.scoresTable);
      KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(
              this.keyboard, KeyboardSensor.SPACE_KEY, hsa);
      this.runner.run(kpsa);
      return null;
   }
}
