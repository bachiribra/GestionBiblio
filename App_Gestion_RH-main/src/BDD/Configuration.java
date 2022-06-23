/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDD;

import static BDD.Parametres.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ali Abdoulkader Ali
 */
public class Configuration {

    static Connection connection;
    static Statement statement;
    static PreparedStatement pst;
    static ResultSet rs;
    static String SQL;

    /* Connexion à la base de données */
    public static Connection dbConn() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(HOST_DB, USERNAME_DB, PASSWORD_DB);

            // System.out.println("connexion Réussie");
        } catch (ClassNotFoundException ex) {
            // System.out.println("echec");
            //Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur");
        } catch (SQLException ex) {
            //Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur");
        }
        return connection;
    }

    public static Connection closeConn() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    /* Execution des requêtes */
    public static ResultSet executionQuery(String sql) {
        dbConn();
        ResultSet resultSet = null;
        try {
            //statement = connection.createStatement();
            pst = dbConn().prepareStatement(sql);
            resultSet = pst.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /* Mise à jour de l'execution */
    public static String executionUpdate(String sql) {

        String result = "";
        try {
            dbConn();

            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;
            //System.out.println("réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /* Insertion des requêtes */
    public static String insert(String table, String[] colonnes, Object[] valeurs) {
        SQL = "INSERT INTO " + table + " (";

        for (int i = 0; i < colonnes.length; i++) {
            SQL += colonnes[i];

            if (i < colonnes.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES (";

        for (int i = 0; i < valeurs.length; i++) {
            SQL += "'" + valeurs[i] + "'";

            if (i < valeurs.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        System.out.println(SQL);
        return executionUpdate(SQL);
    }

    /* Affichage des tables */
    public static ResultSet selectAll(String table) {
        dbConn();
        SQL = "SELECT * FROM " + table;
        System.out.println(SQL);
        return executionQuery(SQL);
    }

    public static ResultSet selectCondition(String table, String valeur, String etat) {
        dbConn();
        SQL = "SELECT COUNT(*) FROM " + table + " WHERE "+valeur+"=" + etat;
        System.out.println(SQL);
        return executionQuery(SQL);

    }
    
     public static ResultSet selectInnerJoin(String table, String table1, String colonnes, String etat) {
        dbConn();
        SQL = "SELECT "+colonnes+" FROM " + table + " INNER JOIN "+table1+" USING(" + etat+")";
        System.out.println(SQL);
        return executionQuery(SQL);

      }
     
     public static ResultSet selectInnerJoin1(String table, String table1, String table2,String colonnes, String etat, String etat1) {
        dbConn();
        SQL = "SELECT "+colonnes+" FROM " + table + " INNER JOIN "+table1+" USING(" + etat+")"+" INNER JOIN "+table2+" USING(" + etat1+")";
        System.out.println(SQL);
        return executionQuery(SQL);

      }
        
        
        public static ResultSet selectCondition1(String table,String etat) {
        dbConn();
        SQL = "SELECT * FROM " + table + " WHERE " + etat;
        System.out.println(SQL);
        return executionQuery(SQL);

    }
    
        public static ResultSet selectConditionLogin(String table, String etat) {
        dbConn();
        SQL = "SELECT * FROM " + table + " WHERE "+ etat;
        System.out.println(SQL);
        return executionQuery(SQL);

    }

    public static ResultSet searchByName(String table, String colonnes, String etat) {
        SQL = "SELECT "+colonnes +" FROM " + table + " WHERE titre_ouv LIKE '" + etat + "%'";
        return executionQuery(SQL);
    }
    
   /* public static ResultSet searchByNameEmprunt(String table, String colonnes, String etat) {
        SQL = "SELECT "+colonnes +" FROM " + table + "INNER JOIN ouvrage USING(num_ouvrage) "
                + " INNER JOIN lecteur USING (num_lecteur) "
                + "WHERE titre_ouv LIKE '" + etat + "%'";
        return executionQuery(SQL);
    }*/
        
    public static ResultSet searchByJob(String table, String colonnes, String etat) {
        SQL = "SELECT " +colonnes+ " FROM " + table + " WHERE poste LIKE '" + etat + "%'";
        return executionQuery(SQL);
    }
    

    public static ResultSet select(String[] nomColonne, String table) {
        dbConn();
        SQL = "SELECT ";
        for (int i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += " ,";
            }
        }
        SQL += " FROM " + table;
        return executionQuery(SQL);
    }

     /*   public static ResultSet selectEmprunt(String etat, String table) {
        dbConn();
        SQL = "SELECT ";
        for (int i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += " ,";
            }
        }
        SQL += " FROM " + table;
        return executionQuery(SQL);
    }*/
        
    public static String updateTable(String table, String[] colonnes, Object[] valeurs, String valeur, String cni) {
        dbConn();

        SQL = "UPDATE " + table + " SET ";
        for (int i = 0; i < colonnes.length; i++) {
            SQL += colonnes[i] + "='" + valeurs[i] + "'";

            if (i < colonnes.length - 1) {
                SQL += ",";
            }
        }
        SQL += " WHERE " + valeur + "=" + cni;

        System.out.println(SQL);
        return executionUpdate(SQL);
    }

    public static String deleteColumn(String table, String valeur, String cni) {
        dbConn();
        SQL = "DELETE FROM " + table + " WHERE "+valeur+"=" + cni;

        return executionUpdate(SQL);
    }
    
        public static String deleteOuvrages(String table, String valeur) {
        dbConn();
        SQL = "DELETE FROM " + table + " WHERE num_ouv=" + valeur;

        return executionUpdate(SQL);
    }
    
    public static ResultSet Compteur(String table){
        dbConn();
        SQL = "SELECT COUNT(*) FROM "+table;
        
        return executionQuery(SQL);
    }

}
