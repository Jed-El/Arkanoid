/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface Collidable {
   /**
   * The method is in charge of finding "collision shape" of the object.
   *
   * @return the "collision shape" of the object.
   */
   Rectangle getCollisionRectangle();
   /**
   * The method is in charge of changing the velocity after a hit.
   *
   * @param hitter the hitting ball.
   * @param collisionPoint the point of the collision.
   * @param currentVelocity the current velocity.
   *
   * @return the new velocity.
   */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}