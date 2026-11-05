/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.KasirDAO;
import java.util.ArrayList;
import java.util.List;
import model.Kasir;
import table.KasirTable;

/**
 *
 * @author LENOVO
 */
public class KasirControl {
    KasirDAO kDao = new KasirDAO();
    
    public void create(Kasir k){
        kDao.create(k);
    }
    
    public List<Kasir> read(){
        return kDao.read();
    }
    
    public void update(Kasir k, Integer id_kasir){
        kDao.update(k, id_kasir);
    }
    
    public void delete(Integer id_kasir){
        kDao.delete(id_kasir);
    }
    
    public List<Kasir> readByNama(String nama){
        return kDao.readByNama(nama);
    }
    
    public KasirTable mapToKasirTable(){
        KasirTable kasirTable = new KasirTable(kDao.read());
        return kasirTable;
    }
    
    public KasirTable mapToKasirTableByNama(String nama){
        KasirTable kasirTable = new KasirTable(kDao.readByNama(nama));
        return kasirTable;
    }
    
    public KasirTable mapToKasirTableById(int id_kasir){
        List<Kasir> listKasir = new ArrayList<>();
        listKasir.add(kDao.searchById(id_kasir));
        KasirTable kasirTable = new KasirTable(listKasir);
        return kasirTable;
    }
}
