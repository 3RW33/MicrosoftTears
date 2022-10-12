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
public class PoutreDto {
    private final Mesure largeur;
    private final Mesure hauteur;
    private final Mesure positionX;
    private final Mesure positionY;
    private final String nom;
    private final MicroRoulotte parent;
    private final Color afficheCouleur;
    private final Color currentCouleur;

    public PoutreDto(Mesure largeur, Mesure hauteur, Mesure positionX, Mesure positionY, String nom, MicroRoulotte parent, 
            Color afficheCouleur, Color currentCouleur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.positionX = positionX;
        this.positionY = positionY;
        this.nom = nom;
        this.parent = parent;
        this.afficheCouleur = afficheCouleur;
        this.currentCouleur = currentCouleur;
    }

    public Mesure getLargeur() {
        return largeur;
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public Mesure getPositionX() {
        return positionX;
    }

    public Mesure getPositionY() {
        return positionY;
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
