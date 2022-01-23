import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class DrawRect implements DrawTask {
    private java.awt.Color color;
    /**
     * Constructor for DrawRect, using a given color value.
     *
     * @param c a color value.
     */
    public DrawRect(java.awt.Color c) {
        this.color = c;
    }
    /**
    * The method is in charge of drawing the block.
    *
    * @param rect the block Rectangle to draw.
    * @param surface a surface to draw on.
    */
    /**
    * The method is in charge of drawing the block.
    *
    * @param rect the block Rectangle to draw.
    * @param surface a surface to draw on.
    */
    public void draw(Rectangle rect, DrawSurface surface) {
        int x = (int) rect.getUpperLeft().getX(),
                y = (int) rect.getUpperLeft().getY(),
                w = (int) rect.getWidth(),
                h = (int) rect.getHeight();
        surface.setColor(this.color);
        surface.fillRectangle(x, y, w, h);
    }
    /**
     * The method is in charge of drawing a Rectangle as background.
     *
     * @param d a surface to draw on.
     */
     public void drawOn(DrawSurface d) {
         int height = d.getHeight(), width = d.getWidth();
         d.fillRectangle(0, 0, width, height);
     }
     /**
     * The method is in charge of notifying the sprite that time has passed.
     *
     * @param dt the amount of seconds passed since the last call.
     */
     public void timePassed(double dt) { }
}