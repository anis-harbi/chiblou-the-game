package com.neet.DiamondHunter.Entity;

import com.neet.DiamondHunter.Manager.JukeBox;
import com.neet.DiamondHunter.TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player
  extends Entity
{
  private BufferedImage[] downSprites;
  private BufferedImage[] leftSprites;
  private BufferedImage[] rightSprites;
  private BufferedImage[] upSprites;
  private BufferedImage[] downBoatSprites;
  private BufferedImage[] leftBoatSprites;
  private BufferedImage[] rightBoatSprites;
  private BufferedImage[] upBoatSprites;
  private final int DOWN = 0;
  private final int LEFT = 1;
  private final int RIGHT = 2;
  private final int UP = 3;
  private final int DOWNBOAT = 4;
  private final int LEFTBOAT = 5;
  private final int RIGHTBOAT = 6;
  private final int UPBOAT = 7;
  private int numDiamonds;
  private int totalDiamonds;
  private boolean hasBoat;
  private boolean hasAxe;
  private boolean onWater;
  private long ticks;
  private boolean accelerate;
  
  public Player(TileMap tm)
  {
    super(tm);
    
    this.width = 16;
    this.height = 16;
    this.cwidth = 12;
    this.cheight = 12;
    
    this.moveSpeed = 3;
    
    this.numDiamonds = 0;
    
    this.downSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[0];
    this.leftSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[1];
    this.rightSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[2];
    this.upSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[3];
    this.downBoatSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[4];
    this.leftBoatSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[5];
    this.rightBoatSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[6];
    this.upBoatSprites = com.neet.DiamondHunter.Manager.Content.PLAYER[7];
    
    this.animation.setFrames(this.downSprites);
    this.animation.setDelay(10);
  }
  
  private void setAnimation(int i, BufferedImage[] bi, int d)
  {
    this.currentAnimation = i;
    this.animation.setFrames(bi);
    this.animation.setDelay(d);
  }
  
  public void collectedDiamond()
  {
    this.numDiamonds += 1;
  }
  
  public int numDiamonds()
  {
    return this.numDiamonds;
  }
  
  public int getTotalDiamonds()
  {
    return this.totalDiamonds;
  }
  
  public void setTotalDiamonds(int i)
  {
    this.totalDiamonds = i;
  }
  
  public void gotBoat()
  {
    this.hasBoat = true;this.tileMap.replace(22, 4);
  }
  
  public void gotAxe()
  {
    this.hasAxe = true;
  }
  
  public boolean hasBoat()
  {
    return this.hasBoat;
  }
  
  public boolean hasAxe()
  {
    return this.hasAxe;
  }
  
  public long getTicks()
  {
    return this.ticks;
  }
  
  public void setDown()
  {
    super.setDown();
  }
  
  public void setLeft()
  {
    super.setLeft();
  }
  
  public void setRight()
  {
    super.setRight();
  }
  
  public void setUp()
  {
    super.setUp();
  }
  
  public void setAction()
  {
    if (this.hasAxe)
    {
      if (((this.currentAnimation == 3) && ((this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 21) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 25))) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 28) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 33))
      {
        this.tileMap.setTile(this.rowTile - 1, this.colTile, 1);
        JukeBox.play("tilechange");
      }
      if (((this.currentAnimation == 0) && ((this.tileMap.getIndex(this.rowTile + 1, this.colTile) == 21) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 25))) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 28) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 33))
      {
        this.tileMap.setTile(this.rowTile + 1, this.colTile, 1);
        JukeBox.play("tilechange");
      }
      if (((this.currentAnimation == 1) && ((this.tileMap.getIndex(this.rowTile, this.colTile - 1) == 21) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 25))) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 28) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 33))
      {
        this.tileMap.setTile(this.rowTile, this.colTile - 1, 1);
        JukeBox.play("tilechange");
      }
      if (((this.currentAnimation == 2) && ((this.tileMap.getIndex(this.rowTile, this.colTile + 1) == 21) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 25))) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 28) || (this.tileMap.getIndex(this.rowTile - 1, this.colTile) == 33))
      {
        this.tileMap.setTile(this.rowTile, this.colTile + 1, 1);
        JukeBox.play("tilechange");
      }
    }
  }
  
  public void update()
  {
    this.ticks += 1L;
    
    boolean current = this.onWater;
    if (this.tileMap.getIndex(this.ydest / this.tileSize, this.xdest / this.tileSize) == 4) {
      this.onWater = true;
    } else {
      this.onWater = false;
    }
    if ((!current) && (this.onWater)) {
      JukeBox.play("splash");
    }
    if (this.down) {
      if ((this.onWater) && (this.currentAnimation != 4)) {
        setAnimation(4, this.downBoatSprites, 10);
      } else if ((!this.onWater) && (this.currentAnimation != 0)) {
        setAnimation(0, this.downSprites, 10);
      }
    }
    if (this.left) {
      if ((this.onWater) && (this.currentAnimation != 5)) {
        setAnimation(5, this.leftBoatSprites, 10);
      } else if ((!this.onWater) && (this.currentAnimation != 1)) {
        setAnimation(1, this.leftSprites, 10);
      }
    }
    if (this.right) {
      if ((this.onWater) && (this.currentAnimation != 6)) {
        setAnimation(6, this.rightBoatSprites, 10);
      } else if ((!this.onWater) && (this.currentAnimation != 2)) {
        setAnimation(2, this.rightSprites, 10);
      }
    }
    if (this.up) {
      if ((this.onWater) && (this.currentAnimation != 7)) {
        setAnimation(7, this.upBoatSprites, 10);
      } else if ((!this.onWater) && (this.currentAnimation != 3)) {
        setAnimation(3, this.upSprites, 10);
      }
    }
    super.update();
  }
  
  public void draw(Graphics2D g)
  {
    super.draw(g);
  }
}
