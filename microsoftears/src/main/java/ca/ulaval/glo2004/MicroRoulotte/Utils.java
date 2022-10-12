/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author fauch
 */
public class Utils {
    public static final float INCH_TO_MM = 25.4f;

    
    public static int ppcm(int a, int b){
        return (a * b) / pgcd(a, b);
    }
    
    public static int pgcd(int a, int b){
        if (b == 0) {
            return a;
        } else {
            return (pgcd(b, a % b));
        }
    }
}
