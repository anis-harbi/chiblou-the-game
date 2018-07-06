package com.neet.DiamondHunter.Entity;

import com.neet.DiamondHunter.TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Item
  extends Entity
{
  private BufferedImage sprite;
  private int type;
  public static final int BOAT = 0;
  public static final int AXE = 1;
  
  public Item(TileMap tm)
  {
    super(tm);
    this.type = -1;
    this.width = (this.height = 16);
    this.cwidth = (this.cheight = 12);
  }
  
  public void setType(int i)
  {
    this.type = i;
    if (this.type == 0) {
      this.sprite = com.neet.DiamondHunter.Manager.Content.ITEMS[1][0];
    } else if (this.type == 1) {
      this.sprite = com.neet.DiamondHunter.Manager.Content.ITEMS[1][1];
    }
  }
  
  public void collected(Player p)
  {
    if (this.type == 0) {
      p.gotBoat();
    }
    if (this.type == 1) {
      p.gotAxe();
    }
  }
  
  public void draw(Graphics2D g)
  {
    setMapPosition();
    g.drawImage(this.sprite, this.x + this.xmap - this.width / 2, this.y + this.ymap - this.height / 2, null);
  }
}
