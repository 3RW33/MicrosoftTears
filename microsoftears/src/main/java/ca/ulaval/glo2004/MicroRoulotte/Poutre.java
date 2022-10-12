/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Jeremy
 */
public class Poutre extends Element implements java.io.Serializable{
    private Mesure largeur;
    private Mesure hauteur;
    private Mesure positionX;
    private Mesure positionY;

    public Poutre(Mesure posX, Mesure posY) {
        largeur = new MesureImperial(2);
        hauteur = new MesureImperial(2);
        positionX = posX;
        positionY = posY;
        
        super.setNom("PoutreArriere");
        super.setAfficheCouleur(Color.MAGENTA);
        super.setCurrentCouleur(super.getAfficheCouleur());
    }
    
    public Poutre(PoutreDto dto){
        largeur = dto.getLargeur();
        hauteur = dto.getHauteur();
        positionX = dto.getPositionX();
        positionY = dto.getPositionY();
        super.setNom(dto.getNom());
        super.setParent(dto.getParent());
        super.setAfficheCouleur(dto.getAfficheCouleur());
        super.setCurrentCouleur(dto.getCurrentCouleur());
    }
   

    public Mesure getLargeur() {
        return largeur;
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public void setLargeur(Mesure largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(Mesure hauteur) {
        this.hauteur = hauteur;
    }

    public void setPositionX(Mesure positionX) {
        this.positionX = positionX;
    }

    public Mesure getPositionY() {
        return positionY;
    }

    public void setPositionY(Mesure positionY) {
        this.positionY = positionY;
    }

    public Mesure getPositionX() {
        return positionX;
    } 

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        largeur = largeur.switchMesure();
        hauteur = hauteur.switchMesure();
        positionX = positionX.switchMesure();
        positionY = positionY.switchMesure();
    }
    
    
}
