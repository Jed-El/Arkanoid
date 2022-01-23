/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*
* Velocity specifies the change in position on the `x` and the `y` axes.
*/
public class Velocity {
   private double dx;
   private double dy;
   /**
   * Constructor for Velocity.
   *
   * @param dx for dx value.
   * @param dy for dy value.
   */
   public Velocity(double dx, double dy) {
      this.dx = dx;
      this.dy = dy;
   }
   /**
   * The method changes Velocity's dx value.
   *
   * @param newDx for new dx value.
   */
   public void setDx(double newDx) {
      this.dx = newDx;
   }
   /**
   * The method changes Velocity's dy value.
   *
   * @param newDy for new dy value.
   */
   public void setDy(double newDy) {
      this.dy = newDy;
   }
   /**
   * The method finds Velocity's dx value.
   *
   * @return dx value.
   */
   public double getDx() {
      return this.dx;
   }
   /**
   * The method finds Velocity's dy value.
   *
   * @return dy value.
   */
   public double getDy() {
      return this.dy;
   }
   /**
   * The method takes a point with position (x,y) and return a new point with position (x+dx, y+dy).
   *
   * @param p the given point.
   * @param dt the amount of seconds passed since the last call.
   *
   * @return the new point.
   */
   public Point applyToPoint(Point p, double dt) {
      double x = p.getX(), y = p.getY();
      return new Point(x + dt * this.dx, y + dt * this.dy);
   }
   /**
   * The method is in charge of creating Velocity using angle and speed values.
   *
   * @param angle for direction.
   * @param speed for length.
   *
   * @return new Velocity
   */
   public static Velocity fromAngleAndSpeed(double angle, double speed) {
      double radians = Math.PI * (((angle % 360) / 180) - 0.5),
         dx = Math.cos(radians) * speed, dy = Math.sin(radians) * speed;
      return new Velocity(dx, dy);
   }
}