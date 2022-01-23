import biuoop.DrawSurface;

/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class LivesIndicator implements Sprite {
    private Counter currentLives;
    /**
     * Constructor for LivesIndicator.
     *
     * @param lives for lives count.
     */
    public LivesIndicator(Counter lives) {
       this.currentLives = lives;
    }
    /**
     * The method is in charge of drawing the object on the given DrawSurface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawText(5, 20, "Lives: " + Integer.toString(this.currentLives.getValue()), 20);
    }
    /**
    * The method is in charge of notifying the sprite that time has passed.
    *
    * @param dt the amount of seconds passed since the last call.
    */
    public void timePassed(double dt) {
    }
}