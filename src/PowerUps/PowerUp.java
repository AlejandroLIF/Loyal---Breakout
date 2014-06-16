/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PowerUps;

import Game.Collisionable;
import Game.GameBoundaries;
import Game.PaintableObject;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Leal
 */
public class PowerUp implements PaintableObject, Collisionable, PowerUpType, GameBoundaries{
    private static final double SPAWN_CHANCE = 10;
    private int type;
    private Image image;
    private double x,
                   y,
                   speed,
                   direction;
    
    public static boolean willSpawn(){
        Random r = new Random();
        return r.nextInt(100) < SPAWN_CHANCE;
    }
    
    public PowerUp(boolean[] availablePowerUps){
        String imageName = "";
        Random r = new Random();
        
        do{
        type = r.nextInt(TOTAL_POWERUPS);
        }while(!availablePowerUps[type]);
        
        direction = -Math.PI/2;
        speed = 3;
        
        switch(type){
            case SHOOTING_PADDLE:
                imageName = "ShootingPaddle";
                break;
            case HOLDING_PADDLE:
                imageName = "HoldingPaddle";
                break;
            case FIREBALL:
                imageName = "FireBall";
                break;
            case THROUGH_BALL:
                imageName = "ThroughBall";
                break;
            case SLOW_BALL:
                imageName =  "SlowBall";
                break;
            case SCORE_MULTIPLIER:
                imageName = "ScoreMultiplier";
                break;
            case EXTRA_LIFE:
                imageName = "ExtraLife";
                break;
            case SPLIT_BALL:
                imageName = "SplitBall";
                break;
            case ENLARGE_PADDLE:
                imageName = "LargerPaddle";
                break;
            case REDUCE_PADDLE:
                imageName = "SmallerPaddle";
                break;
            case FAST_BALL:
                imageName = "FastBall";
                break;
            case DEATH:
                imageName = "Death";
                break;
            case FALLING_BRICKS:
                imageName = "FallingBricks";
                break;
            case MINIATURE_PADDLE:
                imageName = "MiniaturePaddle";
                break;
//            case PLACEHOLDER:
//                imageName = "PlaceHolder";
            default:
                //Should Never Happen
        }
        
        try {
            image = ImageIO.read(PowerUp.class.getResourceAsStream("/PowerUps/Sprites/"+imageName+".png"));
        } catch (IOException ex) {
            Logger.getLogger(PowerUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void move(){
        y = y+speed*Math.sin(direction);
        speed-=.1;
    }
    
    public boolean isOutOfBounds(){
        return getY() > BOTTOM;
    }
    
    public PowerUp(boolean[] availablePowerUps, Point p){
        this(availablePowerUps, p.getX(), p.getY());
    }
    
    public PowerUp(boolean[] availablePowerUps, double x, double y){
        this(availablePowerUps);
        this.x = x;
        this.y = y;
    }
    
    public int getWidth(){
        return image.getWidth(null);
    }
    
    public int getHeight(){
        return image.getHeight(null);
    }
    
    public int getType(){
        return this.type;
    }
    
    @Override
    public Point getPosition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        g.drawImage(image, getX(), getY(), null);
    }

    @Override
    public void paint(Graphics g, int x, int y) {
        setX(x);
        setY(y);
        paint(g);
    }

    @Override
    public Shape getCollisionBoundary() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    @Override
    public void checkCollision(Collisionable c){
        throw new UnsupportedOperationException("Not supported yet!");
    }
}
