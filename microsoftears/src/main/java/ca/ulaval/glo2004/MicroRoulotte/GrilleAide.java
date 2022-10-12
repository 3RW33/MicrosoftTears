/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;

/**
 *
 * @author Gabriel
 */
public class GrilleAide implements java.io.Serializable{
    
    private Mesure mesure;
    private Color couleur = Color.BLACK;
    private Boolean isAffiche = true;
    
    
    public Mesure getMesure() {
        return mesure;
    }

    public void setMesure(Mesure mesure) {
        this.mesure = mesure;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Boolean isAffiche() {
        return isAffiche;
    }

    public void isAffiche(Boolean affiche) {
        this.isAffiche = affiche;
    }
    
    public void switchMesure(){
        mesure = mesure.switchMesure();
    }
}
