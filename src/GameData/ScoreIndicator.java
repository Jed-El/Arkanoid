import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class ScoreIndicator implements Sprite {
    private Counter currentScore;
    /**
     * Constructor for ScoreIndicator.
     *
     * @param scoreCounter for score count.
     */
    public ScoreIndicator(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }
    /**
     * The method is in charge of drawing the object on the given DrawSurface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawText(100, 20, "Score: " + Integer.toString(this.currentScore.getValue()), 20);
    }
    /**
    * The method is in charge of notifying the sprite that time has passed.
    *
    * @param dt the amount of seconds passed since the last call.
    */
    public void timePassed(double dt) {
    }
}
