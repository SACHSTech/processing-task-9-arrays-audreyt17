import java.util.Random;
public class Snow {
  int x;
  int y;
  int speed;
  boolean ballHideStatus = false;
  public static final int SPEED_LIMIT = 4;
  
  Random random = new Random();
  
  public Snow() {
    reset();
  }

  public void reset() {
    this.x = random.nextInt(Sketch.SIZE);
    this.y = random.nextInt(Sketch.SIZE);
    this.speed = random.nextInt(SPEED_LIMIT) + 1;    
  }
}