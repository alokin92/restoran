/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Nikola
 */
public class Pice {
    private int piceID;
    private String nazivPica;
    private double cena;

    public Pice() {
    }

    public int getPiceID() {
        return piceID;
    }

    public void setPiceID(int piceID) {
        this.piceID = piceID;
    }

    public String getNazivPica() {
        return nazivPica;
    }

    public void setNazivPica(String nazivPica) {
        this.nazivPica = nazivPica;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
return nazivPica;    }
    
}
