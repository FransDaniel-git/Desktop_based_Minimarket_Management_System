/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author LENOVO
 */
public class DbConnection {
    public static Connection CON;
    public static final String URL = "jdbc:mysql://";
    // Bisa diarahkan ke database uji tanpa mengubah kode: -Datmart.db.name=db_mart_e2e
    public static final String DBNAME = System.getProperty("atmart.db.name", "db_mart");
    public static final String PATH = "localhost:3306/" + DBNAME
            + "?useSSL=false"
            + "&allowPublicKeyRetrieval=true"
            + "&serverTimezone=Asia/Jakarta"
            + "&characterEncoding=UTF-8";
    
    public static void makeConnection(){
        System.out.println("Opening database....");
        try{
            CON=DriverManager.getConnection(URL + PATH, "root", "");
            System.out.println("Success....");
        }catch(Exception e){
            System.out.println("Error opening database [!]");
            System.out.println(e.toString());
        }
    }
    
    public static void closeConnection(){
        System.out.println("closing database....");
        try{
            CON.close();
            System.out.println("Success....");
        }catch(Exception e){
            System.out.println("Error closing database [!]");
            System.out.println(e.toString());
        }
    }
    
    public static void setAutoCommit(boolean value){
        System.out.println("Set auto commit to: " +value);
        try{
            CON.setAutoCommit(value);
            System.out.println("Success....");
        }catch(Exception e){
            System.out.println("Error set auto commit....");
            System.out.println(e.toString());
        }
    }
}
