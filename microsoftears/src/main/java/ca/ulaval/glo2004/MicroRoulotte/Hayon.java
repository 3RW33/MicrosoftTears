/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 *
 * @author Gabriel
 */
public class Hayon extends Element implements java.io.Serializable{
    
    private Mesure epaisseur;
    private Mesure distanceDePoutre;
    private Mesure distanceDuPlancher;
    private Mesure traitDeScie;
    private double poids = 50.0;
    private Mesure rayonDeCourbure;
    private Ressort ressort;

    
    public Hayon() {
        try{
            this.epaisseur = new MesureImperial(2);
            this.distanceDePoutre = new MesureImperial(0,5,16);
            this.distanceDuPlancher = new MesureImperial(0,3,8);
            this.traitDeScie = new MesureImperial(0,1,16);
            this.rayonDeCourbure = new MesureImperial(2,3,8);
        }
        catch (Exception e){
        
        }
        
        ressort = new Ressort();
        this.setAfficheCouleur(Color.cyan);
        this.setCurrentCouleur(this.getAfficheCouleur());
        this.setNom("Hayon");
    }
    
    public Hayon(HayonDto dto){
        epaisseur = dto.getEpaisseur();
        distanceDePoutre = dto.getDistanceDePoutre();
        distanceDuPlancher = dto.getDistanceDuPlancher();
        traitDeScie = dto.getTraitDeScie();
        rayonDeCourbure = dto.getRayonDeCourbure();
        ressort = dto.getRessort();
        super.setParent(dto.getParent());
        super.setNom(dto.getNom());
        super.setAfficheCouleur(dto.getAfficheCouleur());
        super.setCurrentCouleur(dto.getCurrentCouleur());
    }

    public Mesure getEpaisseur() {
        return epaisseur;
    }

    public Mesure getDistanceDePoutre() {
        return distanceDePoutre;
    }

    public Mesure getDistanceDuPlancher() {
        return distanceDuPlancher;
    }

    public Mesure getTraitDeScie() {
        return traitDeScie;
    }

    public double getPoids() {
        return poids;
    }

    public Mesure getRayonDeCourbure() {
        return rayonDeCourbure;
    }

    public Ressort getRessort() {
        return ressort;
    }

    public void setEpaisseur(Mesure epaisseur) {
        this.epaisseur = epaisseur;
    }

    public void setDistanceDePoutre(Mesure distanceDePoutre) {
        this.distanceDePoutre = distanceDePoutre;
    }

    public void setDistanceDuPlancher(Mesure distanceDuPlancher) {
        this.distanceDuPlancher = distanceDuPlancher;
    }

    public void setTraitDeScie(Mesure traitDeScie) {
        this.traitDeScie = traitDeScie;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setRayonDeCourbure(Mesure rayonDeCourbure) {
        this.rayonDeCourbure = rayonDeCourbure;
    }

    public void setRessort(Ressort ressort) {
        this.ressort = ressort;
    } 

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        epaisseur = epaisseur.switchMesure();
        distanceDePoutre = distanceDePoutre.switchMesure();
        distanceDuPlancher = distanceDuPlancher.switchMesure();
        traitDeScie = traitDeScie.switchMesure();
        rayonDeCourbure = rayonDeCourbure.switchMesure();
    }
    
    
    
}
