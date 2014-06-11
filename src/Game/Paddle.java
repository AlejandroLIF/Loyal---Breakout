package Game;


import Bricks.Brick;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
public class Paddle implements PaintableObject, Collisionable, GameBoundaries{
    private double x, 
                   y;
    private Color color;
    private int width,
                height;
    
    Paddle(){
        x = GameBoundaries.RIGHT-width/2;
        y = GameBoundaries.BOTTOM-height-60;
        
        width = 200;
        height = 10;
        color = Color.GRAY;
    }
    
    @Override
    public void checkCollision(Collisionable c){
        throw new UnsupportedOperationException("Not supported yet!");
    }
    
    public void move(int x){
        if(x-getWidth()/2 > GameBoundaries.RIGHT-width){
            setX(GameBoundaries.RIGHT-width);
        }
        else if(x-getWidth()/2 < GameBoundaries.LEFT){
            setX(GameBoundaries.LEFT);
        }
        else
            setX(x-getWidth()/2);
    }
    
    @Override
    public Point getPosition() {
        return new Point(getX(), getY());
    }

    @Override
    public int getX() {
        return (int)x;
    }

    @Override
    public int getY() {
        return (int)y;
    }
    
    public int getCenterX(){
        return getX()+getWidth()/2;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    @Override
    public void setPosition(Point p) {
        x = p.getX();
        y = p.getY();
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(Color.MAGENTA);
        g.fillRect(getX(), getY(), getWidth()/6, getHeight());
        g.fillRect(getX()+getWidth(), getY(), -getWidth()/6, getHeight());
    }
    
    @Override
    public void paint(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, getWidth(), getHeight());
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, getWidth()/6, getHeight());
        g.fillRect(x+getWidth(), y, -getWidth()/6, getHeight());
    }

    @Override
    public Shape getCollisionBoundary() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
