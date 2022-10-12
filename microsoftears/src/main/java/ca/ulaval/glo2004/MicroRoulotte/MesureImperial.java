/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Camelia
 */
public class MesureImperial implements Mesure,java.io.Serializable{
    private int entier;
    private int num;
    private int denum; // enum ?

    public MesureImperial(String mesure) throws Exception{
        
        String parsedEntier = "0";
        String parsedNum = "0";
        String parsedDenum = "1";
        String word = "";
        
        // trim string mesure
        mesure = mesure.trim();
        int i = 0;
        int length = mesure.length();
        
        if(i < length){
            while((i < length)){
                char currentChar = mesure.charAt(i);
                if(Character.isDigit(currentChar)){
                    word += currentChar;
                } else {
                    
                    if(mesure.contains("/")){
                        if(currentChar == ' '){
                            parsedEntier = word;
                        }
                                                
                        if(currentChar == '/'){
                            parsedNum = word;
                        }
                    }
                    
                    word = "";
                }
               
                i++;
            }
            
            if(mesure.contains("/")){
                parsedDenum = word;
                if(Integer.parseInt(parsedDenum) == 0){
                    throw new Exception("Division par zéro");
                }
            } else {
                parsedEntier = word;
            }
        }
        

        
        this.entier = Integer.parseInt(parsedEntier);
        this.num = Integer.parseInt(parsedNum);
        this.denum = Integer.parseInt(parsedDenum);

    }

    
    
    public MesureImperial(int entier) {
        this.entier = entier;
        this.num = 0;
        this.denum = 1;
    }
    
    public MesureImperial(double pixels) {

        double tolerance = 1.0E-6;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = pixels;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(pixels-h1/k1) > pixels*tolerance);

        this.num = (int)(h1/Zoom.amount);
        this.denum = Math.max((int)(k1/Zoom.amount),1);
    }
    
    public MesureImperial(int entier, int num, int denum) throws Exception {
        if(denum == 0){
            throw new Exception("Division par zéro");
        }
        this.entier = entier;
        this.num = num;
        this.denum = denum;        
    }
    
    @Override
    public int convertToPixel() {
        return (int) (getValueAsFloat()*Zoom.amount);
    }
    
    public int getEntier() {
        return entier;
    }
    
    
    @Override
    public String getString() {
        if(this.denum > 0 && this.num > 0){
                if(this.entier == 0){
                    return Integer.toString(this.num) + "/"+ Integer.toString(this.denum);
                } 
                else {
                    return Integer.toString(this.entier) + " " + Integer.toString(this.num) + "/"+ Integer.toString(this.denum);
                }
            }
            
            
        else {
            return Integer.toString(this.entier);
        }
    
    }
    
    public void setEntier(int entier) {
        this.entier = entier;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDenum() {
        return denum;
    }

    public void setDenum(int denum) throws Exception {
        if(denum == 0){
            throw new Exception("Division par zéro");
        }
        this.denum = denum;
    }
    
    @Override
    public MesureImperial inverse(){
        try{
            return new MesureImperial(-this.entier,-this.num, this.denum);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Mesure add(MesureImperial mesure) {
        int entier = this.entier + mesure.entier;
        int ppcm = Utils.ppcm(this.denum, mesure.denum);
        
        int thisToPpcm = ppcm / this.denum;
        int mesureToPpcm = ppcm / mesure.denum;
        
        int num = (this.num * thisToPpcm) + (mesure.num * mesureToPpcm);
        int denum = ppcm;
        
        try{
            return new MesureImperial(entier, num, denum);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public Mesure add(Mesure mesure) {
        MesureImperial m = (MesureImperial)mesure;
        int entier = this.entier + m.entier;
        int ppcm = Utils.ppcm(this.denum, m.denum);
        
        int thisToPpcm = ppcm / this.denum;
        int mesureToPpcm = ppcm / m.denum;
        
        int num = (this.num * thisToPpcm) + (m.num * mesureToPpcm);
        int denum = ppcm;
        
        try{
            return new MesureImperial(entier, num, denum);
        } catch (Exception e){
            return null;
        }
    } 

    @Override
    public Mesure sub(Mesure mesure) {
        MesureImperial m = (MesureImperial)mesure;
        int entier = this.entier + m.entier;
        int ppcm = Utils.ppcm(this.denum, m.denum);
        
        int thisToPpcm = ppcm / this.denum;
        int mesureToPpcm = ppcm / m.denum;
        
        int num = (this.num * thisToPpcm) - (m.num * mesureToPpcm);
        int denum = ppcm;
        
        try{
            return new MesureImperial(entier, num, denum);
        } catch (Exception e){
            return null;
        }
    }       
        
        
    @Override
    public Mesure switchMesure() {
        float value = getValueAsFloat() * Utils.INCH_TO_MM;
        //Arrondi les valeurs afin d'être plus compréhensible
        return new MesureMetrique(Math.round(value * 10000d) / 10000d);
    }

    @Override
    public float getValueAsFloat() {
        return (float)((double) entier + ((double) num / (double) denum));
    }
}
