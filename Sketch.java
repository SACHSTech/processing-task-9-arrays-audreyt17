import processing.core.PApplet;
import processing.core.PImage;
import java.util.Set;
import java.util.HashSet;


public class Sketch extends PApplet {
	
  Snow[] circles;
  Player player;
  public static final int SIZE = 400;
  int boost = 0;
  Set<Snow> hitSnowBalls = new HashSet<>();
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	  // put your size call here
    size(SIZE, SIZE);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    // set up five snowballs
    circles = new Snow[5];
    for (int i = 0; i < 5; ++i) {
      circles[i] = new Snow();
    }

    // set up one player
    player = new Player();
  }

  /**
  * Returns true if x1, y1 and x2, y2 are close to each other (i.e. a hit)
  */
  public boolean isHit(int x1, int y1, int x2, int y2) {
    return ((Math.abs(x1 - x2) < 13) &&
            (Math.abs(y1 - y2) < 13));  
  }

  /**
  * This function draws the screen.
  */
  public void draw() {
    background(50);

    fill(255, 255, 255);
    for (int i = 0; i < 5; ++i) {
      if (!circles[i].ballHideStatus) {
        // reduce lives when hit by snow
        if (isHit(circles[i].x, circles[i].y, player.x, player.y)) {
          hitSnowBalls.add(circles[i]);
        }

        // render snow ball
        ellipse(circles[i].x, circles[i].y, 25, 25);
        circles[i].y += circles[i].speed;
        circles[i].y += boost;

        // reset snowball once it moves off the screen
        if (circles[i].y > height || circles[i].y < 0) {
          circles[i] = new Snow();
        }
      }
    }

    // draws the player
    fill(0, 0, 255);
    ellipse(player.x, player.y, 20, 20);

    // Draw the score squares
    fill(255, 0, 0);
    if (hitSnowBalls.size() < 1) {
      rect(width - 90, 20, 20, 20);
    }
    if (hitSnowBalls.size() < 2) {
      rect(width - 60, 20, 20, 20);      
    }
    if (hitSnowBalls.size() < 3) {
      rect(width - 30, 20, 20, 20);      
    }

    // Complete the game when hit 3 times
    if (hitSnowBalls.size() >= 3) {
      background(255);
    }
  }
  // define other methods down here.

  /**
  * This function defines all the recognized keystrokes
  **/
  public void keyPressed() {

    // up and down controls the speed of snowballs
    if (key == CODED) {
      if (keyCode == UP) {
        ++boost;
      } else if (keyCode == DOWN) {
        --boost;
      } 
    } else if (key == 'a') {  // asdw controls player movement
      if (player.x > 0) {
        --player.x;
      }  
    } else if (key == 'd') {
      if (player.x < width) {
        ++player.x;
      }
    } else if (key == 's') {
      if (player.y < height) {
        ++player.y;
      }
    } else if (key == 'w') {
      if (player.y > 0) {
        --player.y;
      }
    }
  }

  /**
  * This function implements mouse click callback.
  */
  public void mouseClicked() {
    // hides the snowball being hit
    for (int i = 0; i < 5; ++i) {
      if (isHit(circles[i].x, circles[i].y, mouseX, mouseY)) {
        circles[i].ballHideStatus = true;
      }
    }
  }
}