/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.List;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.Graphics;
import java.awt.Robot;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Gabriel
 */
public class Fichier {

    public Fichier() {
    }

    public static Graphics g;
    
    public static void ouvrirProjet(MicroRoulotteController controller,File fileToOpen) throws Exception{
        MicroRoulotteController e = null;
        if(fileToOpen.getPath().contains(".ser"))
        {
              try {
                 FileInputStream fileIn = new FileInputStream(fileToOpen.getPath());
                 ObjectInputStream in = new ObjectInputStream(fileIn);
                 e = (MicroRoulotteController) in.readObject();
                 in.close();
                 fileIn.close();
              } catch (IOException i) {
                 throw new Exception("Une erreur est survenue lors de la lecture du fichier");
              } catch (ClassNotFoundException c) {
                 System.out.println("Employee class not found");
                 c.printStackTrace();
              }
          }
        else
            throw new Exception("Doit contenir l'extension .ser");
          
          controller.setMicroRoulotte(e.getMicroRoulotte());
          controller.setGrilleDaide(e.getGrilleAide());
          
          System.out.println(e.getPlancher().getArea());
          
    }
    
    public static void sauvegarderProjet(MicroRoulotteController controller,File fileToSave) throws Exception{
        
        if(fileToSave.getPath().contains(".ser"))
        {
            try {
                FileOutputStream fileOut = new FileOutputStream(fileToSave.getPath());
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(controller);
                out.close();
                fileOut.close();
                System.out.printf("C://Users/Utilisateur/Desktop/testSerialisable.ser");
            } catch (IOException i) {
             i.printStackTrace();
            }
        }
        else
            throw new Exception("Doit contenir l'extension .ser");
    }
    
    public static void  exportPlanJPG(BufferedImage image,File file) throws Exception{
      
     ImageIO.write(image, "jpg", file);
        
    }
    
    public static void exportPlanSVG(File file, List<Element> elements,MicroRoulotteController controller) throws Exception{
        
        
        
        
 
 

        Writer fileWriter = new FileWriter(file);

            fileWriter.write(
            
            "<?xml version=\"1.0\" standalone=\"no\"?>\n" +
"<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n" +
"  \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n" 

                    
            
            );
        
            fileWriter.write("<svg xmlns='http://www.w3.org/2000/svg' version='1.1'>\n" +
"	<title>svgOutput</title>\n" +
"	<desc>made with tinkercad</desc>");
            
            //Create area for microroulotte
            
            for(Element e : elements ){
            
                
                if(e.isAffiche() && e.getNom() != "Porte"){
                
                
                    
                    
            Area area = new Area(e.getArea());
            
            if(e.getNom()== "Micro Roulotte"){
                
        area.subtract(controller.getMicroRoulotte().getHayon().getArea());
        area.subtract(controller.getMicroRoulotte().getHayon().getRessort().getArea());
        area.subtract(controller.getMicroRoulotte().getPoutreArriere().getArea());
        area.subtract(controller.getMicroRoulotte().getPlancher().getArea());
        area.subtract(controller.getMicroRoulotte().getToit().getArea());
        area.subtract(controller.getMicroRoulotte().getPorte().getArea());
      
            }
            
            float[] coords = new float[6];
    PathIterator pathIterator = area.getPathIterator(new AffineTransform());
    
    
    
    fileWriter.write("<path d='");
    
    while (!pathIterator.isDone()) {
        switch (pathIterator.currentSegment(coords)) {
        case PathIterator.SEG_MOVETO:
            fileWriter.write(" M "+String.valueOf(coords[0])+" " + String.valueOf(coords[1]));
            
            break;
        case PathIterator.SEG_LINETO:
            fileWriter.write(" L "+String.valueOf(coords[0])+" " + String.valueOf(coords[1]));
            break;
        case PathIterator.SEG_QUADTO:
            fileWriter.write(" Q "+String.valueOf(coords[0])+" " + String.valueOf(coords[1]));
            break;
        case PathIterator.SEG_CUBICTO:
            fileWriter.write(" C "+String.valueOf(coords[0])+" " + String.valueOf(coords[1])+ " " + String.valueOf(coords[2]) + " " + String.valueOf(coords[3])+ " " + String.valueOf(coords[4]) + " "+ String.valueOf(coords[5]));
            break;
        case PathIterator.SEG_CLOSE:
            break;
        }
        pathIterator.next();
    }    
            int r = e.getAfficheCouleur().getRed();
            int g = e.getAfficheCouleur().getGreen();
            int b = e.getAfficheCouleur().getBlue();
            String hex = String.format("#%02x%02x%02x", r, g, b);  
            fileWriter.write("' fill='"+hex+"' />\n");
             //Fin svg
            
            }
                
                }
            fileWriter.write("</svg>\n");
           
            
        fileWriter.close();
        
    }

    public static void setG(Graphics g) {
        Fichier.g = g;
    }
    
    
    
}
