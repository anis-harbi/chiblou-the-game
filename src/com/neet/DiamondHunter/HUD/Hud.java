package com.neet.DiamondHunter.HUD;

import com.neet.DiamondHunter.Entity.Diamond;
import com.neet.DiamondHunter.Entity.Player;
import com.neet.DiamondHunter.Manager.Content;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hud
{
  private int yoffset;
  private BufferedImage bar;
  private BufferedImage diamond;
  private BufferedImage boat;
  private BufferedImage axe;
  private Player player;
  private int numDiamonds;
  private Font font;
  private Color textColor;
  
  public Hud(Player p, ArrayList<Diamond> d)
  {
    this.player = p;
    this.numDiamonds = d.size();
    this.yoffset = 128;
    
    this.bar = Content.BAR[0][0];
    this.diamond = Content.DIAMOND[0][0];
    this.boat = Content.ITEMS[0][0];
    this.axe = Content.ITEMS[0][1];
    
    this.font = new Font("Arial", 0, 10);
    this.textColor = new Color(47, 64, 126);
  }
  
  public void draw(Graphics2D g)
  {
    g.drawImage(this.bar, 0, this.yoffset, null);
    
    g.setColor(this.textColor);
    g.fillRect(8, this.yoffset + 6, (int)(28.0D * this.player.numDiamonds() / this.numDiamonds), 4);
    
    g.setColor(this.textColor);
    g.setFont(this.font);
    String s = this.player.numDiamonds() + "/" + this.numDiamonds;
    Content.drawString(g, s, 40, this.yoffset + 3);
    if (this.player.numDiamonds() >= 10) {
      g.drawImage(this.diamond, 80, this.yoffset, null);
    } else {
      g.drawImage(this.diamond, 72, this.yoffset, null);
    }
    if (this.player.hasBoat()) {
      g.drawImage(this.boat, 100, this.yoffset, null);
    }
    if (this.player.hasAxe()) {
      g.drawImage(this.axe, 112, this.yoffset, null);
    }
    int minutes = (int)(this.player.getTicks() / 1800L);
    int seconds = (int)(this.player.getTicks() / 30L % 60L);
    if (minutes < 10)
    {
      if (seconds < 10) {
        Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
      } else {
        Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
      }
    }
    else if (seconds < 10) {
      Content.drawString(g, minutes + ":0" + seconds, 85, 3);
    } else {
      Content.drawString(g, minutes + ":" + seconds, 85, 3);
    }
  }
}
