import java.awt.Image;
import biuoop.DrawSurface;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class DrawImage implements DrawTask {
    private Image image;
    /**
     * Constructor for DrawImage, using a given image.
     *
     * @param i an image value.
     */
    public DrawImage(Image i) {
        this.image = i;
    }
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
        surface.drawImage(x, y, image);
        surface.setColor(java.awt.Color.BLACK);
        surface.drawRectangle(x, y, w, h);
    }
    /**
     * The method is in charge of drawing the image as background.
     *
     * @param d a surface to draw on.
     */
     public void drawOn(DrawSurface d) {
         d.drawImage(0, 0, this.image);
     }
     /**
     * The method is in charge of notifying the sprite that time has passed.
     *
     * @param dt the amount of seconds passed since the last call.
     */
     public void timePassed(double dt) { }
}
