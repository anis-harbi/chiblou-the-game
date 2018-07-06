package com.neet.DiamondHunter.Entity;

import com.neet.DiamondHunter.TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Diamond
  extends Entity
{
  BufferedImage[] sprites;
  private ArrayList<int[]> tileChanges;
  
  public Diamond(TileMap tm)
  {
    super(tm);
    
    this.width = 16;
    this.height = 16;
    this.cwidth = 12;
    this.cheight = 12;
    
    this.sprites = com.neet.DiamondHunter.Manager.Content.DIAMOND[0];
    this.animation.setFrames(this.sprites);
    this.animation.setDelay(10);
    
    this.tileChanges = new ArrayList();
  }
  
  public void addChange(int[] i)
  {
    this.tileChanges.add(i);
  }
  
  public ArrayList<int[]> getChanges()
  {
    return this.tileChanges;
  }
  
  public void update()
  {
    this.animation.update();
  }
  
  public void draw(Graphics2D g)
  {
    super.draw(g);
  }
}
