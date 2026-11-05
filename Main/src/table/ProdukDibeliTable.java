/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.ProdukDibeli;

/**
 *
 * @author LENOVO
 */
public class ProdukDibeliTable extends AbstractTableModel{
    List<ProdukDibeli> listProdukDibeli;

    public ProdukDibeliTable(List<ProdukDibeli> listProdukDibeli) {
        this.listProdukDibeli = listProdukDibeli;
    }

    public List<ProdukDibeli> getListProdukDibeli() {
        return listProdukDibeli;
    }

    public void setListProdukDibeli(List<ProdukDibeli> listProdukDibeli) {
        this.listProdukDibeli = listProdukDibeli;
    }

    @Override
    public int getRowCount() {
        return listProdukDibeli.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return listProdukDibeli.get(rowIndex).getProduk().getId_produk();
            case 1:
                return listProdukDibeli.get(rowIndex).getProduk().getNama();
            case 2:
                return listProdukDibeli.get(rowIndex).getJumlah();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "ID Produk";
            case 1:
                return "Nama Produk";
            case 2:
                return "Jumlah Dibeli";
            default:
                return null;
        }
    }
}
