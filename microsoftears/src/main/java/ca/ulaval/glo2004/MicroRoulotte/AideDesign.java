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
public class AideDesign extends Element{
    private Mesure largeur;
    private Mesure hauteur;

    public AideDesign(Mesure largeur, Mesure hauteur, Color color, String nom) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.setAfficheCouleur(color);
        this.setCurrentCouleur(color);
        this.setNom(nom);
    }

    public Mesure getLargeur() {
        return largeur;
    }

    public void setLargeur(Mesure largeur) {
        this.largeur = largeur;
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public void setHauteur(Mesure hauteur) {
        this.hauteur = hauteur;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        largeur = largeur.switchMesure();
        hauteur = hauteur.switchMesure();
    }
}
