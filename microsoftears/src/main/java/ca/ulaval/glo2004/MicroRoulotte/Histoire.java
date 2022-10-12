/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.ulaval.glo2004.MicroRoulotte;

/**
 *
 * @author Gabriel
 */
import java.util.Stack;

//Histoire de l'application pour undo redo
public class Histoire implements java.io.Serializable{
    
    ApplicationEtat courrant;
    public Stack<ApplicationEtat> etats = new Stack<ApplicationEtat>();

    public void push(ApplicationEtat applicationEtat){
        if(!etats.empty() && courrant != null){
            for(int x = etats.indexOf(courrant)+1; x < etats.size();x++){
                etats.remove(x);
                x--;
            }
        }
        
        etats.add(applicationEtat);
        courrant = applicationEtat;
        
    }
    
    public ApplicationEtat undo(){
        if(etats.indexOf(courrant) > 0){
            int nextIndex = etats.indexOf(courrant)-1;
            ApplicationEtat nextState = etats.get(nextIndex);
            courrant = nextState;
            return nextState;
        }
        else
            return courrant;
        
    }
    
    public ApplicationEtat redo(){
        if(etats.indexOf(courrant) < etats.size()-1){
            int nextIndex = etats.indexOf(courrant)+1;
            ApplicationEtat nextState = etats.get(nextIndex);
            courrant = nextState;

            return nextState;
        }
        else
            return courrant;
        
    }
    
    
    public Boolean isCourrantOnFirstElement(){
        if(etats.indexOf(courrant) == 0)
            return true;
        else
            return false;
    }
    
    public Boolean isCourrantOnLastElement(){
        if(etats.indexOf(courrant) == etats.size()-1)
            return true;
        else
            return false;
    }
    
    
}
