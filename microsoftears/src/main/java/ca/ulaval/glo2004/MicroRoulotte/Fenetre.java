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
public class Fenetre extends OuvertureLateral{
    private Mesure distanceFromFront;
    private Mesure distanceFromToit;

    public Fenetre() {
        super(new MesureImperial(6), new MesureImperial(6), Color.ORANGE, "Fenetre");
        
        distanceFromFront = new MesureImperial(8);
        distanceFromToit = new MesureImperial(12);
    }

    public Mesure getDistanceFromFront() {
        return distanceFromFront;
    }

    public void setDistanceFromFront(Mesure distanceFromFront) {
        this.distanceFromFront = distanceFromFront;
    }
    
    public Mesure getDistanceFromToit() {
        return distanceFromToit;
    }

    public void setDistanceFromToit(Mesure distanceFromToit) {
        this.distanceFromToit = distanceFromToit;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        super.switchMesureTo(switchTo);
        
        distanceFromFront = distanceFromFront.switchMesure();
        distanceFromToit = distanceFromToit.switchMesure();
    }
}
