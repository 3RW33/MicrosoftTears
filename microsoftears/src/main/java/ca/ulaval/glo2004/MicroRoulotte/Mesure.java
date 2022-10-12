/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Camelia
 */
public interface Mesure {
    
    public int convertToPixel();
    public Mesure add(MesureImperial mesure);
    public Mesure add(Mesure mesure);
    public Mesure sub(Mesure mesure);
    public String getString();
    public Mesure switchMesure();
    public float getValueAsFloat();
    public Mesure inverse();
}
