/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;

/**
 *
 * @author fauch
 */
public class HayonDto{
    private final Mesure epaisseur;
    private final Mesure distanceDePoutre;
    private final Mesure distanceDuPlancher;
    private final Mesure traitDeScie;
    private final Mesure rayonDeCourbure;
    private final Ressort ressort;
    private final String nom;
    private final MicroRoulotte parent;
    private final Color afficheCouleur;
    private final Color currentCouleur;

    public HayonDto(Mesure epaisseur, Mesure distanceDePoutre, Mesure distanceDuPlancher, Mesure traitDeScie, Mesure rayonDeCourbure, 
            Ressort ressort, String nom, MicroRoulotte parent, Color afficheCouleur, Color currentCouleur) {
        this.epaisseur = epaisseur;
        this.distanceDePoutre = distanceDePoutre;
        this.distanceDuPlancher = distanceDuPlancher;
        this.traitDeScie = traitDeScie;
        this.rayonDeCourbure = rayonDeCourbure;
        this.ressort = ressort;
        this.nom = nom;
        this.parent = parent;
        this.afficheCouleur = afficheCouleur;
        this.currentCouleur = currentCouleur;
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

    public Mesure getRayonDeCourbure() {
        return rayonDeCourbure;
    }

    public Ressort getRessort() {
        return ressort;
    }

    public String getNom() {
        return nom;
    }

    public MicroRoulotte getParent() {
        return parent;
    }

    public Color getAfficheCouleur() {
        return afficheCouleur;
    }

    public Color getCurrentCouleur() {
        return currentCouleur;
    }
    
    
}
