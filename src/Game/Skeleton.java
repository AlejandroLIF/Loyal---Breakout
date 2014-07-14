package Game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
public class Skeleton extends JFrame{
    JDesktopPane desktop;
    
    public Skeleton(){
        super("Loyal Breakout");
        int inset = 50;
        int hInset = 6;
        int vInset = 28;
        desktop = new JDesktopPane();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        desktop.add(new MainMenu(desktop, this));
        
        setContentPane(desktop);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(inset, inset, 800+hInset, 600+vInset);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args){
        Skeleton s = new Skeleton();
        File levels = new File("Levels");
        if(!levels.exists()){
            levels.mkdir();
        }
    }
}
