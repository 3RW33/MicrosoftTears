/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Area;

/**
 *
 * @author Gabriel
 */
public class Plancher extends Element implements java.io.Serializable{
    
    private Mesure epaisseur;
    private Mesure margeAvant;
    private Mesure margeArriere;

    public Plancher() throws Exception{
        
        epaisseur = new MesureImperial(1,1,4);
        
        margeAvant = new MesureImperial(1);
        margeArriere = new MesureImperial(3);
        
        this.setAfficheCouleur(Color.blue);
        this.setCurrentCouleur(this.getAfficheCouleur());
        this.setNom("Plancher");
    }

    public Plancher(PlancherDto dto){
        epaisseur = dto.getEpaisseur();
        margeAvant = dto.getMargeAvant();
        margeArriere = dto.getMargeArriere();
        super.setNom(dto.getNom());
        super.setParent(dto.getParent());
        super.setAfficheCouleur(dto.getAfficheCouleur());
        super.setCurrentCouleur(dto.getCurrentCouleur());
    }
    

    public void setEpaisseur(Mesure epaisseur){
        this.epaisseur = epaisseur;
    }

    public void setMargeAvant(Mesure margeAvant){
        this.margeAvant = margeAvant;
    }

    public void setMargeArriere(Mesure margeArriere){
        this.margeArriere = margeArriere;
    }

    public Mesure getEpaisseur(){
        return epaisseur;
    }

    public Mesure getMargeAvant(){
        return margeAvant;
    }

    public Mesure getMargeArriere(){
        return margeArriere;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        epaisseur = epaisseur.switchMesure();
        margeAvant = margeAvant.switchMesure();
        margeArriere = margeArriere.switchMesure();
    }
}
