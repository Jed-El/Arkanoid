import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class SpriteCollection {
   private List<Sprite> list;
   /**
    * Constructor for SpriteCollection.
    */
   public SpriteCollection() {
      this.list = new ArrayList<Sprite>();
   }
   /**
   * The method is in charge of adding a new Sprite object to the list.
   *
   * @param s the new object.
   */
   public void addSprite(Sprite s) {
      this.list.add(s);
   }
   /**
   * The method is in charge of removing a given Sprite object from the list.
   *
   * @param s the given object.
   */
   public void removeSprite(Sprite s) {
       this.list.remove(s);
   }
   /**
   * The method is in charge of notifying all the sprites that time has passed.
   *
   * @param dt the amount of seconds passed since the last call.
   */
   public void notifyAllTimePassed(double dt) {
       List<Sprite> codiedList = new ArrayList<Sprite>(this.list);
       for (Sprite i: codiedList) {
         i.timePassed(dt);
      }
   }
   /**
   * The method is in charge of drawing all the sprites on a given DrawSurface.
   *
   * @param d a surface to draw on.
   */
   public void drawAllOn(DrawSurface d) {
      for (Sprite i: this.list) {
         i.drawOn(d);
      }
   }
}