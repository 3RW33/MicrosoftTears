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
public class OuvertureLateral extends Element{
    protected Mesure hauteur;
    protected Mesure largeur;


    public OuvertureLateral(Mesure hauteur, Mesure largeur, Color color, String nom) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.setAfficheCouleur(color);
        this.setCurrentCouleur(color);
        this.setNom(nom);
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public void setHauteur(Mesure hauteur) {
        this.hauteur = hauteur;
    }

    public Mesure getLargeur() {
        return largeur;
    }

    public void setLargeur(Mesure largeur) {
        this.largeur = largeur;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        hauteur = hauteur.switchMesure();
        largeur = largeur.switchMesure();
    }
    
}
