package com.neet.DiamondHunter.Main;

import javax.swing.JFrame;

public class Game
{
  public static void main(String[] args)
  {
    JFrame window = new JFrame("The Legend of Chiblou");
    
    window.add(new GamePanel());
    
    window.setResizable(false);
    window.pack();
    
    window.setLocationRelativeTo(null);
    window.setVisible(true);
    window.setDefaultCloseOperation(3);
  }
}
