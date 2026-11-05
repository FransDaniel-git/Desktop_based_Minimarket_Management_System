/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Transaksi;

/**
 *
 * @author LENOVO
 */
public class TransaksiTable extends AbstractTableModel{
    private List<Transaksi> listTransaksi;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TransaksiTable(List<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    public List<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(List<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    @Override
    public int getRowCount() {
        return listTransaksi.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return listTransaksi.get(rowIndex).getId_transaksi();
            case 1:
                return dateFormat.format(listTransaksi.get(rowIndex).getTanggal());
            case 2:
                return listTransaksi.get(rowIndex).getTotal();
            case 3:
                return listTransaksi.get(rowIndex).getNama_kasir();
            case 4:
                return listTransaksi.get(rowIndex).getNama_pembeli();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "Id Transaksi";
            case 1:
                return "Tanggal";
            case 2:
                return "Total";
            case 3:
                return "Nama Kasir";
            case 4:
                return "Nama Pembeli";
            default:
                return null;
        }
    }
}
