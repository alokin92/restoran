/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.so;

import database.DBBroker;
import domen.Narudzbina;
import domen.Sto;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class SOVratiNarudzbe extends OpstaSistemskaOperacija{
List<Narudzbina> lista;

    public List<Narudzbina> getLista() {
        return lista;
    }
    @Override
    protected void proveriPreduslov(Object obj) throws Exception {
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(Object obj) throws Exception {
lista=DBBroker.getInstance().vratiNarudzbine((int) obj);    }
    
}
