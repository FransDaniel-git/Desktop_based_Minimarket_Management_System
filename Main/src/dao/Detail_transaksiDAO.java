/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DbConnection;
import interfaceDAO.InterfaceDelete;
import interfaceDAO.InterfaceSearchById;
import java.util.List;
import model.Detail_transaksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
/**
 *
 * @author LENOVO
 */
public class Detail_transaksiDAO implements InterfaceSearchById<Detail_transaksi, Integer>, InterfaceDelete<Integer>{
    public void batchCreate(List<Detail_transaksi> listDetail) {
        int i = 0;
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(false);
        String sql = "INSERT INTO `detail_transaksi`(`id_transaksi`, `id_produk`, `id_kasir`, `jumlah`, `sub_total`) "
                   + "VALUES (?,?,?,?,?);";
        System.out.println("Creating Detail Transaksi....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            for (Detail_transaksi d : listDetail) {
                statement.setInt(1, d.getId_transaksi());
                statement.setInt(2, d.getId_produk());
                statement.setInt(3, d.getId_kasir());
                statement.setInt(4, d.getJumlah());
                statement.setDouble(5, d.getSub_total());
                statement.addBatch();
                i++;
            }
            statement.executeBatch();
            System.out.println("Success creating " + i + " detail transaksi....");
            statement.close();
            DbConnection.CON.commit();
            System.out.println("Success commit....");
        } catch (Exception e) {
            System.out.println("Error creating detail transaksi....");
            System.out.println(e.toString());
            try {
                if (DbConnection.CON != null) {
                    DbConnection.CON.rollback();
                    System.out.println("Success rollback....");
                }
            } catch (Exception e1) {
                System.out.println("Error rollback....");
                System.out.println(e1.toString());
            }
        } finally {
            DbConnection.closeConnection();
        }
    }

    @Override
    public Detail_transaksi searchById(Integer id_detail) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `detail_transaksi` WHERE id_detail = ?;";
        System.out.println("Searching detail_transaksi by id....");
        Detail_transaksi dt = null;
        TransaksiDAO tDao = new TransaksiDAO();
        ProdukDAO pDao = new ProdukDAO();
        KasirDAO kDao = new KasirDAO();
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_detail);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                dt = new Detail_transaksi(
                    rs.getInt("id_detail"),
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_produk"),
                    rs.getInt("id_kasir"),
                    rs.getInt("jumlah"),
                    rs.getDouble("sub_total")
                );
                dt.setTransaksi(tDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_transaksi()));
                dt.setProduk(pDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_produk()));
                dt.setKasir(kDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_kasir()));
                System.out.println("Found detail_transaksi....");
            } else {
                System.out.println("Not Found Detail_transaksi....");
            }
            System.out.println("Success Searching detail_transaksi by id....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching detail_transaksi by id....");
            System.out.println(e.toString());
        }finally {
            DbConnection.closeConnection();
            return dt;
        }
    }
    
    public Detail_transaksi searchByIdTransaksi(Integer id_transaksi) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `detail_transaksi` WHERE id_transaksi = ?;";
        System.out.println("Searching detail_transaksi by id transaksi....");
        Detail_transaksi dt = null;
        TransaksiDAO tDao = new TransaksiDAO();
        ProdukDAO pDao = new ProdukDAO();
        KasirDAO kDao = new KasirDAO();
        
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()){
                dt = new Detail_transaksi(
                    rs.getInt("id_detail"),
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_produk"),
                    rs.getInt("id_kasir"),
                    rs.getInt("jumlah"),
                    rs.getDouble("sub_total")
                );
                dt.setTransaksi(tDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_transaksi()));
                dt.setProduk(pDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_produk()));
                dt.setKasir(kDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_kasir()));
                System.out.println("Found detail_transaksi....");
            } else {
                System.out.println("Not Found Detail_transaksi....");
            }
            System.out.println("Success Searching detail_transaksi by id transaksi....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching detail_transaksi by id transaksi....");
            System.out.println(e.toString());
        }finally {
            DbConnection.closeConnection();
            return dt;
        }
    }

    @Override
    public void delete(Integer id_detail) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "DELETE FROM `detail_transaksi` WHERE id_detail = ?;";
        System.out.println("Deleting detail_transaksi....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_detail);
            int result = statement.executeUpdate();
            System.out.println("Success Deleting " + result + " Detail Transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Deleting Detail Transaksi....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
        }
    }
    
    public List<Detail_transaksi> readByIdTransaksi(int id_transaksi){
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        int i=0;
        String sql = "SELECT * FROM `detail_transaksi` WHERE id_transaksi = ?;";
        System.out.println("Reading detail transaksi by id transaksi....");
        List<Detail_transaksi> listDetail = new ArrayList<>();
        TransaksiDAO tDao = new TransaksiDAO();
        ProdukDAO pDao = new ProdukDAO();
        KasirDAO kDao = new KasirDAO();
        
        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            ResultSet rs = statement.executeQuery();
            if(rs!=null){
                while (rs.next()){
                Detail_transaksi dt = new Detail_transaksi(
                    rs.getInt("id_detail"),
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_produk"),
                    rs.getInt("id_kasir"),
                    rs.getInt("jumlah"),
                    rs.getDouble("sub_total")
                );
                dt.setTransaksi(tDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_transaksi()));
                dt.setProduk(pDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_produk()));
                dt.setKasir(kDao.searchByIdWithParameterConnection(DbConnection.CON, dt.getId_kasir()));
                listDetail.add(dt);
                i++;
                }
                System.out.println("Found " +i+ " detail transaksi");
            }else{
                System.out.println("Not found any detail transaksi");
            }
            System.out.println("Success reading detail transaksi by id transaksi....");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error reading detail transaksi by id transaksi....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
            return listDetail;
        }
    }
    
    public void batchCreateWithParameterConnection(Connection CON, List<Detail_transaksi> listDetail) {
        int i = 0;
        String sql = "INSERT INTO `detail_transaksi`(`id_transaksi`, `id_produk`, `id_kasir`, `jumlah`, `sub_total`) "
                   + "VALUES (?,?,?,?,?);";
        System.out.println("Creating Detail Transaksi....");

        try {
            PreparedStatement statement = CON.prepareStatement(sql);
            for (Detail_transaksi d : listDetail) {
                statement.setInt(1, d.getId_transaksi());
                statement.setInt(2, d.getId_produk());
                statement.setInt(3, d.getId_kasir());
                statement.setInt(4, d.getJumlah());
                statement.setDouble(5, d.getSub_total());
                statement.addBatch();
                i++;
            }
            statement.executeBatch();
            System.out.println("Success creating " + i + " detail transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error creating detail transaksi....");
            System.out.println(e.toString());
            throw new RuntimeException("Gagal membuat detail transaksi", e);
        }
    }
    
    public Detail_transaksi searchByIdWithParameterConnection(Connection CON, Integer id_detail) {
        String sql = "SELECT * FROM `detail_transaksi` WHERE id_detail = ?;";
        System.out.println("Searching detail_transaksi by id....");
        Detail_transaksi dt = null;
        
        try{
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_detail);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                dt = new Detail_transaksi(
                    rs.getInt("id_detail"),
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_produk"),
                    rs.getInt("id_kasir"),
                    rs.getInt("jumlah"),
                    rs.getDouble("sub_total")
                );
                System.out.println("Found detail_transaksi....");
            } else {
                System.out.println("Not Found Detail_transaksi....");
            }
            System.out.println("Success Searching detail_transaksi by id....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching detail_transaksi by id....");
            System.out.println(e.toString());
        }finally {
            return dt;
        }
    }
    
    public void deleteWithParameterConnection(Connection CON, Integer id_detail) {
        String sql = "DELETE FROM `detail_transaksi` WHERE id_detail = ?;";
        System.out.println("Deleting detail_transaksi....");

        try {
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_detail);
            int result = statement.executeUpdate();
            System.out.println("Success Deleting " + result + " Detail Transaksi....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Deleting Detail Transaksi....");
            System.out.println(e.toString());
            throw new RuntimeException("Gagal menghapus detail transaksi", e);
        }
    }
    
    public Detail_transaksi searchByIdTransaksiWithParameterConncetion(Connection CON, Integer id_transaksi) {
        String sql = "SELECT * FROM `detail_transaksi` WHERE id_transaksi = ?;";
        System.out.println("Searching detail_transaksi by id transaksi....");
        Detail_transaksi dt = null;
        TransaksiDAO tDao = new TransaksiDAO();
        ProdukDAO pDao = new ProdukDAO();
        KasirDAO kDao = new KasirDAO();
        
        try{
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_transaksi);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()){
                dt = new Detail_transaksi(
                    rs.getInt("id_detail"),
                    rs.getInt("id_transaksi"),
                    rs.getInt("id_produk"),
                    rs.getInt("id_kasir"),
                    rs.getInt("jumlah"),
                    rs.getDouble("sub_total")
                );
                dt.setTransaksi(tDao.searchByIdWithParameterConnection(CON, dt.getId_transaksi()));
                dt.setProduk(pDao.searchByIdWithParameterConnection(CON, dt.getId_produk()));
                dt.setKasir(kDao.searchByIdWithParameterConnection(CON, dt.getId_kasir()));
                System.out.println("Found detail_transaksi....");
            } else {
                System.out.println("Not Found Detail_transaksi....");
            }
            System.out.println("Success Searching detail_transaksi by id transaksi....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching detail_transaksi by id transaksi....");
            System.out.println(e.toString());
        }finally {
            return dt;
        }
    }
}
