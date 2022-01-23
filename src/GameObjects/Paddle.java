import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Paddle implements Sprite, Collidable {
   private biuoop.KeyboardSensor keyboard;
   private Rectangle rect;
   private int speed;
   /**
    * Constructor for Paddle, using a given KeyboardSensor.
    *
    * @param kb a KeyboardSensor.
    * @param size for paddle width.
    * @param s for paddle speed.
    */
   public Paddle(biuoop.KeyboardSensor kb, double size, int s) {
      this.rect = new Rectangle(new Point((800 - size) / 2, 575), size, 10);
      this.keyboard = kb;
      this.speed = s;
   }
   /**
   * The method is in charge of reseting the paddle location and size.
   */
   public void resetPaddle() {
       double size = this.rect.getWidth();
       this.rect = new Rectangle(new Point((800 - size) / 2, 575), size, 10);
   }
   /**
   * The method is in charge of moving the paddle to the left.
   *
   * @param dt the amount of seconds passed since the last call.
   */
   public void moveLeft(double dt) {
      double newX = this.rect.getUpperLeft().getX() - dt * this.speed,
         y = this.rect.getUpperLeft().getY();
      Point p = new Point(newX, y);
      if (newX >= 0) {
         this.rect.setUpperLeft(p);
      }
   }
   /**
   * The method is in charge of moving the paddle to the right.
   *
   * @param dt the amount of seconds passed since the last call.
   */
   public void moveRight(double dt) {
      double newX = this.rect.getUpperLeft().getX() + dt * this.speed,
         y = this.rect.getUpperLeft().getY();
      Point p = new Point(newX, y);
      if (newX + this.rect.getWidth() <= 800) {
         this.rect.setUpperLeft(p);
      }
   }
   // Sprite
   /**
   * The method is in charge of charge of notifying the paddle that time has passed,
   * and changing the paddle location if needed.
   *
   * @param dt the amount of seconds passed since the last call.
   */
   public void timePassed(double dt) {
      if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
         moveLeft(dt);
       }
      if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
         moveRight(dt);
       }
   }
   /**
   * The method is in charge of drawing the paddle on the given DrawSurface.
   *
   * @param surface a surface to draw on.
   */
   public void drawOn(DrawSurface surface) {
      // Get the Paddle location values
      int x = (int) this.rect.getUpperLeft().getX(),
         y = (int) this.rect.getUpperLeft().getY(),
         w = (int) this.rect.getWidth(),
         h = (int) this.rect.getHeight();
      // Draw the block
      surface.setColor(java.awt.Color.BLUE);
      surface.fillRectangle(x, y, w, h);
      surface.setColor(java.awt.Color.BLACK);
      surface.drawRectangle(x, y, w, h);
   }
   // Collidable
   /**
   * The method is in charge of finding "collision shape" of the object.
   *
   * @return the "collision shape" of the object.
   */
   public Rectangle getCollisionRectangle() {
      return this.rect;
   }
   /**
    * The method is in charge of changing the velocity after a hit.
    *
    * @param hitter the hitting Ball.
    * @param collisionPoint the point of the collision.
    * @param currentVelocity the current velocity.
    *
    * @return the new velocity.
    */
   public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
      double x = this.rect.getUpperLeft().getX(), w = this.rect.getWidth(),
         velDx = currentVelocity.getDx(), velDy = currentVelocity.getDy(),
         ballSpeed = Math.sqrt((velDx * velDx) + (velDy * velDy));
      for (double i = 1; i <= 2; i++) {
         if (collisionPoint.getX() - x <= (w * i / 5)) {
            return Velocity.fromAngleAndSpeed(360 - (3 - i) * 30, ballSpeed);
         }
         if (collisionPoint.getX() - x >= w * (1 - i / 5)) {
            return Velocity.fromAngleAndSpeed((3 - i) * 30, ballSpeed);
         }
      }
      return Velocity.fromAngleAndSpeed(0, ballSpeed);
   }
   /**
   * The method is in charge of adding the paddle to the game.
   *
   * @param game the game.
   */
   public void addToGame(GameLevel game) {
      game.addCollidable(this);
      game.addSprite(this);
   }
}