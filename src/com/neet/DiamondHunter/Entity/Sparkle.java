package com.neet.DiamondHunter.Entity;

import com.neet.DiamondHunter.TileMap.TileMap;
import java.awt.Graphics2D;

public class Sparkle
  extends Entity
{
  private boolean remove;
  
  public Sparkle(TileMap tm)
  {
    super(tm);
    this.animation.setFrames(com.neet.DiamondHunter.Manager.Content.SPARKLE[0]);
    this.animation.setDelay(5);
    this.width = (this.height = 16);
  }
  
  public boolean shouldRemove()
  {
    return this.remove;
  }
  
  public void update()
  {
    this.animation.update();
    if (this.animation.hasPlayedOnce()) {
      this.remove = true;
    }
  }
  
  public void draw(Graphics2D g)
  {
    super.draw(g);
  }
}
