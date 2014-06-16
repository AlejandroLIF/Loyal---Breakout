/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Projectiles;

import Game.Collisionable;
import Game.PaintableObject;
import Bricks.Brick;
import Game.GameBoundaries;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 *
 * @author Leal
 */
public class Laser implements PaintableObject, Collisionable{
    private static final double speed = 3;
    public static final int     width = 3,
                                height = 10;
    private static final int    arcWidth = 3,
                                arcHeight = 2;
    private double x,
                   y;
    private boolean dead;
    
    public Laser(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public void move(){
        y-=speed;
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

    @Override
    public void setPosition(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRoundRect(getX(), getY(), width, height, arcWidth, arcHeight);
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

    @Override
    public void checkCollision(Collisionable c) {
        if(collides(c)){
            if(c.getClass() == Brick.class){
                ((Brick)c).damage(false);
                dead = true;
            }
            else{
                throw new UnsupportedOperationException("Laser collision with "+c.getClass()+" is undefined.");
            }
        }
    }
    
    private boolean collides(Collisionable c){
        return c.getCollisionBoundary().intersects((Rectangle)getCollisionBoundary());
    }
    
    public boolean isDead(){
        return dead || (getY() + getHeight() < GameBoundaries.TOP);
    }
}
