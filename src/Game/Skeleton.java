package Game;


import java.awt.Color;
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
        desktop.add(new MainMenu(desktop));
        
        setContentPane(desktop);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(inset, inset, 800+hInset, 600+vInset);
        setVisible(true);
    }
    
    public static void main(String[] args){
        Skeleton s = new Skeleton();
    }
}
