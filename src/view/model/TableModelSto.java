/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.model;

import domen.Narudzbina;
import domen.Pice;
import domen.Sto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nikola
 */
public class TableModelSto extends AbstractTableModel {

    List<Narudzbina> lista;
    String[] kolone = new String[]{"Naziv pica", "Cena", "Kolicina"};

    public TableModelSto(List<Narudzbina> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        if (lista == null) {
            return 0;
        }
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Narudzbina s = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                if (s.getPice() == null) {
                    return "N/A";
                }
                return s.getPice().getNazivPica();
            }
            case 1: {
                if (s.getPice() == null) {
                    return "N/A";
                }
                return s.getPice().getCena();
            }
            case 2:
                return s.getKolicina();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void dodajRed(int stoid,Pice p, int kolicina) {
        
        Sto s=new Sto();
        s.setStoID(stoid);
        Pice pic = new Pice();
        Narudzbina narud = new Narudzbina();
        
        for (Narudzbina n : lista) {
            if (n.getPice().getPiceID() == p.getPiceID()) {
                n.setKolicina(n.getKolicina() + kolicina);
                n.setIznos(n.getPice().getCena()*n.getKolicina());
                n.setSto(s);
                fireTableDataChanged();
                return;
            }
        }
        pic.setPiceID(p.getPiceID());
        pic.setNazivPica(p.getNazivPica());
        pic.setCena(p.getCena());
        if(lista==null || lista.size()==0)narud.setRbNarudzbine(1);
        else{narud.setRbNarudzbine(lista.get(lista.size()-1).getRbNarudzbine()+1);}
        narud.setPice(pic);
        narud.setKolicina(kolicina);
        narud.setIznos(pic.getCena()*kolicina);
        narud.setSto(s);
        
        lista.add(narud);
        fireTableDataChanged();
        

    }

    public List<Narudzbina> getLista() {
        return lista;
    }
}
