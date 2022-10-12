/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;

/**
 *
 * @author Camelia
 */
public class MurSeparateur extends Element{
    private Mesure epaisseurDado;
    private Mesure distanceDuPlancher;
    private Mesure distancePoutreArriere;

    public MurSeparateur(Mesure epaisseurDado, Mesure distanceDuPlancher, Mesure distancePoutreArriere) {
        this.setNom("Mur");
        this.setAfficheCouleur(new Color(56,84,125));
        this.epaisseurDado = epaisseurDado;
        this.distanceDuPlancher = distanceDuPlancher;
        this.distancePoutreArriere = distancePoutreArriere;
    }

    public Mesure getEpaisseurDado() {
        return epaisseurDado;
    }

    public void setEpaisseurDado(Mesure epaisseurDado) {
        this.epaisseurDado = epaisseurDado;
    }

    public Mesure getDistanceDuPlancher() {
        return distanceDuPlancher;
    }

    public void setDistanceDuPlancher(Mesure distanceDuPlancher) {
        this.distanceDuPlancher = distanceDuPlancher;
    }

    public Mesure getDistancePoutreArriere() {
        return distancePoutreArriere;
    }

    public void setDistancePoutreArriere(Mesure distancePoutreArriere) {
        this.distancePoutreArriere = distancePoutreArriere;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        epaisseurDado = epaisseurDado.switchMesure();
        distanceDuPlancher = distanceDuPlancher.switchMesure();
        distancePoutreArriere = distancePoutreArriere.switchMesure();
    }
    
    
}
