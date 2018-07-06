package com.neet.DiamondHunter.Entity;

import java.awt.image.BufferedImage;

public class Animation
{
  private BufferedImage[] frames;
  private int currentFrame;
  private int numFrames;
  private int count;
  private int delay;
  private int timesPlayed;
  
  public Animation()
  {
    this.timesPlayed = 0;
  }
  
  public void setFrames(BufferedImage[] frames)
  {
    this.frames = frames;
    this.currentFrame = 0;
    this.count = 0;
    this.timesPlayed = 0;
    this.delay = 2;
    this.numFrames = frames.length;
  }
  
  public void setDelay(int i)
  {
    this.delay = i;
  }
  
  public void setFrame(int i)
  {
    this.currentFrame = i;
  }
  
  public void setNumFrames(int i)
  {
    this.numFrames = i;
  }
  
  public void update()
  {
    if (this.delay == -1) {
      return;
    }
    this.count += 1;
    if (this.count == this.delay)
    {
      this.currentFrame += 1;
      this.count = 0;
    }
    if (this.currentFrame == this.numFrames)
    {
      this.currentFrame = 0;
      this.timesPlayed += 1;
    }
  }
  
  public int getFrame()
  {
    return this.currentFrame;
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public BufferedImage getImage()
  {
    return this.frames[this.currentFrame];
  }
  
  public boolean hasPlayedOnce()
  {
    return this.timesPlayed > 0;
  }
  
  public boolean hasPlayed(int i)
  {
    return this.timesPlayed == i;
  }
}
