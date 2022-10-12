/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;
import java.awt.Polygon;


/**
 *
 * @author fauch
 */
public class ElementDto {
    private final Polygon polygon;
    private final Color afficheCouleur;

    public ElementDto(Polygon polygon, Color afficheCouleur) {
        this.polygon = polygon;
        this.afficheCouleur = afficheCouleur;
    }

    public Polygon getPolygon() {
        return polygon;
    }
    
    public Color getAfficheCouleur(){
        return this.afficheCouleur;
    }
    
    
}
