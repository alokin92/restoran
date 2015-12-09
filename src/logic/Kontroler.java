/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import domen.Konobari;
import domen.Narudzbina;
import domen.Pice;
import domen.Sto;
import java.util.List;
import logic.so.OpstaSistemskaOperacija;
import logic.so.SOObrisiNarudzine;
import logic.so.SOSacuvajUBazi;
import logic.so.SOVratiKorisnike;
import logic.so.SOVratiPica;
import logic.so.SOVratiNarudzbe;


/**
 *
 * @author Nikola
 */
public class Kontroler {
    private static Kontroler instance;

    public Kontroler() {
    }

    public static Kontroler getInstance() {
       if(instance==null)instance=new Kontroler(); 
       return instance;
    }
   
   public List<Pice> vratiPica() throws Exception{
       OpstaSistemskaOperacija oso=new SOVratiPica();
       oso.izvrsenjeSO(null);
       return ((SOVratiPica)oso).getListaPica();
   }
      public List<Narudzbina> vratiNarudzbe(int i) throws Exception{
          OpstaSistemskaOperacija oso=new SOVratiNarudzbe();
          oso.izvrsenjeSO(i);
          return ((SOVratiNarudzbe)oso).getLista();
      }
   public void sacuvajUpdateUBazi(List<Narudzbina> lista) throws Exception{
       OpstaSistemskaOperacija oso=new SOSacuvajUBazi();
       for(Narudzbina n:lista){
           oso.izvrsenjeSO(n);
       }
   }
   public void obrisiIzBaze(int stoid) throws Exception{
       OpstaSistemskaOperacija oso=new SOObrisiNarudzine();
       oso.izvrsenjeSO(stoid);
   }
   public boolean vratiKonobare(Konobari k) throws Exception{
       OpstaSistemskaOperacija oso=new SOVratiKorisnike();
       oso.izvrsenjeSO(k);
       return ((SOVratiKorisnike)oso).isI();
   }
}
