/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import connection.DbConnection;
import dao.Detail_transaksiDAO;
import dao.ProdukDAO;
import dao.TransaksiDAO;
import model.Detail_transaksi;
import model.Produk;
import java.sql.Connection;
import model.Transaksi;
/**
 *
 * @author LENOVO
 */
public class PengembalianService {
    ProdukDAO pDao = new ProdukDAO();
    TransaksiDAO tDao = new TransaksiDAO();
    Detail_transaksiDAO dtDao = new Detail_transaksiDAO();
    
    public double pengembalianProduk(int id_detail){
        System.out.println("Memproses pengembalian....");
        Detail_transaksi dt=null;
        double subTotal=-1.0;
        Connection con = null;
        Transaksi t = null;
        
        try{
            DbConnection.makeConnection();
            con = DbConnection.CON;
            con.setAutoCommit(false);
            dt = dtDao.searchByIdWithParameterConnection(con, id_detail);
            Produk p = pDao.searchByIdWithParameterConnection(con, dt.getId_produk());
            dtDao.deleteWithParameterConnection(con, id_detail);
            p.setStok((p.getStok())+(dt.getJumlah()));
            pDao.updateWithParameterConnection(con, p, p.getId_produk());
            t = tDao.searchByIdWithParameterConnection(con, dt.getId_transaksi());
            t.setTotal((t.getTotal()) - (dt.getSub_total()));
            tDao.updateWithParameterConnection(con, t, t.getId_transaksi());
            if(dtDao.searchByIdTransaksiWithParameterConncetion(con, dt.getId_transaksi())==null){
                tDao.deleteWithParameterConnection(con, dt.getId_transaksi());
            }
            con.commit();
            System.out.println("Pengembalian Sukses....");
            subTotal = dt.getSub_total();
        }catch(Exception e){
            System.out.println("Error memproses pengembalian....");
            System.out.println(e.toString());
            try{
                con.rollback();
                System.out.println("Success Rollback....");
            }catch(Exception e1){
                System.out.println("Error rollback....");
                System.out.println(e1.toString());
            }
        }finally{
            try{
                con.close();
            }catch(Exception e1){
                System.out.println("Error closing con....");
                System.out.println(e1.toString());
            }
        }
        
        return subTotal;
    }
}
