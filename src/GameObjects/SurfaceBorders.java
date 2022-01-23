import biuoop.DrawSurface;

/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class SurfaceBorders extends Block {
    /**
     * Constructor for SurfaceBorders, using a given width and height values.
     *
     * @param width the game's gui width (not the borders width!).
     * @param height the game's gui height (not the borders height!).
     */
   public SurfaceBorders(double width, double height) {
      super(new Point(1, 26), width - 3, height - 26, null);
   }
   /**
   * The method is in charge of changing the velocity after a hit.
   *
   * @param hitter the hitting ball.
   * @param collisionPoint the point of the collision.
   * @param currentVelocity the current velocity.
   *
   * @return the new velocity.
   */
   @Override
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
      double x1 = super.getCollisionRectangle().getUpperLeft().getX(),
         y1 = super.getCollisionRectangle().getUpperLeft().getY(),
         x2 = x1 + super.getCollisionRectangle().getWidth(), y2 = y1 + super.getCollisionRectangle().getHeight(),
         velDx = currentVelocity.getDx(), velDy = currentVelocity.getDy();
      if (y2 <= collisionPoint.getY()) {
          hitter.notifyDeath(this);
      }
      // Checking the horizontal direction
      if ((x1 == collisionPoint.getX()) || (x2 == collisionPoint.getX())) {
         velDx = -velDx;
      }
      // Checking the vertical direction
      if (y1 == collisionPoint.getY()) {
         velDy = -velDy;
      }
      return new Velocity(velDx, velDy);
   }
   /**
   * The method is in charge of drawing the SurfaceBorders on the given DrawSurface.
   *
   * @param surface a surface to draw on.
   */
   @Override
   public void drawOn(DrawSurface surface) {
       // Get the SurfaceBorders location values
       int x = (int) super.getCollisionRectangle().getUpperLeft().getX(),
          y = (int) super.getCollisionRectangle().getUpperLeft().getY(),
          w = (int) super.getCollisionRectangle().getWidth(),
          h = (int) super.getCollisionRectangle().getHeight();
       surface.setColor(java.awt.Color.RED);
       // Draw the SurfaceBorders
       surface.drawRectangle(x, y, w, h);
   }
}