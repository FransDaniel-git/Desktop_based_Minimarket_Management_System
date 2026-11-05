/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DbConnection;
import interfaceDAO.InterfaceCreate;
import interfaceDAO.InterfaceDelete;
import interfaceDAO.InterfaceRead;
import interfaceDAO.InterfaceSearchById;
import interfaceDAO.InterfaceUpdate;
import model.Transaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class TransaksiDAO implements InterfaceCreate<Transaksi>, InterfaceRead<Transaksi>, InterfaceDelete<Integer>, InterfaceSearchById<Transaksi, Integer>, InterfaceUpdate<Transaksi, Integer>{
    
    @Override
    public void create(Transaksi t) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "INSERT INTO `transaksi`(`id_transaksi`, `tanggal`, `total`, `nama_kasir`, `nama_pembeli`) "
                + "VALUES (?,?,?,?,?)";
        System.out.println("Creating Transaksi....");
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            java.sql.Date tanggalSql = new java.sql.Date(t.getTanggal().getTime());
            statement.setInt(1, t.getId_transaksi());
            statement.setDate(2, tanggalSql);
            statement.setDouble(3, t.getTotal());
            statement.setString(4, t.getNama_kasir());
            statement.setString(5, t.getNama_pembeli());
            int result = statement.executeUpdate();
            System.out.println("Success Creating " +result+ " Transaksi....");
            statement.close();
        }catch(Exception e){
            System.out.println("Error Creating Transaksi....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
        }
    }

    @Override
    public List<Transaksi> read() {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        int i = 0;
        String sql = "SELECT * FROM `transaksi`";
        System.out.println("Reading Transaksi....");
        List<Transaksi> listTransaksi = new ArrayList<Transaksi>();
        java.util.Date tanggalUtil;
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    tanggalUtil = new java.util.Date(rs.getDate("tanggal").getTime());
                    listTransaksi.add(new Transaksi(
                    rs.getInt("id_transaksi"),
                    tanggalUtil,
                    rs.getDouble("total"),
                    rs.getString("nama_kasir"),
                    rs.getString("nama_pembeli")));
                    i++;
                }
                System.out.println("Found " +i+ " transaksi....");
            }else{
                System.out.println("Not Found Any Transaksi....");
            }
            System.out.println("Success Reading Transaksi....");
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error reading transaksi....");
            System.out.println(e.toString());
        }finally{
            DbConnection.closeConnection();
            return listTransaksi;
        }
    }

    @Override
    public void delete(Integer id_transaksi) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "DELETE FROM `transaksi` WHERE id_transaksi = ?;";
        System.out.println("Deleting transaksi....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            int result = statement.executeUpdate();
            System.out.println("Success Deleting " + result + " Transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Deleting Transaksi....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
        }
    }

    @Override
    public Transaksi searchById(Integer id_transaksi) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `transaksi` WHERE id_transaksi = ?;";
        System.out.println("Searching transaksi by id....");
        Transaksi t = null;
        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()){
                t = new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getDate("tanggal"),
                    rs.getDouble("total"),
                    rs.getString("nama_kasir"),
                    rs.getString("nama_pembeli")
                );
                System.out.println("Found transaksi....");
            } else {
                System.out.println("Not Found Transaksi....");
            }
            System.out.println("Success Searching transaksi by id....");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Searching transaksi by id....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
            return t;
        }
    }
    
    public void createWithParameterConnection(Connection CON, Transaksi t) {
        String sql = "INSERT INTO `transaksi`(`id_transaksi`, `tanggal`, `total`, `nama_kasir`, `nama_pembeli`) "
                + "VALUES (?,?,?,?,?)";
        System.out.println("Creating Transaksi....");
        
        try{
            PreparedStatement statement = CON.prepareStatement(sql);
            java.sql.Date tanggalSql = new java.sql.Date(t.getTanggal().getTime());
            statement.setInt(1, t.getId_transaksi());
            statement.setDate(2, tanggalSql);
            statement.setDouble(3, t.getTotal());
            statement.setString(4, t.getNama_kasir());
            statement.setString(5, t.getNama_pembeli());
            int result = statement.executeUpdate();
            System.out.println("Success Creating " +result+ " Transaksi....");
            statement.close();
        }catch(Exception e){
            System.out.println("Error Creating Transaksi....");
            System.out.println(e.toString());
            throw new RuntimeException("Gagal membuat transaksi", e);
        }
    }
    
    public void deleteWithParameterConnection(Connection CON, Integer id_transaksi) {
        String sql = "DELETE FROM `transaksi` WHERE id_transaksi = ?;";
        System.out.println("Deleting transaksi....");

        try {
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            int result = statement.executeUpdate();
            System.out.println("Success Deleting " + result + " Transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Deleting Transaksi....");
            System.out.println(e.toString());
            throw new RuntimeException("Gagal menghapus transaksi", e);
        }
    }
    
    public List<Transaksi> readWithParameterConnection(Connection CON){
        int i = 0;
        String sql = "SELECT * FROM `transaksi`";
        System.out.println("Reading Transaksi....");
        List<Transaksi> listTransaksi = new ArrayList<Transaksi>();
        java.util.Date tanggalUtil;
        
        try{
            PreparedStatement statement = CON.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    tanggalUtil = new java.util.Date(rs.getDate("tanggal").getTime());
                    listTransaksi.add(new Transaksi(
                    rs.getInt("id_transaksi"),
                    tanggalUtil,
                    rs.getDouble("total"),
                    rs.getString("nama_kasir"),
                    rs.getString("nama_pembeli")));
                    i++;
                }
                System.out.println("Found " +i+ " transaksi....");
            }else{
                System.out.println("Not Found Any Transaksi....");
            }
            System.out.println("Success Reading Transaksi....");
            rs.close();
            statement.close();
        }catch(Exception e){
            System.out.println("Error reading transaksi....");
            System.out.println(e.toString());
        }finally{
            return listTransaksi;
        }
    }
    
    public Transaksi searchByIdWithParameterConnection(Connection CON, Integer id_transaksi) {
        String sql = "SELECT * FROM `transaksi` WHERE id_transaksi = ?;";
        System.out.println("Searching transaksi by id....");
        Transaksi t = null;
        try {
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()){
                t = new Transaksi(
                    rs.getInt("id_transaksi"),
                    rs.getDate("tanggal"),
                    rs.getDouble("total"),
                    rs.getString("nama_kasir"),
                    rs.getString("nama_pembeli")
                );
                System.out.println("Found transaksi....");
            } else {
                System.out.println("Not Found Transaksi....");
            }
            System.out.println("Success Searching transaksi by id....");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Searching transaksi by id....");
            System.out.println(e.toString());
        } finally {
            return t;
        }
    }

    @Override
    public void update(Transaksi t, Integer id_transaksi) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "UPDATE `transaksi` "
                   + "SET `tanggal`= ?, `total`= ?, `nama_kasir`= ?, `nama_pembeli`= ? "
                   + "WHERE id_transaksi = ?;";
        System.out.println("Updating transaksi....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(t.getTanggal().getTime()));
            statement.setDouble(2, t.getTotal());
            statement.setString(3, t.getNama_kasir());
            statement.setString(4, t.getNama_pembeli());
            statement.setInt(5, id_transaksi);
            int result = statement.executeUpdate();
            System.out.println("Success Updating " + result + " Transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating Transaksi....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
        }
    }
    
    public void updateWithParameterConnection(Connection CON, Transaksi t, Integer id_transaksi) {
        String sql = "UPDATE `transaksi` "
                   + "SET `tanggal`= ?, `total`= ?, `nama_kasir`= ?, `nama_pembeli`= ? "
                   + "WHERE id_transaksi = ?;";
        System.out.println("Updating transaksi....");

        try {
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(t.getTanggal().getTime()));
            statement.setDouble(2, t.getTotal());
            statement.setString(3, t.getNama_kasir());
            statement.setString(4, t.getNama_pembeli());
            statement.setInt(5, id_transaksi);
            int result = statement.executeUpdate();
            System.out.println("Success Updating " + result + " Transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating Transaksi....");
            System.out.println(e.toString());
            throw new RuntimeException("Gagal mengubah transaksi", e);
        }
    }
}
