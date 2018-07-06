package com.neet.DiamondHunter.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileMap
{
  private int x;
  private int y;
  private int xdest;
  private int ydest;
  private int speed;
  private boolean moving;
  private int xmin;
  private int ymin;
  private int xmax;
  private int ymax;
  private int[][] map;
  private int tileSize;
  private int numRows;
  private int numCols;
  private int width;
  private int height;
  private BufferedImage tileset;
  private int numTilesAcross;
  private Tile[][] tiles;
  private int rowOffset;
  private int colOffset;
  private int numRowsToDraw;
  private int numColsToDraw;
  
  public TileMap(int tileSize)
  {
    this.tileSize = tileSize;
    this.numRowsToDraw = (128 / tileSize + 2);
    this.numColsToDraw = (128 / tileSize + 2);
    this.speed = 4;
  }
  
  public void loadTiles(String s)
  {
    try
    {
      this.tileset = ImageIO.read(
        getClass().getResourceAsStream(s));
      
      this.numTilesAcross = (this.tileset.getWidth() / this.tileSize);
      this.tiles = new Tile[2][this.numTilesAcross];
      for (int col = 0; col < this.numTilesAcross; col++)
      {
        BufferedImage subimage = this.tileset.getSubimage(
          col * this.tileSize, 
          0, 
          this.tileSize, 
          this.tileSize);
        
        this.tiles[0][col] = new Tile(subimage, 0);
        subimage = this.tileset.getSubimage(
          col * this.tileSize, 
          this.tileSize, 
          this.tileSize, 
          this.tileSize);
        
        this.tiles[1][col] = new Tile(subimage, 1);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void loadMap(String s)
  {
    try
    {
      InputStream in = getClass().getResourceAsStream(s);
      BufferedReader br = new BufferedReader(
        new InputStreamReader(in));
      
      this.numCols = Integer.parseInt(br.readLine());
      this.numRows = Integer.parseInt(br.readLine());
      this.map = new int[this.numRows][this.numCols];
      this.width = (this.numCols * this.tileSize);
      this.height = (this.numRows * this.tileSize);
      
      this.xmin = (128 - this.width);
      this.xmin = (-this.width);
      this.xmax = 0;
      this.ymin = (128 - this.height);
      this.ymin = (-this.height);
      this.ymax = 0;
      
      String delims = "\\s+";
      for (int row = 0; row < this.numRows; row++)
      {
        String line = br.readLine();
        String[] tokens = line.split(delims);
        for (int col = 0; col < this.numCols; col++) {
          this.map[row][col] = Integer.parseInt(tokens[col]);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public int getTileSize()
  {
    return this.tileSize;
  }
  
  public int getx()
  {
    return this.x;
  }
  
  public int gety()
  {
    return this.y;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getNumRows()
  {
    return this.numRows;
  }
  
  public int getNumCols()
  {
    return this.numCols;
  }
  
  public int getType(int row, int col)
  {
    int rc = this.map[row][col];
    int r = rc / this.numTilesAcross;
    int c = rc % this.numTilesAcross;
    return this.tiles[r][c].getType();
  }
  
  public int getIndex(int row, int col)
  {
    return this.map[row][col];
  }
  
  public boolean isMoving()
  {
    return this.moving;
  }
  
  public void setTile(int row, int col, int index)
  {
    this.map[row][col] = index;
  }
  
  public void replace(int i1, int i2)
  {
    for (int row = 0; row < this.numRows; row++) {
      for (int col = 0; col < this.numCols; col++) {
        if (this.map[row][col] == i1) {
          this.map[row][col] = i2;
        }
      }
    }
  }
  
  public void setPosition(int x, int y)
  {
    this.xdest = x;
    this.ydest = y;
  }
  
  public void setPositionImmediately(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  public void fixBounds()
  {
    if (this.x < this.xmin) {
      this.x = this.xmin;
    }
    if (this.y < this.ymin) {
      this.y = this.ymin;
    }
    if (this.x > this.xmax) {
      this.x = this.xmax;
    }
    if (this.y > this.ymax) {
      this.y = this.ymax;
    }
  }
  
  public void update()
  {
    if (this.x < this.xdest)
    {
      this.x += this.speed;
      if (this.x > this.xdest) {
        this.x = this.xdest;
      }
    }
    if (this.x > this.xdest)
    {
      this.x -= this.speed;
      if (this.x < this.xdest) {
        this.x = this.xdest;
      }
    }
    if (this.y < this.ydest)
    {
      this.y += this.speed;
      if (this.y > this.ydest) {
        this.y = this.ydest;
      }
    }
    if (this.y > this.ydest)
    {
      this.y -= this.speed;
      if (this.y < this.ydest) {
        this.y = this.ydest;
      }
    }
    fixBounds();
    
    this.colOffset = (-this.x / this.tileSize);
    this.rowOffset = (-this.y / this.tileSize);
    if ((this.x != this.xdest) || (this.y != this.ydest)) {
      this.moving = true;
    } else {
      this.moving = false;
    }
  }
  
  public void draw(Graphics2D g)
  {
    for (int row = this.rowOffset; row < this.rowOffset + this.numRowsToDraw; row++)
    {
      if (row >= this.numRows) {
        break;
      }
      for (int col = this.colOffset; col < this.colOffset + this.numColsToDraw; col++)
      {
        if (col >= this.numCols) {
          break;
        }
        if (this.map[row][col] != 0)
        {
          int rc = this.map[row][col];
          int r = rc / this.numTilesAcross;
          int c = rc % this.numTilesAcross;
          
          g.drawImage(
            this.tiles[r][c].getImage(), 
            this.x + col * this.tileSize, 
            this.y + row * this.tileSize, 
            null);
        }
      }
    }
  }
}
