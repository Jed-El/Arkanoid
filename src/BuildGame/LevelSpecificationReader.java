import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class LevelSpecificationReader {
    /**
     * The method is in charge of creating a levelInforation from list of String.
     *
     * @param text a given list of String.
     *
     * @return a levelInforation.
     *
     * @throws Exception when the file missing data or unreadable.
     */
     private LevelInformation levelInfoFromLines(List<String> text) throws Exception {
         List<Velocity> ballsVelocity = new ArrayList<Velocity>();
         int paddleSpeed = 0, paddleWidth = 0, numOfBlocksToRemove = 0,
             blocksStartX = 0, blocksStartY = 0, rowHeight = 0;
         String levelName = new String();
         List<Block> blocks;
         Sprite background = null;
         Reader blockDefinitions = null;
         List<String> blocksText = new ArrayList<String>();
         for (int i = 0; i < text.size(); i++) {
             String[] val = text.get(i).split(":");
             if (val[0].equals("level_name")) {
                 levelName = val[1];
             }
             if (val[0].equals("ball_velocities")) { // create balls velocities
                 String[] vel = val[1].split(" "); // split to balls
                 for (int j = 0; j < vel.length; j++) {
                     String[] velVal = vel[j].split(","); // split to angle and speed
                     double angle = Double.parseDouble(velVal[0]),
                            speed = Double.parseDouble(velVal[1]);
                     ballsVelocity.add(Velocity.fromAngleAndSpeed(angle, speed));
                 }
             }
             if (val[0].equals("background")) {
                 background = ReadText.getSprite(val[1]);
             }
             if (val[0].equals("paddle_speed")) {
                 paddleSpeed = Integer.parseInt(val[1]);
             }
             if (val[0].equals("paddle_width")) {
                 paddleWidth = Integer.parseInt(val[1]);
             }
             if (val[0].equals("num_blocks")) {
                 numOfBlocksToRemove = Integer.parseInt(val[1]);
             }
             if (val[0].equals("row_height")) {
                 rowHeight = Integer.parseInt(val[1]);
             }
             if (val[0].equals("block_definitions")) {
                 InputStream source = ClassLoader.getSystemClassLoader().getResourceAsStream(val[1]);
                 blockDefinitions = new InputStreamReader(source);
             }
             if (val[0].equals("blocks_start_x")) {
                 blocksStartX = Integer.parseInt(val[1]);
             }
             if (val[0].equals("blocks_start_y")) {
                 blocksStartY = Integer.parseInt(val[1]);
             }
             if (val[0].equals("START_BLOCKS")) {
                 i++;
                 while ((text.get(i) != null) && (!text.get(i).equals("END_BLOCKS"))) {
                     blocksText.add(text.get(i));
                     i++;
                 }
             }
         }
         // create the blocks
         blocks = createBlocks(blockDefinitions, blocksText,
                 blocksStartX, blocksStartY, rowHeight);
         // create the level (- may throw an Exception, if a given value is null).
         return new Level(ballsVelocity, paddleSpeed, paddleWidth,
                 levelName, blocks, numOfBlocksToRemove, background);
    }
   /**
    * The method is in charge of creating a list of Blocks.
    *
    * @param blockDefinitions a reader for block types data.
    * @param blocksText a given list of String.
    * @param blocksStartX a x value for blocks location.
    * @param blocksStartY a y value for blocks location.
    * @param rowHeight a value for row height.
    *
    * @return a List<Block>.
    *
    * @throws Exception when the text missing data or unreadable.
    */
   private List<Block> createBlocks(Reader blockDefinitions, List<String> blocksText,
           int blocksStartX, int blocksStartY, int rowHeight) throws Exception {
       List<Block> blocks = new ArrayList<Block>();
       BlocksFromSymbolsFactory bfsf = BlocksDefinitionReader.fromReader(blockDefinitions);
       int y = blocksStartY;
       for (int r = 0; r < blocksText.size(); r++) {
           int x = blocksStartX;
           String row = blocksText.get(r);
           for (int i = 0; i < row.length(); i++) {
               String s = "" + row.charAt(i);
               if (bfsf.isBlockSymbol(s)) {
                  blocks.add(bfsf.getBlock(s, x, y));
                  x = x + bfsf.getBlockWidth(s);
               }
               if (bfsf.isSpaceSymbol(s)) {
                  x = x + bfsf.getSpaceWidth(s);
               }
           }
           y = y + rowHeight;
       }
       return blocks;
   }
   /**
   * The method is in charge of creating a list of levelInforation from the file.
   *
   * @param reader a given file reader.
   *
   * @return a list of levelInforation.
   */
   public List<LevelInformation> fromReader(Reader reader) {
       List<LevelInformation> levels = new ArrayList<LevelInformation>();
       List<List<String>> levelsText = new ArrayList<List<String>>();
       List<String> text = new ArrayList<String>();
       try {
           text = ReadText.readText(reader);
           levelsText = ReadText.splitText(text);
           for (int i = 0; i < levelsText.size(); i++) {
               levels.add(levelInfoFromLines(levelsText.get(i)));
           }
       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return levels;
   }
}