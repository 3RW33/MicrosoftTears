/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.util.ArrayList;

/**
 *
 * @author jole-
 */
public class Ellipse implements java.io.Serializable{
    private Mesure positionX;
    private Mesure positionY;
    private Mesure hauteur;
    private Mesure largeur;
    private int coin;

    public int getCoin() {
        return coin;
    }

    public Ellipse(Mesure positionX, Mesure positionY, Mesure hauteur, Mesure largeur, int coin) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.coin = coin;
    }

    public Ellipse(int positionX, int positionY, int hauteur, int largeur, int coin) {
        this.positionX = new MesureImperial(positionX);
        this.positionY = new MesureImperial(positionY);
        this.hauteur = new MesureImperial(hauteur);
        this.largeur = new MesureImperial(largeur);
        this.coin = coin;
    }

    public Mesure getPositionX() {
        return positionX;
    }

    public void setPositionX(Mesure positionX) {
        this.positionX = positionX;
    }

    public void setPositionXOffset(Mesure positionX, Mesure offsetFrom) {
        double offsetX = positionX.convertToPixel() - offsetFrom.convertToPixel();
        this.positionX = new MesureImperial(offsetX);
    }

    public Mesure getPositionY() {
        return positionY;
    }

    public void setPositionYOffset(Mesure positionY, Mesure offsetFrom) {
        double offsetY = positionY.convertToPixel() - offsetFrom.convertToPixel();
        this.positionY = new MesureImperial(offsetY);
    }

    public void setPositionY(Mesure positionY) {
        this.positionY = positionY;
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public void setHauteur(Mesure hauteur) {
        this.hauteur = hauteur;
    }

    public Mesure getLargeur() {
        return largeur;
    }

    public void setLargeur(Mesure largeur) {
        this.largeur = largeur;
    }
    
    public void switchMesure(){
        positionX = positionX.switchMesure();
        positionY = positionY.switchMesure();
        hauteur = hauteur.switchMesure();
        largeur = largeur.switchMesure();
    }
}
