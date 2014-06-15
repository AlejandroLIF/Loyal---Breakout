package Game;


import Bricks.Brick;
import PowerUps.PowerUp;
import static PowerUps.PowerUpType.DEATH;
import static PowerUps.PowerUpType.ENLARGE_PADDLE;
import static PowerUps.PowerUpType.EXTRA_LIFE;
import static PowerUps.PowerUpType.FALLING_BRICKS;
import static PowerUps.PowerUpType.FAST_BALL;
import static PowerUps.PowerUpType.FIREBALL;
import static PowerUps.PowerUpType.HOLDING_PADDLE;
import static PowerUps.PowerUpType.MINIATURE_PADDLE;
import static PowerUps.PowerUpType.REDUCE_PADDLE;
import static PowerUps.PowerUpType.SCORE_MULTIPLIER;
import static PowerUps.PowerUpType.SHOOTING_PADDLE;
import static PowerUps.PowerUpType.SLOW_BALL;
import static PowerUps.PowerUpType.SPLIT_BALL;
import static PowerUps.PowerUpType.THROUGH_BALL;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private JInternalFrame mainMenu,
                           gameWindow;
    
    private Thread animator;
    private boolean paused,
                    gameOver;
    private Paddle paddle;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<PowerUp> powerUps;
    private int lives,
                score;
    private double multiplier;
    private String endMessage;
    
    public GameWindow(JInternalFrame mainMenu) {
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
        
        paused = false;
        gameOver = false;
        paddle = new Paddle();
        balls = new ArrayList<>();
        bricks = new ArrayList<>();
        powerUps = new ArrayList<>();
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
            if(!paused){
                beforeTime = System.currentTimeMillis();
                
                gameLogic();
                repaint();
                
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
                        System.out.println("Power Up!");
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
        
        for(Ball ball : balls){
            ball.paint(g);
        }
        
        for(Brick brick : bricks){
            brick.paint(g);
        }
        
        for(PowerUp powerUp : powerUps){
            powerUp.paint(g);
        }
        
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
            for(Ball b : balls){
                b.release();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Mouse Pressed Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Mouse Released Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            throw new UnsupportedOperationException("Mouse Entered Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            throw new UnsupportedOperationException("Mouse Exited Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            throw new UnsupportedOperationException("Mouse Dragged Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            paddle.move(e.getX());
            for(Ball b : balls){
                b.moveHeld(paddle.getX());
            }
        }
    }
}

