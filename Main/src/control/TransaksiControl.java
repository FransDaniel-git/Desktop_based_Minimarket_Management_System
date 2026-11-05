/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.TransaksiDAO;
import java.util.List;
import model.Transaksi;
import table.TransaksiTable;

/**
 *
 * @author LENOVO
 */
public class TransaksiControl {
    TransaksiDAO tDao = new TransaksiDAO();
    
    public TransaksiTable mapToTransaksiTable(){
        List<Transaksi> listTransaksi =  tDao.read();
        TransaksiTable transaksiTable = new TransaksiTable(listTransaksi);
        return transaksiTable;
    }
    
    public Transaksi searchById(int id_transaksi){
        return tDao.searchById(id_transaksi);
    }
}
