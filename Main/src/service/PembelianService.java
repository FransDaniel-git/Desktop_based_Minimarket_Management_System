/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import connection.DbConnection;
import dao.Detail_transaksiDAO;
import dao.TransaksiDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import model.Detail_transaksi;
import model.Kasir;
import model.ProdukDibeli;
import model.Transaksi;
import java.sql.Connection;

/**
 *
 * @author LENOVO
 */
public class PembelianService {
    private TransaksiDAO tDao = new TransaksiDAO();
    private Detail_transaksiDAO dtDao = new Detail_transaksiDAO();
    private Random random = new Random();
    Connection con = null;
    
    public double pembelianProduk(List<ProdukDibeli> listProdukDibeli, Kasir kasir, String nama_pembeli){
        System.out.println("Memproses Pembelian....");
        double totalPembelian=-1.0;
        try{
            DbConnection.makeConnection();
            con = DbConnection.CON;
            con.setAutoCommit(false);
            int id_transaksi = generateRandomTransaksiId(tDao.readWithParameterConnection(con));
            Date tanggal = new Date();
            totalPembelian = hitungTotalPembelian(listProdukDibeli);
            Transaksi t = new Transaksi(id_transaksi, tanggal, totalPembelian, kasir.getNama(), nama_pembeli);
            
            List<Detail_transaksi> listDetailTransaksi = new ArrayList<>();

            for(ProdukDibeli pd: listProdukDibeli){
                Detail_transaksi dt = new Detail_transaksi(
                id_transaksi,
                pd.getProduk().getId_produk(),
                kasir.getId_kasir(),
                pd.getJumlah(),
                hitungSubTotalPembelian(pd));
                listDetailTransaksi.add(dt);
            }

            tDao.createWithParameterConnection(con, t);
            dtDao.batchCreateWithParameterConnection(con, listDetailTransaksi);
            
            con.commit();
            System.out.println("Pembelian Success....");
        }catch(Exception e){
            System.out.println("Pembelian Error....");
            System.out.println(e.toString());
            totalPembelian = -1.0;
            try{
                con.rollback();
                System.out.println("Success Rollback....");
            }catch(Exception e1){
                System.out.println("Error Rollback....");
                System.out.println(e1.toString());
            }
        }finally{
            try{
                con.close();
            }catch(Exception e1){
                System.out.println("Error Closing Con....");
                System.out.println(e1.toString());
            }
        }
        
        return totalPembelian;
    }
    
    public double hitungTotalPembelian(List<ProdukDibeli> listProdukDibeli){
        double total=0.00;
        for(ProdukDibeli pd: listProdukDibeli){
            total += hitungSubTotalPembelian(pd);
        }
        return total;
    }
    
    public double hitungSubTotalPembelian(ProdukDibeli pd){
        double subTotal = 0.00;
        subTotal = (pd.getProduk().getHarga() * pd.getJumlah());
        return subTotal;
    }
    
    public int generateRandomTransaksiId(List<Transaksi> listTransaksi){
        int id;
        do{
            id = random.nextInt(Integer.MAX_VALUE);
        }while(!isTransaksiIdUnique(listTransaksi, id));
        
        return id;
    }
    
    public boolean isTransaksiIdUnique(List<Transaksi> listTransaksi, int id){
        for(Transaksi t: listTransaksi){
            if(t.getId_transaksi() == id){
                return false;
            }
        }
        
        return true;
    }
    
}
