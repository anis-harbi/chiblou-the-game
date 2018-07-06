package com.neet.DiamondHunter.Main;

import com.neet.DiamondHunter.Manager.GameStateManager;
import com.neet.DiamondHunter.Manager.Keys;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel
  extends JPanel
  implements Runnable, KeyListener
{
  public static final int WIDTH = 128;
  public static final int HEIGHT = 128;
  public static final int HEIGHT2 = 144;
  public static final int SCALE = 3;
  private Thread thread;
  private boolean running;
  private final int FPS = 30;
  private final int TARGET_TIME = 33;
  private BufferedImage image;
  private Graphics2D g;
  private GameStateManager gsm;
  
  public GamePanel()
  {
    setPreferredSize(new Dimension(384, 432));
    setFocusable(true);
    requestFocus();
  }
  
  public void addNotify()
  {
    super.addNotify();
    if (this.thread == null)
    {
      addKeyListener(this);
      this.thread = new Thread(this);
      this.thread.start();
    }
  }
  
  public void run()
  {
    init();
    while (this.running)
    {
      long start = System.nanoTime();
      
      update();
      draw();
      drawToScreen();
      
      long elapsed = System.nanoTime() - start;
      
      long wait = 33L - elapsed / 1000000L;
      if (wait < 0L) {
        wait = 33L;
      }
      try
      {
        Thread.sleep(wait);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private void init()
  {
    this.running = true;
    this.image = new BufferedImage(128, 144, 1);
    this.g = ((Graphics2D)this.image.getGraphics());
    this.gsm = new GameStateManager();
  }
  
  private void update()
  {
    this.gsm.update();
    Keys.update();
  }
  
  private void draw()
  {
    this.gsm.draw(this.g);
  }
  
  private void drawToScreen()
  {
    Graphics g2 = getGraphics();
    g2.drawImage(this.image, 0, 0, 384, 432, null);
    g2.dispose();
  }
  
  public void keyTyped(KeyEvent key) {}
  
  public void keyPressed(KeyEvent key)
  {
    Keys.keySet(key.getKeyCode(), true);
  }
  
  public void keyReleased(KeyEvent key)
  {
    Keys.keySet(key.getKeyCode(), false);
  }
}
