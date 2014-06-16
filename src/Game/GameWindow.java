package Game;


import Bricks.Brick;
import PowerUps.PowerUp;
import static PowerUps.PowerUpType.*;
import Projectiles.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Robot;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
class GameWindow extends JInternalFrame implements Runnable {
    private final String WINNER = "You win!",
                         LOSER  = "Game Over!";
    
    private final MainMenu mainMenu;
    private final GameWindow gameWindow;
    
    private final Robot robot;
    
    private Thread animator;
    private boolean paused,
                    gameOver;
    private final Paddle paddle;
    private final List<Ball> balls;
    private final List<Brick> bricks;
    private final List<PowerUp> powerUps;
    private final List<Laser> lasers;
    private final List<ExplodingDummy> explodingDummies;
    
    private int lives,
                score;
    private double multiplier;
    private String endMessage;
    
    public GameWindow(MainMenu mainMenu) throws AWTException {
        super("", false, false, false, false);
        this.setCursor(
                Toolkit.getDefaultToolkit().createCustomCursor(
                        new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0,0), "blank cursor"));
        this.mainMenu = mainMenu;
        gameWindow = this;
        ((BasicInternalFrameUI)super.getUI()).setNorthPane(null);
        setSize(800,600);
        setVisible(true);
        setBorder(null);
        setFocusable(true);
        setDoubleBuffered(true);
        setOpaque(true);
        
        addKeyListener(new KeyboardAdapter());
        addMouseListener(new MouseAdapter());
        addMouseMotionListener(new MouseAdapter());
        
        robot = new Robot();
        
        paused = false;
        gameOver = false;
        paddle = new Paddle();
        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        powerUps = new ArrayList<>();
        lasers = new ArrayList<>();
        explodingDummies = new ArrayList<>();
        
        balls.add(new Ball(paddle.getWidth()/2, paddle.getY()-Ball.getDiameter(), true));
        
        lives = 3;
        score = 0;
        multiplier = 1;
        createLevel();
    }

    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    
    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        final long delay = 10;
        
        while(!gameOver){
            beforeTime = System.currentTimeMillis();

            if(!paused){
                gameLogic();
                repaint();
            }
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = delay - timeDiff;
            if(sleep < 0){
                sleep = 2L;
            }

            try{
                Thread.sleep(sleep);
            }catch(InterruptedException e){
                System.out.println("Thread interrupted!");
            }
        }
        while(true){
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted!");
            }
        }
    }
    
    private void gameLogic(){
        Iterator<Ball> ballIT = balls.iterator();
        while(ballIT.hasNext()){
            Ball ball = ballIT.next();
            ball.move();
            ball.checkCollision(paddle);
            
            Iterator<Brick> brickIT = bricks.iterator();
            while(brickIT.hasNext()){
                Brick brick = brickIT.next();
                ball.checkCollision(brick);
                if(brick.isDestroyed()){
                    increaseScore(brick.getScore());
                    brickIT.remove();
                    if(PowerUp.willSpawn()){
                        powerUps.add(new PowerUp(brick.getPosition()));
                    }
                }
            }
            if(ball.isDead())
                ballIT.remove();
        }
        
        Iterator<PowerUp> powerUpIT = powerUps.iterator();
        while(powerUpIT.hasNext()){
            PowerUp powerUp = powerUpIT.next();
            powerUp.move();
            if(paddle.collides(powerUp)){
                applyPowerUp(powerUp);
                powerUpIT.remove();
            }
            else if(powerUp.isOutOfBounds())
                powerUpIT.remove();
        }
        
        Iterator<Laser> laserIT = lasers.iterator();
        while(laserIT.hasNext()){
            Laser laser = laserIT.next();
            laser.move();
            
            Iterator<Brick> brickIT = bricks.iterator();
            while(brickIT.hasNext()){
                Brick brick = brickIT.next();
                laser.checkCollision(brick);
                if(brick.isDestroyed()){
                    increaseScore(brick.getScore());
                    brickIT.remove();
                    if(PowerUp.willSpawn()){
                        powerUps.add(new PowerUp(brick.getPosition()));
                    }
                }
            }
            if(laser.isDead())
                laserIT.remove();
        }
        
        if(balls.isEmpty()){
            lives--;
            if(lives > 0)
                balls.add(new Ball(paddle.getWidth()/2, paddle.getY()-Ball.getDiameter(), true));
            else{
                gameOver = true;
                endMessage = LOSER;
            }
        }
        if(bricks.isEmpty()){
            gameOver = true;
            endMessage = WINNER;
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        paintBackground(g);
        
        paddle.paint(g);
        
        for(Laser laser : lasers)
            laser.paint(g);
        
        for(Ball ball : balls)
            ball.paint(g);
        
        for(Brick brick : bricks)
            brick.paint(g);
        
        for(PowerUp powerUp : powerUps)
            powerUp.paint(g);
        
        drawStatusBar(g);
        
        if(gameOver){
            g.setXORMode(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawString(endMessage,
                            GameBoundaries.RIGHT/2-g.getFontMetrics().stringWidth(endMessage)/2,
                            GameBoundaries.BOTTOM/2-g.getFontMetrics().getHeight()/2);
        }
        g.dispose();
    }
    
    private void paintBackground(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
    }
    
    private void drawStatusBar(Graphics g){
        final int height = 20;
        final int bottomOffset = 5;
        Ball ball = new Ball();
        g.setColor(Color.BLACK);
        g.fillRect(GameBoundaries.LEFT, GameBoundaries.BOTTOM-height, GameBoundaries.RIGHT, height);
        for(int i=0; i<lives; i++)
            ball.paint(g, Ball.getDiameter()*i + 2, GameBoundaries.BOTTOM-Ball.getDiameter()-bottomOffset);
        
        g.setColor(Color.YELLOW);
        String s = "Score: "+score;
        g.drawString(s, GameBoundaries.RIGHT-g.getFontMetrics().stringWidth(s), GameBoundaries.BOTTOM-bottomOffset);
    }

    private void createLevel() {
        Brick stdBrick = new Brick(0,0);
        for(int i=100; i<GameBoundaries.RIGHT-100; i+=stdBrick.getWidth()){
            for(int j=200; j>=100; j-=stdBrick.getHeight())
                bricks.add(new Brick(i, j));
        }
    }
    
    private void increaseScore(int score){
        this.score += score*multiplier;
    }

    private void applyPowerUp(PowerUp powerUp) {
        switch(powerUp.getType()){
            case SHOOTING_PADDLE:
                paddle.setShooting(true);
                break;
            case HOLDING_PADDLE:
                paddle.setHolding(true);
                break;
            case FIREBALL:
                for(Ball b : balls){
                    b.setFireBall(true);
                }
                break;
            case THROUGH_BALL:
                for(Ball b : balls){
                    b.setThroughBall(true);
                }
                break;
            case SLOW_BALL:
                for(Ball b : balls){
                    b.setSpeed(Ball.SLOW);
                }
                break;
            case SCORE_MULTIPLIER:
                multiplier*=2;
                break;
            case EXTRA_LIFE:
                lives++;
                break;
            case SPLIT_BALL:
                // TODO
                break;
            case ENLARGE_PADDLE:
                paddle.grow();
                break;
            case REDUCE_PADDLE:
                paddle.shrink();
                break;
            case FAST_BALL:
                for(Ball b : balls)
                    b.setSpeed(Ball.FAST);
                break;
            case DEATH:
                lives--;
                break;
            case FALLING_BRICKS:
//                throw new UnsupportedOperationException("Not supported yet!");
                break;
            case MINIATURE_PADDLE:
                paddle.miniaturize();
                break;
            default:
                //Should never happen
        }
    }
    
    private class KeyboardAdapter extends KeyAdapter{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_ESCAPE:
                    mainMenu.setCursor(Cursor.getDefaultCursor());
                    mainMenu.setVisible(true);
                    gameWindow.dispose();
                    break;
                case KeyEvent.VK_P:
                    paused = !paused;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private class MouseAdapter implements MouseListener, MouseMotionListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(!paused && !gameOver){
                for(Ball b : balls){
                    b.release();
                }
                if(paddle.isShooting()){
                    paddle.fire(lasers);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //Do nothing
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //Do nothing
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //Do nothing
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //Do nothing
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(!paused)
                lockMouse(e);
            if(!paused && !gameOver){
                paddle.move(e.getX());
                for(Ball b : balls){
                    b.moveHeld(paddle.getX());
                }
            }
        }
        
        /**
         * Locks mouse within the screen. WARNING: uses magic numbers.
         * @param e The triggering mouse event.
         */
        private void lockMouse(MouseEvent e){
            final int gameWindowX = mainMenu.getXOnScreen(),
                      gameWindowY = mainMenu.getYOnScreen();
            
            int mouseX = e.getX(),
                mouseY = e.getY();
            if(mouseX < GameBoundaries.LEFT   + paddle.getWidth()/4  ||
               mouseX > GameBoundaries.RIGHT  - paddle.getWidth()/4  ||
               mouseY < GameBoundaries.TOP    + 100                  ||
               mouseY > GameBoundaries.BOTTOM - 100)
                robot.mouseMove(gameWindowX + paddle.getCenterX(), gameWindowY + 400);
        }
    }
}

