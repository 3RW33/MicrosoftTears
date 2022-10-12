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
public class Porte extends OuvertureLateral{

    private Mesure distanceDeBoutToit;

    public Porte() {
        super(new MesureImperial(36), new MesureImperial(24), Color.LIGHT_GRAY, "Porte");
        
        distanceDeBoutToit = new MesureImperial(6);
    }

    public Mesure getDistanceDeBoutToit() {
        return distanceDeBoutToit;
    }

    public void setDistanceDeBoutToit(Mesure distanceDeBoutToit) {
        this.distanceDeBoutToit = distanceDeBoutToit;
    }
    
    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        super.switchMesureTo(switchTo);
        
        distanceDeBoutToit = distanceDeBoutToit.switchMesure();
    }
}
