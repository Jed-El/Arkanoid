import java.util.HashMap;
import java.util.Map;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths = new HashMap<String, Integer>();
    private Map<String, BlockType> blockCreators = new HashMap<String, BlockType>();
    private BlockType defaultBlock = new BlockType();
    /**
     * The method is in charge of setting a BlockType values, Using a given
     * BlockType (default) and String of data.
     *
     * @param text a given String of data.
     * @param bt a BlockType (default).
     *
     * @return String with the new BlockType symbol.
     *
     * @throws Exception if could not read the text.
     */
     private String setBlockType(String text, BlockType bt) throws Exception {
         String[] values = text.split(" ");
         String symbol = "";
         for (int i = 1; i < values.length; i++) {
             String[] val = values[i].split(":");
             if (val[0].equals("symbol")) {
                 symbol = val[1];
              }
             if (val[0].equals("height")) {
                bt.setHeight(Integer.parseInt(val[1]));
             }
             if (val[0].equals("width")) {
                 bt.setWidth(Integer.parseInt(val[1]));
              }
             if (val[0].equals("hit_points")) {
                 bt.setCount(Integer.parseInt(val[1]));
              }
             if (val[0].equals("stroke")) {
                 bt.setBorders(ReadText.findColor(val[1]));
              }
             if (val[0].startsWith("fill")) {
                 DrawTask fill = ReadText.getSprite(val[1]);
                 String[] k = val[0].split("[-]");
                 if (k.length > 1) {
                     int num = Integer.parseInt(k[1]);
                     bt.getFill().addToFill(num, fill);
                 } else {
                    bt.getFill().defaultFill(fill);
                 }
             }
         }
         return symbol;
     }
     /**
      * The method is in charge of setting a default BlockType values,
      * using a given String of data.
      *
      * @param text a given String of data.
      *
      * @throws Exception if could not read the text.
      */
    public void setDefaultBlock(String text) throws Exception {
        setBlockType(text, this.defaultBlock);
    }
    /**
     * The method is in charge of adding a new BlockType,
     * using a given String of data.
     *
     * @param text a given String of data.
     *
     * @throws Exception if could not read the text, or if values data is missing.
     */
    public void addBlockType(String text) throws Exception {
        BlockType b = this.defaultBlock.createClone();
        String symbol = setBlockType(text, b);
        if (b.check()) {
            this.blockCreators.put(symbol, b);
        } else {
            throw new Exception("Error in a Block type");
        }
    }
    /**
     * The method is in charge of adding a new Spacer,
     * using a given String of data.
     *
     * @param text a given String of data.
     */
    public void addSpacer(String text) {
        int w = 0;
        String symbol = "";
        String[] values = text.split(" ");
        for (int i = 1; i < values.length; i++) {
            String[] val = values[i].split(":");
            if (val[0].equals("symbol")) {
                symbol = val[1];
             }
            if (val[0].equals("width")) {
                w = Integer.parseInt(val[1]);
             }
        }
        this.spacerWidths.put(symbol, w);
    }
    /**
     * The method is in charge of checking if a given String is a space symbol.
     *
     * @param s a given String for space symbol.
     *
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }
    /**
     * The method is in charge of checking if a given String is a block symbol.
     *
     * @param s a given String for block symbol.
     *
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
    /**
     * The method is in charge of creating a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     *
     * @param s a given String for block-symbol.
     * @param xpos a x location value.
     * @param ypos a y location value.
     *
     * @return a new Block.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }
    /**
     * The method is in charge of finding the width in pixels
     * of the Block associated with the given block-symbol.
     *
     * @param s a given String for block-symbol.
     *
     * @return a width value.
     */
    public int getBlockWidth(String s) {
        return this.blockCreators.get(s).getWidth();
    }
    /**
     * The method is in charge of finding the width in pixels
     * associated with the given spacer-symbol.
     *
     * @param s a given String for spacer-symbol.
     *
     * @return a width value.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
