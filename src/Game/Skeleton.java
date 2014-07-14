package Game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public static void main(String[] args) throws URISyntaxException, IOException{
        Skeleton s = new Skeleton();
        File levels = new File("Levels");
        System.out.println(levels.getAbsolutePath());
        if(!levels.exists()){
            System.out.println("Create Levels Folder");
            levels.mkdir();
            File temp = new File(new URI(Skeleton.class.getResource("/Levels").toString()));
            File[] templevels = temp.listFiles();
            System.out.println(templevels.length);
            for(File f : templevels){
                f.createNewFile();
                InputStream is = Skeleton.class.getResourceAsStream("/Levels/"+f.getName());
                OutputStream os;
                byte[] buffer = new byte[1000];
                int readBytes;
                try{
                    os = new FileOutputStream(new File("Levels/"+f.getName()));
                    while((readBytes = is.read(buffer)) > 0){
                        os.write(buffer, 0, readBytes);
                    }
                    is.close();
                    os.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex){
                    Logger.getLogger(Skeleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
