import java.util.ArrayList;
import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class GameEnvironment {
   private List<Collidable> list;
   /**
    * Constructor for GameEnvironment.
    */
   public GameEnvironment() {
      this.list = new ArrayList<Collidable>();
   }
   /**
   * The method is in charge of adding a new Collidable object to the list.
   *
   * @param c the new object.
   */
   public void addCollidable(Collidable c) {
      this.list.add(c);
   }
   /**
   * The method is in charge of removing a given Collidable object from the list.
   *
   * @param c the given object.
   */
   public void removeCollidable(Collidable c) {
       this.list.remove(c);
   }
   /**
   * The method is in charge of finding collidable info, using a given trajectory line.
   *
   * @param trajectory the given line.
   *
   * @return information about the closest collision to trajectory.start.
   */
   public CollisionInfo getClosestCollision(Line trajectory) {
      Point colPoint = null, checkPoint = null;
      Collidable colObj = null;
      List<Collidable> copiedList = new ArrayList<Collidable>(this.list);
      // check the closest point to start point
      for (Collidable checkObj: copiedList) {
         checkPoint = trajectory.closestIntersectionToStartOfLine(checkObj.getCollisionRectangle());
         // if the point closer then colPoint point - change colPoint point
         if (checkPoint != null) {
             double x = Math.round(checkPoint.getX()),
                y = Math.round(checkPoint.getY());
             checkPoint = new Point(x, y);
             if (colPoint != null) {
                if (checkPoint.distance(trajectory.start()) < colPoint.distance(trajectory.start())) {
                   colPoint = checkPoint;
                   colObj = checkObj;
                }
             } else {
                if (colPoint == null) {
                   colPoint = checkPoint;
                   colObj = checkObj;
                }
             }
         }
      }
      return new CollisionInfo(colPoint, colObj);
   }
}