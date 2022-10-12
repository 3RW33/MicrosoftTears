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
public class Humain extends AideDesign{
    
    private Mesure distanceBoutPlancher;

    public Humain() {
        super(new MesureImperial(45), new MesureImperial(6), new Color(232, 190, 172), "Humain");
        
        distanceBoutPlancher = new MesureImperial(0);
    }

    public Mesure getDistanceBoutPlancher() {
        return distanceBoutPlancher;
    }

    public void setDistanceBoutPlancher(Mesure distanceBoutPlancher) {
        this.distanceBoutPlancher = distanceBoutPlancher;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        super.switchMesureTo(switchTo);
        
        distanceBoutPlancher = distanceBoutPlancher.switchMesure();
    }
    
    
}
