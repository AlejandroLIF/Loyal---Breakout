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
    public final static int SMALL = 50,
                            LARGE = 400;
    private double x, 
                   y;
    private Color color;
    private int width,
                height;
    private boolean isShooting,
                    isHolding;
    
    
    Paddle(){
        x = GameBoundaries.RIGHT-width/2;
        y = GameBoundaries.BOTTOM-height-60;
        
        width = 100;
        height = 10;
        color = Color.GRAY;
    }
    
    public void setShooting(boolean isShooting){
        this.isShooting = isShooting;
    }
    
    public void setHolding(boolean isHolding){
        this.isHolding = isHolding;
    }
    
    public void grow(){
        if(width<LARGE)
            width*=2;
    }
    
    public void shrink(){
        if(width>SMALL)
            width/=2;
    }
    
    public void miniaturize(){
        width = SMALL;
    }
    
    public boolean isHolding(){
        return isHolding;
    }
    
    public boolean isShooting(){
        return isShooting;
    }
    
    @Override
    public void checkCollision(Collisionable c){
        throw new UnsupportedOperationException("Not supported yet!");
    }
    
    public boolean collides(Collisionable c){
        return c.getCollisionBoundary().intersects((Rectangle)getCollisionBoundary());
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
        setX(x);
        setY(y);
        paint(g);
    }

    @Override
    public Shape getCollisionBoundary() {
        return new Rectangle(getX(), getY(), width, height);
    }
}
