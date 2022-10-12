package ca.ulaval.glo2004.MicroRoulotte;

import java.util.ArrayList;

public class ProfilClassique implements Profil,java.io.Serializable{
    ArrayList<Ellipse> listeEllipses;

    private boolean afficheEllipses = false;
    
    public ProfilClassique(ArrayList<Ellipse> listeEllipses) {
        this.listeEllipses = listeEllipses;
    }

    public ArrayList<Ellipse> getListeEllipses() {
        return listeEllipses;
    }

    public void setListeEllipses(ArrayList<Ellipse> listeEllipses) {
        this.listeEllipses = listeEllipses;
    }

    public boolean isAfficheEllipses() {
        return afficheEllipses;
    }

    public void setAfficheEllipses(boolean afficheEllipses) {
        this.afficheEllipses = afficheEllipses;
    }

    @Override
    public void switchMesure() {
        for(Ellipse e: listeEllipses){
            e.switchMesure();
        }
    }
}
