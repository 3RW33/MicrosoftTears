/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;



/**
 *
 * @author Camelia
 */
public class MicroRoulotteAfficheur {
    private final MicroRoulotteController controller;
    private final Zoom zoom;

    
    
    
    public MicroRoulotteAfficheur(MicroRoulotteController controller, Zoom zoom) {
        this.controller = controller;
        this.zoom = zoom;
    }
    
    public void drawMicroRoulotte(Graphics g, Dimension panelDimension){
        
        
        
        Graphics2D g2d = (Graphics2D)g;
        
        drawGrilleDaide(g2d,panelDimension);
        
        drawProfil(g2d);
       
        
        drawPlancher(g2d);
        drawPoutre(g2d);
        drawHayon(g2d);

        drawMurSeparateur(g2d);
        drawToit(g2d);
        drawMatelas(g2d);
        drawHumain(g2d);
        drawPorte(g2d);
        drawFenetre(g2d);
        drawRessort(g2d);
        
   
        drawMurExterne(g2d);
        drawMurMillieu(g2d);
        drawMurInterne(g2d);
    }
    
    public void setZoom(double amount, Point center){
        this.zoom.setAmount(this.zoom.getAmount() + amount);
        this.zoom.setCenter(center);
    }
    
    private void drawPlancher(Graphics2D g2d){
        

        int roulottewidth = controller.getMicroRoulotte().getLargeur().convertToPixel();
        int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();
        int roulotteX = controller.getMicroRoulotte().getPositionX().convertToPixel();
        int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();
        
        double plancherStartX;
        double plancherEndX = 0;
        if(controller.isProfilClassique()){
            plancherStartX = roulotteX+(controller.getEllipses().get(2).getLargeur().convertToPixel()/2);
            plancherEndX = roulotteX+roulottewidth-(controller.getEllipses().get(3).getLargeur().convertToPixel()/2);
        } else {
            plancherStartX = roulotteX;
            plancherEndX = roulotteX + roulottewidth;
        }
        
        
        
        double plancherStartY = roulotteY+roulotteHeight;
        double plancherEndY = roulotteY+roulotteHeight;

        int width = (int)((plancherEndX - controller.getPlancher().getMargeAvant().convertToPixel()) - (plancherStartX + controller.getPlancher().getMargeArriere().convertToPixel()));
        int height = controller.getPlancher().getEpaisseur().convertToPixel();
        int positionX = (int)(plancherStartX + controller.getPlancher().getMargeArriere().convertToPixel());
        int positionY = (int)(plancherStartY - height);
        
        Rectangle plancher = new Rectangle(positionX, positionY, width, height);

        Area plancherArea = new Area(plancher);
        controller.getMicroRoulotte().getPlancher().setArea(plancherArea);
        controller.addElement(controller.getMicroRoulotte().getPlancher());
        if(controller.getPlancher().isAffiche())
        {
            g2d.setColor(new Color(0, 81, 255));
            g2d.fill(plancherArea);
        }
    
    }

    private void drawProfil(Graphics2D g2d){
        if(controller.isProfilClassique()){
            drawProfilClassique(g2d);
        } else {
            drawProfilBezier(g2d);
        }
    }
    
    private void drawProfilBezier(Graphics2D g2d){
        int roulotteWidth = controller.getMicroRoulotte().getLargeur().convertToPixel();
        int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();

        int roulotteX = controller.getMicroRoulotte().getPositionX().convertToPixel();
        int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();
        
        ProfilBezier profil = (ProfilBezier) controller.getMicroRoulotte().getProfil();
        ArrayList<PointControle> p = profil.getListePointsControles();
        
        Path2D.Double profilBezier = new Path2D.Double();
        
        profilBezier.moveTo(roulotteX, roulotteY + roulotteHeight);
        profilBezier.curveTo(p.get(0).getX().convertToPixel(), p.get(0).getY().convertToPixel(), p.get(1).getX().convertToPixel(), p.get(1).getY().convertToPixel(), roulotteX+roulotteWidth, roulotteY+roulotteHeight);
        profilBezier.closePath();
        
        Area profilArea = new Area(new Rectangle(roulotteX,roulotteY,roulotteWidth,roulotteHeight));
        Area profilCutout = new Area(profilBezier);
        
        profilArea.intersect(profilCutout);
        
                
        g2d.setColor(controller.getMicroRoulotte().getAfficheCouleur());
        g2d.fill(profilArea);
        
        
        controller.getMicroRoulotte().setArea(profilArea);
        controller.addElement(controller.getMicroRoulotte());
        
        // Afficher point de contrôle
        g2d.setColor(new Color(255,167,0));
        Ellipse2D.Double pointNW = new Ellipse2D.Double(p.get(0).getX().convertToPixel()-5, p.get(0).getY().convertToPixel()-5, 10.0, 10.0);
        p.get(0).setArea(pointNW);
        p.get(0).setActive(true);
        Ellipse2D.Double pointNE = new Ellipse2D.Double(p.get(1).getX().convertToPixel()-5, p.get(1).getY().convertToPixel()-5, 10.0, 10.0);
        p.get(1).setArea(pointNE);
        p.get(1).setActive(true);
        
        controller.addPointControle(p.get(0));
        controller.addPointControle(p.get(1));
        
        g2d.fill(pointNW);
        g2d.fill(pointNE);
        
    }
    
   private void drawProfilClassique(Graphics2D g2d){

      
       
       
        Mesure roulotteWidth = controller.getMicroRoulotte().getLargeur();
        Mesure roulotteHeight = controller.getMicroRoulotte().getHauteur();

        Mesure roulotteX = controller.getMicroRoulotte().getPositionX();
        Mesure roulotteY = controller.getMicroRoulotte().getPositionY();

        Rectangle microroulotte = new Rectangle(roulotteX.convertToPixel(), roulotteY.convertToPixel(), roulotteWidth.convertToPixel(), roulotteHeight.convertToPixel());

        Area microroulotteArea = new Area(microroulotte);


        // Affichage des ellipses
        ProfilClassique profil = (ProfilClassique) controller.getMicroRoulotte().getProfil();
        ArrayList<Ellipse> ellipses = profil.getListeEllipses();

        Rectangle cutout = new Rectangle(0,0,0,0);
        
        for (Ellipse ellipse:ellipses) {
            
            int hauteur = ellipse.getHauteur().convertToPixel();
            int largeur = ellipse.getLargeur().convertToPixel();
            int halfHauteur = hauteur / 2;
            int halfLargeur = largeur / 2;
            int ellipseX = 0;
            int ellipseY = 0;
            int buffer = 10;
            switch(ellipse.getCoin()){
                case 0:
                    ellipseX = roulotteX.convertToPixel()-ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()-ellipse.getPositionY().convertToPixel();
                    cutout = new Rectangle(ellipseX-buffer,ellipseY-buffer,halfLargeur+buffer,halfHauteur+buffer);
                    
                    break;
                case 1:
                    ellipseX = roulotteX.convertToPixel()+roulotteWidth.convertToPixel()-largeur+ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()+ellipse.getPositionY().convertToPixel();                    
                    cutout = new Rectangle(ellipseX + halfLargeur+buffer,ellipseY-buffer,halfLargeur+buffer,halfHauteur+buffer);
                    break;
                case 2:
                    ellipseX = roulotteX.convertToPixel()+ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()+roulotteHeight.convertToPixel()-hauteur+ellipse.getPositionY().convertToPixel();                   
                    cutout = new Rectangle(ellipseX-buffer,ellipseY + halfHauteur,halfLargeur+buffer,halfHauteur+buffer);
                    break;
                case 3:
                    ellipseX = roulotteX.convertToPixel()+roulotteWidth.convertToPixel()-largeur+ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()+roulotteHeight.convertToPixel()-hauteur+ellipse.getPositionY().convertToPixel();                   
                    cutout = new Rectangle(ellipseX + halfLargeur+buffer,ellipseY + halfHauteur+buffer,halfLargeur+buffer,halfHauteur+buffer);
                    break;
            }

            Ellipse2D.Double ellipse2D = new Ellipse2D.Double(
                    ellipseX,
                    ellipseY,
                    largeur,
                    hauteur
            );

            Area cutoutArea = new Area(cutout);
            Area ellipseArea = new Area(ellipse2D);           
            cutoutArea.subtract(ellipseArea);
            microroulotteArea.subtract(cutoutArea);
        }
        
        if(controller.getMicroRoulotte().isAffiche()){
        g2d.setColor(controller.getMicroRoulotte().getAfficheCouleur());
        g2d.fill(microroulotteArea);
        }
        
        controller.getMicroRoulotte().setArea(microroulotteArea);
        
        controller.addElement(controller.getMicroRoulotte());
        
        
        for (Ellipse ellipse:ellipses) {
            
            int hauteur = ellipse.getHauteur().convertToPixel();
            int largeur = ellipse.getLargeur().convertToPixel();
            int halfHauteur = hauteur / 2;
            int halfLargeur = largeur / 2;
            int ellipseX = 0;
            int ellipseY = 0;
            int buffer = 10;
            PointControle p = new PointControle(new MesureImperial(0),new MesureImperial(0),"");
            switch(ellipse.getCoin()){
                case 0:
                    ellipseX = roulotteX.convertToPixel()-ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()-ellipse.getPositionY().convertToPixel();
                    p = new PointControle(new MesureImperial(ellipseX+halfLargeur),new MesureImperial(ellipseY+halfHauteur),"ellipseNW");
                    break;
                case 1:
                    ellipseX = roulotteX.convertToPixel()+roulotteWidth.convertToPixel()-largeur+ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()+ellipse.getPositionY().convertToPixel();
                    p = new PointControle(new MesureImperial(ellipseX+halfLargeur),new MesureImperial(ellipseY+halfHauteur),"ellipseNE");
                    break;
                case 2:
                    ellipseX = roulotteX.convertToPixel()+ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()+roulotteHeight.convertToPixel()-hauteur+ellipse.getPositionY().convertToPixel();
                    p = new PointControle(new MesureImperial(ellipseX+halfLargeur),new MesureImperial(ellipseY+halfHauteur),"ellipseSW");                   
                    break;
                case 3:
                    ellipseX = roulotteX.convertToPixel()+roulotteWidth.convertToPixel()-largeur+ellipse.getPositionX().convertToPixel();
                    ellipseY = roulotteY.convertToPixel()+roulotteHeight.convertToPixel()-hauteur+ellipse.getPositionY().convertToPixel();
                    p = new PointControle(new MesureImperial(ellipseX+halfLargeur),new MesureImperial(ellipseY+halfHauteur),"ellipseSE");                   
                    break;
            }

            Ellipse2D.Double ellipse2D = new Ellipse2D.Double(
                    ellipseX,
                    ellipseY,
                    largeur,
                    hauteur
            );


            Area ellipseArea = new Area(ellipse2D);
            
            g2d.setColor(Color.YELLOW);

            ProfilClassique profilClassique = (ProfilClassique)controller.getProfil();
            if(profilClassique.isAfficheEllipses()){
                
                g2d.draw(ellipseArea);
                

                g2d.setColor(new Color(255,167,0));
                
                Ellipse2D.Double point = new Ellipse2D.Double(ellipseX+halfLargeur-5, ellipseY+halfHauteur-5, 10.0, 10.0);
                p.setArea(point);
                p.setActive(true);
                
                g2d.fill(point);

                controller.addPointControle(p);
            }
                
            
        }
        
        
        
    }
   
   public void drawHayon(Graphics2D g2d){
       
        

        Area microroulotteArea = controller.getMicroRoulotte().getArea();
        Area hayon = (Area)microroulotteArea.clone();
        Mesure width = controller.getMicroRoulotte().getLargeur();
        Mesure height = controller.getMicroRoulotte().getHauteur();



        
        int roulottewidth = controller.getMicroRoulotte().getLargeur().convertToPixel();
        int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();
        int roulotteX = controller.getMicroRoulotte().getPositionX().convertToPixel();
        int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();
        
        int hayonEpaisseur = controller.getMicroRoulotte().getHayon().getEpaisseur().convertToPixel();
        
        // Scale at center
        
        //Affine transform pour le scale
        AffineTransform atScale = new AffineTransform();
        
        //Affine transform pour remettre à la bonne place àpres le scale
        AffineTransform atTranslate = new AffineTransform();
        
        //Affine transform pour le tasser de l'epaisseur du hayon
        AffineTransform atTranslateEpaisseurHayon = new AffineTransform();
        
        Area hayonCutout = microroulotteArea.createTransformedArea(atTranslate);
        
        int x = hayonEpaisseur;
        int y = hayonEpaisseur;
        
        double scaleX = ((double)(roulottewidth-(hayonEpaisseur * 2))/(double)roulottewidth);
        double scaleY = ((double)(roulotteHeight-(hayonEpaisseur * 2))/(double)roulotteHeight);
        
        //Aller chercher la position du hayonCutout avant que le scale sois fais
        double testX1 = hayonCutout.getBounds2D().getX();
        double testY1 = hayonCutout.getBounds2D().getY();
        
        //Faire le scale
        atScale.setToScale(scaleX, scaleY);
        hayonCutout.transform(atScale);
        
        //Aller chercher les valeurs apres le scale
        double testX2 = hayonCutout.getBounds2D().getX();
        double testY2 = hayonCutout.getBounds2D().getY();
        
        
        //Remettre a la bonne place
        atTranslate.translate(Math.abs(testX2-testX1), Math.abs(testY2-testY1));
        hayonCutout.transform(atTranslate);
        
        //Mettre à la bonne place avec l'épaisseur du hayon
        atTranslateEpaisseurHayon.translate(x, y);
        hayonCutout.transform(atTranslateEpaisseurHayon);
        hayon.subtract(hayonCutout);


        double plancherStartX;
        if(controller.isProfilClassique()){
            plancherStartX = roulotteX+(controller.getEllipses().get(2).getLargeur().convertToPixel()/2);
        } else {
            plancherStartX = roulotteX;
        }
        
        Area poutreArea = controller.getPoutreArriere().getArea();
        double poutreStartX = poutreArea.getBounds().getX();

        
        // Retirer bas
        int margeArriere = controller.getPlancher().getMargeArriere().convertToPixel();
        int distancePlancher = controller.getHayon().getDistanceDuPlancher().convertToPixel();
        int epaisseurPlancher = controller.getPlancher().getEpaisseur().convertToPixel();
        Rectangle cutout = new Rectangle((int)plancherStartX-distancePlancher+margeArriere,roulotteY + height.convertToPixel() - epaisseurPlancher,width.convertToPixel(),height.convertToPixel());
        Area cutoutArea = new Area(cutout);
        hayon.subtract(cutoutArea);
        
        if(controller.isProfilClassique()){
            cutout = new Rectangle((int)plancherStartX-distancePlancher+margeArriere,height.convertToPixel()/2,width.convertToPixel(),height.convertToPixel());
        } else {
            cutout = new Rectangle(roulotteX + hayonEpaisseur,roulotteY + height.convertToPixel() - hayonEpaisseur,width.convertToPixel(),height.convertToPixel());
        }

        cutoutArea = new Area(cutout);
        hayon.subtract(cutoutArea);
       
        
        int distancePoutre = controller.getHayon().getDistanceDePoutre().convertToPixel();
        
        if(!controller.isProfilClassique()){
            // Coutout pour le devant
            cutout = new Rectangle((int)poutreStartX,roulotteY,width.convertToPixel(),height.convertToPixel());
            cutoutArea = new Area(cutout);
            hayon.subtract(cutoutArea);
        }
        
        
        
        cutout = new Rectangle((int)poutreStartX-distancePoutre,roulotteY,width.convertToPixel(),height.convertToPixel()/2);
        cutoutArea = new Area(cutout);
        hayon.subtract(cutoutArea);
        
        // todo: Enlever rayon de courbure
        PathIterator pathIterator = hayon.getPathIterator(new AffineTransform());
        double[] coords = new double[6];

        double inter1X = 0;
        double inter1Y = 0;
        while (!pathIterator.isDone()) {
            int type = pathIterator.currentSegment(coords);

            if(type == PathIterator.SEG_MOVETO){

                inter1X = coords[0];
                inter1Y = coords[1];
            }

            pathIterator.next();
        }
        
        int rayonCourbure = controller.getHayon().getRayonDeCourbure().convertToPixel();
        Ellipse2D.Double cercleCourbure = new Ellipse2D.Double(inter1X,inter1Y,rayonCourbure*2,rayonCourbure*2);
        Area areaCourbure = new Area(cercleCourbure);

        if(controller.getHayon().isAffiche())
        {
        g2d.setColor(controller.getHayon().getAfficheCouleur());
        g2d.fill(hayon);
        }
        controller.getMicroRoulotte().getHayon().setArea(hayon);
        controller.addElement(controller.getMicroRoulotte().getHayon());
        
   }
   
   public void drawToit(Graphics2D g2d){
       
        

        Area microroulotteArea = controller.getMicroRoulotte().getArea();
        Area toit = (Area)microroulotteArea.clone();
        Mesure width = controller.getMicroRoulotte().getLargeur();
        Mesure height = controller.getMicroRoulotte().getHauteur();



        
        int roulottewidth = controller.getMicroRoulotte().getLargeur().convertToPixel();
        int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();
        int roulotteX = controller.getMicroRoulotte().getPositionX().convertToPixel();
        int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();
        
        int toitEpaisseur = controller.getToit().getEpaisseurDado().convertToPixel();
        
        // Scale at center
        
        //Affine transform pour le scale
        AffineTransform atScale = new AffineTransform();
        
        //Affine transform pour remettre à la bonne place àpres le scale
        AffineTransform atTranslate = new AffineTransform();
        
        //Affine transform pour le tasser de l'epaisseur du hayon
        AffineTransform atTranslateEpaisseurHayon = new AffineTransform();
        
        Area toitCutout = microroulotteArea.createTransformedArea(atTranslate);
        
        int x = toitEpaisseur;
        int y = toitEpaisseur;
        
        double scaleX = ((double)(roulottewidth-(toitEpaisseur * 2))/(double)roulottewidth);
        double scaleY = ((double)(roulotteHeight-(toitEpaisseur * 2))/(double)roulotteHeight);
        
        //Aller chercher la position du hayonCutout avant que le scale sois fais
        double testX1 = toitCutout.getBounds2D().getX();
        double testY1 = toitCutout.getBounds2D().getY();
        
        //Faire le scale
        atScale.setToScale(scaleX, scaleY);
        toitCutout.transform(atScale);
        
        //Aller chercher les valeurs apres le scale
        double testX2 = toitCutout.getBounds2D().getX();
        double testY2 = toitCutout.getBounds2D().getY();
        
        
        //Remettre a la bonne place
        atTranslate.translate(Math.abs(testX2-testX1), Math.abs(testY2-testY1));
        toitCutout.transform(atTranslate);
        
        //Mettre à la bonne place avec l'épaisseur du hayon
        atTranslateEpaisseurHayon.translate(x, y);
        toitCutout.transform(atTranslateEpaisseurHayon);
        
        
        toit.subtract(toitCutout);
        
        AffineTransform at2 = new AffineTransform();
        PathIterator path = microroulotteArea.getPathIterator(at2);

        int count = 0;
        Area areaStop = new Area();
        if(controller.getMurSeparateur() != null)
            areaStop = controller.getMurSeparateur().getArea();
        else
            areaStop = controller.getPoutreArriere().getArea();
        double murX = areaStop.getBounds().getX();
        double murWidth = areaStop.getBounds().getWidth();
        int murSeparateurEndX = (int)(murX+murWidth);
        int plancherMargeAvant = controller.getPlancher().getMargeAvant().convertToPixel();
        
        Rectangle cutout = new Rectangle(roulotteX,roulotteY,murSeparateurEndX-roulotteX,roulotteHeight);
        Area cutoutArea = new Area(cutout);
        toit.subtract(cutoutArea);
        
        
        Area plancherArea = controller.getPlancher().getArea();
        int plancherEndX = (int)(plancherArea.getBounds().getX() + plancherArea.getBounds().getWidth());
        int epaisseurPlancher = controller.getPlancher().getEpaisseur().convertToPixel();
        cutout = new Rectangle(roulotteX,roulotteY+roulotteHeight-epaisseurPlancher,plancherEndX-roulotteX+plancherMargeAvant,roulotteHeight);
        cutoutArea = new Area(cutout);
        toit.subtract(cutoutArea);
        
        if(controller.isProfilClassique()){
            cutout = new Rectangle(roulotteX,roulotteY+roulotteHeight/2,plancherEndX-roulotteX+plancherMargeAvant,roulotteHeight);
        } else {
            cutout = new Rectangle(roulotteX,roulotteY + roulotteHeight - toitEpaisseur,roulottewidth - toitEpaisseur,roulotteHeight);
        }
        cutoutArea = new Area(cutout);
        toit.subtract(cutoutArea);

        if(controller.getToit().isAffiche())
        {
        g2d.setColor(controller.getToit().getAfficheCouleur());
        g2d.fill(toit);
        }
        controller.getMicroRoulotte().getToit().setArea(toit);
        controller.addElement(controller.getMicroRoulotte().getToit());
        
   }
   
   public void drawPoutre(Graphics2D g2d){
       
           
            int roulottewidth = controller.getMicroRoulotte().getLargeur().convertToPixel();
            int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();
            int roulotteX = controller.getMicroRoulotte().getPositionX().convertToPixel();
            int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();

            int largeur = controller.getPoutreArriere().getLargeur().convertToPixel();
            int hauteur = controller.getPoutreArriere().getHauteur().convertToPixel();

            int positionX = controller.getPoutreArriere().getPositionX().convertToPixel();
           
            
            Rectangle poutreArriere;
            
            if(controller.isProfilClassique()){
                poutreArriere = new Rectangle((controller.getEllipses().get(0).getLargeur().convertToPixel()/2)-positionX,roulotteY,largeur,roulotteHeight/2);
            } else {
                poutreArriere = new Rectangle(roulotteX+(roulottewidth/2)-positionX,roulotteY,largeur,roulotteHeight/2);
            }
             
            
            Area poutreCutout = new Area(poutreArriere);
            Area microroulotteArea = controller.getMicroRoulotte().getArea();
            poutreCutout.intersect(microroulotteArea);
            
            PathIterator pathIterator = poutreCutout.getPathIterator(new AffineTransform());
            double[] coords = new double[6];
            double inter1X = poutreCutout.getBounds().getX() + poutreCutout.getBounds().getWidth();
            double inter1Y = poutreCutout.getBounds().getY();
            
            double inter2X = 0;
            double inter2Y = 0;
            
            boolean hasCurve = false;
            while (!pathIterator.isDone()) {
                int type = pathIterator.currentSegment(coords);
                
                if(type == PathIterator.SEG_CUBICTO){
                    
                    inter2X = coords[4];
                    inter2Y = coords[5];
                    hasCurve = true;
                }

                pathIterator.next();
            } 
            
            if(!hasCurve){
                inter2X = poutreCutout.getBounds().getX();
                inter2Y = poutreCutout.getBounds().getY();
            }

            // Placer coin poutre sur un des points
            poutreArriere = new Rectangle((int)inter2X,(int)inter2Y,largeur,hauteur);
            Area poutreArriereArea = new Area(poutreArriere);     
           
            // Calcul rotation pour atteindre le 2e point
            AffineTransform rotation = new AffineTransform();
            double slope1 = -((inter1Y-inter2Y)/(inter1X-inter2X));
            double slope2 = 0;
            double theta = Math.atan((slope2-slope1)/(1+(slope2*slope1)));
            
            rotation.rotate(theta,inter2X, inter2Y);
            poutreArriereArea.transform(rotation);

           if(controller.getPoutreArriere().isAffiche()){ 
                g2d.setColor(controller.getPoutreArriere().getAfficheCouleur());
                g2d.fill(poutreArriereArea);
           }
           controller.getPoutreArriere().setArea(poutreArriereArea);
           controller.addElement(controller.getPoutreArriere());
       
   }
   
   private void drawMatelas(Graphics2D g2d){
       if(controller.getMatelas() != null){
            Plancher plancher = controller.getPlancher();
            Matelas matelas = controller.getMatelas();
            int plancherFront = (int)(plancher.getArea().getBounds().getX() + plancher.getArea().getBounds().getWidth());
            int topPlancher = (int) plancher.getArea().getBounds().getY();

            int x = plancherFront - matelas.getLargeur().convertToPixel();
            int y = topPlancher - matelas.getHauteur().convertToPixel();

            Rectangle matelasRect = new Rectangle(x, y, matelas.getLargeur().convertToPixel(), matelas.getHauteur().convertToPixel());
            Area matelasArea = new Area(matelasRect);

            Area microroulotteArea = controller.getMicroRoulotte().getArea();
            matelasArea.intersect(microroulotteArea);

            if(controller.getMatelas().isAffiche()){
                g2d.setColor(controller.getMatelas().getAfficheCouleur());
                g2d.fill(matelasArea);
            }
            
            controller.getMatelas().setArea(matelasArea);
            controller.addElement(controller.getMatelas());
       }
   }
   
   public void drawHumain(Graphics2D g2d){
       if(controller.getHumain() != null){
           Plancher plancher = controller.getPlancher();
           Humain humain = controller.getHumain();
           
           int plancherFront = (int)(plancher.getArea().getBounds().getX() + plancher.getArea().getBounds().getWidth());
           
           int x = plancherFront - humain.getLargeur().convertToPixel() - humain.getDistanceBoutPlancher().convertToPixel();
           int y = controller.getMatelas() != null ? (int) (controller.getMatelas().getArea().getBounds().getY() - humain.getHauteur().convertToPixel())
                   :(int)(plancher.getArea().getBounds().getY() - humain.getHauteur().convertToPixel());
           
           Rectangle humainRect = new Rectangle(x, y, humain.getLargeur().convertToPixel(), humain.getHauteur().convertToPixel());
           Area humainArea = new Area(humainRect);
           
           Area microroulotteArea = controller.getMicroRoulotte().getArea();
           humainArea.intersect(microroulotteArea);
           
           if(controller.getHumain().isAffiche()){
               g2d.setColor(controller.getHumain().getAfficheCouleur());
               g2d.fill(humainArea);
           }
           
           controller.getHumain().setArea(humainArea);
           controller.addElement(controller.getHumain());
       }
   }
   
    private void drawMurSeparateur(Graphics2D g2d){

        if(controller.getMurSeparateur() != null && controller.getMurSeparateur().isAffiche()){
        
        
            int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();
            int plancherHeight = controller.getPlancher().getEpaisseur().convertToPixel();


            Area poutreArea = controller.getPoutreArriere().getArea();
            int poutreWidth = (int)poutreArea.getBounds().getWidth();
            int poutreX = (int)poutreArea.getBounds().getX();
            int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();

            int murSeparateurWidth = controller.getMurSeparateur().getEpaisseurDado().convertToPixel();
            int distancePoutre = controller.getMurSeparateur().getDistancePoutreArriere().convertToPixel();
            int distanceDuPlancher = controller.getMurSeparateur().getDistanceDuPlancher().convertToPixel();

            Rectangle murSeparateur = new Rectangle(poutreX+poutreWidth+distancePoutre,roulotteY,murSeparateurWidth,roulotteHeight-plancherHeight-distanceDuPlancher);
            Area murSeparateurArea = new Area(murSeparateur);

            Area microroulotteArea = controller.getMicroRoulotte().getArea();
            murSeparateurArea.intersect(microroulotteArea);

            g2d.setColor(controller.getMurSeparateur().getAfficheCouleur());
            g2d.fill(murSeparateurArea);

            controller.getMurSeparateur().setArea(murSeparateurArea);
        }

    }

    private void drawPorte(Graphics2D g2d){
        
            int roulotteHeight = controller.getMicroRoulotte().getHauteur().convertToPixel();
            int plancherHeight = controller.getPlancher().getEpaisseur().convertToPixel();
            int roulotteY = controller.getMicroRoulotte().getPositionY().convertToPixel();
            
            int porteWidth = controller.getPorte().getLargeur().convertToPixel();
            int porteHeight = controller.getPorte().getHauteur().convertToPixel();

            int boutToit = (int)controller.getToit().getArea().getBounds().getMinX();
            int portePositionX = boutToit + controller.getPorte().getDistanceDeBoutToit().convertToPixel();
            
            Rectangle porte = new Rectangle(portePositionX, roulotteY + (roulotteHeight - plancherHeight) - porteHeight, porteWidth, porteHeight);

            Area porteArea = new Area(porte);
            
            Area microroulotteArea = controller.getMicroRoulotte().getArea();
            porteArea.intersect(microroulotteArea);
            
            if(controller.getPorte().isAffiche()){
            g2d.setColor(controller.getPorte().getAfficheCouleur());
            g2d.fill(porteArea);
            }
            
            controller.getPorte().setArea(porteArea);
            controller.getMicroRoulotte().setArea(microroulotteArea);
            
        controller.addElement(controller.getPorte());
    }
    
    private void drawFenetre(Graphics2D g2d){
        if(controller.getFenetre() != null){
            Toit toit = controller.getToit();
            Fenetre fenetre = controller.getFenetre();
            int basToit = (int) (toit.getArea().getBounds().getY() + toit.getEpaisseurDado().convertToPixel());
            int front = (int)(toit.getArea().getBounds().getMaxX() - toit.getEpaisseurDado().convertToPixel());
            int distanceFromFront = fenetre.getDistanceFromFront().convertToPixel() + fenetre.getLargeur().convertToPixel();
            
            int x = front - distanceFromFront;
            int y = basToit + fenetre.getDistanceFromToit().convertToPixel();
            int height = fenetre.getHauteur().convertToPixel();
            int width = fenetre.getLargeur().convertToPixel();
            
            Rectangle fenetreRect = new Rectangle(x, y, width, height);
            Area fenetreArea = new Area(fenetreRect);
            
            Area microroulotteArea = controller.getMicroRoulotte().getArea();
            fenetreArea.intersect(microroulotteArea);
            
            if(controller.getFenetre().isAffiche()){
            g2d.setColor(fenetre.getAfficheCouleur());
            g2d.fill(fenetreArea);
            }
            
            controller.getFenetre().setArea(fenetreArea);
            controller.addElement(controller.getFenetre());
        }
    }
    
    private void drawGrilleDaide(Graphics2D g2d,Dimension panelDimension) {
        
        GrilleAideDTO grilleAide = controller.getGrilleAideDTO();
        
        if(grilleAide.isAffiche()){
            //Prendre la couleur de la grille daide
            g2d.setColor(grilleAide.getCouleur());

            //Dessiner les barres vertical
            int nombreDeBarreVertical = (int)(panelDimension.width / grilleAide.getMesure().convertToPixel());
            int pointX = 0;
            for(int x = 0; x < nombreDeBarreVertical; x++){
                pointX += grilleAide.getMesure().convertToPixel();
                g2d.drawLine(pointX, 0, pointX, panelDimension.height);
            }

            //Dessiner les barres horizontal
            int nombreDeBarreHorizontal = panelDimension.height / grilleAide.getMesure().convertToPixel();
            int pointY = 0;
            for(int y = 0; y < nombreDeBarreHorizontal; y++){
                pointY += grilleAide.getMesure().convertToPixel();
                g2d.drawLine(0, pointY,panelDimension.width , pointY);
            }
        
        }
    }
    
    private void drawRessort(Graphics2D g2d){
        RessortDTO ressortDTO = controller.getRessort();
        
        double hauteurHayonEnPouce = ressortDTO.getHauteurHayonEnPouce();
        double debutPoutreX = controller.getPoutreArriere().getArea().getBounds2D().getMinX();
        double debutPoutreY = controller.getPoutreArriere().getArea().getBounds2D().getMaxY();


        double diametre = (ressortDTO.getExtendedLength()*2)*Zoom.amount;
        int entreHayonEtPoutre = controller.getHayon().getDistanceDePoutre().convertToPixel()/2;


        Ellipse2D.Double ellipseInferieur = new Ellipse2D.Double((debutPoutreX-diametre/2)-(controller.getHayon().getDistanceDePoutre().convertToPixel()/2), debutPoutreY-(diametre/2), diametre,diametre);


        Area areaEllipse1 = new Area(ellipseInferieur);

        double centrePoint = ressortDTO.getCentrePoint()*Zoom.amount;


        Area areaEllipseInferieurPourDessiner = new Area(ellipseInferieur);


        double diametre2 = centrePoint;


        Ellipse2D.Double ellipseSuperieur = new Ellipse2D.Double((debutPoutreX-diametre2/2)-(controller.getHayon().getDistanceDePoutre().convertToPixel()/2), debutPoutreY-(diametre2/2), diametre2,diametre2);


        Area areaEllipse2 = new Area(ellipseSuperieur);

        if(controller.getSelectedItem() == "Ressort"){
            g2d.setColor(Color.black);
            g2d.draw(areaEllipseInferieurPourDessiner);
            g2d.draw(areaEllipse2);
        }


        Area areaHayon = controller.getHayon().getArea();

        areaEllipse1.intersect(areaHayon);


        areaEllipse1.subtract(areaEllipse2);



        float[] coords = new float[6];
        PathIterator pathIterator = areaEllipse1.getPathIterator(new AffineTransform());

        Point2D pointInferieur = new Point2D.Float();
        Point2D pointSuperieur = new Point2D.Float();


        int count = 0;
        while (!pathIterator.isDone()) {

            if(count == 5)
                pointInferieur.setLocation(coords[4]+1*zoom.amount,coords[5]+1*zoom.amount);
            if(count == 7)
                pointSuperieur.setLocation(coords[4]+1*zoom.amount,coords[5]+1*zoom.amount);

            //Si je lenleve ca pete
            switch (pathIterator.currentSegment(coords)) {
                case PathIterator.SEG_MOVETO:
                    //System.out.println(" M "+String.valueOf(coords[0])+" " + String.valueOf(coords[1]));

                    break;
                case PathIterator.SEG_LINETO:
                    //System.out.println(" L "+String.valueOf(coords[0])+" " + String.valueOf(coords[1]));
                    break;
                case PathIterator.SEG_QUADTO:
                    //System.out.println(" Q "+String.valueOf(coords[0])+" " + String.valueOf(coords[1]));
                    break;
                case PathIterator.SEG_CUBICTO:
                    //System.out.println(" C "+String.valueOf(coords[0])+" " + String.valueOf(coords[1])+ " " + String.valueOf(coords[2]) + " " + String.valueOf(coords[3])+ " " + String.valueOf(coords[4]) + " "+ String.valueOf(coords[5]));
                    break;
                case PathIterator.SEG_CLOSE:
                    break;
            }
            pathIterator.next();
            count++;
        }

        double epaisseurRessort = new MesureImperial(1).convertToPixel();


        Point2D pointInferieur2 = new Point2D.Float();
        Point2D pointSuperieur2 = new Point2D.Float();



        pointInferieur2.setLocation(pointInferieur.getX() +epaisseurRessort, pointInferieur.getY() + epaisseurRessort);
        pointSuperieur2.setLocation(pointSuperieur.getX() +epaisseurRessort, pointSuperieur.getY() + epaisseurRessort);


        Polygon ressortPolygon = new Polygon();


        ressortPolygon.addPoint((int)pointInferieur.getX(), (int)pointInferieur.getY());
        ressortPolygon.addPoint((int)pointSuperieur.getX(), (int)pointSuperieur.getY());
        ressortPolygon.addPoint((int)pointSuperieur2.getX(), (int)pointSuperieur2.getY());
        ressortPolygon.addPoint((int)pointInferieur2.getX(), (int)pointInferieur2.getY());


        Area areaRessort = new Area(ressortPolygon);

        if(ressortDTO.isAffiche()){
        g2d.setColor(ressortDTO.getAfficheCouleur());
        g2d.fill(areaRessort);
        }

        controller.setRessortArea(areaRessort);
        controller.addElement(controller.getMicroRoulotte().getHayon().getRessort());
        
        
    }
    
    private void drawMurExterne(Graphics2D g2d){
        
        Area areaMurExterne = new Area(controller.getMicroRoulotte().getArea());
        
        areaMurExterne.subtract(controller.getPorte().getArea());
        areaMurExterne.subtract(controller.getHayon().getArea());
        if(controller.getFenetre() != null)
            areaMurExterne.subtract(controller.getFenetre().getArea());
        
        controller.setPanneauExterneArea(areaMurExterne);
        
        if(controller.getPanneauExterne().isAffiche()){
            g2d.setColor(controller.getPanneauExterne().getAfficheCouleur());
            g2d.fill(areaMurExterne);
        }
        controller.addElement(controller.getPanneauExterne());
    }
    
    private void drawMurInterne(Graphics2D g2d){
        Area areaMurInterne = new Area(controller.getMicroRoulotte().getArea());
        
        areaMurInterne.subtract(controller.getPorte().getArea());
        areaMurInterne.subtract(controller.getHayon().getArea());
        areaMurInterne.subtract(controller.getPlancher().getArea());
        areaMurInterne.subtract(controller.getToit().getArea());
        areaMurInterne.subtract(controller.getPoutreArriere().getArea());
        if(controller.getFenetre() != null)
            areaMurInterne.subtract(controller.getFenetre().getArea());
        
        
        controller.setPanneauInterneArea(areaMurInterne);
        
        if(controller.getPanneauInterne().isAffiche()){
            g2d.setColor(controller.getPanneauInterne().getAfficheCouleur());
            g2d.fill(areaMurInterne);
        }
        
        controller.addElement(controller.getPanneauInterne());
        
    }
    
    private void drawMurMillieu(Graphics2D g2d){
        Area areaMurMillieu = new Area(controller.getMicroRoulotte().getArea());
        
        areaMurMillieu.subtract(controller.getPorte().getArea());
        areaMurMillieu.subtract(controller.getHayon().getArea());
        areaMurMillieu.subtract(controller.getPlancher().getArea());
        areaMurMillieu.subtract(controller.getToit().getArea());
        areaMurMillieu.subtract(controller.getPoutreArriere().getArea());
        if(controller.getFenetre() != null)
            areaMurMillieu.subtract(controller.getFenetre().getArea());
        
        controller.setPanneauMillieuArea(areaMurMillieu);
        
        if(controller.getPanneauMillieu().isAffiche()){
            g2d.setColor(controller.getPanneauInterne().getAfficheCouleur());
            g2d.fill(areaMurMillieu);
        }
        
        controller.addElement(controller.getPanneauMillieu());
        
    }
   
}
