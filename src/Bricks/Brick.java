package Bricks;

import Game.Ball;
import Game.Collisionable;
import Game.PaintableObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alejandro
 */
public class Brick implements PaintableObject, Collisionable{
    static final int width = 50,
                     height = 20;
    int hp,
        score,
        x,
        y;
    Color c;
    
    public Brick(int x, int y){
        this.x = x;
        this.y = y;
        score = 100;
        hp = 1;
        c = Color.CYAN;
    }
    
    public Brick(int x, int y, Color c){
        this(x, y);
        this.c = c;
    }
    
    public Brick(Point p){
        this((int)p.getX(), (int)p.getY());
    }
    
    public Brick(Point p, Color c){
        this(p);
        this.c = c;
    }
    
    public boolean isDestroyed(){
        return hp == 0;
    }
    
    public int getScore(){
        return score;
    }
    
    public void damage(boolean instakill){
        if(instakill)
            hp=0;
        else
            hp--;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getCenterX(){
        return getX()+getWidth()/2;
    }
    
    public int getCenterY(){
        return getY()+getHeight()/2;
    }
    
    @Override
    public Point getPosition() {
        return new Point(getX(), getY());
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosition(Point p) {
        x = (int)p.getX();
        y = (int)p.getY();
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
        g.setColor(c);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }
    
    @Override
    public void paint(Graphics g, int x, int y) {
        g.setColor(c);
        g.fillRect(x, y, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(x, y, getWidth(), getHeight());
    }

    @Override
    public Shape getCollisionBoundary() {
        return new Rectangle(getX(), getY(), width, height);
    }

    @Override
    public void checkCollision(Collisionable c) {
        throw new UnsupportedOperationException("Not supported yet!");
    }
    
    private void explode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}