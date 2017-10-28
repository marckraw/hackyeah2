package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class automateImport
{
    public static void main(String[] args) 
    {
        DBase db = new DBase();
        Connection conn = db.connect(
                "jdbc:mysql://localhost:3306/db1","root","root");
        db.importData(conn);
    }
 
}
 
class DBase
{
    public DBase()
    {
    }
 
            public Connection connect(String db_connect_str, 
  String db_userid, String db_password)
    {
        Connection conn;
        try
        {
            Class.forName( 
                    "com.mysql.jdbc.Driver").newInstance();
 
            conn = DriverManager.getConnection(db_connect_str,
                    db_userid, db_password);
         
        }
        catch(Exception e)
        {
            e.printStackTrace();
            conn = null;
        }
 
        return conn;    
    }
     
            public void importData(Connection conn)
    {
        Statement stmt;
        String query;
 
        try
        {
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            //query = "LOAD DATA LOCAL INFILE '" + "slownik.csv" + "' INTO TABLE slownik FIELDS TERMINATED BY ';'" + " LINES TERMINATED BY '\n' (idslownik,ulica,adr_nr,nazwa_przedrostek_a_czesc,nazwa_przedrostek_b_czesc,nazwa_nazwa_a_czesc,nazwa_glowna_czesc,typ_ciagu_komunikacyjnego,teryt_kod,dzl_numer,dzl_nazwa,geom_x,geom_y) ";
            //load wejsciowe
            query = "LOAD DATA LOCAL INFILE '" + "dane_wejsciowe.csv" + "' INTO TABLE wejsciowe FIELDS TERMINATED BY ';'" + " LINES TERMINATED BY '\n' (idwejsciowe,idadres,ul,nr_bud,data,miejsce,typ_miejsc,stan_nawie,oznakowani,oswietleni,warunki_at,wypadek,ilosc_ucze,wiek_spraw,pojazd_spr,wiek_piesz,lekko_rann,ciezko_ran,zabici) ";

            stmt.executeUpdate(query);
                 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            stmt = null;
        }
    }
};