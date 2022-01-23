import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class GameLevel implements Animation {
   private LevelInformation level;
   private AnimationRunner runner;
   private boolean running;
   private biuoop.KeyboardSensor keyboard;
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private Counter remainingBlocks;
   private Counter remainingBalls;
   private Counter score;
   private Counter lives;
   /**
    * Constructor for GameLevel, using a given arguments.
    *
    * @param levelInfo a LevelInformation.
    * @param keyboardSensor a KeyboardSensor.
    * @param animationRunner an AnimationRunner.
    * @param liveNum a Counter for lives.
    * @param scoreNum a Counter for score.
    */
   public GameLevel(LevelInformation levelInfo,
           biuoop.KeyboardSensor keyboardSensor,
           AnimationRunner animationRunner,
           Counter liveNum, Counter scoreNum) {
      this.level = levelInfo;
      this.keyboard = keyboardSensor;
      this.runner = animationRunner;
      this.lives = liveNum;
      this.score = scoreNum;
      this.environment = new GameEnvironment();
      this.sprites = new SpriteCollection();
      this.remainingBlocks = new Counter(0);
      this.remainingBalls = new Counter(0);
      this.running = true;
   }
   /**
   * The method is in charge of adding a new Collidable object to the list.
   *
   * @param c the new object.
   */
   public void addCollidable(Collidable c) {
      this.environment.addCollidable(c);
   }
   /**
   * The method is in charge of removing a given Collidable object from the list.
   *
   * @param c the given object.
   */
   public void removeCollidable(Collidable c) {
       this.environment.removeCollidable(c);
   }
   /**
   * The method is in charge of adding a new Sprite object to the list.
   *
   * @param s the new object.
   */
   public void addSprite(Sprite s) {
      this.sprites.addSprite(s);
   }
   /**
   * The method is in charge of removing a given Sprite object from the list.
   *
   * @param s the given object.
   */
   public void removeSprite(Sprite s) {
       this.sprites.removeSprite(s);
   }
   /**
   * The method is in charge of adding all the blocks.
   *
   * @param scoreTrack a ScoreTrackingListener
   */
   private void addBlocks(ScoreTrackingListener scoreTrack) {
      BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
      // a loop that creates all the blocks
      for (Block b: this.level.blocks()) {
         this.remainingBlocks.increase(1);
         b.addHitListener(scoreTrack);
         b.addHitListener(blockRemover);
         b.addToGame(this);
      }
   }
   /**
   * The method is in charge of adding all the balls.
   */
   private void addBalls() {
      BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
      // a loop that creates all the balls
      for (Velocity v: this.level.initialBallVelocities()) {
         Ball ball = new Ball(new Point(400, 550), 6, java.awt.Color.WHITE);
         ball.addHitListener(ballRemover);
         this.remainingBalls.increase(1);
         ball.setEnvironment(this.environment);
         ball.addToGame(this);
         ball.setVelocity(v);
      }
   }
   /**
   * The method is in charge of setting up the game, by creating objects
   * (like the Blocks, the Ball and the Paddle) and add them to the game.
   */
   public void initialize() {
      SurfaceBorders surfaceBorders = new SurfaceBorders(800, 600);
      this.sprites.addSprite(this.level.getBackground());
      this.environment.addCollidable(surfaceBorders);
      this.sprites.addSprite(surfaceBorders);
      ScoreTrackingListener scoreTrack = new ScoreTrackingListener(this.score);
      ScoreIndicator scoreIndicat = new ScoreIndicator(this.score);
      LivesIndicator livesIndicat = new LivesIndicator(this.lives);
      LevelIndicator levelIndicat = new LevelIndicator(this.level.levelName());
      this.sprites.addSprite(scoreIndicat);
      this.sprites.addSprite(livesIndicat);
      this.sprites.addSprite(levelIndicat);
      addBlocks(scoreTrack);
   }
   /**
   * The method is in charge of checking if the animation should stop.
   *
   * @return true if yes, and false if not.
   */
   public boolean shouldStop() {
      return !this.running;
   }
   /**
   * The method is in charge of checking if the level is finished.
   *
   * @return true if yes, and false if not.
   */
   public boolean levelDone() {
      int minimumBlocks = this.level.blocks().size() - this.level.numberOfBlocksToRemove();
      if (this.remainingBlocks.getValue() <= minimumBlocks) {
         return true;
      }
      return false;
   }
   /**
   * The method is in charge of doing one frame of the animation.
   *
   * @param d a surface to draw on.
   * @param dt the amount of seconds passed since the last call.
   */
   public void doOneFrame(DrawSurface d, double dt) {
      this.sprites.notifyAllTimePassed(dt);
      this.sprites.drawAllOn(d);
      if (levelDone()) {
         this.running = false;
         this.score.increase(100);
      }
      if (this.remainingBalls.getValue() == 0) {
         this.lives.decrease(1);
         this.running = false;
      }
      if (this.keyboard.isPressed("p")) {
         KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(
                 this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen());
         this.runner.run(kpsa);
      }
   }
   /**
   * The method is in charge of running one turn, by starting the animation loop.
   */
   public void playOneTurn() {
      this.addBalls();
      this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts
      this.running = true;
      // use our runner to run the current animation -- which is one turn of
      // the game.
      this.runner.run(this);
   }
}