import java.util.List;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public interface LevelInformation {
   /**
   * The method is in charge of finding the number of balls.
   *
   * @return a number of balls.
   */
   int numberOfBalls();
   /**
   * The method is in charge of finding the Velocities of all the balls.
   *
   * @return a List of Velocities.
   */
   List<Velocity> initialBallVelocities();
   /**
   * The method is in charge of finding the paddle speed.
   *
   * @return a number for paddle speed value.
   */
   int paddleSpeed();
   /**
   * The method is in charge of finding the paddle width.
   *
   * @return a number for paddle width value.
   */
   int paddleWidth();
   /**
   * The method is in charge of finding the level name,
   * so it could be displayed at the top of the screen.
   *
   * @return a String for level name value.
   */
   String levelName();
   /**
   * The method is in charge of finding the level background.
   *
   * @return a sprite with the background of the level.
   */
   Sprite getBackground();
   /**
   * The method is in charge of finding the Blocks that make up this level,
   * each block contains its size, color and location.
   *
   * @return a List of Blocks.
   */
   List<Block> blocks();
   /**
   * The method is in charge of finding the Number of Blocks that should
   * be removed before the level is considered to be "cleared".
   *
   * @return a number of Blocks to remove.
   */
   int numberOfBlocksToRemove();
}