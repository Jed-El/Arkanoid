/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Point {
   private double x;
   private double y;
   /**
    * Constructor for Point.
    *
    * @param x for point's x value.
    * @param y for point's y value.
    */
   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   /**
    * The method is in charge of finding the distance to another point.
    *
    * @param other the other point.
    *
    * @return the distance of this point to the other point.
    */
   public double distance(Point other) {
      return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
   }
   /**
    * The method is in charge of testing if the point equals to another point.
    *
    * @param other the other point.
    *
    * @return true if the points are equal, false otherwise.
    */
   public boolean equals(Point other) {
      if ((this.x == other.x) && (this.y == other.y)) {
         return true;
      }
      return false;
   }
   /**
   * The method is in charge of finding x value of the point.
   *
   * @return the x value of this point.
   */
   public double getX() {
      return this.x;
   }
   /**
   * The method is in charge of finding y value of the point.
   *
   * @return the y value of this point.
   */
   public double getY() {
      return this.y;
   }
}