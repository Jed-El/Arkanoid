import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Ass6Game {
    /**
     * The method is in charge of creating a sub-Menu.
     *
     * @param reader a Reader for sub-menu data.
     * @param runner a AnimationRunner for Menu build.
     * @param ks a KeyboardSensor for Menu build.
     * @param showHSTask a Task of showing the high score.
     *
     * @return a new Menu for sub-menu.
     *
     * @throws Exception when the text missing data or unreadable.
     */
    private static Menu<Task<Void>> createSM(Reader reader, AnimationRunner runner,
            KeyboardSensor ks, ShowHiScoresTask showHSTask) throws Exception {
        Menu<Task<Void>> sub = new MenuAnimation<Task<Void>>("Set-Levels", ks);
        sub.setMessage("start");
        LevelSpecificationReader lsr = new LevelSpecificationReader();
        List<String> text = ReadText.readText(reader);
        for (int i = 0; i < text.size(); i++) {
            String key, message;
            if ((i % 2 == 0) && (i + 1 < text.size())) {
                String[] val = text.get(i).split(":");
                if ((val.length > 0) && (i + 1 < text.size())) {
                    i++;
                    key = val[0];
                    message = val[1];
                    String levelsuorce = text.get(i);
                    sub.addSelection(key, message, new Task<Void>() {
                        // the task that run the levels
                        public Void run() {
                            InputStream source = ClassLoader.getSystemClassLoader().getResourceAsStream(levelsuorce);
                            Reader read = new InputStreamReader(source);
                            List<LevelInformation> levels = lsr.fromReader(read);
                            GameFlow game = new GameFlow(runner, ks, levels, 7);
                            int score = game.runLevels();
                            showHSTask.addToTable(score);
                            showHSTask.run();
                            return null;
                        }
                    });
                }
            }
        }
        return sub;
    }
   /**
   * This is the main function. It's runs the game.
   *
   * @param args used to run a given levels set.
   */
   public static void main(String[] args) {
      GUI gui = new GUI("title", 800, 600);
      AnimationRunner animationRunner = new AnimationRunner(gui);
      biuoop.KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
      DialogManager dialog = gui.getDialogManager();
      ShowHiScoresTask showHSTask = new ShowHiScoresTask(animationRunner, dialog, keyboardSensor);
      // Creating the sub-menu
      Menu<Task<Void>> subMenu;
      String path = "level_sets.txt";
      if (args.length > 0) {
          path = args[0];
      }
      try {
          InputStream source = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
          Reader reader = new InputStreamReader(source);
          subMenu = createSM(reader, animationRunner, keyboardSensor, showHSTask);
      } catch (Exception e) {
           e.printStackTrace();
           return;
      }
      // Creating the main menu
      Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keyboardSensor);
      menu.addSubMenu("s", "start a new game", subMenu);
      menu.addSelection("h", "see the high scores", showHSTask);
      menu.addSelection("q", "quit", new Task<Void>() {
          public Void run() {
             gui.close();
             System.exit(0);
             return null;
           } });
      while (true) {
          menu.setMenu();
          animationRunner.run(menu);
          Task<Void> status = menu.getStatus();
          status.run();
      }
   }
}