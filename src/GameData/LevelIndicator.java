import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class LevelIndicator implements Sprite {
    private String levelName;
    /**
     * Constructor for LevelIndicator.
     *
     * @param name for level name value.
     */
    public LevelIndicator(String name) {
       this.levelName = name;
    }
    /**
     * The method is in charge of drawing the object on the given DrawSurface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.BLACK);
        surface.drawText(250, 20, "Level Name: " + this.levelName, 20);
    }
    /**
    * The method is in charge of notifying the sprite that time has passed.
    *
    * @param dt the amount of seconds passed since the last call.
    */
    public void timePassed(double dt) {
    }
}
