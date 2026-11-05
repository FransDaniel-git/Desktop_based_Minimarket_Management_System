/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DbConnection;
import interfaceDAO.InterfaceCreate;
import interfaceDAO.InterfaceDelete;
import interfaceDAO.InterfaceRead;
import model.Produk;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.List;
import interfaceDAO.InterfaceReadByNama;
import interfaceDAO.InterfaceSearchById;
import java.util.ArrayList;
import interfaceDAO.InterfaceUpdate;

/**
 *
 * @author LENOVO
 */
public class ProdukDAO implements InterfaceCreate<Produk>, InterfaceRead<Produk>, InterfaceUpdate<Produk, Integer>, InterfaceDelete<Integer>, InterfaceReadByNama<Produk>, InterfaceSearchById<Produk, Integer>{

    @Override
    public void create(Produk p) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "INSERT INTO `produk`(`nama`, `tipe`, `harga`, `stok`) "
                + "VALUES (?,?,?,?);";
        System.out.println("Creating produk....");
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setString(1, p.getNama());
            statement.setString(2, p.getTipe());
            statement.setDouble(3, p.getHarga());
            statement.setInt(4, p.getStok());
            int result = statement.executeUpdate();
            System.out.println("Success creating " +result+ " produk....");
            statement.close();
        }catch(Exception e){
            System.out.println("Error creating produk....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
        }
    }          

    @Override
    public List<Produk> read() {
        int i=0;
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `produk`;";
        System.out.println("Reading produk....");
        List<Produk> listProduk = new ArrayList<Produk>();
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    i++;
                    listProduk.add(new Produk(
                    rs.getInt("id_produk"),
                    rs.getString("nama"),
                    rs.getString("tipe"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")));
                }
                System.out.println("Found " +i+ " produk....");
            }else{
                System.out.println("Not Found Any Produk....");
            }
            System.out.println("Success Reading produk....");
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error reading produk....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
            return listProduk;
        }
    }

    @Override
    public void update(Produk p, Integer id_produk) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "UPDATE `produk` "
                + "SET `nama`= ?,`tipe`= ?,`harga`= ?,`stok`= ? "
                + "WHERE id_produk = ?;";
        System.out.println("Updating produk....");
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setString(1, p.getNama());
            statement.setString(2, p.getTipe());
            statement.setDouble(3, p.getHarga());
            statement.setInt(4, p.getStok());
            statement.setInt(5, id_produk);
            int result = statement.executeUpdate();
            System.out.println("Success Updating " +result+ " Produk....");
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating Produk....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
        }
    }

    @Override
    public void delete(Integer id_produk) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "DELETE FROM `produk` WHERE id_produk = ?;";
        System.out.println("Deleting produk....");
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_produk);
            int result = statement.executeUpdate();
            System.out.println("Success Deleting " +result+ " Produk....");
            statement.close();
        }catch(Exception e){
            System.out.println("Error Deleting Produk....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
        }
    }

    @Override
    public List<Produk> readByNama(String nama) {
        int i=0;
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `produk` "
                + "WHERE nama LIKE ?;";
        System.out.println("Reading produk by nama....");
        List<Produk> listProduk = new ArrayList<Produk>();
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setString(1, "%" +nama+ "%");
            ResultSet rs = statement.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    i++;
                    listProduk.add(new Produk(
                    rs.getInt("id_produk"),
                    rs.getString("nama"),
                    rs.getString("tipe"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")));
                }
                System.out.println("Found " +i+ " produk....");
            }else{
                System.out.println("Not Found Any Produk....");
            }
            System.out.println("Success Reading produk by nama....");
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error reading produk by nama....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
            return listProduk;
        }
    }
    
    public void batchUpdate(List<Produk> listProduk){
        int i=0;
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(false);
        String sql = "UPDATE `produk` "
                + "SET `nama`= ?,`tipe`= ?,`harga`= ?,`stok`= ? "
                + "WHERE id_produk = ?;";
        System.out.println("Updating produk....");
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            for(Produk p: listProduk){
                statement.setString(1, p.getNama());
                statement.setString(2, p.getTipe());
                statement.setDouble(3, p.getHarga());
                statement.setInt(4, p.getStok());
                statement.setInt(5, p.getId_produk());
                statement.addBatch();
                i++;
            }
            statement.executeBatch();
            System.out.println("Success Updating " +i+ " Produk....");
            statement.close();
            DbConnection.CON.commit();
            System.out.println("Success commit....");
        }catch(Exception e){
            System.out.println("Error Updating Produk....");
            System.out.println(e.toString());
            try{
                if(DbConnection.CON!=null){
                    DbConnection.CON.rollback();
                    System.out.println("Success rollback....");
                }
            }catch(Exception e1){
                System.out.println("Error rollback....");
                System.out.println(e1.toString());
            }
        }finally{
            DbConnection.closeConnection();
        }
    }

    @Override
    public Produk searchById(Integer id_produk) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `produk` WHERE id_produk = ?;";
        System.out.println("Searching produk by id....");
        Produk p = null;
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_produk);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                p = new Produk(
                    rs.getInt("id_produk"),
                    rs.getString("nama"),
                    rs.getString("tipe"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
                System.out.println("Found produk....");
            } else {
                System.out.println("Not Found Produk....");
            }
            System.out.println("Success Searching produk by id....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching produk by id....");
            System.out.println(e.toString());
        }finally {
            DbConnection.closeConnection();
            return p;
        }
    }
    
    public Produk searchByIdWithParameterConnection(Connection CON, Integer id_produk) {
        String sql = "SELECT * FROM `produk` WHERE id_produk = ?;";
        System.out.println("Searching produk by id....");
        Produk p = null;
        try{
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_produk);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                p = new Produk(
                    rs.getInt("id_produk"),
                    rs.getString("nama"),
                    rs.getString("tipe"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );
                System.out.println("Found produk....");
            } else {
                System.out.println("Not Found Produk....");
            }
            System.out.println("Success Searching produk by id....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching produk by id....");
            System.out.println(e.toString());
        }finally {
            return p;
        }
    }
    
    public void updateWithParameterConnection(Connection CON, Produk p, Integer id_produk) {
        String sql = "UPDATE `produk` "
                + "SET `nama`= ?,`tipe`= ?,`harga`= ?,`stok`= ? "
                + "WHERE id_produk = ?;";
        System.out.println("Updating produk....");
        
        try{
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setString(1, p.getNama());
            statement.setString(2, p.getTipe());
            statement.setDouble(3, p.getHarga());
            statement.setInt(4, p.getStok());
            statement.setInt(5, id_produk);
            int result = statement.executeUpdate();
            System.out.println("Success Updating " +result+ " Produk....");
            statement.close();
        }catch(Exception e){
            System.out.println("Error Updating Produk....");
            System.out.println(e.toString());
            throw new RuntimeException("Gagal mengubah produk", e);
        }
    }
}
