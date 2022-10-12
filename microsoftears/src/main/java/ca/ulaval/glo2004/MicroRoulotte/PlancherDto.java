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
public class PlancherDto {
    private final Mesure epaisseur;
    private final Mesure margeAvant;
    private final Mesure margeArriere;
    private final String nom;
    private final MicroRoulotte parent;
    private final Color afficheCouleur;
    private final Color currentCouleur;

    public PlancherDto(Mesure epaisseur, Mesure margeAvant, Mesure margeArriere, String nom, MicroRoulotte parent, Color afficheCouleur, Color currentCouleur) {
        this.epaisseur = epaisseur;
        this.margeAvant = margeAvant;
        this.margeArriere = margeArriere;
        this.nom = nom;
        this.parent = parent;
        this.afficheCouleur = afficheCouleur;
        this.currentCouleur = currentCouleur;
    }

    public Mesure getEpaisseur() {
        return epaisseur;
    }

    public Mesure getMargeAvant() {
        return margeAvant;
    }

    public Mesure getMargeArriere() {
        return margeArriere;
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
