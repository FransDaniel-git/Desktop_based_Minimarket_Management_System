/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.Detail_transaksiDAO;
import java.util.ArrayList;
import java.util.List;
import model.Detail_transaksi;
import table.Detail_transaksiTable;

/**
 *
 * @author LENOVO
 */
public class Detail_transaksiControl {
    Detail_transaksiDAO dtDao = new Detail_transaksiDAO();
    
    public Detail_transaksiTable mapToDetailTransaksiTableByIdTransaksi(int id_transaksi){
        List<Detail_transaksi> listDetailTransaksi = dtDao.readByIdTransaksi(id_transaksi);
        Detail_transaksiTable detailTransaksiTable = new Detail_transaksiTable(listDetailTransaksi);
        return detailTransaksiTable;
    }
    
    public Detail_transaksiTable mapToDetailTransaksiTableByIdTransaksiEmpty(){
        List<Detail_transaksi> listDetailTransaksi = new ArrayList<>();
        Detail_transaksiTable detailTransaksiTable = new Detail_transaksiTable(listDetailTransaksi);
        return detailTransaksiTable;
    }
}
