import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
class HighScoresTable {
   private List<ScoreInfo> scores;
   /**
   * Constructor for HighScoresTable.
   *
   * @param size for table size.
   */
   public HighScoresTable(int size) {
       List<ScoreInfo> highScores = new ArrayList<ScoreInfo>();
       for (int i = 0; i < size; i++) {
           highScores.add(new ScoreInfo("---", 0));
       }
       this.scores = highScores;
   }
   /**
   * The method is in charge of checking if a given score value can be add to the high-scores table.
   *
   * @param score the given score value.
   *
   * @return true if the score rank high enough, and false otherwise
   */
   public boolean checkToAdd(int score) {
       int rank = this.getRank(score) - 1,
               size = this.size();
       if (rank < size) {
           return true;
       }
       return false;
   }
   /**
   * The method is in charge of adding a given high-score.
   *
   * @param score the new high-score.
   */
   public void add(ScoreInfo score) {
       int rank = this.getRank(score.getScore()) - 1,
               size = this.size();
       if (rank < size) {
           this.scores.add(rank, score);
           this.scores.remove(size);
       }
   }
   /**
   * The method is in charge of finding the size of the high-scores table.
   *
   * @return table size.
   */
   public int size() {
       return this.scores.size();
   }
   /**
   * The method is in charge of finding the current high scores.
   * Note: The list is sorted such that the highest scores come first.
   *
   * @return the current high scores.
   */
   public List<ScoreInfo> getHighScores() {
       return this.scores;
   }
   /**
   * The method is in charge of finding the rank of the current score in the high-scores table.
   *
   * @param score the current score.
   *
   * @return the rank.
   */
   public int getRank(int score) {
       int i;
       for (i = 0; i < this.scores.size(); i++) {
           if (this.scores.get(i).getScore() <= score) {
              break;
           }
       }
       return i + 1;
   }
   /**
   * The method is in charge of clearing the high-scores table.
   */
   public void clear() {
       this.scores.clear();
   }
   /**
   * The method is in charge of loading the high-scores table data from a specified file.
   * Note: Current table data is cleared.
   *
   * @param fileName the file.
   *
   * @throws IOException if can't find file to open.
   * @throws ClassNotFoundException if thasn't find the object class.
   */
   public void load(File fileName) throws IOException, ClassNotFoundException {
       ObjectInputStream objectInputStream = null;
       this.scores.clear();
       objectInputStream = new ObjectInputStream(
               new FileInputStream(fileName));
       this.scores = (ArrayList<ScoreInfo>) objectInputStream.readObject();
       if (objectInputStream != null) {
           objectInputStream.close();
       }
   }
   /**
   * The method is in charge of saving the high-scores table data to a specified file.
   *
   * @param fileName the name to the file.
   *
   * @throws IOException if failed saving the object and closing the file.
   */
   public void save(File fileName) throws IOException {
       ObjectOutputStream objectOutputStream = null;
       objectOutputStream = new ObjectOutputStream(
                              new FileOutputStream(fileName));
       objectOutputStream.writeObject(this.scores);
       if (objectOutputStream != null) {
           objectOutputStream.close();
       }
   }
   /**
   * The method is in charge of reading a high-scores table data from a specified file,
   * and return the high-scores table. If the file could not be found or read - a new file
   * with new high-score table created.
   *
   * @param fileName the file to read from.
   *
   * @return the high-scores table from the file.
   */
   public static HighScoresTable loadFromFile(File fileName) {
       HighScoresTable scores = new HighScoresTable(0);
       ObjectInputStream objectInputStream = null;
       try {
           scores.load(fileName);
       } catch (Exception e) {
           scores = new HighScoresTable(5);
           try {
               scores.save(fileName);
           } catch (Exception ex) {
               System.out.println(ex);
           }
       } finally {
           try {
               if (objectInputStream != null) {
                   objectInputStream.close();
               }
           } catch (IOException e) {
               System.err.println("Failed closing file: " + fileName);
           }
       }
       return scores;
   }
}