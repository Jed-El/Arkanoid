import java.awt.Color;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class BlockType {
    private int width = 0;
    private int height = 0;
    private int count = 0;
    private FillBlock appearance = new FillBlock();
    private Color borders;
     /**
      * The method is in charge of setting the BlockType width.
      *
      * @param w a width value.
      */
     public void setWidth(int w) {
         this.width = w;
     }
     /**
      * The method is in charge of setting the BlockType height.
      *
      * @param h a height value.
      */
     public void setHeight(int h) {
         this.height = h;
     }
     /**
      * The method is in charge of setting the BlockType hit-count.
      *
      * @param c a count value.
      */
     public void setCount(int c) {
         this.count = c;
     }
     /**
      * The method is in charge of setting the BlockType borders color.
      *
      * @param b a color value.
      */
     public void setBorders(Color b) {
         this.borders = b;
     }
     /**
      * The method is in charge of setting the BlockType FillBlock.
      *
      * @param fb a FillBlock.
      */
     public void setFill(FillBlock fb) {
         this.appearance = fb;
     }
     /**
      * The method is in charge of finding the BlockType Fill.
      *
      * @return the FillBlock symbol.
      */
     public FillBlock getFill() {
         return this.appearance;
     }
     /**
      * The method is in charge of finding the width of the block.
      *
      * @return block width.
      */
     public int getWidth() {
         return this.width;
     }
    /**
     * The method is in charge of creating a new Block using a given location values.
     *
     * @param x a x location value.
     * @param y a y location value.
     *
     * @return a new Block.
     */
     public Block create(double x, double y) {
        Block b = new Block(new Point(x, y), this.width, this.height, this.appearance);
        if (this.borders != null) {
            b.setBorders(this.borders);
        }
        b.setCounter(this.count);
        return b;
     }
     /**
      * The method is in charge of checking if the BlockType as all the
      * values needed to be used.
      *
      * @return true if yes, and false otherwise.
      */
     public boolean check() {
         if ((this.count <= 0) || (this.height <= 0) || (this.width <= 0)
                 || (!this.appearance.check(this.count))) {
             return false;
         }
         return true;
     }
     /**
      * The method is in charge of creating a clone of the BlockType.
      *
      * @return the new BlockType.
      */
     public BlockType createClone() {
         BlockType bt = new BlockType();
         bt.setHeight(this.height);
         bt.setWidth(this.width);
         bt.setCount(this.count);
         bt.setBorders(this.borders);
         bt.setFill(this.appearance.createClone());
         return bt;
     }
}
