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
public class GrilleAideDTO {
    
    private final Mesure mesure;
    private final Color couleur;
    private final Boolean isAffiche;

    public GrilleAideDTO(Mesure mesure, Color couleur, Boolean isAffiche) {
        this.mesure = mesure;
        this.couleur = couleur;
        this.isAffiche = isAffiche;
    }
    
    
    public Mesure getMesure() {
        return mesure;
    }

    public Color getCouleur() {
        return couleur;
    }


    public Boolean isAffiche() {
        return isAffiche;
    }
    

    

}
