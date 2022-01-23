import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
 public class ReadText {
    /**
     * The method is in charge of creating a list of String for the text.
     *
     * @param reader a given file reader.
     *
     * @return a list of Strings.
     *
     * @throws IOException if could not read the file.
     */
    public static List<String> readText(Reader reader) throws IOException {
       String line = new String("");
       List<String> text = new ArrayList<String>();
       int intValOfChar;
       while ((intValOfChar = reader.read()) != -1) {
           if (intValOfChar == '\n') {
               text.add(line);
               line = new String("");
           } else {
           line += (char) intValOfChar;
           }
       }
       text.add(line);
       return text;
   }
   /**
   * The method is in charge of creating a list of levels-text (as list of Strings).
   *
   * @param text a given List<String> of data.
   *
   * @return a list of list of Strings.
   */
    public static List<List<String>> splitText(List<String> text) {
       List<List<String>> levels = new ArrayList<List<String>>();
       int start = text.indexOf("START_LEVEL"),
               end = text.indexOf("END_LEVEL");
       while ((end > 0) && (start >= 0)) {
           levels.add(new ArrayList<String>(text.subList(start + 1, end)));
           text.remove(end);
           text.remove(start);
           start = text.indexOf("START_LEVEL");
           end = text.indexOf("END_LEVEL");
       }
       return levels;
   }
   /**
    * The method is in charge of creating a Color from a String.
    *
    * @param text a given String.
    *
    * @return a Color.
    *
    * @throws Exception when the text missing data or unreadable.
    */
    public static Color findColor(String text) throws Exception {
        String[] sprite = text.split("[()]");
        if (sprite[1].equals("RGB")) {
            String[] val = sprite[2].split(",");
            int x = Integer.parseInt(val[0]),
                y = Integer.parseInt(val[1]),
                z = Integer.parseInt(val[2]);
            return new Color(x, y, z);
        } else {
            Field field = Class.forName("java.awt.Color").getField(sprite[1]);
            return (Color) field.get(null);
        }
   }
   /**
   * The method is in charge of creating a Sprite from a String.
   *
   * @param text a given String.
   *
   * @return a Sprite.
   *
   * @throws Exception when the text missing data or unreadable.
   */
    public static DrawTask getSprite(String text) throws Exception {
       String[] sprite = text.split("[()]");
       if (sprite[0].equals("image")) {
           InputStream source = ClassLoader.getSystemClassLoader().getResourceAsStream(sprite[1]);
           Image img = ImageIO.read(source);
           return new DrawImage(img);
       }
       if (sprite[0].equals("color")) {
           Color color = findColor(text);
           return new DrawRect(color);
       }
       throw new Exception("Error in Sprite data");
   }
}
