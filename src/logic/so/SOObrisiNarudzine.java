/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.so;

import database.DBBroker;

/**
 *
 * @author Nikola
 */
public class SOObrisiNarudzine extends OpstaSistemskaOperacija{

    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
        DBBroker.getInstance().obrisiNarudzbinu((int) obj);    }
    
    
}
