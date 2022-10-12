/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Polygon;

/**
 *
 * @author fauch
 */
public class MicroRoulotteDto {
    private final Mesure hauteur;
    private final Mesure largeur;

    private final Plancher plancher;
    private final Hayon hayon;
    private final Poutre poutreArriere;
    private final Porte porte;
    private final Fenetre fenetre;
    private final Matelas matelas;
    private final Humain humain;
    
    private final Profil profil;
    public final Polygon profilPolygon;
    public final String nom;

    public MicroRoulotteDto(Mesure hauteur, Mesure largeur, Plancher plancher, Hayon hayon, Poutre poutreArriere, Porte porte, 
            Fenetre fenetre, Matelas matelas, Humain humain, Profil profil, Polygon profilPolygon, String nom) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.plancher = plancher;
        this.hayon = hayon;
        this.poutreArriere = poutreArriere;
        this.profil = profil;
        this.profilPolygon = profilPolygon;
        this.nom = nom;
        this.porte = porte;
        this.fenetre = fenetre;
        this.matelas = matelas;
        this.humain = humain;
    }

    public Mesure getHauteur() {
        return hauteur;
    }

    public Mesure getLargeur() {
        return largeur;
    }

    public Plancher getPlancher() {
        return plancher;
    }

    public Hayon getHayon() {
        return hayon;
    }

    public Poutre getPoutreArriere() {
        return poutreArriere;
    }

    public Profil getProfil() {
        return profil;
    }

    public Polygon getProfilPolygon() {
        return profilPolygon;
    }

    public String getNom() {
        return nom;
    }

    public Porte getPorte() {
        return porte;
    }

    public Fenetre getFenetre() {
        return fenetre;
    }

    public Matelas getMatelas() {
        return matelas;
    }

    public Humain getHumain() {
        return humain;
    }
    
    
}
