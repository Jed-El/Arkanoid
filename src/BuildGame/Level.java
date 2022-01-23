import java.util.List;

/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Level implements LevelInformation {
    private List<Velocity> ballsVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private int numOfBlocksToRemove;
    private Sprite background;
    /**
     * Constructor for Level, using a given values.
     *
     * @param bv for ballsVelocity.
     * @param ps for paddleSpeed.
     * @param pw for paddleWidth.
     * @param ln for levelName.
     * @param b for blocks.
     * @param nobtr for numOfBlocksToRemove.
     * @param bg for background.
     *
     * @throws Exception when a value is missing.
     */
    public Level(List<Velocity> bv, int ps, int pw, String ln,
            List<Block> b, int nobtr, Sprite bg)  throws Exception {
        if ((bv != null) && (bv.size() != 0) && (ps != 0) && (pw != 0)
                && (ln != null) && (b != null) && (nobtr != 0) && (bg != null)) {
            this.ballsVelocity = bv;
            this.paddleSpeed = ps;
            this.paddleWidth = pw;
            this.levelName = ln;
            this.blocks = b;
            this.numOfBlocksToRemove = nobtr;
            this.background = bg;
        } else {
            throw new Exception("Error while creating new level");
        }
    }
    /**
     * The method is in charge of finding the number of balls.
     *
     * @return a number of balls.
     */
    public int numberOfBalls() {
        return this.ballsVelocity.size();
    }
    /**
     * The method is in charge of finding the Velocities of all the balls.
     *
     * @return a List of Velocities.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocity;
    }
    /**
     * The method is in charge of finding the paddle speed.
     *
     * @return a number for paddle speed value.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * The method is in charge of finding the paddle width.
     *
     * @return a number for paddle width value.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * The method is in charge of finding the level name,
     * so it could be displayed at the top of the screen.
     *
     * @return a String for level name value.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * The method is in charge of finding the level background.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * The method is in charge of finding the Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a List of Blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * The method is in charge of finding the Number of Blocks that should
     * be removed before the level is considered to be "cleared".
     *
     * @return a number of Blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.numOfBlocksToRemove;
    }
}