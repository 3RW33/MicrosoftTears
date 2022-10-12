/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Point;

/**
 *
 * @author Camelia
 */
public class Zoom {
    public static double amount;
    private Point center;

    public Zoom(int amount, Point center) {
        this.amount = amount;
        this.center = center;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
    
    
}
