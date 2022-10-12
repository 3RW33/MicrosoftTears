/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

import com.google.common.util.concurrent.ForwardingListenableFuture;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author Camelia
 */
public class MicroRoulotte extends Element implements java.io.Serializable{
    
    private Mesure hauteur;
    private Mesure largeur;
    private Mesure positionX;
    private Mesure positionY;

    private Plancher plancher;
    private Hayon hayon;
    private Poutre poutreArriere;
    private MurSeparateur murSeparateur;
    private Toit toit;
    private Porte porte;
    private Fenetre fenetre;
    private Matelas matelas;
    private Humain humain;
    
    public Profil profil;
    public Polygon profilPolygon;
    
    //Murs
    private Panneau panneaux[] = new Panneau[3];
    
    public MicroRoulotte() throws Exception {
        
        int h = 48;
        int l = 85;
        int x = 10;
        int y = 10;

        this.hauteur = new MesureImperial(h);
        this.largeur = new MesureImperial(l);
        this.positionX = new MesureImperial(x);
        this.positionY = new MesureImperial(y);
        
        plancher = new Plancher();
        hayon = new Hayon();      
        poutreArriere = new Poutre(new MesureImperial(0), new MesureImperial(0));
        
        toit = new Toit(new MesureImperial(2));
        porte = new Porte();

        this.setAfficheCouleur(new Color(160,120,90));
        this.setCurrentCouleur(super.getAfficheCouleur());
        this.setNom("Micro Roulotte");
        
        plancher.setParent(this);
        hayon.setParent(this);
        poutreArriere.setParent(this);
        porte.setParent(this);
        

        ArrayList<Ellipse> ellipses = new ArrayList<Ellipse>();

        Ellipse nw = new Ellipse(0,0,60,70,0);
        Ellipse ne = new Ellipse(0,0,50,50,1);
        Ellipse sw = new Ellipse(0,0,30,30,2);
        Ellipse se = new Ellipse(0,0,15,15,3);

        ellipses.add(nw);
        ellipses.add(ne);
        ellipses.add(sw);
        ellipses.add(se);

        
        
        
        this.profil = new ProfilClassique(ellipses);
        
        
        panneaux[0] = new Panneau();
        panneaux[1] = new Panneau();
        panneaux[2] = new Panneau();
        
        //Panneau interne
        panneaux[0].setEpaisseur(new MesureImperial(0,1,8));
        panneaux[0].setNom("Panneau Interne");
        //Panneau millieu
        panneaux[1].setEpaisseur(new MesureImperial(0,3,4));
        panneaux[1].setNom("Panneau Millieu");
        //Panneau externe
        panneaux[2].setEpaisseur(new MesureImperial(0,1,4));
        panneaux[2].setNom("Panneau Externe");
        
    }
    
    public MicroRoulotte(MicroRoulotteDto dto){
        hauteur = dto.getHauteur();
        largeur = dto.getLargeur();
        plancher = dto.getPlancher();
        hayon = dto.getHayon();
        poutreArriere = dto.getPoutreArriere();
        profil = dto.getProfil();
        profilPolygon = dto.getProfilPolygon();
        porte = dto.getPorte();
        fenetre = dto.getFenetre();
        matelas = dto.getMatelas();
        humain = dto.getHumain();
        super.setNom(dto.getNom());
        
        
        plancher.setParent(this);
        hayon.setParent(this);
        poutreArriere.setParent(this);
        porte.setParent(this);
        fenetre.setParent(this);
    }

    public Humain getHumain() {
        return humain;
    }

    public void setHumain(Humain humain) {
        this.humain = humain;
    }

    
    
    public Matelas getMatelas() {
        return matelas;
    }

    public void setMatelas(Matelas matelas) {
        this.matelas = matelas;
    }
    
    public Fenetre getFenetre() {
        return fenetre;
    }

    public void setFenetre(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    public Porte getPorte() {
        return porte;
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public Mesure getLargeur() {
        return largeur;
    }
    
    public Plancher getPlancher(){
        
        return plancher;
    }

    public Poutre getPoutreArriere() {
        return poutreArriere;
    }

    public Hayon getHayon() {
        return hayon;
    }
    
    public Profil getProfil(){
        return profil;
    }
    public Mesure getPositionX() {
        return positionX;
    }

    public Mesure getPositionY() {
        return positionY;
    }


    public void setPositionX(Mesure positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Mesure positionY) {
        this.positionY = positionY;
    }
    
    public void setHauteur(Mesure hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Mesure longeur) {
        this.largeur = longeur;
    }
    
    public void setPlancher(Plancher plancher){
        this.plancher = plancher;
    }

    public MurSeparateur getMurSeparateur() {
        return murSeparateur;
    }

    public void setMurSeparateur(MurSeparateur murSeparateur) {
        this.murSeparateur = murSeparateur;
    }

    public Toit getToit() {
        return toit;
    }

    public void setToit(Toit toit) {
        this.toit = toit;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        hauteur = hauteur.switchMesure();
        largeur = largeur.switchMesure();
        positionX = positionX.switchMesure();
        positionY = positionY.switchMesure();
        profil.switchMesure();
    }

    

    public Panneau getPanneauInterne() {
        return panneaux[0];
    }
    
    public Panneau getPanneauMillieu() {
        return panneaux[1];
    }
    
    public Panneau getPanneauExterne() {
        return panneaux[2];
    }
    
    
}
