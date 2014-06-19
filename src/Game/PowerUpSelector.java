/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import PowerUps.PowerUpType;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Leal
 */
public class PowerUpSelector extends javax.swing.JInternalFrame implements PowerUpType{
        private GameWindow gameWindow;
        private boolean[] availablePowerUps;
        
    /**
     * Creates new form PowerUpSelector
     * @param gameWindow
     * @param availablePowerUps
     */
    public PowerUpSelector(GameWindow gameWindow, boolean[] availablePowerUps) {
        super("", false, false, false, false);
        initComponents();
        setOpaque(true);
        setVisible(true);
        ((BasicInternalFrameUI)super.getUI()).setNorthPane(null);
        setBorder(null);
        
        this.gameWindow = gameWindow;
        this.availablePowerUps = availablePowerUps;
        
        System.out.println("PowerUpSelector built!");
    }
    
    public void updatePowerUps(){
        availablePowerUps[SHOOTING_PADDLE] = ShootingPaddle.isSelected();
        availablePowerUps[HOLDING_PADDLE] = HoldingPaddle.isSelected();
        availablePowerUps[FIREBALL] = FireBall.isSelected();
        availablePowerUps[THROUGH_BALL] = ThroughBall.isSelected();
        availablePowerUps[SLOW_BALL] = SlowBall.isSelected();
        availablePowerUps[SCORE_MULTIPLIER] = ScoreMultiplier.isSelected();
        availablePowerUps[EXTRA_LIFE] = ExtraLife.isSelected();
        availablePowerUps[SPLIT_BALL] = SplitBall.isSelected();
        availablePowerUps[ENLARGE_PADDLE] = EnlargePaddle.isSelected();
        availablePowerUps[REDUCE_PADDLE] = ReducePaddle.isSelected();
        availablePowerUps[FAST_BALL] = FastBall.isSelected();
        availablePowerUps[DEATH] = Death.isSelected();
        availablePowerUps[FALLING_BRICKS] = FallingBricks.isSelected();
        availablePowerUps[MINIATURE_PADDLE] = MiniaturePaddle.isSelected();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        HoldingPaddle = new javax.swing.JToggleButton();
        ThroughBall = new javax.swing.JToggleButton();
        ScoreMultiplier = new javax.swing.JToggleButton();
        SplitBall = new javax.swing.JToggleButton();
        Death = new javax.swing.JToggleButton();
        EnlargePaddle = new javax.swing.JToggleButton();
        SlowBall = new javax.swing.JToggleButton();
        ExtraLife = new javax.swing.JToggleButton();
        MiniaturePaddle = new javax.swing.JToggleButton();
        ShootingPaddle = new javax.swing.JToggleButton();
        FastBall = new javax.swing.JToggleButton();
        FallingBricks = new javax.swing.JToggleButton();
        FireBall = new javax.swing.JToggleButton();
        ReducePaddle = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(836, 606));

        HoldingPaddle.setBackground(new java.awt.Color(239, 239, 239));
        HoldingPaddle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/HoldingPaddle.png"))); // NOI18N
        HoldingPaddle.setSelected(true);
        HoldingPaddle.setText("Holding Paddle");
        HoldingPaddle.setToolTipText("Click to toggle Power-Up availability!");
        HoldingPaddle.setBorderPainted(false);
        HoldingPaddle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        HoldingPaddle.setDoubleBuffered(true);
        HoldingPaddle.setFocusable(false);
        HoldingPaddle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HoldingPaddle.setMaximumSize(new java.awt.Dimension(180, 60));
        HoldingPaddle.setMinimumSize(new java.awt.Dimension(180, 60));
        HoldingPaddle.setPreferredSize(new java.awt.Dimension(180, 60));

        ThroughBall.setBackground(new java.awt.Color(239, 239, 239));
        ThroughBall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/ThroughBall.png"))); // NOI18N
        ThroughBall.setSelected(true);
        ThroughBall.setText("Through Ball");
        ThroughBall.setToolTipText("Click to toggle Power-Up availability!");
        ThroughBall.setBorderPainted(false);
        ThroughBall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ThroughBall.setDoubleBuffered(true);
        ThroughBall.setFocusable(false);
        ThroughBall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ThroughBall.setMaximumSize(new java.awt.Dimension(180, 60));
        ThroughBall.setMinimumSize(new java.awt.Dimension(180, 60));
        ThroughBall.setPreferredSize(new java.awt.Dimension(180, 60));

        ScoreMultiplier.setBackground(new java.awt.Color(239, 239, 239));
        ScoreMultiplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/ScoreMultiplier.png"))); // NOI18N
        ScoreMultiplier.setSelected(true);
        ScoreMultiplier.setText("Score Multiplier");
        ScoreMultiplier.setToolTipText("Click to toggle Power-Up availability!");
        ScoreMultiplier.setBorderPainted(false);
        ScoreMultiplier.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ScoreMultiplier.setDoubleBuffered(true);
        ScoreMultiplier.setFocusable(false);
        ScoreMultiplier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ScoreMultiplier.setMaximumSize(new java.awt.Dimension(180, 60));
        ScoreMultiplier.setMinimumSize(new java.awt.Dimension(180, 60));
        ScoreMultiplier.setPreferredSize(new java.awt.Dimension(180, 60));

        SplitBall.setBackground(new java.awt.Color(239, 239, 239));
        SplitBall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/SplitBall.png"))); // NOI18N
        SplitBall.setSelected(true);
        SplitBall.setText("Split Ball");
        SplitBall.setToolTipText("Click to toggle Power-Up availability!");
        SplitBall.setBorderPainted(false);
        SplitBall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SplitBall.setDoubleBuffered(true);
        SplitBall.setFocusable(false);
        SplitBall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SplitBall.setMaximumSize(new java.awt.Dimension(180, 60));
        SplitBall.setMinimumSize(new java.awt.Dimension(180, 60));
        SplitBall.setPreferredSize(new java.awt.Dimension(180, 60));

        Death.setBackground(new java.awt.Color(239, 239, 239));
        Death.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/Death.png"))); // NOI18N
        Death.setSelected(true);
        Death.setText("Death");
        Death.setToolTipText("Click to toggle Power-Up availability!");
        Death.setBorderPainted(false);
        Death.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Death.setDoubleBuffered(true);
        Death.setFocusable(false);
        Death.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Death.setMaximumSize(new java.awt.Dimension(180, 60));
        Death.setMinimumSize(new java.awt.Dimension(180, 60));
        Death.setPreferredSize(new java.awt.Dimension(180, 60));

        EnlargePaddle.setBackground(new java.awt.Color(239, 239, 239));
        EnlargePaddle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/LargerPaddle.png"))); // NOI18N
        EnlargePaddle.setSelected(true);
        EnlargePaddle.setText("Enlarge Paddle");
        EnlargePaddle.setToolTipText("Click to toggle Power-Up availability!");
        EnlargePaddle.setBorderPainted(false);
        EnlargePaddle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EnlargePaddle.setDoubleBuffered(true);
        EnlargePaddle.setFocusable(false);
        EnlargePaddle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        EnlargePaddle.setMaximumSize(new java.awt.Dimension(180, 60));
        EnlargePaddle.setMinimumSize(new java.awt.Dimension(180, 60));
        EnlargePaddle.setPreferredSize(new java.awt.Dimension(180, 60));

        SlowBall.setBackground(new java.awt.Color(239, 239, 239));
        SlowBall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/SlowBall.png"))); // NOI18N
        SlowBall.setSelected(true);
        SlowBall.setText("Slow Ball");
        SlowBall.setToolTipText("Click to toggle Power-Up availability!");
        SlowBall.setBorderPainted(false);
        SlowBall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SlowBall.setDoubleBuffered(true);
        SlowBall.setFocusable(false);
        SlowBall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SlowBall.setMaximumSize(new java.awt.Dimension(180, 60));
        SlowBall.setMinimumSize(new java.awt.Dimension(180, 60));
        SlowBall.setPreferredSize(new java.awt.Dimension(180, 60));

        ExtraLife.setBackground(new java.awt.Color(239, 239, 239));
        ExtraLife.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/ExtraLife.png"))); // NOI18N
        ExtraLife.setSelected(true);
        ExtraLife.setText("Extra Life");
        ExtraLife.setToolTipText("Click to toggle Power-Up availability!");
        ExtraLife.setBorderPainted(false);
        ExtraLife.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ExtraLife.setDoubleBuffered(true);
        ExtraLife.setFocusable(false);
        ExtraLife.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ExtraLife.setMaximumSize(new java.awt.Dimension(180, 60));
        ExtraLife.setMinimumSize(new java.awt.Dimension(180, 60));
        ExtraLife.setPreferredSize(new java.awt.Dimension(180, 60));

        MiniaturePaddle.setBackground(new java.awt.Color(239, 239, 239));
        MiniaturePaddle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/MiniaturePaddle.png"))); // NOI18N
        MiniaturePaddle.setSelected(true);
        MiniaturePaddle.setText("Miniature Paddle");
        MiniaturePaddle.setToolTipText("Click to toggle Power-Up availability!");
        MiniaturePaddle.setBorderPainted(false);
        MiniaturePaddle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MiniaturePaddle.setDoubleBuffered(true);
        MiniaturePaddle.setFocusable(false);
        MiniaturePaddle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MiniaturePaddle.setMaximumSize(new java.awt.Dimension(180, 60));
        MiniaturePaddle.setMinimumSize(new java.awt.Dimension(180, 60));
        MiniaturePaddle.setPreferredSize(new java.awt.Dimension(180, 60));

        ShootingPaddle.setBackground(new java.awt.Color(239, 239, 239));
        ShootingPaddle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/ShootingPaddle.png"))); // NOI18N
        ShootingPaddle.setSelected(true);
        ShootingPaddle.setText("Shooting Paddle");
        ShootingPaddle.setToolTipText("Click to toggle Power-Up availability!");
        ShootingPaddle.setBorderPainted(false);
        ShootingPaddle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ShootingPaddle.setDoubleBuffered(true);
        ShootingPaddle.setFocusable(false);
        ShootingPaddle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ShootingPaddle.setMaximumSize(new java.awt.Dimension(180, 60));
        ShootingPaddle.setMinimumSize(new java.awt.Dimension(180, 60));
        ShootingPaddle.setPreferredSize(new java.awt.Dimension(180, 60));

        FastBall.setBackground(new java.awt.Color(239, 239, 239));
        FastBall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/FastBall.png"))); // NOI18N
        FastBall.setSelected(true);
        FastBall.setText("Fast Ball");
        FastBall.setToolTipText("Click to toggle Power-Up availability!");
        FastBall.setBorderPainted(false);
        FastBall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FastBall.setDoubleBuffered(true);
        FastBall.setFocusable(false);
        FastBall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FastBall.setMaximumSize(new java.awt.Dimension(180, 60));
        FastBall.setMinimumSize(new java.awt.Dimension(180, 60));
        FastBall.setPreferredSize(new java.awt.Dimension(180, 60));

        FallingBricks.setBackground(new java.awt.Color(239, 239, 239));
        FallingBricks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/FallingBricks.png"))); // NOI18N
        FallingBricks.setSelected(true);
        FallingBricks.setText("Falling Bricks");
        FallingBricks.setToolTipText("Click to toggle Power-Up availability!");
        FallingBricks.setBorderPainted(false);
        FallingBricks.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FallingBricks.setDoubleBuffered(true);
        FallingBricks.setFocusable(false);
        FallingBricks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FallingBricks.setMaximumSize(new java.awt.Dimension(180, 60));
        FallingBricks.setMinimumSize(new java.awt.Dimension(180, 60));
        FallingBricks.setPreferredSize(new java.awt.Dimension(180, 60));

        FireBall.setBackground(new java.awt.Color(239, 239, 239));
        FireBall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/FireBall.png"))); // NOI18N
        FireBall.setText("Fire Ball");
        FireBall.setToolTipText("Click to toggle Power-Up availability!");
        FireBall.setBorderPainted(false);
        FireBall.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FireBall.setDoubleBuffered(true);
        FireBall.setEnabled(false);
        FireBall.setFocusable(false);
        FireBall.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FireBall.setMaximumSize(new java.awt.Dimension(180, 60));
        FireBall.setMinimumSize(new java.awt.Dimension(180, 60));
        FireBall.setPreferredSize(new java.awt.Dimension(180, 60));

        ReducePaddle.setBackground(new java.awt.Color(239, 239, 239));
        ReducePaddle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PowerUps/Sprites/SmallerPaddle.png"))); // NOI18N
        ReducePaddle.setSelected(true);
        ReducePaddle.setText("Shrink Paddle");
        ReducePaddle.setToolTipText("Click to toggle Power-Up availability!");
        ReducePaddle.setBorderPainted(false);
        ReducePaddle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReducePaddle.setDoubleBuffered(true);
        ReducePaddle.setFocusable(false);
        ReducePaddle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ReducePaddle.setMaximumSize(new java.awt.Dimension(180, 60));
        ReducePaddle.setMinimumSize(new java.awt.Dimension(180, 60));
        ReducePaddle.setPreferredSize(new java.awt.Dimension(180, 60));

        jLabel1.setText("Click to toggle the available Power-Ups for this game.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(FireBall, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ExtraLife, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(HoldingPaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ShootingPaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(SlowBall, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(1, 1, 1))
                                        .addComponent(ScoreMultiplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(SplitBall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ReducePaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EnlargePaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Death, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FastBall, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(FallingBricks, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(ThroughBall, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(MiniaturePaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Death, EnlargePaddle, ExtraLife, FallingBricks, FastBall, MiniaturePaddle, ScoreMultiplier, ShootingPaddle, ThroughBall});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ShootingPaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FastBall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SplitBall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SlowBall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HoldingPaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ScoreMultiplier, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Death, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EnlargePaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FallingBricks, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FireBall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ExtraLife, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ReducePaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ThroughBall, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MiniaturePaddle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Death, EnlargePaddle, ExtraLife, FallingBricks, FastBall, FireBall, HoldingPaddle, MiniaturePaddle, ReducePaddle, ScoreMultiplier, ShootingPaddle, SlowBall, SplitBall, ThroughBall});

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Power-Up Selection!");

        jButton1.setText("Start!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updatePowerUps();
        gameWindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton Death;
    private javax.swing.JToggleButton EnlargePaddle;
    private javax.swing.JToggleButton ExtraLife;
    private javax.swing.JToggleButton FallingBricks;
    private javax.swing.JToggleButton FastBall;
    private javax.swing.JToggleButton FireBall;
    private javax.swing.JToggleButton HoldingPaddle;
    private javax.swing.JToggleButton MiniaturePaddle;
    private javax.swing.JToggleButton ReducePaddle;
    private javax.swing.JToggleButton ScoreMultiplier;
    private javax.swing.JToggleButton ShootingPaddle;
    private javax.swing.JToggleButton SlowBall;
    private javax.swing.JToggleButton SplitBall;
    private javax.swing.JToggleButton ThroughBall;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
