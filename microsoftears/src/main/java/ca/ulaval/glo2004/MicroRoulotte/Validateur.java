/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Gabriel
 */

import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Validateur {
    
    
    public static void validerMicroRoulotte(MicroRoulotte microRoulotte) throws Exception{
        
        
        
        validerPlancher(microRoulotte);
    }
    
    public static void validerProfil(){
    
    }
    
    public static void validerPlancher(MicroRoulotte microRoulotte) throws Exception{
        
           
            if(microRoulotte.getPlancher().getMargeArriere().convertToPixel() > microRoulotte.getLargeur().convertToPixel() || microRoulotte.getPlancher().getMargeAvant().convertToPixel() > microRoulotte.getLargeur().convertToPixel()){
                throw new Exception("Marge arrière et marge avant ne peuvent être plus grand que la largeur de la roulotte");
            }
        
    }
    
    public static void validerToit(){
    
    }
    
    public static void validerHayon(){
    
    }
    
    public static void validerPoutreArriere(){
    
    }
    
}
