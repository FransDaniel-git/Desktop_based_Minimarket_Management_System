/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ProdukDAO;
import java.util.ArrayList;
import java.util.List;
import model.Produk;
import table.ProdukTable;

/**
 *
 * @author LENOVO
 */
public class ProdukControl {
    ProdukDAO pDao = new ProdukDAO();
    
    public void create(Produk p){
        pDao.create(p);
    }
    
    public List<Produk> read(){
        return pDao.read();
    }
    
    public void update(Produk p, Integer id_produk){
        pDao.update(p, id_produk);
    }
    
    public void batchUpdate(List<Produk> listTempProduk){
        pDao.batchUpdate(listTempProduk);
    }
    
    public void delete(Integer id_produk){
        pDao.delete(id_produk);
    }
    
    public List<Produk> readByNama(String nama){
        return pDao.readByNama(nama);
    }
    
    public ProdukTable mapToProdukTable(){
        ProdukTable produkTable = new ProdukTable(pDao.read());
        return produkTable;
    }
    
    public ProdukTable mapToProdukTableByNama(String nama){
        ProdukTable produkTable = new ProdukTable(pDao.readByNama(nama));
        return produkTable;
    }
    
    public ProdukTable mapToProdukTableStokNotEmpty(){
        List<Produk> listProduk = pDao.read();
        listProduk.removeIf(p -> p.getStok() <= 0);
        ProdukTable produkTable = new ProdukTable(listProduk);
        return produkTable;
    }
    
    public ProdukTable mapToProdukTableById(int id_produk){
        List<Produk> listProduk = new ArrayList<>();
        listProduk.add(pDao.searchById(id_produk));
        ProdukTable produkTable = new ProdukTable(listProduk);
        return produkTable;
    }
    
    public List<Produk> readStokNotEmpty(){
        List<Produk> listProduk = pDao.read();
        listProduk.removeIf(p -> p.getStok() <= 0);
        return listProduk;
    }
}
