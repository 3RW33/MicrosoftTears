/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.util.List;

/**
 *
 * @author Gabriel
 */

//Etat de l'application qui sera stocké dans l'histoire de l'application
public class ApplicationEtat implements java.io.Serializable{
    
    //Micro Roulotte
    private final Mesure hauteurMicroRoulotte;
    private final Mesure largeurMicroRoulotte;
    
    //Plancher
    private final Mesure epaisseurPlancher;
    private final Mesure margeArrierePlancher;
    private final Mesure margeAvantPlancher;
    
    //Hayon
    private final Mesure epaisseurHayon;
    private final Mesure distancePoutreHayon;
    private final Mesure distancePlancherHayon;
    private final Mesure traitDeScieHayon;
    private final Double poidsHayon;
    
    //Poutre Arrière
    private final Mesure largeurPoutreArriere;
    private final Mesure hauteurPoutreArriere;
    private final Mesure positionXPoutreArriere;
    private final Mesure positionYPoutreArriere;
    
    //Ellipse Haut Gauche
    
    private final Mesure hauteurEllipseHG;
    private final Mesure largeurEllipseHG;
    private final Mesure positionXEllipseHG;
    private final Mesure positionYEllipseHG;
    
    //Ellipse Haut Droit
    private final Mesure hauteurEllipseHD;
    private final Mesure largeurEllipseHD;
    private final Mesure positionXEllipseHD;
    private final Mesure positionYEllipseHD;
    
    //Ellipse Bas Gauche
    private final Mesure hauteurEllipseBG;
    private final Mesure largeurEllipseBG;
    private final Mesure positionXEllipseBG;
    private final Mesure positionYEllipseBG;
    
    //Ellipse Bad Droit
    
    private final Mesure hauteurEllipseBD;
    private final Mesure largeurEllipseBD;
    private final Mesure positionXEllipseBD;
    private final Mesure positionYEllipseBD;
    
    
    //Ressort
    private final Mesure longueurRessort;
    

    
    //Mur séparateur
    private final Mesure epaisseurMurSeparateur;
    private final Mesure distancePoutreMurSeparateur;
    private final Mesure distancePlancherMurSeparateur;
    
    //Toit
    private final Mesure epaisseurToit;
    
    //Porte
    private final Mesure largeurPorte;
    private final Mesure hauteurPorte;
    private final Mesure distanceDeBoutToitPorte;
    
    //Fenetre
    private final Mesure largeurFenetre;
    private final Mesure hauteurFenetre;
    private final Mesure distanceFromFrontFenetre;
    private final Mesure distanceFromToitFenetre;
    
    //Matelas
    private final Mesure largeurMatelas;
    private final Mesure hauteurMatelas;
    
    //Humain
    private final Mesure largeurHumain;
    private final Mesure hauteurHumain;
    private final Mesure distanceBoutPlancherHumain;
    
    private List<PointControle> pointsControle;


    public ApplicationEtat(
        //Micro roulotte
        Mesure hauteurMicroRoulotte, Mesure largeurMicroRoulotte,
        //Plancher
        Mesure epaisseurPlancher, Mesure margeArrierePlancher, Mesure margeAvantPlancher,
        //epaisseur hayon
        Mesure epaisseurHayon,Mesure distancePoutreHayon,Mesure distancePlancherHayon,Mesure traitDeScieHayon,Double poidsHayon,
        //Poutre arriere
        Mesure largeurPoutreArriere,Mesure hauteurPoutreArriere,Mesure positionXPoutreArriere, Mesure positionYPoutreArriere,
        //Ellipse
        Mesure hauteurEllipseHG, Mesure largeurEllipseHG,Mesure positionXEllipseHG,Mesure positionYEllipseHG,
        Mesure hauteurEllipseHD, Mesure largeurEllipseHD,Mesure positionXEllipseHD,Mesure positionYEllipseHD,
        Mesure hauteurEllipseBG, Mesure largeurEllipseBG,Mesure positionXEllipseBG,Mesure positionYEllipseBG,
        Mesure hauteurEllipseBD, Mesure largeurEllipseBD,Mesure positionXEllipseBD,Mesure positionYEllipseBD,
        //Ressort
        Mesure longueurRessort,

        //Mur séparateur
        Mesure epaisseurMurSeparateur,
        Mesure distancePoutreMurSeparateur,
        Mesure distancePlancherMurSeparateur,

        //Toit
        Mesure epaisseurToit,

        //Porte
        Mesure largeurPorte,
        Mesure hauteurPorte,
        Mesure distanceDeBoutToitPorte,
            
        //Fenetre
        Mesure largeurFenetre,
        Mesure hauteurFenetre,
        Mesure distanceFromFrontFenetre,
        Mesure distanceFromToitFenetre,
        
        //Matelas
        Mesure largeurMatelas,
        Mesure hauteurMatelas,
        
        //Humain
        Mesure largeurHumain,
        Mesure hauteurHumain,
        Mesure distanceBoutPlancherHumain,
        
        List<PointControle> pointsControle
    ) {
        
        //Micro Roulottte
        this.hauteurMicroRoulotte = hauteurMicroRoulotte;
        this.largeurMicroRoulotte = largeurMicroRoulotte;
        
        //Plancher
        this.epaisseurPlancher = epaisseurPlancher;
        this.margeArrierePlancher = margeArrierePlancher;
        this.margeAvantPlancher = margeAvantPlancher;
        
        //Hayon
        this.epaisseurHayon = epaisseurHayon;
        this.distancePoutreHayon = distancePoutreHayon;
        this.distancePlancherHayon = distancePlancherHayon;
        this.traitDeScieHayon = traitDeScieHayon;
        this.poidsHayon = poidsHayon;
        
        //Poutre Arrière
        this.largeurPoutreArriere = largeurPoutreArriere;
        this.hauteurPoutreArriere = hauteurPoutreArriere;
        this.positionXPoutreArriere = positionXPoutreArriere;
        this.positionYPoutreArriere = positionYPoutreArriere;
        
        //Ellipse Haut Gauche
        this.hauteurEllipseHG = hauteurEllipseHG;
        this.largeurEllipseHG = largeurEllipseHG;
        this.positionXEllipseHG = positionXEllipseHG;
        this.positionYEllipseHG = positionYEllipseHG;
        
        //Ellipse Haut Droit
        this.hauteurEllipseHD = hauteurEllipseHD;
        this.largeurEllipseHD = largeurEllipseHD;
        this.positionXEllipseHD = positionXEllipseHD;
        this.positionYEllipseHD = positionYEllipseHD;
        
        //Ellipse Bas Gauche
        this.hauteurEllipseBG = hauteurEllipseBG;
        this.largeurEllipseBG = largeurEllipseBG;
        this.positionXEllipseBG = positionXEllipseBG;
        this.positionYEllipseBG = positionYEllipseBG;
        
        //Ellipse Bas Droit
        this.hauteurEllipseBD = hauteurEllipseBD;
        this.largeurEllipseBD = largeurEllipseBD;
        this.positionXEllipseBD = positionXEllipseBD;
        this.positionYEllipseBD = positionYEllipseBD;
        
        //Ressort
        this.longueurRessort = longueurRessort;
        
        //Mur Séparateur
        this.epaisseurMurSeparateur = epaisseurMurSeparateur;
        this.distancePoutreMurSeparateur = distancePoutreMurSeparateur;
        this.distancePlancherMurSeparateur = distancePlancherMurSeparateur;
        
        //Toit
        this.epaisseurToit = epaisseurToit;
        
        //Porte
        this.largeurPorte = largeurPorte;
        this.hauteurPorte = hauteurPorte;
        this.distanceDeBoutToitPorte = distanceDeBoutToitPorte;
        
        //Fenetre
        this.largeurFenetre = largeurFenetre;
        this.hauteurFenetre = hauteurFenetre;
        this.distanceFromFrontFenetre = distanceFromFrontFenetre;
        this.distanceFromToitFenetre = distanceFromToitFenetre;
        
        //Matelas
        this.largeurMatelas = largeurMatelas;
        this.hauteurMatelas = hauteurMatelas;
        
        //Humain
        this.hauteurHumain = hauteurHumain;
        this.largeurHumain = largeurHumain;
        this.distanceBoutPlancherHumain = distanceBoutPlancherHumain;
        
        this.pointsControle = pointsControle;
    }

    //Micro Roulotte
    public Mesure getHauteurMicroRoulotte() {
        return hauteurMicroRoulotte;
    }

    public Mesure getLargeurMicroRoulotte() {
        return largeurMicroRoulotte;
    }

    //Plancher
    public Mesure getEpaisseurPlancher() {
        return epaisseurPlancher;
    }

    public Mesure getMargeArrierePlancher() {
        return margeArrierePlancher;
    }

    public Mesure getMargeAvantPlancher() {
        return margeAvantPlancher;
    }

    public Mesure getEpaisseurHayon() {
        return epaisseurHayon;
    }

    public Mesure getDistancePoutreHayon() {
        return distancePoutreHayon;
    }

    public Mesure getDistancePlancherHayon() {
        return distancePlancherHayon;
    }

    public Mesure getTraitDeScieHayon() {
        return traitDeScieHayon;
    }

    public Mesure getLargeurPoutreArriere() {
        return largeurPoutreArriere;
    }

    public Mesure getHauteurPoutreArriere() {
        return hauteurPoutreArriere;
    }

    public Mesure getPositionXPoutreArriere() {
        return positionXPoutreArriere;
    }

    public Mesure getPositionYPoutreArriere() {
        return positionYPoutreArriere;
    }

    public Mesure getHauteurEllipseHG() {
        return hauteurEllipseHG;
    }

    public Mesure getLargeurEllipseHG() {
        return largeurEllipseHG;
    }

    public Mesure getPositionXEllipseHG() {
        return positionXEllipseHG;
    }

    public Mesure getPositionYEllipseHG() {
        return positionYEllipseHG;
    }

    public Mesure getHauteurEllipseHD() {
        return hauteurEllipseHD;
    }

    public Mesure getLargeurEllipseHD() {
        return largeurEllipseHD;
    }

    public Mesure getPositionXEllipseHD() {
        return positionXEllipseHD;
    }

    public Mesure getPositionYEllipseHD() {
        return positionYEllipseHD;
    }

    public Mesure getHauteurEllipseBG() {
        return hauteurEllipseBG;
    }

    public Mesure getLargeurEllipseBG() {
        return largeurEllipseBG;
    }

    public Mesure getPositionXEllipseBG() {
        return positionXEllipseBG;
    }

    public Mesure getPositionYEllipseBG() {
        return positionYEllipseBG;
    }

    public Mesure getHauteurEllipseBD() {
        return hauteurEllipseBD;
    }

    public Mesure getLargeurEllipseBD() {
        return largeurEllipseBD;
    }

    public Mesure getPositionXEllipseBD() {
        return positionXEllipseBD;
    }

    public Mesure getPositionYEllipseBD() {
        return positionYEllipseBD;
    }

    public Mesure getLongueurRessort() {
        return longueurRessort;
    }

    public Double getPoidsHayon() {
        return poidsHayon;
    }

    public Mesure getEpaisseurMurSeparateur() {
        return epaisseurMurSeparateur;
    }

    public Mesure getDistancePoutreMurSeparateur() {
        return distancePoutreMurSeparateur;
    }

    public Mesure getDistancePlancherMurSeparateur() {
        return distancePlancherMurSeparateur;
    }

    public Mesure getEpaisseurToit() {
        return epaisseurToit;
    }

    public Mesure getLargeurPorte() {
        return largeurPorte;
    }

    public Mesure getHauteurPorte() {
        return hauteurPorte;
    }

    public Mesure getDistanceDeBoutToitPorte() {
        return distanceDeBoutToitPorte;
    }

    public Mesure getLargeurFenetre() {
        return largeurFenetre;
    }

    public Mesure getHauteurFenetre() {
        return hauteurFenetre;
    }

    public Mesure getDistanceFromFrontFenetre() {
        return distanceFromFrontFenetre;
    }

    public Mesure getDistanceFromToitFenetre() {
        return distanceFromToitFenetre;
    }

    public Mesure getLargeurMatelas() {
        return largeurMatelas;
    }

    public Mesure getHauteurMatelas() {
        return hauteurMatelas;
    }

    public Mesure getLargeurHumain() {
        return largeurHumain;
    }

    public Mesure getHauteurHumain() {
        return hauteurHumain;
    }

    public Mesure getDistanceBoutPlancherHumain() {
        return distanceBoutPlancherHumain;
    }

    
    
    
    
    
}
