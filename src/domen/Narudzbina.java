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
public class Narudzbina {
    private Sto sto;
    private int rbNarudzbine;
    private int kolicina;
    private double iznos;
    private Pice pice;

    public Pice getPice() {
        return pice;
    }

    public void setPice(Pice pice) {
        this.pice = pice;
    }
    

    public Narudzbina() {
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    public int getRbNarudzbine() {
        return rbNarudzbine;
    }

    public void setRbNarudzbine(int rbNarudzbine) {
        this.rbNarudzbine = rbNarudzbine;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    @Override
    public String toString() {
return getPice().getNazivPica()+ "      "+(getPice().getCena()*kolicina)+" din";    }
    
}
