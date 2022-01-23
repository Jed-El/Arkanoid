import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Line {
   private Point start;
   private Point end;
   /**
    * Constructor for Line, using given points.
    *
    * @param start for start point value.
    * @param end for end point value.
    */
   public Line(Point start, Point end) {
      this.start = start;
      this.end = end;
   }
   /**
    * Constructor for Line, using given values.
    *
    * @param x1 for start point's x value.
    * @param y1 for start point's y value.
    * @param x2 for end point's x value.
    * @param y2 for end point's y value.
    */
   public Line(double x1, double y1, double x2, double y2) {
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
   }
   /**
    * The method is in charge of finding the length of the line, using Point's distance method to find
    * the distance between the start and end points.
    *
    * @return the length of the line.
    */
   public double length() {
      return this.start.distance(this.end);
   }
   /**
    * The method is in charge of finding the middle point of the line,
    * by finding the averages of x values and the y values of the points.
    *
    * @return the middle point of the line.
    */
   public Point middle() {
      double mX = (this.start.getX() + this.end.getX()) / 2, mY = (this.start.getY() + this.end.getY()) / 2;
      Point mid = new Point(mX, mY);
      return mid;
   }
   /**
    * The method is in charge of finding the start point of the line.
    *
    * @return the start point of the line.
    */
   public Point start() {
      return this.start;
   }
   /**
    * The method is in charge of finding the end point of the line.
    *
    * @return the end point of the line.
    */
   public Point end() {
      return this.end;
   }
   /**
    * The method is in charge of testing if the line is intersect with another line.
    *
    * @param other the other line.
    *
    * @return true if the lines intersect, false otherwise.
    */
   public boolean isIntersecting(Line other) {
      // Get all the x and y values
      double s1X = this.start.getX(), s1Y = this.start.getY(),
         e1X = this.end.getX(), e1Y = this.end.getY(),
         s2X = other.start().getX(), s2Y = other.start().getY(),
         e2X = other.end().getX(), e2Y = other.end().getY(),
         // Calculate dx and dy values
         dX1 = s1X - e1X, dX2 = s2X - e2X,
         dY1 = s1Y - e1Y, dY2 = s2Y - e2Y;
      // Check if the lines are parallel or unite (if they have the same slope)
      if (dY1 * dX2 == dY2 * dX1) {
         return false;
      }
      // Check for intersect in a case that this line is perpendicular.
      if (dX1 == 0) {
         if ((s1X <= Math.max(s2X, e2X)) && (s1X >= Math.min(s2X, e2X))) {
            return true;
      }
         return false;
      }
      // Check for intersect in a case that the other line is perpendicular.
      if (dX2 == 0) {
         if ((s2X <= Math.max(s1X, e1X)) && (s2X >= Math.min(s1X, e1X))) {
            return true;
         }
         return false;
      }
      double ansX = ((s1X * dY1 / dX1) - (s2X * dY2 / dX2) + s2Y - s1Y) / ((dY1 / dX1) - (dY2 / dX2));
      // Check for intersect.
      if ((ansX <= Math.max(s1X, e1X)) && (ansX >= Math.min(s1X, e1X))
         && (ansX <= Math.max(s2X, e2X)) && (ansX >= Math.min(s2X, e2X))) {
         return true;
      }
      return false;
   }
   /**
    * The method is in charge of finding the intersection point of the line with another line.
    *
    * @param other the other line.
    *
    * @return the intersection point if the lines intersect, and null otherwise
    */
   public Point intersectionWith(Line other) {
      // Get all the x and y values
      double s1X = this.start.getX(), s1Y = this.start.getY(),
         e1X = this.end.getX(), e1Y = this.end.getY(),
         s2X = other.start().getX(), s2Y = other.start().getY(),
         e2X = other.end().getX(), e2Y = other.end().getY(),
         // Calculate dx and dy values
         dX1 = s1X - e1X, dX2 = s2X - e2X,
         dY1 = s1Y - e1Y, dY2 = s2Y - e2Y;
      // Check if the lines are parallel or unite (if they have the same slope)
      if (dY1 * dX2 == dY2 * dX1) {
         return null;
      }
      // Check for intersect in a case that this line is perpendicular.
      if (dX1 == 0) {
         double x = s1X, y = (((s1X - s2X) * dY2 / dX2) + s2Y);
         if (((x <= Math.max(s2X, e2X)) && (x >= Math.min(s2X, e2X))
               && (y <= Math.max(s1Y, e1Y)) && (y >= Math.min(s1Y, e1Y)))) {
            return new Point(x, y);
         }
         return null;
      }
      // Check for intersect in a case that the other line is perpendicular.
      if (dX2 == 0) {
         double x = s2X, y = (((s2X - s1X) * dY1 / dX1) + s1Y);
         if (((x <= Math.max(s1X, e1X)) && (x >= Math.min(s1X, e1X))
               && (y <= Math.max(s2Y, e2Y)) && (y >= Math.min(s2Y, e2Y)))) {
            return new Point(x, y);
         }
      }
      double ansX = ((s1X * dY1 / dX1) - (s2X * dY2 / dX2) + s2Y - s1Y) / ((dY1 / dX1) - (dY2 / dX2)),
         ansY = ((ansX - s1X) * dY1 / dX1) + s1Y;
      // Check for intersect.
      if ((ansX <= Math.max(s1X, e1X)) && (ansX >= Math.min(s1X, e1X))
         && (ansX <= Math.max(s2X, e2X)) && (ansX >= Math.min(s2X, e2X))) {
         return new Point(ansX, ansY);
      }
      return null;
   }
   /**
    * The method is in charge of testing if the line equals to another line.
    *
    * @param other the other line.
    *
    * @return true if the lines are equal, false otherwise.
    */
   public boolean equals(Line other) {
      return ((this.start.equals(other.start()) && this.end.equals(other.end()))
         || (this.start.equals(other.end()) && this.end.equals(other.start())));
   }
   /**
    * The method checks if the line intersects with a given rectangle.
    *
    * @param rect the rectangle.
    *
    * @return If this line does not intersect with the rectangle, return null.
    * Otherwise, return the closest intersection point to the start of the line.
    */
   public Point closestIntersectionToStartOfLine(Rectangle rect) {
      List<Point> list = rect.intersectionPoints(this);
      Point answer = null;
      // check the closest point to start point
      for (Point i: list) {
         // if the point closer then answer point - change answer point
         if ((answer != null) && (i != null)) {
            if (i.distance(this.start) < answer.distance(this.start)) {
               answer = i;
            }
         } else {
            if (answer == null) {
               answer = i;
            }
         }
      }
      return answer;
   }
}