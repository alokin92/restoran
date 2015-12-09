/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.so;

import database.DBBroker;
import domen.Pice;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOVratiPica extends OpstaSistemskaOperacija{
List<Pice> listaPica;

    public List<Pice> getListaPica() {
        return listaPica;
    }
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
listaPica=DBBroker.getInstance().vratiPice();    }
    
}
