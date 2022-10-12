/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.geom.Ellipse2D;

/**
 *
 * @author Camelia
 */
public class PointControle implements java.io.Serializable {
    private Mesure x;
    private Mesure y;
    private String nom;
    private boolean active = false;
    private Ellipse2D.Double area;

    public PointControle(Mesure x, Mesure y, String nom) {
        this.x = x;
        this.y = y;
        this.nom = nom;
    }
    

    public Mesure getX() {
        return x;
    }

    public void setX(Mesure x) {
        this.x = x;
    }

    public Mesure getY() {
        return y;
    }

    public void setY(Mesure y) {
        this.y = y;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Ellipse2D.Double getArea() {
        return area;
    }

    public void setArea(Ellipse2D.Double area) {
        this.area = area;
    }
    
    
}
