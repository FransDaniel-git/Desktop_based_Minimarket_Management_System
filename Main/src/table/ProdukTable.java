/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Produk;

/**
 *
 * @author LENOVO
 */
public class ProdukTable extends AbstractTableModel{
    private List<Produk> listProduk;

    public ProdukTable(List<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    public List<Produk> getListProduk() {
        return listProduk;
    }

    public void setListProduk(List<Produk> listProduk) {
        this.listProduk = listProduk;
    }

    @Override
    public int getRowCount() {
        return listProduk.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return listProduk.get(rowIndex).getId_produk();
            case 1:
                return listProduk.get(rowIndex).getNama();
            case 2:
                return listProduk.get(rowIndex).getTipe();
            case 3:
                return listProduk.get(rowIndex).getHarga();
            case 4:
                return listProduk.get(rowIndex).getStok();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "Id Produk";
            case 1:
                return "Nama Produk";
            case 2:
                return "Tipe Produk";
            case 3:
                return "Harga Produk";
            case 4:
                return "Sisa Stok Produk";
            default:
                return null;
        }
    }
}
