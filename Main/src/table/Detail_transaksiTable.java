/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Detail_transaksi;

/**
 *
 * @author LENOVO
 */
public class Detail_transaksiTable extends AbstractTableModel{
    private List<Detail_transaksi> listDetailTransaksi;

    public Detail_transaksiTable(List<Detail_transaksi> listDetailTransaksi) {
        this.listDetailTransaksi = listDetailTransaksi;
    }

    public List<Detail_transaksi> getListDetailTransaksi() {
        return listDetailTransaksi;
    }

    public void setListDetailTransaksi(List<Detail_transaksi> listDetailTransaksi) {
        this.listDetailTransaksi = listDetailTransaksi;
    }

    @Override
    public int getRowCount() {
        return listDetailTransaksi.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Detail_transaksi detail = listDetailTransaksi.get(rowIndex);
        switch(columnIndex){
            case 0:
                return detail.getId_detail();
            case 1:
                return detail.getProduk().getNama();
            case 2:
                return detail.getJumlah();
            case 3:
                return detail.getSub_total();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "Id Detail Transaksi";
            case 1:
                return "Nama Produk";
            case 2:
                return "Jumlah Dibeli";
            case 3:
                return "Sub Total";
            default:
                return null;
        }
    }
}
