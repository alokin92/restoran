/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.so;

import database.DBBroker;

/**
 *
 * @author nikola
 */
public abstract class OpstaSistemskaOperacija {
    
    public final void izvrsenjeSO(Object obj) throws Exception {
        try {
            ucitajDriver();
            proveriPreduslov(obj);
            izvrsiKonkretnuOperaciju(obj);
            commitTransakcije();
        } catch (Exception ex) {
            rollbackTransakcije();
            throw ex;
        } finally {
            zatvoriKonekciju();
        }
        
    }

    private void ucitajDriver() throws Exception {
        DBBroker.getInstance().uspostaviKonekciju();
    }

    // Specificno za svaku SO
    protected abstract void proveriPreduslov(Object obj) throws Exception;

    // Specificno za svaku SO
    protected abstract void izvrsiKonkretnuOperaciju(Object obj) throws Exception;

    private void commitTransakcije() throws Exception {
        DBBroker.getInstance().commitTransakciju();
    }

    private void rollbackTransakcije() throws Exception {
        DBBroker.getInstance().rollbackTransakciju();
    }

    private void zatvoriKonekciju() throws Exception {
        DBBroker.getInstance().zatvoriKonekciju();
    }
    
}
