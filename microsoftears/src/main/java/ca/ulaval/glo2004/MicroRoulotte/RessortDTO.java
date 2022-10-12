/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Gabriel
 */
import java.awt.Color;

public class RessortDTO{
    
    private final Mesure longueur;
    private final double force;
    private final Mesure positionSurMur;
    private final Mesure positionSurHayon;
    private final boolean affiche;
    
    private final Color afficheCouleur;
    
    private final double hauteurHayonEnPouce;
    private final double centrePoint;
    private final double extendedLength;
    private final double stokeLength;
    private final String noMcMaster;

    public RessortDTO(Mesure longueur,double force,Mesure positionSurMur,Mesure positionSurHayon,boolean affiche,Color afficheCouleur,double hauteurHayonEnPouce,double centrePoint,double extendedLength ,double strokeLength,String noMcMaster) {
        this.longueur = longueur;
        this.force = force;
        this.positionSurMur = positionSurMur;
        this.positionSurHayon = positionSurHayon;
        this.affiche = affiche;
        this.afficheCouleur = afficheCouleur;
        this.hauteurHayonEnPouce = hauteurHayonEnPouce;
        this.centrePoint = centrePoint;
        this.extendedLength = extendedLength;
        this.stokeLength = strokeLength;
        this.noMcMaster = noMcMaster;
    }

    
    
    public Mesure getLongeur() {
        return longueur;
    }

    public double getForce() {
        return force;
    }

    public Mesure getPositionSurMur() {
        return positionSurMur;
    }

    public Mesure getPositionSurHayon() {
        return positionSurHayon;
    }

    public boolean isAffiche() {
        return affiche;
    }
    
    
    
    public Color getAfficheCouleur(){
        return this.afficheCouleur;
    }

    public Double getHauteurHayonEnPouce() {
        return hauteurHayonEnPouce;
    }

    public double getCentrePoint() {
        return centrePoint;
    }

    public double getStokeLength() {
        return stokeLength;
    }

    public double getExtendedLength() {
        return extendedLength;
    }

    public String getNoMcMaster() {
        return noMcMaster;
    }
    
    
    
}
