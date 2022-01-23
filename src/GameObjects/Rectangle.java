import java.util.ArrayList;
import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
class Rectangle {
   private Point location;
   private double width;
   private double height;
   /**
   * Constructor for Rectangle, using a given location and size values.
   *
   * @param upperLeft for rectangle location.
   * @param width for rectangle width.
   * @param height for rectangle height.
   */
   public Rectangle(Point upperLeft, double width, double height) {
      this.location = upperLeft;
      this.width = width;
      this.height = height;
   }
   /**
   * The method is in charge of finding a list of intersection points with a given line.
   *
   * @param line is the given line.
   *
   * @return list of intersection points.
   */
   public List<Point> intersectionPoints(Line line) {
      // get all the rectangle's points
      double x1 = this.getUpperLeft().getX(), y1 = this.getUpperLeft().getY(),
         x2 = x1 + this.width, y2 = y1 + this.height;
      // check for the line's intersection with every rectangle's line
      List<Point> list =  new ArrayList<Point>();
      list.add(line.intersectionWith(new Line(x1, y1, x2, y1)));
      list.add(line.intersectionWith(new Line(x2, y1, x2, y2)));
      list.add(line.intersectionWith(new Line(x2, y2, x1, y2)));
      list.add(line.intersectionWith(new Line(x1, y2, x1, y1)));
      return list;
   }
   /**
   * The method is in charge of changing the width of the rectangle.
   *
   * @param w the new width of the rectangle.
   */
   public void setWidth(double w) {
           this.width = w;
   }
   /**
   * The method is in charge of changing the height of the rectangle.
   *
   * @param h the new height of the rectangle.
   */
   public void setHeight(double h) {
      this.height = h;
   }
   /**
   * The method is in charge of changing the upper-left point of the rectangle.
   *
   * @param upperLeft the new upper-left point of the rectangle.
   */
   public void setUpperLeft(Point upperLeft) {
      this.location = upperLeft;
   }
   /**
   * The method is in charge of finding the width of the rectangle.
   *
   * @return the width of the rectangle.
   */
   public double getWidth() {
      return this.width;
   }
   /**
   * The method is in charge of finding the height of the rectangle.
   *
   * @return the height of the rectangle.
   */
   public double getHeight() {
      return this.height;
   }
   /**
   * The method is in charge of finding the upper-left point of the rectangle.
   *
   * @return the upper-left point of the rectangle.
   */
   public Point getUpperLeft() {
      return this.location;
   }
}