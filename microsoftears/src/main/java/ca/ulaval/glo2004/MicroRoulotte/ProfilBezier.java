/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Camelia
 */
public class ProfilBezier implements Profil,java.io.Serializable{
    ArrayList<PointControle> listePointsControles;
    private boolean affichePoints = false;

    public ProfilBezier(ArrayList<PointControle> listePointsControles) {
        this.listePointsControles = listePointsControles;
    }

    public ArrayList<PointControle> getListePointsControles() {
        return listePointsControles;
    }

    public void setListePointsControles(ArrayList<PointControle> listePointsControles) {
        this.listePointsControles = listePointsControles;
    }
    
    public void setPointControle(int i, PointControle newPoint){
        this.listePointsControles.set(i, newPoint);
    }

    public boolean isAffichePoints() {
        return affichePoints;
    }

    public void setAffichePoints(boolean affichePoints) {
        this.affichePoints = affichePoints;
    }
    
    @Override
    public void switchMesure() {
        
    }
    
    
}
