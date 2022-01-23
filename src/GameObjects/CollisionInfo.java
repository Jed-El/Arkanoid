/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class CollisionInfo {
   private Point point;
   private Collidable object;
   /**
   * Constructor for CollisionInfo, using given Point and Collidable.
   *
   * @param p for collision point value.
   * @param c for collision object value.
   */
   public CollisionInfo(Point p, Collidable c) {
      this.point = p;
      this.object = c;
   }
   /**
    * The method is in charge of finding the point at which the collision occurs.
    *
    * @return the collision point value.
    */
   public Point collisionPoint() {
      return this.point;
   }
   /**
    * The method is in charge of finding the collidable object involved in the collision.
    *
    * @return the collision object value.
    */
   public Collidable collisionObject() {
      return this.object;
   }
}