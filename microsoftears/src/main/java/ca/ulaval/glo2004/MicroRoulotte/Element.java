


package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Gabriel
 */

import java.awt.Color;
import java.awt.geom.Area;

public abstract class Element implements java.io.Serializable{
    private String nom;
    protected transient Area area;
    protected MicroRoulotte parent;
    private Boolean estSelectionne = false;
    private Color afficheCouleur;
    private Color currentCouleur;
    private boolean affiche = true;

    public String getNom(){
        return nom;
    }
    
    public Area getArea() {
        return area;
    }

    public Color getAfficheCouleur() {
        return afficheCouleur;
    }

    public void setAfficheCouleur(Color afficheCouleur) {
        this.afficheCouleur = afficheCouleur;
    }

    public Color getCurrentCouleur() {
        return currentCouleur;
    }

    public void setCurrentCouleur(Color currentCouleur) {
        this.currentCouleur = currentCouleur;
    }
    
    public void setNom(String nom){
        this.nom = nom;
    }
    
    public void setArea(Area area){
        this.area = area;
    }
   
    public void setSelection(boolean selected) {
        this.estSelectionne = selected;
    }
    
    public boolean estSelectionne() {
        return estSelectionne;
    }

    public void setParent(MicroRoulotte parent) {
        this.parent = parent;
    }

    public boolean isAffiche() {
        return affiche;
    }

    public void setAffiche(boolean affiche) {
        this.affiche = affiche;
    }
    
    //TODO: Verify abstract class doesnt break everything
    public abstract void switchMesureTo(MesureEnum switchTo);
    
}
