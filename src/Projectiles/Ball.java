package Projectiles;


import Bricks.Brick;
import Game.Collisionable;
import Game.GameBoundaries;
import Game.Paddle;
import Game.PaintableObject;
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
public class Ball implements PaintableObject, GameBoundaries, Collisionable{
    private static final int diameter = 10;
    public static final double SLOW = 2,
                        NORMAL = 3,
                        FAST = 8;
    private double x, y, heldX;
    private double speed;
    private double direction;
    private Color color;
    
    private boolean fireBall,
                    throughBall,
                    held,
                    bouncedThisCycle,
                    dead;
    
    public Ball(){
        this(0,0);
    }
    
    public Ball(int x, int y){
        this.x = x;
        this.y = y;
        color = Color.RED;
        fireBall = false;
        throughBall = false;
        held = false;
        bouncedThisCycle = false;
        dead = false;
        speed = NORMAL;
        direction = -0.7;
    }
    
    public Ball(int x, int y, boolean held){
        this(x, y);
        this.held = held;
        if(held){
            this.heldX = x;
        }
    }
    
    public Ball(Point p){
        this((int)p.getX(), (int)p.getY());
    }
    
    public Ball(Point p, boolean held){
        this(p);
        this.held = held;
    }
    
    public void setFireBall(boolean fireBall){
        this.fireBall = fireBall;
    }
    
    public void setThroughBall(boolean throughBall){
        this.throughBall = throughBall;
    }
    
    public void hold(int heldX){
        held = true;
        this.heldX = heldX;
        y = y-diameter/2;
    }
    
    public void release(){
        held = false;
    }
    
    public void moveHeld(int x){
        if(held){
            setX(x+(int)heldX);
        }
    }
    
    public void move(){
        double tempX, tempY;
        if(!held){
            tempX = x + speed*Math.cos(direction);
            tempY = y + speed*Math.sin(direction);

            boundaryCheck(tempX, tempY);
        }
        bouncedThisCycle = false;
    }
    
    public void boundaryCheck(double tempX, double tempY){
        if(tempX >= GameBoundaries.RIGHT-diameter){
            tempX = GameBoundaries.RIGHT-diameter;
            bounceRight();
        }
        if(tempX <= GameBoundaries.LEFT){
            tempX = GameBoundaries.LEFT;
            bounceLeft();
        }
        if(tempY <= GameBoundaries.TOP){
            tempY = GameBoundaries.TOP;
            bounceTop();
        }
        if(tempY >= GameBoundaries.BOTTOM){
            tempY = GameBoundaries.BOTTOM;
            dead = true;
        }
        x = tempX;
        y = tempY;
    }
    
    private void bounceBottom(){
        direction = -direction;
    }
    
    private void bounceTop(){
        direction = -direction;
    }
    
    private void bounceLeft(){
        direction = (direction > 0) ? (Math.PI - direction) : (-Math.PI - direction);
    }
    
    private void bounceRight(){
        direction = (direction > 0) ? (-Math.PI - direction) : (Math.PI - direction);
    }
    
    private void bouncePaddle(Paddle p){
        double angle[] = {1.2, 0.7, 0.45};
        double displacement = (getCenterX() - p.getCenterX())/(p.getWidth()/2.0);
        
        if(Math.abs(displacement) <= 0.30){
            direction = (displacement > 0) ? -angle[0] : angle[0] - Math.PI;
        }
        else if(Math.abs(displacement) <= 0.70){
            direction = (displacement > 0) ? -angle[1] : angle[1] - Math.PI;
        }
        else{
            direction = (displacement > 0) ? -angle[2] : angle[2] - Math.PI;
        }
        
        if(!isHeld())
            goFaster();
    }
    
    private void bounce(Rectangle r){
        int tolerance = (int)speed;
        Rectangle ball = (Rectangle)getCollisionBoundary();
        if(!bouncedThisCycle){
            if(r.getMinX() - tolerance <= ball.getMaxX()            &&
                        ball.getMinX() <= r.getMaxX() + tolerance   &&
                        ball.getMaxY() <= r.getMinY() + tolerance)
                bounceBottom();
            else if(r.getMinX() - tolerance <= ball.getMaxX()            &&
                        ball.getMinX() <= r.getMaxX() + tolerance   &&
                        ball.getMinY() >= r.getMaxY() - tolerance)
                bounceTop();
            else if(r.getMinY() + tolerance <= ball.getMaxY()            &&
                        ball.getMinY() <= r.getMaxY() + tolerance   &&
                        ball.getMaxX() <= r.getMinX() + tolerance)
                bounceRight();
            else
                bounceLeft();
            bouncedThisCycle = true;
        }
    }
    
    public boolean isFireball(){
        return fireBall;
    }
    
    public boolean isDead(){
        return dead;
    }
    
    public boolean isThroughBall(){
        return throughBall;
    }
    
    public boolean isHeld(){
        return held;
    }
    
    public static int getDiameter(){
        return diameter;
    }
    
    public void setSpeed(double speed){
        this.speed = speed;
    }
    
    public void goFaster(){
        if(speed<FAST)
            speed+=.2;
    }
    
    
    @Override
    public Point getPosition() {
        return new Point(getX(), getY());
    }

    @Override
    public int getX() {
        return (int)x;
    }
    
    public int getCenterX(){
        return getX()+getDiameter()/2;
    }

    @Override
    public int getY() {
        return (int)y;
    }
    
    public int getCenterY(){
        return getY()+getDiameter()/2;
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
        if(fireBall && throughBall)
            color = Color.ORANGE;
        else if(fireBall)
            color = Color.RED;
        else if(throughBall)
            color = Color.YELLOW;
        else
            color = Color.GRAY;
        g.setColor(color);
        g.fillOval(getX(), getY(), diameter, diameter);
    }
    
    @Override
    public void paint(Graphics g, int x, int y){
        setX(x);
        setY(y);
        paint(g);
    }

    @Override
    public Shape getCollisionBoundary() {
        return new Rectangle(getX(), getY(), diameter, diameter);
    }

    @Override
    public void checkCollision(Collisionable c){
        if(collides(c)){
            if(c.getClass() == Paddle.class){
                Paddle paddle = (Paddle)c; 
                bouncePaddle(paddle);
                if(paddle.isHolding()){
                    hold(getX()-paddle.getX());
                }
            }
            else if(c.getClass() == Brick.class){
                ((Brick)c).damage(fireBall || throughBall);
                if(!throughBall)
                    bounce((Rectangle) c.getCollisionBoundary());
            }
        }
    }

    private boolean collides(Collisionable c){
        return c.getCollisionBoundary().intersects((Rectangle) getCollisionBoundary());
    }
    
}
