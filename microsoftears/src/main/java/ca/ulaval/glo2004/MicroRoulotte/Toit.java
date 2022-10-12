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
public class Toit extends Element{
    private Mesure epaisseurDado;

    public Toit(Mesure epaisseurDado) {
        this.setNom("Toit");
        this.setAfficheCouleur(new Color(156,51,255));
        this.epaisseurDado = epaisseurDado;
    }

    public Mesure getEpaisseurDado() {
        return epaisseurDado;
    }

    public void setEpaisseurDado(Mesure epaisseurDado) {
        this.epaisseurDado = epaisseurDado;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        epaisseurDado = epaisseurDado.switchMesure();
    }
    
    
}
