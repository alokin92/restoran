/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domen.Konobari;
import domen.Narudzbina;
import domen.Pice;
import domen.Sto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Material;

/**
 *
 * @author Nikola
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;
    public final static long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

    public static int differenceInDays(Date from, Date to) {
        return (int) ((to.getTime() - from.getTime()) / MILLISECONDS_IN_DAY);
    }

    public DBBroker() {
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public void uspostaviKonekciju() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         * 
         * DODAJ NAZIV BAZE
         * 
         */
        String url = "jdbc:mysql://localhost/restoran";
        String user = "root";
        String pass = "";
        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void commitTransakciju() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollbackTransakciju() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zatvoriKonekciju() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Pice> vratiPice() {
        List<Pice> listaPica = new ArrayList<>();
        String upit = "SELECT * FROM pice";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Pice p = new Pice();
                p.setPiceID(rs.getInt("piceid"));
                p.setCena(rs.getDouble("cena"));
                p.setNazivPica(rs.getString("nazivpica"));
                listaPica.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPica;
    }

    public List<Narudzbina> vratiNarudzbine(int brStola) {
        List<Narudzbina> lista = new ArrayList<>();
        String upit = "SELECT * FROM narudzbina n inner join sto s on(n.stoid=s.stoid) inner join pice p on(n.piceid=p.piceid) where n.stoid=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(upit);
            ps.setInt(1, brStola);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Sto s = new Sto();
                Pice pi = new Pice();
                Narudzbina p = new Narudzbina();
                s.setStoID(brStola);
                p.setSto(s);
                p.setRbNarudzbine(rs.getInt("rbnarudzbine"));
                p.setKolicina(rs.getInt("kolicina"));
                p.setIznos(rs.getDouble("iznos"));
                pi.setPiceID(rs.getInt("piceid"));
                pi.setCena(rs.getDouble("cena"));
                pi.setNazivPica(rs.getString("nazivpica"));
                p.setPice(pi);
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void updateNarudzbine(Narudzbina nar) {
        PreparedStatement ps = null;
        String upit = "";

        upit = "insert into narudzbina values(?,?,?,?,?)";

        try {

            ps = connection.prepareStatement(upit);

            ps.setInt(1, nar.getSto().getStoID());
            ps.setInt(2, nar.getRbNarudzbine());
            ps.setInt(3, nar.getKolicina());
            ps.setDouble(4, nar.getIznos());
            ps.setInt(5, nar.getPice().getPiceID());
            ps.executeUpdate();

            ps.close();
        } catch (Exception ex) {
            upit = "update narudzbina set kolicina=?, iznos=? where rbnarudzbine=? and stoid=?";

            try {

                ps = connection.prepareStatement(upit);

                ps.setInt(1, nar.getKolicina());
                ps.setDouble(2, nar.getIznos());
                ps.setInt(3, nar.getRbNarudzbine());
                ps.setInt(4, nar.getSto().getStoID());
                ps.executeUpdate();

                ps.close();
            } catch (SQLException exe) {
                System.out.println("puko sam");
            }
        }

    }
    public void obrisiNarudzbinu(int idStola){
        String upit="Delete from narudzbina where stoid=?";
        PreparedStatement ps=null;
        try {
            ps=connection.prepareStatement(upit);
            ps.setInt(1, idStola);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean vratiKonobare(Konobari k){
        String upit="select * from konobari where korisnik LIKE ? and lozinka LIKE ?";
        PreparedStatement ps=null;
        try {
            ps=connection.prepareStatement(upit);
            ps.setString(1, k.getKorisnik());
            ps.setString(2, k.getLozinka());
            ResultSet rs=ps.executeQuery();
            rs.next();
            if(rs.getString("korisnik").equals(k.getKorisnik()) && rs.getString("lozinka").equals(k.getLozinka())){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
