/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Camelia
 */

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.geom.Area;
import java.util.ListIterator;
import java.util.ArrayList;

import java.io.*;
import javax.swing.JComponent;


public class MicroRoulotteController implements Serializable{
    
    private List<Element> elements;
    private List<PointControle> pointsControle;
    private PointControle currentPointControle;
    private MesureEnum currentMesure;
    
    private MicroRoulotte microRoulotte;

    
    private GrilleAide grilleAide;
    public Histoire histoire;
    
    public MicroRoulotteController(){
        try {
            this.microRoulotte = new MicroRoulotte();
            elements = new LinkedList<>();
            pointsControle = new LinkedList<>();
        }
        catch(Exception e) {
          
        }
        
        
        grilleAide = new GrilleAide();
        grilleAide.setMesure(new MesureImperial(12));
        
        currentMesure = MesureEnum.Imperial;
        
        //History book
        histoire = new Histoire();
        
 
        // todo: fix state with profil
        histoire.push(createState());
        
    }
    
    public boolean isProfilClassique(){
        return this.getProfil() instanceof ProfilClassique;
    }
    
    public void enleverElementAvecNom(String nom){
        
        //Si on trouve l'élément
        Boolean trouve = false;
        
        //Boucler sur tous les éléments de la liste
        for(int i = 0; i < elements.size() && !trouve;i++)
        {
            //Si l'élément à le même nom
            if (elements.get(i).getNom().equals(nom)){
                //Enlever l'élément de la liste
                trouve = true;
                elements.remove(elements.get(i));
            }
        }
    }
    
    public Poutre getPoutreArriere(){
        return microRoulotte.getPoutreArriere();
    }
    
    public void emptyElements(){
        this.elements = new LinkedList<>();
    }
    
    public void addElement(Element element){
        if(!elements.contains(element))
            elements.add(element);
    }
        
    public List<Element> getElements(){
        return elements;
    }
    
    public void addPointControle(PointControle point){
        if(!pointsControle.contains(point))
            pointsControle.add(point);
    }
        
    public List<PointControle> getPointsControle(){
        return pointsControle;
    }
    
    public boolean selectionPointControle(Point mousePoint){
        boolean siTrouve = false;
        if(!pointsControle.isEmpty()){
            for (int i = 0; i < pointsControle.size(); i++) {
                if(pointsControle.get(i).getArea().contains(mousePoint) && pointsControle.get(i).isActive()){
                    currentPointControle = pointsControle.get(i);
                    siTrouve = true;
                }
            }
        }

        
        return siTrouve;
    }

    public PointControle getCurrentPointControle() {
        return currentPointControle;
    }

    public void setCurrentPointControle(PointControle currentPointControle) {
        this.currentPointControle = currentPointControle;
    }
    
    
    
    public void dragPointControle(Point mousePoint){
        currentPointControle.setX(new MesureImperial(mousePoint.getX()));
        currentPointControle.setY(new MesureImperial(mousePoint.getY()));
        
        ProfilBezier profilBezier;
        ProfilClassique profilClassique;
        switch(currentPointControle.getNom()){
            case "pointNW":
                profilBezier = (ProfilBezier) getMicroRoulotte().getProfil();
                profilBezier.setPointControle(0, currentPointControle);
                break;
            case "pointNE":
                profilBezier = (ProfilBezier) getMicroRoulotte().getProfil();
                profilBezier.setPointControle(1, currentPointControle);
                break;
            case "ellipseNW":
                profilClassique = (ProfilClassique) getMicroRoulotte().getProfil();
                Ellipse ellipseNW = profilClassique.getListeEllipses().get(0);
                ellipseNW.setPositionXOffset(currentPointControle.getX().inverse(),microRoulotte.getPositionX());
                ellipseNW.setPositionYOffset(currentPointControle.getY().inverse(),microRoulotte.getPositionY());
                break;
            case "ellipseNE":
                profilClassique = (ProfilClassique) getMicroRoulotte().getProfil();
                Ellipse ellipseNE = profilClassique.getListeEllipses().get(1);
                ellipseNE.setPositionXOffset(currentPointControle.getX(),microRoulotte.getPositionX().add(microRoulotte.getLargeur()));
                ellipseNE.setPositionYOffset(currentPointControle.getY(),microRoulotte.getPositionY());
                break;
            case "ellipseSW":
                profilClassique = (ProfilClassique) getMicroRoulotte().getProfil();
                Ellipse ellipseSW = profilClassique.getListeEllipses().get(2);
                ellipseSW.setPositionXOffset(currentPointControle.getX(),microRoulotte.getPositionX());
                ellipseSW.setPositionYOffset(currentPointControle.getY(),microRoulotte.getPositionY().add(microRoulotte.getHauteur()));
                break;
            case "ellipseSE":
                profilClassique = (ProfilClassique) getMicroRoulotte().getProfil();
                Ellipse ellipseSE = profilClassique.getListeEllipses().get(3);
                ellipseSE.setPositionXOffset(currentPointControle.getX(),microRoulotte.getPositionX().add(microRoulotte.getLargeur()));
                ellipseSE.setPositionYOffset(currentPointControle.getY(),microRoulotte.getPositionY().add(microRoulotte.getHauteur()));
                break;
        }
    }
    
    public String selectionElement(Point mousePoint){
        
        //Si on trouve lelement selectionné
        boolean siTrouve = false;
        
        
        
        //Si la liste n'est pas vide
        if(!getElements().isEmpty())
        {
            //Boucler sur tous les items si on las pas trouver encore
            for (int i = elements.size()-1; i >= 0 && !siTrouve;i--) {
                elements.get(i).setSelection(false);
                //Regarder si le polygon contient le point
                if (elements.get(i).getArea().contains(mousePoint) && elements.get(i).isAffiche()) {
                    
                    elements.get(i).setSelection(true);
                        
                    siTrouve = true;
                        
                    return elements.get(i).getNom();
                }
            }
        }
        return "";
    }
    
    public void setMicroRoulotteLargeur(String largeur) throws Exception {
        microRoulotte.setLargeur(currentMesure == MesureEnum.Imperial ? new MesureImperial(largeur): new MesureMetrique(largeur));
    }
    
    public void setMicroRoulotteHauteur(String hauteur) throws Exception{
        microRoulotte.setHauteur(currentMesure == MesureEnum.Imperial ? new MesureImperial(hauteur): new MesureMetrique(hauteur));
    }
    
    public void setPlancherEpaisseur(String epaisseur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(epaisseur): new MesureMetrique(epaisseur);
        microRoulotte.getPlancher().setEpaisseur(m);        
    }
    
     public void setToitEpaisseur(String epaisseur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(epaisseur): new MesureMetrique(epaisseur);
        microRoulotte.getToit().setEpaisseurDado(m);        
    }
     
    public void setPlancherMargeAvant(String marge) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(marge): new MesureMetrique(marge);
        Mesure avant = microRoulotte.getPlancher().getMargeAvant();
        microRoulotte.getPlancher().setMargeAvant(m);
        
        
        try {
            Validateur.validerMicroRoulotte(microRoulotte);
                
        } catch (Exception e) {
            microRoulotte.getPlancher().setMargeAvant(avant);
            throw new Exception(e.getMessage());
        }
    }
    
    public void setPlancherMargeArriere(String marge) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(marge): new MesureMetrique(marge);
        Mesure avant = microRoulotte.getPlancher().getMargeArriere();
        microRoulotte.getPlancher().setMargeArriere(m);
        
        try {
            Validateur.validerMicroRoulotte(microRoulotte);
                
        } catch (Exception e) {
            microRoulotte.getPlancher().setMargeArriere(avant);
            throw new Exception(e.getMessage());
        }
    }
    
    public Matelas getMatelas(){
        return microRoulotte.getMatelas();
    }
    
    public void setMatelasLargeur(String largeur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(largeur): new MesureMetrique(largeur);
        microRoulotte.getMatelas().setLargeur(m);
    }
    
    public void setMatelasHauteur(String hauteur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(hauteur): new MesureMetrique(hauteur);
        microRoulotte.getMatelas().setHauteur(m);
    }
    
    public void newMatelas(){
        if(!elements.contains(microRoulotte.getMatelas())){
            microRoulotte.setMatelas(new Matelas());
            if(currentMesure == MesureEnum.Metrique){
                microRoulotte.getMatelas().switchMesureTo(currentMesure);
            }
            addElement(microRoulotte.getMatelas());
        }
    }
    
    public void supprimerMatelas(){
        elements.remove(microRoulotte.getMatelas());
        microRoulotte.setMatelas(null);
    }
    
    public void setMatelasAffiche(boolean affiche){
        microRoulotte.getMatelas().setAffiche(affiche);
    }
    
    public Humain getHumain(){
        return microRoulotte.getHumain();
    }
    
    public void setHumainLargeur(String largeur)throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(largeur): new MesureMetrique(largeur);
        microRoulotte.getHumain().setLargeur(m);
    }
    
    public void setHumainHauteur(String hauteur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(hauteur): new MesureMetrique(hauteur);
        microRoulotte.getHumain().setHauteur(m);
    }
    
    public void setHumainDistanceBoutPlancher(String distance) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distance): new MesureMetrique(distance);
        microRoulotte.getHumain().setDistanceBoutPlancher(m);
    }
    
    public void newHumain(){
        if(!elements.contains(microRoulotte.getHumain())){
            microRoulotte.setHumain(new Humain());
            if(currentMesure == MesureEnum.Metrique){
                microRoulotte.getHumain().switchMesureTo(currentMesure);
            }
            addElement(microRoulotte.getHumain());
        }
    }
    
    public void supprimerHumain(){
        elements.remove(microRoulotte.getHumain());
        microRoulotte.setHumain(null);
    }
    
    public void setHumainAffiche(boolean affiche){
        microRoulotte.getHumain().setAffiche(affiche);
    }
    
    public void setPoutreArriereLargeur(String largeur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(largeur): new MesureMetrique(largeur);
        microRoulotte.getPoutreArriere().setLargeur(m);
    }
    
    public void setPoutreArriereHauteur(String hauteur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(hauteur): new MesureMetrique(hauteur);
        microRoulotte.getPoutreArriere().setHauteur(m);
    }
    
    public void setHayonEpaisseur(String epaisseur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(epaisseur): new MesureMetrique(epaisseur);
        microRoulotte.getHayon().setEpaisseur(m);
    }
        
    public void setHayonDistancePoutre(String distance) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distance): new MesureMetrique(distance);
        microRoulotte.getHayon().setDistanceDePoutre(m);
    }
        
    public void setHayonDistancePlancher(String distance) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distance): new MesureMetrique(distance);
        microRoulotte.getHayon().setDistanceDuPlancher(m);
    }
        
    public void setHayonTrait(String trait) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(trait): new MesureMetrique(trait);
        microRoulotte.getHayon().setTraitDeScie(m);
    }
    
    public void setHayonPoids(String poids) throws Exception{
        try {
            Double poidsDouble = Double.parseDouble(poids);
            microRoulotte.getHayon().setPoids(poidsDouble);
        } catch (Exception e) {
            throw new Exception("Champ doit être un nombre");
        }
    }
    
    public Porte getPorte(){
        return microRoulotte.getPorte();
    }
    
    public Plancher getPlancher(){
        return microRoulotte.getPlancher();

    }
    
    public Hayon getHayon(){
        return microRoulotte.getHayon();
    }
    
    public MicroRoulotte getMicroRoulotte(){
        return microRoulotte;
    }
    
    public MurSeparateur getMurSeparateur(){
        return microRoulotte.getMurSeparateur();
    }
    
    public void setMurEpaisseur(String epaisseur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(epaisseur): new MesureMetrique(epaisseur);
        microRoulotte.getMurSeparateur().setEpaisseurDado(m);
    }
    
    public void setMurDistancePoutre(String distance) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distance): new MesureMetrique(distance);
        microRoulotte.getMurSeparateur().setDistancePoutreArriere(m);
    }
    
    public void setMurDistancePlancher(String distance) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distance): new MesureMetrique(distance);
        microRoulotte.getMurSeparateur().setDistanceDuPlancher(m);
    }
    
    public void setPorteLargeur(String largeur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(largeur): new MesureMetrique(largeur);
        microRoulotte.getPorte().setLargeur(m);
    }
    
    public void setPorteHauteur(String hauteur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(hauteur): new MesureMetrique(hauteur);
        microRoulotte.getPorte().setHauteur(m);
    }
    
    public void setPorteDistanceDeBoutToit(String distanceDeBoutToit) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distanceDeBoutToit): new MesureMetrique(distanceDeBoutToit);
        microRoulotte.getPorte().setDistanceDeBoutToit(m);
    }
    
    public void setPorteAffiche(boolean affiche){
        microRoulotte.getPorte().setAffiche(affiche);
    }
    
    public Fenetre getFenetre(){
        return microRoulotte.getFenetre();
    }
    
    public void setFenetreLargeur(String largeur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(largeur): new MesureMetrique(largeur);
        microRoulotte.getFenetre().setLargeur(m);
    }
    
    public void setFenetreHauteur(String hauteur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(hauteur): new MesureMetrique(hauteur);
        microRoulotte.getFenetre().setHauteur(m);
    }
    
    public void setFenetreDistanceFromFront(String distanceFromFront) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distanceFromFront): new MesureMetrique(distanceFromFront);
        microRoulotte.getFenetre().setDistanceFromFront(m);
    }
    
    public void setFenetreDistanceFromToit(String distanceFromToit) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(distanceFromToit): new MesureMetrique(distanceFromToit);
        microRoulotte.getFenetre().setDistanceFromToit(m);
    }
    
    public void setFenetreAffiche(boolean affiche){
        if(microRoulotte.getFenetre() != null){
            microRoulotte.getFenetre().setAffiche(affiche);
        }
    }
    
    public void newFenetre(){
        if(!elements.contains(microRoulotte.getFenetre())){
            microRoulotte.setFenetre(new Fenetre());
            if(currentMesure == MesureEnum.Metrique){
                microRoulotte.getFenetre().switchMesureTo(currentMesure);
            }
            addElement(microRoulotte.getFenetre());
        }
    }
    
    public void supprimerFenetre(){
        elements.remove(microRoulotte.getFenetre());
        microRoulotte.setFenetre(null);
    }
    
    public Toit getToit(){
        return microRoulotte.getToit();
    }
    
    public ArrayList<Ellipse> getEllipses(){
        if(microRoulotte.getProfil().getClass() == ProfilClassique.class){
            ProfilClassique profilClassique = (ProfilClassique)microRoulotte.getProfil();
            return profilClassique.getListeEllipses();
        }
        else
            return null;
        
    }
    
    public Mesure newMesureString(String mesureEntree) throws Exception{
        try {
            Mesure mesure = new MesureImperial(mesureEntree);
            return mesure;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void setPlancherAffiche(boolean affiche){
        microRoulotte.getPlancher().setAffiche(affiche);
    }
    
    public void setHayonAffiche(boolean  affiche){
        microRoulotte.getHayon().setAffiche(affiche);
    }
    
    public Profil getProfil(){
        return microRoulotte.getProfil();
    }
    
    public void setAfficheEllipses(boolean affiche){
        if(microRoulotte.getProfil().getClass() == ProfilClassique.class){
                ProfilClassique profilClassique = (ProfilClassique)microRoulotte.getProfil();
                profilClassique.setAfficheEllipses(affiche);
        }
    }
    
    public void setPoutreArriereAffiche(boolean affiche){
        microRoulotte.getPoutreArriere().setAffiche(affiche);
    }

    //Grille Daide
    public GrilleAideDTO getGrilleAideDTO() {
        return new GrilleAideDTO(grilleAide.getMesure(),grilleAide.getCouleur(),grilleAide.isAffiche());
    }
    
    //Grille Daide
    public GrilleAide getGrilleAide() {
        return grilleAide;
    }
    
    public void setGrilleDaideCouleur(Color couleur) {
        this.grilleAide.setCouleur(couleur);
    }

    public void setGrilleDaideisAffiche(Boolean affiche) {
        this.grilleAide.isAffiche(affiche);
    }

    public void setGrilleDaideMesure(Mesure mesure) {
        this.grilleAide.setMesure(mesure);
    }
    
    //Save l'état de l'application
    public ApplicationEtat createState(){

        return new ApplicationEtat(
                //Micro roulotte
                microRoulotte.getHauteur(),microRoulotte.getLargeur(),
                //Plancher
                microRoulotte.getPlancher().getEpaisseur(),microRoulotte.getPlancher().getMargeArriere(),microRoulotte.getPlancher().getMargeAvant(),
                //Hayon
                microRoulotte.getHayon().getEpaisseur(),microRoulotte.getHayon().getDistanceDePoutre(),microRoulotte.getHayon().getDistanceDuPlancher(),microRoulotte.getHayon().getTraitDeScie(),microRoulotte.getHayon().getPoids(),
                //Poutre arrière
                microRoulotte.getPoutreArriere().getLargeur(),microRoulotte.getPoutreArriere().getHauteur(),microRoulotte.getPoutreArriere().getPositionX(),microRoulotte.getPoutreArriere().getPositionY(),
                //Ellipses
                isProfilClassique() ? getEllipses().get(0).getHauteur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(0).getLargeur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(0).getPositionX(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(0).getPositionY(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(1).getHauteur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(1).getLargeur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(1).getPositionX(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(1).getPositionY(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(2).getHauteur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(2).getLargeur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(2).getPositionX(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(2).getPositionY(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(3).getHauteur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(3).getLargeur(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(3).getPositionX(): new MesureImperial(0),
                isProfilClassique() ? getEllipses().get(3).getPositionY(): new MesureImperial(0),
                //Ressort
                microRoulotte.getHayon().getRessort().getLongeur(),
        
                //Mur séparateur
                microRoulotte.getMurSeparateur() != null ? microRoulotte.getMurSeparateur().getEpaisseurDado(): new MesureImperial(0),
                microRoulotte.getMurSeparateur() != null ? microRoulotte.getMurSeparateur().getDistancePoutreArriere(): new MesureImperial(0),
                microRoulotte.getMurSeparateur() != null ? microRoulotte.getMurSeparateur().getDistanceDuPlancher(): new MesureImperial(0),
                
                //Toit
                microRoulotte.getToit().getEpaisseurDado(),
                
                //Porte
                microRoulotte.getPorte().getLargeur(),
                microRoulotte.getPorte().getHauteur(),
                microRoulotte.getPorte().getDistanceDeBoutToit(),
                
                //Fenetre
                microRoulotte.getFenetre() != null ? microRoulotte.getFenetre().getLargeur(): new MesureImperial(0),
                microRoulotte.getFenetre() != null ? microRoulotte.getFenetre().getHauteur(): new MesureImperial(0),
                microRoulotte.getFenetre() != null ? microRoulotte.getFenetre().getDistanceFromFront(): new MesureImperial(0),
                microRoulotte.getFenetre() != null ? microRoulotte.getFenetre().getDistanceFromToit(): new MesureImperial(0),
                
                //Matelas
                microRoulotte.getMatelas() != null ? microRoulotte.getMatelas().getLargeur(): new MesureImperial(0),
                microRoulotte.getMatelas() != null ? microRoulotte.getMatelas().getHauteur(): new MesureImperial(0),
                
                //Humain
                microRoulotte.getHumain() != null ? microRoulotte.getHumain().getLargeur(): new MesureImperial(0),
                microRoulotte.getHumain() != null ? microRoulotte.getHumain().getHauteur(): new MesureImperial(0),
                microRoulotte.getHumain() != null ? microRoulotte.getHumain().getDistanceBoutPlancher(): new MesureImperial(0),
                
                pointsControle
        );
    }
    
    public void undo(){
        //Faire undo
        ApplicationEtat etat = histoire.undo();
        
        //Aller checher les bonnes valeurs dans l'histoire
        
        //Micro Roulotte
        microRoulotte.setHauteur(etat.getHauteurMicroRoulotte());
        microRoulotte.setLargeur(etat.getLargeurMicroRoulotte());
        
        //Plancher
        microRoulotte.getPlancher().setEpaisseur(etat.getEpaisseurPlancher());
        microRoulotte.getPlancher().setMargeArriere(etat.getMargeArrierePlancher());
        microRoulotte.getPlancher().setMargeAvant(etat.getMargeAvantPlancher());
        
        //Hayon
        microRoulotte.getHayon().setEpaisseur(etat.getEpaisseurHayon());
        microRoulotte.getHayon().setDistanceDePoutre(etat.getDistancePoutreHayon());
        microRoulotte.getHayon().setDistanceDuPlancher(etat.getDistancePlancherHayon());
        microRoulotte.getHayon().setTraitDeScie(etat.getTraitDeScieHayon());
        microRoulotte.getHayon().setPoids(etat.getPoidsHayon());
        
        //Poutre Arrière
        microRoulotte.getPoutreArriere().setLargeur(etat.getLargeurPoutreArriere());
        microRoulotte.getPoutreArriere().setHauteur(etat.getHauteurPoutreArriere());
        microRoulotte.getPoutreArriere().setPositionX(etat.getPositionXPoutreArriere());
        microRoulotte.getPoutreArriere().setPositionY(etat.getPositionYPoutreArriere());
        
        //Ellipse Haut Gauche
        if(isProfilClassique()){
            getEllipses().get(0).setHauteur(etat.getHauteurEllipseHG());
            getEllipses().get(0).setLargeur(etat.getLargeurEllipseHG());
            getEllipses().get(0).setPositionX(etat.getPositionXEllipseHG());
            getEllipses().get(0).setPositionY(etat.getPositionYEllipseHG());

            //Ellipse Haut Droit
            getEllipses().get(1).setHauteur(etat.getHauteurEllipseHD());
            getEllipses().get(1).setLargeur(etat.getLargeurEllipseHD());
            getEllipses().get(1).setPositionX(etat.getPositionXEllipseHD());
            getEllipses().get(1).setPositionY(etat.getPositionYEllipseHD());

            //Ellipse Bas Gauche
            getEllipses().get(2).setHauteur(etat.getHauteurEllipseBG());
            getEllipses().get(2).setLargeur(etat.getLargeurEllipseBG());
            getEllipses().get(2).setPositionX(etat.getPositionXEllipseBG());
            getEllipses().get(2).setPositionY(etat.getPositionYEllipseBG());

            //Ellipse Bas Droit
            getEllipses().get(3).setHauteur(etat.getHauteurEllipseBD());
            getEllipses().get(3).setLargeur(etat.getLargeurEllipseBD());
            getEllipses().get(3).setPositionX(etat.getPositionXEllipseBD());
            getEllipses().get(3).setPositionY(etat.getPositionYEllipseBD());
        }

        
        //Ressort
        microRoulotte.getHayon().getRessort().setLongeur(etat.getLongueurRessort());
        
        //Mur Séparateur
        if(microRoulotte.getMurSeparateur() != null){
            microRoulotte.getMurSeparateur().setEpaisseurDado(etat.getEpaisseurMurSeparateur());
            microRoulotte.getMurSeparateur().setDistancePoutreArriere(etat.getDistancePoutreMurSeparateur());
            microRoulotte.getMurSeparateur().setDistanceDuPlancher(etat.getDistancePlancherMurSeparateur());
        }
        
        //Toit
        microRoulotte.getToit().setEpaisseurDado(etat.getEpaisseurToit());
        
        //Porte
        microRoulotte.getPorte().setLargeur(etat.getLargeurPorte());
        microRoulotte.getPorte().setHauteur(etat.getHauteurPorte());
        microRoulotte.getPorte().setDistanceDeBoutToit(etat.getDistanceDeBoutToitPorte());
        
        //Fenetre
        if(microRoulotte.getFenetre() != null){
            microRoulotte.getFenetre().setLargeur(etat.getLargeurFenetre());
            microRoulotte.getFenetre().setHauteur(etat.getHauteurFenetre());
            microRoulotte.getFenetre().setDistanceFromFront(etat.getDistanceFromFrontFenetre());
            microRoulotte.getFenetre().setDistanceFromToit(etat.getDistanceFromToitFenetre());
        }
        
        //Matelas
        if(microRoulotte.getMatelas() != null){
            microRoulotte.getMatelas().setLargeur(etat.getLargeurMatelas());
            microRoulotte.getMatelas().setHauteur(etat.getHauteurMatelas());
        }
        
        //Humain
        if(microRoulotte.getHumain() != null){
            microRoulotte.getHumain().setLargeur(etat.getLargeurHumain());
            microRoulotte.getHumain().setHauteur(etat.getHauteurHumain());
            microRoulotte.getHumain().setDistanceBoutPlancher(etat.getDistanceBoutPlancherHumain());
        }
    }
    
    public void redo(){
        //Faire un redo
        ApplicationEtat etat = histoire.redo();
        
        //Aller chercher les bonnes valeurs dans l'histoire
        
        //Micro Roulotte
        microRoulotte.setHauteur(etat.getHauteurMicroRoulotte());
        microRoulotte.setLargeur(etat.getLargeurMicroRoulotte());
        
        //Plancher
        microRoulotte.getPlancher().setEpaisseur(etat.getEpaisseurPlancher());
        microRoulotte.getPlancher().setMargeArriere(etat.getMargeArrierePlancher());
        microRoulotte.getPlancher().setMargeAvant(etat.getMargeAvantPlancher());
        
        //Hayon
        microRoulotte.getHayon().setEpaisseur(etat.getEpaisseurHayon());
        microRoulotte.getHayon().setDistanceDePoutre(etat.getDistancePoutreHayon());
        microRoulotte.getHayon().setDistanceDuPlancher(etat.getDistancePlancherHayon());
        microRoulotte.getHayon().setTraitDeScie(etat.getTraitDeScieHayon());
        microRoulotte.getHayon().setPoids(etat.getPoidsHayon());
        
        //Poutre Arrière
        microRoulotte.getPoutreArriere().setLargeur(etat.getLargeurPoutreArriere());
        microRoulotte.getPoutreArriere().setHauteur(etat.getHauteurPoutreArriere());
        microRoulotte.getPoutreArriere().setPositionX(etat.getPositionXPoutreArriere());
        microRoulotte.getPoutreArriere().setPositionY(etat.getPositionYPoutreArriere());
        
        //Ellipse Haut Gauche
        if(isProfilClassique()){
            getEllipses().get(0).setHauteur(etat.getHauteurEllipseHG());
            getEllipses().get(0).setLargeur(etat.getLargeurEllipseHG());
            getEllipses().get(0).setPositionX(etat.getPositionXEllipseHG());
            getEllipses().get(0).setPositionY(etat.getPositionYEllipseHG());

            //Ellipse Haut Droit
            getEllipses().get(1).setHauteur(etat.getHauteurEllipseHD());
            getEllipses().get(1).setLargeur(etat.getLargeurEllipseHD());
            getEllipses().get(1).setPositionX(etat.getPositionXEllipseHD());
            getEllipses().get(1).setPositionY(etat.getPositionYEllipseHD());

            //Ellipse Bas Gauche
            getEllipses().get(2).setHauteur(etat.getHauteurEllipseBG());
            getEllipses().get(2).setLargeur(etat.getLargeurEllipseBG());
            getEllipses().get(2).setPositionX(etat.getPositionXEllipseBG());
            getEllipses().get(2).setPositionY(etat.getPositionYEllipseBG());

            //Ellipse Bas Droit
            getEllipses().get(3).setHauteur(etat.getHauteurEllipseBD());
            getEllipses().get(3).setLargeur(etat.getLargeurEllipseBD());
            getEllipses().get(3).setPositionX(etat.getPositionXEllipseBD());
            getEllipses().get(3).setPositionY(etat.getPositionYEllipseBD());
        }

        
        //Ressort
        microRoulotte.getHayon().getRessort().setLongeur(etat.getLongueurRessort());
        
        
        //Mur Séparateur
        if(microRoulotte.getMurSeparateur() != null){
            microRoulotte.getMurSeparateur().setEpaisseurDado(etat.getEpaisseurMurSeparateur());
            microRoulotte.getMurSeparateur().setDistancePoutreArriere(etat.getDistancePoutreMurSeparateur());
            microRoulotte.getMurSeparateur().setDistanceDuPlancher(etat.getDistancePlancherMurSeparateur());
        }
        
        
        //Toit
        microRoulotte.getToit().setEpaisseurDado(etat.getEpaisseurToit());
        
        //Porte
        microRoulotte.getPorte().setLargeur(etat.getLargeurPorte());
        microRoulotte.getPorte().setHauteur(etat.getHauteurPorte());
        microRoulotte.getPorte().setDistanceDeBoutToit(etat.getDistanceDeBoutToitPorte());
        
        //Fenetre
        if(microRoulotte.getFenetre() != null){
            microRoulotte.getFenetre().setLargeur(etat.getLargeurFenetre());
            microRoulotte.getFenetre().setHauteur(etat.getHauteurFenetre());
            microRoulotte.getFenetre().setDistanceFromFront(etat.getDistanceFromFrontFenetre());
            microRoulotte.getFenetre().setDistanceFromToit(etat.getDistanceFromToitFenetre());
        }
        
        //Matelas
        if(microRoulotte.getMatelas() != null){
            microRoulotte.getMatelas().setLargeur(etat.getLargeurMatelas());
            microRoulotte.getMatelas().setHauteur(etat.getHauteurMatelas());
        }
        
        //Humain
        if(microRoulotte.getHumain() != null){
            microRoulotte.getHumain().setLargeur(etat.getLargeurHumain());
            microRoulotte.getHumain().setHauteur(etat.getHauteurHumain());
            microRoulotte.getHumain().setDistanceBoutPlancher(etat.getDistanceBoutPlancherHumain());
        }
    }
    
    public void pushToHistory(){
        histoire.push(createState());
    }
    
    public Histoire getHistoire(){
        return histoire;
    }
    
    public void setMicroRoulottePositionX(Mesure mesure){
        microRoulotte.setPositionX(mesure);
    }
    
    public void setMicroRoulottePositionY(Mesure mesure){
        microRoulotte.setPositionY(mesure);
    }
    
    public void ouvrirProjet(File fileToOpen) throws Exception{
        Fichier.ouvrirProjet(this,fileToOpen);
    }
    
    public void sauvegarderProjet(File fileToSave) throws Exception{
        Fichier.sauvegarderProjet(this,fileToSave);
    }
    
    public void setMicroRoulotte(MicroRoulotte microRoulotte){
        this.microRoulotte = microRoulotte;
    }
    
    public void setGrilleDaide(GrilleAide grilleDaide){
        this.grilleAide = grilleDaide;
    }
    
    public void setRessortLongueur(String longueur) throws Exception{
        Mesure m = currentMesure == MesureEnum.Imperial ? new MesureImperial(longueur): new MesureMetrique(longueur);
        microRoulotte.getHayon().getRessort().setLongeur(m);
    }
    
    public void setRessortForce(String force) throws Exception{
        try {
            Double forceDouble = Double.parseDouble(force);
            microRoulotte.getHayon().getRessort().setForce(forceDouble);
        } catch (Exception e) {
            throw new Exception("Champ doit être un nombre");
        }
        
    }
    
    public RessortDTO getRessort(){
        return new RessortDTO(microRoulotte.getHayon().getRessort().getLongeur(), microRoulotte.getHayon().getRessort().getForce(),microRoulotte.getHayon().getRessort().getPositionSurMur(),
                microRoulotte.getHayon().getRessort().getPositionSurHayon(),microRoulotte.getHayon().getRessort().isAffiche(),microRoulotte.getHayon().getRessort().getAfficheCouleur(),
                microRoulotte.getHayon().getRessort().getHauteurHayonEnPouce(microRoulotte.getHayon()),microRoulotte.getHayon().getRessort().getCenterPoint(),
                microRoulotte.getHayon().getRessort().getLongueurIdealEnExtension(),
                microRoulotte.getHayon().getRessort().getStrokeLenth(), microRoulotte.getHayon().getRessort().getNoMcMaster());
    }
    
    public void setRessortAffiche(boolean affiche){
        microRoulotte.getHayon().getRessort().setAffiche(affiche);
    }
    
    public void switchMesure(MesureEnum switchTo){
        if(!currentMesure.equals(switchTo)){
            for(Element e: elements){
                e.switchMesureTo(switchTo);
            }   
            grilleAide.switchMesure();
            currentMesure = switchTo;
        }
    }
    
    public boolean isMurAffiche(){
        return microRoulotte.getPanneauExterne().isAffiche() || microRoulotte.getPanneauInterne().isAffiche() || microRoulotte.getPanneauMillieu().isAffiche();
    }
    
    public void exportSVG(File file) throws Exception{
        Fichier.exportPlanSVG(file,elements,this);
    }
    
    
    public void setMurSeparateurAffiche(boolean affiche){
        if(microRoulotte.getMurSeparateur() != null){
            microRoulotte.getMurSeparateur().setAffiche(affiche);
        }
    }
    
    public void setToitAffiche(boolean affiche){
        microRoulotte.getToit().setAffiche(affiche);
    }
    
    public void setProfilAffiche(boolean affiche){
        microRoulotte.setAffiche(affiche);
    }
 
    public void setRessortArea(Area area){
        microRoulotte.getHayon().getRessort().setArea(area);
    }
    
    public void newMurSeparateur(){
        microRoulotte.setMurSeparateur(new MurSeparateur(new MesureImperial(2),new MesureImperial(2),new MesureImperial(2)));
        if(currentMesure == MesureEnum.Metrique){
            microRoulotte.getMurSeparateur().switchMesureTo(currentMesure);
        }
        addElement(microRoulotte.getMurSeparateur());
    }
    
    public String getSelectedItem(){
        Element selectElement = null;
        for(Element e : elements){
            if(e.estSelectionne())
                selectElement = e;
        }
        
        if(selectElement!=null)
        return selectElement.getNom();
        else
            return "";
    }
    
    public void supprimerMurSeparateur(){
        elements.remove(microRoulotte.getMurSeparateur());
        microRoulotte.setMurSeparateur(null);
    }
    
    public void changeProfilTo(String type){
        switch(type){
            case "classique":
                 ArrayList<Ellipse> ellipses = new ArrayList<Ellipse>();

                Ellipse nw = new Ellipse(0,0,60,70,0);
                Ellipse ne = new Ellipse(0,0,50,50,1);
                Ellipse sw = new Ellipse(0,0,30,30,2);
                Ellipse se = new Ellipse(0,0,15,15,3);

                ellipses.add(nw);
                ellipses.add(ne);
                ellipses.add(sw);
                ellipses.add(se);

                getMicroRoulotte().profil = new ProfilClassique(ellipses);
                break;
            case "bezier":

                ArrayList<PointControle> points = new ArrayList<PointControle>();
                PointControle pnw = new PointControle(getMicroRoulotte().getPositionX(),getMicroRoulotte().getPositionY(),"profilNW");
                PointControle pne = new PointControle(getMicroRoulotte().getPositionX().add(getMicroRoulotte().getLargeur()),getMicroRoulotte().getPositionY(),"profilNE");
                points.add(pnw);
                points.add(pne);
                getMicroRoulotte().profil = new ProfilBezier(points);
                break;
        }
    }
    
    
    
    
    
    public Panneau getPanneauMillieu(){
        return  microRoulotte.getPanneauMillieu();
    }
    
    public void setPanneauMillieuAffiche(boolean affiche){
        microRoulotte.getPanneauMillieu().setAffiche(affiche);
    }
    
    public void setPanneauExterneAffiche(boolean affiche){
        microRoulotte.getPanneauExterne().setAffiche(affiche);
    }
    
    public void setPanneauInterneAffiche(boolean affiche){
        microRoulotte.getPanneauInterne().setAffiche(affiche);
    }
    
    public Panneau getPanneauExterne(){
        return microRoulotte.getPanneauExterne();
    }
    
    public Panneau getPanneauInterne(){
        return microRoulotte.getPanneauInterne();
    }
    
    public void setPanneauMillieuArea(Area area){
        microRoulotte.getPanneauMillieu().setArea(area);
    }
    
    public void setPanneauInterneArea(Area area){
        microRoulotte.getPanneauInterne().setArea(area);
    }
    
    public void setPanneauExterneArea(Area area){
        microRoulotte.getPanneauExterne().setArea(area);
    }
    
}
