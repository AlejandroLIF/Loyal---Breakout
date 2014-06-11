package Game;


import java.awt.Graphics;
import java.awt.Point;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
public interface PaintableObject {
   
   public Point getPosition();
   
   public int getX();
   
   public int getY();
   
   public void setPosition(Point p);
   
   public void setX(int x);
   
   public void setY(int y);
   
   public void paint(Graphics g);
   
   public void paint(Graphics g, int x, int y);
}
