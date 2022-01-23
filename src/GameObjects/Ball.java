import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Ball implements Sprite, HitNotifier {
   private List<HitListener> hitListeners;
   private Point location;
   private int size;
   private java.awt.Color color;
   private Velocity vel;
   private GameEnvironment environment;
   /**
    * Constructor for Ball, using a given location point.
    *
    * @param center for ball location.
    * @param r for ball size.
    * @param color for ball color.
    */
   public Ball(Point center, int r, java.awt.Color color) {
      this.location = center;
      this.size = r;
      this.color = color;
      this.vel = new Velocity(0, 0);
      this.environment = null;
      this.hitListeners = new ArrayList<HitListener>();
   }
   /**
   * Constructor for Ball, using given location values (needed for the test).
   *
   * @param x for x value of the ball location.
   * @param y for y value of the ball location.
   * @param r for ball size.
   * @param color for ball color.
   */
   public Ball(int x, int y, int r, java.awt.Color color) {
      this.location = new Point(x, y);
      this.size = r;
      this.color = color;
      this.vel = new Velocity(0, 0);
      this.environment = null;
      this.hitListeners = new ArrayList<HitListener>();
   }
   /**
   * The method is in charge of setting velocity, using a given velocity.
   *
   * @param v for Velocity of the ball.
   */
   public void setVelocity(Velocity v) {
      this.vel = v;
   }
   /**
   * The method is in charge of setting velocity values, using given velocity values.
   *
   * @param dx for dx value of the ball velocity.
   * @param dy for dy value of the ball velocity.
   */
   public void setVelocity(double dx, double dy) {
      this.vel = new Velocity(dx, dy);
   }
   /**
   * The method is in charge of setting environment.
   *
   * @param envi for the ball environment.
   */
   public void setEnvironment(GameEnvironment envi) {
      this.environment = envi;
   }
   /**
   * The method is in charge of adding a given HitListener.
   *
   * @param hl the HitListener.
   */
   public void addHitListener(HitListener hl) {
       this.hitListeners.add(hl);
   }
   /**
   * The method is in charge of removing a given HitListener.
   *
   * @param hl the HitListener.
   */
   public void removeHitListener(HitListener hl) {
       this.hitListeners.remove(hl);
   }
   // accessors
   /**
   * The method is in charge of finding x value of the ball location.
   *
   * @return x value.
   */
   public int getX() {
      return (int) this.location.getX();
   }
   /**
   * The method is in charge of finding y value of the ball location.
   *
   * @return y value.
   */
   public int getY() {
      return (int) this.location.getY();
   }
   /**
   * The method is in charge of finding the ball size.
   *
   * @return size value.
   */
   public int getSize() {
      return this.size;
   }
   /**
   * The method is in charge of finding the ball color.
   *
   * @return color value.
   */
   public java.awt.Color getColor() {
      return this.color;
   }
   /**
   * The method is in charge of finding the ball velocity.
   *
   * @return velocity value.
   */
   public Velocity getVelocity() {
      return this.vel;
   }
   /**
   * The method is in charge of drawing the ball on the given DrawSurface.
   *
   * @param surface a surface to draw on.
   */
   public void drawOn(DrawSurface surface) {
      // Get the ball location values
      int x = getX(), y = getY();
      surface.setColor(this.color);
      // Draw the ball
      surface.fillCircle(x, y, this.size);
      surface.setColor(java.awt.Color.BLACK);
      surface.drawCircle(x, y, this.size);
   }
   /**
   * The method is in charge of changing the ball location, using the velocity values.
   * Note: check if the ball's environment for a hit, and changes it's velocity.
   *
   * @param dt the amount of seconds passed since the last call.
   */
   public void timePassed(double dt) {
      double x = this.location.getX(), y = this.location.getY();
      Point next = this.vel.applyToPoint(this.location, dt);
      CollisionInfo col = null;
      int i;
      for (i = 0; i < 250; i++) {
          double dx = this.vel.getDx(), dy = this.vel.getDy();
          Line move = new Line(this.location, next);
          // check for a hit
          col = this.environment.getClosestCollision(move);
          if (col.collisionPoint() == null) {
             // if not - break the loop
             this.setVelocity(dx, dy);
             break;
          } else {
             // change the return (for close location) angel after 4 tries
             double j = (1 + i / 4) * Math.PI / 6;
             if (dx < 0) {
                x = col.collisionPoint().getX() + 7 * Math.sin(j);
             }
             if (dx > 0) {
                x = col.collisionPoint().getX() - 7 * Math.sin(j);
             }
             if (dy < 0) {
                y = col.collisionPoint().getY() + 7 * Math.cos(j);
             }
             if (dy > 0) {
                y = col.collisionPoint().getY() - 7 * Math.cos(j);
             }
             // set the new velocity
             this.setVelocity(col.collisionObject().hit(this, col.collisionPoint(), this.vel));
             // check again for a hit
             next = new Point(x, y);
            }
         }
      // move the ball
      this.location = next;
   }
   /**
   * The method is in charge of adding the ball to the game.
   *
   * @param game the game.
   */
   public void addToGame(GameLevel game) {
      game.addSprite(this);
   }
   /**
   * The method is in charge of removing the ball from the game.
   *
   * @param game the game.
   */
   public void removeFromGame(GameLevel game) {
      game.removeSprite(this);
   }
   /**
   * The method is in charge of notifying the Ball's Listeners that it's being removed.
   *
   * @param beingHit the object that being hit to remove the Ball.
   */
   public void notifyDeath(Block beingHit) {
       // Make a copy of the hitListeners before iterating over them.
       List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
       // Notify all listeners about a hit event:
       for (HitListener hl : listeners) {
          hl.hitEvent(beingHit, this);
       }
   }
}