import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class BlocksDefinitionReader {
    /**
     * The method is in charge of creating a BlocksFromSymbolsFactory,
     * using a given Reader.
     *
     * @param reader a Reader for block types data.
     *
     * @return a BlocksFromSymbolsFactory.
     *
     * @throws Exception when the text missing data or unreadable.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws Exception {
        List<String> text = ReadText.readText(reader);
        BlocksFromSymbolsFactory bfsf = new BlocksFromSymbolsFactory();
        for (int l = 0; l < text.size(); l++) {
            String line = text.get(l);
            if (line.startsWith("default")) { // set default
                bfsf.setDefaultBlock(line);
            }
            if (line.startsWith("bdef")) { // add new BlockType
                bfsf.addBlockType(line);
            }
            if (line.startsWith("sdef")) { // add new SpacerType
                bfsf.addSpacer(line);
            }
        }
        return bfsf;
     }
}
