/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.UI;

import ca.ulaval.glo2004.MicroRoulotte.Fichier;
import ca.ulaval.glo2004.MicroRoulotte.MicroRoulotteAfficheur;
import ca.ulaval.glo2004.MicroRoulotte.MicroRoulotteController;
import ca.ulaval.glo2004.MicroRoulotte.ProfilBezier;
import ca.ulaval.glo2004.MicroRoulotte.Zoom;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Robot;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import java.awt.Rectangle;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.awt.image.ImageFilter;

import javax.swing.JFileChooser;
/**
 *
 * @author Camelia
 */
public class DrawingPanel extends JComponent {
    private MicroRoulotteController controller;
    private MicroRoulotteAfficheur afficheur;

    public DrawingPanel(MicroRoulotteController controller) {
        this.controller = controller;
        
        //this.setPreferredSize(new Dimension(800,600));
        afficheur = new MicroRoulotteAfficheur(this.controller, new Zoom(13, new Point(0,0)));
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        afficheur.drawMicroRoulotte(g, new Dimension(this.getWidth(),this.getHeight())); 
    }
    
    public void zoom(int rotation, int amount, Point center){
        // If wheel rotation value is a negative it means rotate up, while
        // positive value means rotate down
        if (rotation < 0) {
            afficheur.setZoom(amount, center);
        } else {
            afficheur.setZoom(-(amount), center);
        }
    }
    
    public void saveImage(File file) throws Exception{

            Dimension dimension = new Dimension((int)this.getPreferredSize().getWidth(),(int)this.getPreferredSize().getHeight());
            BufferedImage image = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_BYTE_BINARY);
            Graphics g = image.createGraphics();
            this.paint(g);
            Fichier.exportPlanJPG(image,file);

   

    }
}
