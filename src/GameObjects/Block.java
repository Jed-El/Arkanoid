import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Block implements Collidable, Sprite, HitNotifier {
   private List<HitListener> hitListeners;
   private Rectangle rect;
   private int count;
   private FillBlock appearance;
   private Color borders;
   /**
   * Constructor for Block, using a given location, size and appearance values.
   *
   * @param upperLeft for block location.
   * @param width for block width.
   * @param height for block height.
   * @param fb a FillBlock.
   */
   public Block(Point upperLeft, double width, double height, FillBlock fb) {
      this.rect = new Rectangle(upperLeft, width, height);
      this.appearance = fb;
      this.hitListeners = new ArrayList<HitListener>();
      this.count = -1;
   }
   /**
   * The method is in charge of setting a new color for borders.
   *
   * @param b for the new borders color.
   */
   public void setBorders(Color b) {
       this.borders = b;
    }
   /**
   * The method is in charge of setting hits counter, using a given number.
   *
   * @param num for the new counter value.
   */
   public void setCounter(int num) {
       this.count = num;
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
   /**
   * The method is in charge of finding "collision shape" of the object.
   *
   * @return the "collision shape" of the object.
   */
   public Rectangle getCollisionRectangle() {
      return this.rect;
   }
   /**
   * The method is in charge of finding HitPoints of the Block.
   *
   * @return the count value.
   */
   public int getHitPoints() {
       return this.count;
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
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
      if (this.count > 0) {
         this.count--;
      }
      this.notifyHit(hitter);
      double x1 = this.rect.getUpperLeft().getX(), y1 = this.rect.getUpperLeft().getY(),
         x2 = x1 + this.rect.getWidth(), y2 = y1 + this.rect.getHeight(),
         velDx = currentVelocity.getDx(), velDy = currentVelocity.getDy();
      // Checking the horizontal direction
      if ((x1 == collisionPoint.getX()) || (x2 == collisionPoint.getX())) {
         velDx = -velDx;
      }
      // Checking the vertical direction
      if ((y1 == collisionPoint.getY()) || (y2 == collisionPoint.getY())) {
         velDy = -velDy;
      }
      return new Velocity(velDx, velDy);
   }
   /**
   * The method is in charge of drawing the block on the given DrawSurface.
   *
   * @param surface a surface to draw on.
   */
   public void drawOn(DrawSurface surface) {
      this.appearance.getFill(this.count).draw(this.rect, surface);
      if (this.borders != null) {
          int x = (int) this.rect.getUpperLeft().getX(),
                  y = (int) this.rect.getUpperLeft().getY(),
                  w = (int) this.rect.getWidth(),
                  h = (int) this.rect.getHeight();
          surface.setColor(this.borders);
          surface.drawRectangle(x, y, w, h);
      }
   }
   /**
   * The method is in charge of notifying the block that time has passed.
   *
   * @param dt the amount of seconds passed since the last call.
   */
   public void timePassed(double dt) {
   }
   /**
   * The method is in charge of adding the block to the game.
   *
   * @param game the game.
   */
   public void addToGame(GameLevel game) {
      game.addCollidable(this);
      game.addSprite(this);
   }
   /**
   * The method is in charge of removing the block from the game.
   *
   * @param game the game.
   */
   public void removeFromGame(GameLevel game) {
       game.removeCollidable(this);
       game.removeSprite(this);
   }
   /**
   * The method is in charge of notifying about a hit.
   *
   * @param hitter the hitting ball.
   */
   protected void notifyHit(Ball hitter) {
       // Make a copy of the hitListeners before iterating over them.
       List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
       // Notify all listeners about a hit event:
       for (HitListener hl : listeners) {
          hl.hitEvent(this, hitter);
       }
   }
}