/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

import java.awt.Color;
import static java.util.Objects.isNull;

/**
 *
 * @author Gabriel
 */
public class Ressort extends Element implements java.io.Serializable{
    
    private Mesure longueur;
    private double force;
    private Mesure positionSurMur;
    private Mesure positionSurHayon;
    
    private String noMcMaster="";
    
    //Calcul de force requis
    private double hauteurHayonEnPouce;
    
    //Longueur ideal du ressort à gaz en extension
    double longueurIdealEnExtension = ((hauteurHayonEnPouce)*60)/100;

    int nombreSprings = 2;

    //Dead Weight in Newton
    double deadWeight ;

    //Center of Gravity Length in mm
    double centreGraviteEnMM;

    //Power Arm length in mm (CHANGER 12.8)
    double strokeLenth;
    double centerPoint;
    double powerArmLengthEnMM;

    //Force required in Newton
    double forceRequisEnNewton;

    //Safety Factor in Newton 
    double safetyFacteurEnNewton;
    
    
    double longeursMcMaster[] = new double[12];

    //Fore Requis pour le ressort a gaz
    public Ressort() {
        
   

        this.setAfficheCouleur(Color.GREEN);
        this.setNom("Ressort");
       
        longueur = new MesureImperial(0);
        
        longeursMcMaster[0] = 7.01;
        longeursMcMaster[1] = 7.4;
        longeursMcMaster[2] = 9.65;
        longeursMcMaster[3] = 12.2;
        longeursMcMaster[4] = 13.19;
        longeursMcMaster[5] = 15.24;
        longeursMcMaster[6] = 17.13;
        longeursMcMaster[7] = 19.72;
        longeursMcMaster[8] = 20.12;
        longeursMcMaster[9] = 27.87;
        longeursMcMaster[10] = 29.49;
        longeursMcMaster[11] = 35.43;
    }

    
    
    public Mesure getLongeur() {
        return longueur;
    }

    public double getForce() {
        return force;
    }

    public Mesure getPositionSurMur() {
        return positionSurMur;
    }

    public Mesure getPositionSurHayon() {
        return positionSurHayon;
    }
    
    public void setLongeur(Mesure longeur) {
        this.longueur = longeur;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public void setPositionSurMur(Mesure positionSurMur) {
        this.positionSurMur = positionSurMur;
    }

    public void setPositionSurHayon(Mesure positionSurHayon) {
        this.positionSurHayon = positionSurHayon;
    }

    @Override
    public void switchMesureTo(MesureEnum switchTo) {
        longueur = longueur.switchMesure();
        positionSurMur = !isNull(positionSurMur) ? positionSurMur.switchMesure(): null;
        positionSurHayon = !isNull(positionSurHayon) ? positionSurHayon.switchMesure(): null;
    }

    public Mesure getLongueur() {
        return longueur;
    }

    public double getHauteurHayonEnPouce(Hayon hayon) {
        //Calculer hauteur hayon
        double hayonY1 = hayon.getArea().getBounds2D().getMinY();
        double hayonY2 = hayon.getArea().getBounds2D().getMaxY();
        double hayonLengthInPixel = hayonY2-hayonY1;
            
        hauteurHayonEnPouce = (hayonLengthInPixel/Zoom.amount);
        
        //Longueur ideal du ressort à gaz en extension
        longueurIdealEnExtension = ((hauteurHayonEnPouce)*60)/100;
        
        
        
        boolean trouve = false;
        for(int i = 0;i<longeursMcMaster.length && !trouve;i++){
            if(longeursMcMaster[i] > longueurIdealEnExtension){
                trouve = true;
                longueurIdealEnExtension = longeursMcMaster[i];
            }
        }
        
        switch(Double.toString(longueurIdealEnExtension)){
            case "7.01":
                strokeLenth = 1.97;
                noMcMaster = "4138T51";
                break;
            case "7.4":
                strokeLenth = 2.36;
                noMcMaster = "4138T52";
                break;
            case "9.65":
                strokeLenth = 3.54;
                noMcMaster = "4138T53";
                break;
            case "12.2":
                strokeLenth = 3.94;
                noMcMaster = "4138T54";
                break;
            case "13.19":
                strokeLenth = 4.92;
                noMcMaster = "4138T71";
                break;
            case "15.24":
                strokeLenth = 5.47;
                noMcMaster = "4138T55";
                break;
            case "17.13":
                strokeLenth = 6.3;
                noMcMaster = "4138T56";
                break;
            case "19.72":
                strokeLenth = 7.87;
                noMcMaster = "4138T57";
                break;
            case "20.12":
                strokeLenth = 8.27;
                noMcMaster = "4138T58";
                break; 
            case "27.87":
                strokeLenth = 10.24;
                noMcMaster = "4138T61";
                break;
            case "29.49":
                strokeLenth = 12.8;
                noMcMaster = "4138T62";
                break;
            case "35.43":
                strokeLenth = 16.14;
                noMcMaster = "4138T63";
                break;
        }
        
        
        
            
        //Dead Weight in Newton
        deadWeight = hayon.getPoids()*4.4482216;
        
        //Power Arm length in mm (CHANGER 12.8)
        centerPoint = (strokeLenth*85)/100;
        powerArmLengthEnMM = centerPoint*25.4;
        
        //Center of Gravity Length in mm
        centreGraviteEnMM = (hauteurHayonEnPouce*25.4)/2;
        
        //Force required in Newton
        forceRequisEnNewton = (deadWeight*centreGraviteEnMM)/(powerArmLengthEnMM*nombreSprings);
        
        //Safety Factor in Newton 
        safetyFacteurEnNewton = 0.0;
            
        if(deadWeight < 300)
            safetyFacteurEnNewton = forceRequisEnNewton*0.1;
        else
            safetyFacteurEnNewton = 50.0;
        
        //Fore Requis pour le ressort a gaz
        force = (int)((forceRequisEnNewton+safetyFacteurEnNewton)*0.224808943871);
        
        
        
        return hauteurHayonEnPouce;
        
    }

    public double getLongueurIdealEnExtension() {
        
        return longueurIdealEnExtension;
    }

    public int getNombreSprings() {
        return nombreSprings;
    }

    public double getDeadWeight() {
        
        return deadWeight;
    }

    public double getCentreGraviteEnMM() {
        
        return centreGraviteEnMM;
    }

    public double getStrokeLenth() {
        
        return strokeLenth;
    }

    public double getCenterPoint() {
        return centerPoint;
    }

    public double getPowerArmLengthEnMM() {
        
        return powerArmLengthEnMM;
    }

    public double getForceRequisEnNewton() {
        
        return forceRequisEnNewton;
    }

    public double getSafetyFacteurEnNewton() {
        
        return safetyFacteurEnNewton;
    }

    public String getNoMcMaster() {
        return noMcMaster;
    }

    
    
    
}
