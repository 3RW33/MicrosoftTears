/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author Camelia
 */
public class MesureMetrique implements Mesure {
    private double value;

    public MesureMetrique(double value) {
        this.value = value;
    }
    
    public MesureMetrique(String mesure) throws Exception{
        this.value = Double.valueOf(mesure);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    @Override
    public int convertToPixel(){
        return (int) Math.round(value * (Zoom.amount / Utils.INCH_TO_MM));
    }

    @Override
    public Mesure add(MesureImperial mesure) {
        MesureMetrique toAdd = (MesureMetrique) mesure.switchMesure();
        return new MesureMetrique(value + toAdd.getValue());
    }
    @Override
    public Mesure add(Mesure mesure) {
        MesureMetrique toAdd = (MesureMetrique) mesure;
        return new MesureMetrique(value + toAdd.getValue());
    }
    @Override
    public Mesure sub(Mesure mesure) {
        MesureMetrique toAdd = (MesureMetrique) mesure;
        return new MesureMetrique(value - toAdd.getValue());
    }
    
    @Override
    public String getString() {
        return Double.toString(value);
    }

    @Override
    public Mesure switchMesure() {
        double feet = value / Utils.INCH_TO_MM;
        BigDecimal bd = new BigDecimal(feet).setScale(4, RoundingMode.HALF_EVEN);
        feet = bd.doubleValue();
      
        int entier = (int)feet;
        String feetAsString = Double.toString(feet);
        String decimalsAsString = feetAsString.substring(feetAsString.indexOf(".") + 1);
        
        int decimalsAsInt = (int) Double.valueOf(decimalsAsString).floatValue();
        int power = (int) Math.pow(10, decimalsAsString.length());
        int pgcd = Utils.pgcd(decimalsAsInt, power);
        
        int num = decimalsAsInt / pgcd;
        int denum = power / pgcd;
        
        try{
            return new MesureImperial(entier, num, denum);
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public float getValueAsFloat() {
        return (float)value;
    }
    
    @Override
    public MesureMetrique inverse(){

        return new MesureMetrique(-this.value);

    }
}
