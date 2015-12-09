/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.so;

import database.DBBroker;
import domen.Konobari;

/**
 *
 * @author Nikola
 */
public class SOVratiKorisnike extends OpstaSistemskaOperacija {

    boolean i;

    public boolean isI() {
        return i;
    }

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        i = DBBroker.getInstance().vratiKonobare((Konobari) obj);
    }

}
