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
public class Panneau extends Element{
    
    private Mesure epaisseur;
    
    public Panneau(){
        setAffiche(false);
        this.setAfficheCouleur(new Color(160,120,90));
    }
    
    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        epaisseur = epaisseur.switchMesure();
    }

    public Mesure getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(Mesure epaisseur) {
        this.epaisseur = epaisseur;
    }
    
    
    
}
