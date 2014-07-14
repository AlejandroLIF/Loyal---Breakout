/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import Bricks.Brick;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Leal
 */
public class LevelEditor extends javax.swing.JInternalFrame implements Runnable{

    private MainMenu mainMenu;
    private Color activeColor;
    private Paddle paddle;
    private Brick nextBrick;
    private List<Brick> bricks;
    private Image background;
    private Thread animator;
    private boolean withinLimits;
    private File saveFile;
    private boolean modified;
    /**
     * Creates new form LevelEditor
     * @param mainMenu
     */
    public LevelEditor(MainMenu mainMenu) {
        super("", false, false, false, false);
        initComponents();
        setOpaque(true);
        setVisible(true);
        ((BasicInternalFrameUI)super.getUI()).setNorthPane(null);
        setBorder(null);
        
        this.addMouseListener(new MouseAdapter());
        this.addMouseMotionListener(new MouseAdapter());
        this.mainMenu = mainMenu;
        
        activeColor = Color.CYAN;
        paddle = new Paddle();
        bricks = new ArrayList<>();
        nextBrick = new Brick(GameBoundaries.RIGHT,GameBoundaries.BOTTOM);
        
        setColoredBackground(Color.WHITE);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        paintGame(g);
    }
    
    private void paintGame(Graphics g){
        paintBackground(g);
        paddle.paint(g);
        for(Brick b : bricks)
            b.paint(g);
        if(withinLimits)
            nextBrick.paint(g);
//        paintMouseCoordinates(g, mouseX, mouseY);
    }
    
    private void paintBackground(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 21, GameBoundaries.RIGHT, 516, null);
        g2d.setColor(Color.RED);
        Stroke def = g2d.getStroke();
        g2d.setStroke(new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, new float[]{10.0f}, 0.0f));
        g2d.drawRect(0, 30, GameBoundaries.RIGHT-1, 19*nextBrick.getHeight());
        g2d.setStroke(def);
    }

    private void paintMouseCoordinates(Graphics g, int x, int y){
        g.setColor(Color.RED);
        g.drawString("("+x+","+y+")", GameBoundaries.CENTERX, GameBoundaries.CENTERY);
    }
    
    private void setColoredBackground(Color color){
        BufferedImage bg = new BufferedImage(GameBoundaries.RIGHT, GameBoundaries.BOTTOM, BufferedImage.OPAQUE);
        Graphics2D g2d = bg.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(0, 0, GameBoundaries.RIGHT, GameBoundaries.BOTTOM);
        g2d.dispose();
        background = bg;
    }
    
    private boolean isWithinLimits(Point p){
        return p.getY() >= 30 && p.getY() < 30+19*nextBrick.getHeight();
    }
    
    private void snapToBrickGrid(Point p){
        int x, y;
        if(isWithinLimits(p)){
            x = (int)p.getX();
            x = (x/nextBrick.getWidth())*nextBrick.getWidth();
            y = (int)p.getY();
            y = ((y-10)/nextBrick.getHeight())*nextBrick.getHeight()+10;
            nextBrick.setX(x);
            nextBrick.setY(y);
            withinLimits = true;
        }
        else{
            withinLimits = false;
        }
    }
    
    private boolean spaceIsOccupied(Brick b){
        for(Brick br : bricks){
            if(collide(br, b))
                return true;
        }
        return false;
    }
    
    private void removeBrick(Brick b){
        Iterator it = bricks.iterator();
        while(it.hasNext()){
            Brick br = (Brick) it.next();
            if(collide(br, b)){
                it.remove();
            }
        }
    }
    
    private void writeLevelToFile(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile.getAbsoluteFile()));
            oos.writeObject(bricks);
            ImageIO.write((RenderedImage) background, "png", oos);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(LevelEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadLevelFromFile(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile.getAbsoluteFile()));
            bricks = (List<Brick>) ois.readObject();
            background = ImageIO.read(ois);
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LevelEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean collide(Brick a, Brick b){
        return a.getCollisionBoundary().intersects((Rectangle2D) b.getCollisionBoundary());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ColorsToolbar = new javax.swing.JToolBar();
        Color1Button = new javax.swing.JButton();
        Color2Button = new javax.swing.JButton();
        Color3Button = new javax.swing.JButton();
        Color4Button = new javax.swing.JButton();
        Color5Button = new javax.swing.JButton();
        Color6Button = new javax.swing.JButton();
        Color7Button = new javax.swing.JButton();
        Color8Button = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        MoreColorsButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newMenu = new javax.swing.JMenuItem();
        openMenu = new javax.swing.JMenuItem();
        saveMenu = new javax.swing.JMenuItem();
        saveAsMenu = new javax.swing.JMenuItem();
        returnMenu = new javax.swing.JMenuItem();
        bgMenu = new javax.swing.JMenu();
        bgImageMenu = new javax.swing.JMenuItem();
        bgColorMenu = new javax.swing.JMenuItem();

        setPreferredSize(new java.awt.Dimension(800, 600));

        ColorsToolbar.setFloatable(false);
        ColorsToolbar.setFocusable(false);

        Color1Button.setBackground(new java.awt.Color(255, 51, 51));
        Color1Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color1Button.setFocusable(false);
        Color1Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color1Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color1Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color1Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color1Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color1ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color1Button);

        Color2Button.setBackground(new java.awt.Color(51, 255, 51));
        Color2Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color2Button.setFocusable(false);
        Color2Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color2Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color2Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color2Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color2Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color2ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color2Button);

        Color3Button.setBackground(new java.awt.Color(51, 51, 255));
        Color3Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color3Button.setFocusable(false);
        Color3Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color3Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color3Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color3Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color3Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color3ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color3Button);

        Color4Button.setBackground(new java.awt.Color(204, 0, 204));
        Color4Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color4Button.setFocusable(false);
        Color4Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color4Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color4Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color4Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color4Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color4Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color4ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color4Button);

        Color5Button.setBackground(new java.awt.Color(204, 204, 0));
        Color5Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color5Button.setFocusable(false);
        Color5Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color5Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color5Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color5Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color5Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color5Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color5ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color5Button);

        Color6Button.setBackground(new java.awt.Color(0, 255, 255));
        Color6Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color6Button.setFocusable(false);
        Color6Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color6Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color6Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color6Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color6Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color6Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color6ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color6Button);

        Color7Button.setBackground(new java.awt.Color(0, 0, 0));
        Color7Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color7Button.setFocusable(false);
        Color7Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color7Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color7Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color7Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color7Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color7Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color7ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color7Button);

        Color8Button.setBackground(new java.awt.Color(255, 255, 255));
        Color8Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Color8Button.setFocusable(false);
        Color8Button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Color8Button.setMaximumSize(new java.awt.Dimension(50, 50));
        Color8Button.setMinimumSize(new java.awt.Dimension(50, 50));
        Color8Button.setPreferredSize(new java.awt.Dimension(50, 50));
        Color8Button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Color8Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Color8ButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(Color8Button);
        ColorsToolbar.add(jSeparator1);

        MoreColorsButton.setText("More Colors");
        MoreColorsButton.setFocusable(false);
        MoreColorsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        MoreColorsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        MoreColorsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoreColorsButtonActionPerformed(evt);
            }
        });
        ColorsToolbar.add(MoreColorsButton);

        jMenuBar1.setFocusable(false);

        jMenu1.setText("File");
        jMenu1.setFocusable(false);

        newMenu.setText("New");
        newMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });
        jMenu1.add(newMenu);

        openMenu.setText("Open");
        openMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuActionPerformed(evt);
            }
        });
        jMenu1.add(openMenu);

        saveMenu.setText("Save");
        saveMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuActionPerformed(evt);
            }
        });
        jMenu1.add(saveMenu);

        saveAsMenu.setText("Save As");
        saveAsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuActionPerformed(evt);
            }
        });
        jMenu1.add(saveAsMenu);

        returnMenu.setText("Return to Title");
        returnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnMenuActionPerformed(evt);
            }
        });
        jMenu1.add(returnMenu);

        jMenuBar1.add(jMenu1);

        bgMenu.setText("Background");
        bgMenu.setFocusable(false);

        bgImageMenu.setText("Image");
        bgImageMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgImageMenuActionPerformed(evt);
            }
        });
        bgMenu.add(bgImageMenu);

        bgColorMenu.setText("Color");
        bgColorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgColorMenuActionPerformed(evt);
            }
        });
        bgMenu.add(bgColorMenu);

        jMenuBar1.add(bgMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ColorsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(475, Short.MAX_VALUE)
                .addComponent(ColorsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Color1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color1ButtonActionPerformed
        nextBrick.setColor(Color1Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color1ButtonActionPerformed

    private void Color2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color2ButtonActionPerformed
        nextBrick.setColor(Color2Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color2ButtonActionPerformed

    private void Color3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color3ButtonActionPerformed
        nextBrick.setColor(Color3Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color3ButtonActionPerformed

    private void Color4ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color4ButtonActionPerformed
        nextBrick.setColor(Color4Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color4ButtonActionPerformed

    private void Color5ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color5ButtonActionPerformed
        nextBrick.setColor(Color5Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color5ButtonActionPerformed

    private void Color6ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color6ButtonActionPerformed
        nextBrick.setColor(Color6Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color6ButtonActionPerformed

    private void Color7ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color7ButtonActionPerformed
        nextBrick.setColor(Color7Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color7ButtonActionPerformed

    private void Color8ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Color8ButtonActionPerformed
        nextBrick.setColor(Color8Button.getBackground());
        activeColor = nextBrick.getColor();
    }//GEN-LAST:event_Color8ButtonActionPerformed

    private void returnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnMenuActionPerformed
        if(modified){
            if(JOptionPane.showConfirmDialog(returnMenu, "Some changes might not have been saved. Discard changes and return to title?", "Discard changes?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                mainMenu.setVisible(true);
                this.dispose();    
            }
        }
        else{
            mainMenu.setVisible(true);
            this.dispose();
            }
    }//GEN-LAST:event_returnMenuActionPerformed

    private void bgColorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgColorMenuActionPerformed
        Color temp = JColorChooser.showDialog(this, "Select background color:", null);
        if(temp != null){
            setColoredBackground(temp);
        }
    }//GEN-LAST:event_bgColorMenuActionPerformed

    private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuActionPerformed
        Collections.sort(bricks);
        if(saveFile == null){
            saveAsMenuActionPerformed(evt);
        }
        else{
            writeLevelToFile();
            JOptionPane.showMessageDialog(returnMenu, "Level \""+saveFile.getName()+"\" saved successfully!");
            modified = false;
        }
    }//GEN-LAST:event_saveMenuActionPerformed

    private void openMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuActionPerformed
        JFileChooser fileChooser = new JFileChooser("Select file to open:");
        fileChooser.setCurrentDirectory(new File("Levels"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Breakout Level", new String[]{"BOL"}));
        if(modified){
            if(JOptionPane.showConfirmDialog(returnMenu, "Some changes might not have been saved. Discard changes and open new level?", "Discard changes?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                switch(fileChooser.showOpenDialog(this)){
                    case JFileChooser.APPROVE_OPTION:
                        saveFile = fileChooser.getSelectedFile();
                        loadLevelFromFile();
                        break;
                    case JFileChooser.CANCEL_OPTION:
                    case JFileChooser.ERROR_OPTION:
                        break;
                    default:
                }
            }
        }
        else{
            switch(fileChooser.showOpenDialog(this)){
                case JFileChooser.APPROVE_OPTION:
                    saveFile = fileChooser.getSelectedFile();
                    loadLevelFromFile();
                    break;
                case JFileChooser.CANCEL_OPTION:
                case JFileChooser.ERROR_OPTION:
                    break;
                default:
            }
        }
    }//GEN-LAST:event_openMenuActionPerformed

    private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuActionPerformed
        Collections.sort(bricks);
            JFileChooser fileChooser = new JFileChooser("Select save location");
            fileChooser.setCurrentDirectory(new File("Levels"));
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Breakout Level", new String[]{"bol"}));
            switch(fileChooser.showSaveDialog(this)){
                case JFileChooser.APPROVE_OPTION:
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    if(!filePath.endsWith(".bol")){
                        filePath = filePath.concat(".bol");
                    }
                    saveFile = new File(filePath);
                    if(saveFile.exists()){
                        switch(JOptionPane.showConfirmDialog(returnMenu, "File \""+saveFile.getName() + "\" already exists. Do you wish to overwrite?", "Overwrite?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)){
                            case JOptionPane.YES_OPTION:
                                writeLevelToFile();
                                break;
                            case JOptionPane.NO_OPTION:
                                break;
                            default:
                                
                        }
                    }
                    else{
                        writeLevelToFile();
                        JOptionPane.showMessageDialog(returnMenu, "Level \""+saveFile.getName()+"\" saved successfully!");
                        modified = false;
                    }
                    break;
                case JFileChooser.CANCEL_OPTION:
                case JFileChooser.ERROR_OPTION:
                    break;
                default:
            }
    }//GEN-LAST:event_saveAsMenuActionPerformed

    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuActionPerformed
        
        if(modified){
            if(JOptionPane.showConfirmDialog(returnMenu, "Some changes might not have been saved. Discard changes and create new level?", "Discard changes?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION){
                bricks = new ArrayList<>();
                setColoredBackground(Color.WHITE);
                saveFile = null;
            }
        }
        else{
            bricks = new ArrayList<>();
            setColoredBackground(Color.WHITE);
            saveFile = null;
        }
    }//GEN-LAST:event_newMenuActionPerformed

    private void bgImageMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgImageMenuActionPerformed
        JFileChooser fileChooser = new JFileChooser("Select an image:");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG, JPEG, PNG", new String[] {"JPG", "JPEG", "PNG"}); 
        fileChooser.setFileFilter(filter);
        
        switch(fileChooser.showOpenDialog(this)){
            case JFileChooser.APPROVE_OPTION:
                try {
                    BufferedImage newbg = ImageIO.read(fileChooser.getSelectedFile());
                    Graphics2D g2d = (Graphics2D) background.getGraphics();
                    g2d.drawImage(newbg, AffineTransform.getScaleInstance(background.getWidth(null)/(newbg.getWidth()*1.0), background.getHeight(null)/(newbg.getHeight()*1.0)), null);
                    g2d.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(LevelEditor.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            case JFileChooser.ERROR_OPTION:
                break;
            default:
                
        }
    }//GEN-LAST:event_bgImageMenuActionPerformed

    private void MoreColorsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoreColorsButtonActionPerformed
        Color temp = JColorChooser.showDialog(this, "Select background color:", activeColor);
        if(temp != null){
            nextBrick.setColor(temp);
            activeColor = nextBrick.getColor();
        }
    }//GEN-LAST:event_MoreColorsButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Color1Button;
    private javax.swing.JButton Color2Button;
    private javax.swing.JButton Color3Button;
    private javax.swing.JButton Color4Button;
    private javax.swing.JButton Color5Button;
    private javax.swing.JButton Color6Button;
    private javax.swing.JButton Color7Button;
    private javax.swing.JButton Color8Button;
    private javax.swing.JToolBar ColorsToolbar;
    private javax.swing.JButton MoreColorsButton;
    private javax.swing.JMenuItem bgColorMenu;
    private javax.swing.JMenuItem bgImageMenu;
    private javax.swing.JMenu bgMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JMenuItem newMenu;
    private javax.swing.JMenuItem openMenu;
    private javax.swing.JMenuItem returnMenu;
    private javax.swing.JMenuItem saveAsMenu;
    private javax.swing.JMenuItem saveMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        final long delay = 10;
        
        while(true){
            beforeTime = System.currentTimeMillis();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = delay - timeDiff;
            if(sleep < 0)
                sleep = 2L;
            try{
                Thread.sleep(sleep);
            }
            catch(InterruptedException e){
                System.out.println("Thred interrupted!");
            }
        }
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
    private class MouseAdapter implements MouseListener, MouseMotionListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            modified = true;
            if(SwingUtilities.isLeftMouseButton(e)){
                if(isWithinLimits(nextBrick.getPosition()) && ! spaceIsOccupied(nextBrick)){
                    bricks.add(nextBrick);
                    nextBrick = new Brick(GameBoundaries.RIGHT, GameBoundaries.BOTTOM);
                    nextBrick.setColor(activeColor);
                }
            }
            else if(SwingUtilities.isRightMouseButton(e)){
                if(isWithinLimits(nextBrick.getPosition())){
                    removeBrick(nextBrick);
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
            mouseClicked(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
//            if(hasFocus()){
                snapToBrickGrid(e.getPoint());
//            }
        }
        
    }
}
