/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kasir;

/**
 *
 * @author LENOVO
 */
public class KasirTable extends AbstractTableModel{
    private List<Kasir> listKasir;

    public KasirTable(List<Kasir> listKasir) {
        this.listKasir = listKasir;
    }

    public List<Kasir> getListKasir() {
        return listKasir;
    }

    public void setListKasir(List<Kasir> listKasir) {
        this.listKasir = listKasir;
    }

    @Override
    public int getRowCount() {
        return listKasir.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listKasir.get(rowIndex).getId_kasir();
            case 1:
                return listKasir.get(rowIndex).getNama();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id Kasir";
            case 1:
                return "Nama Kasir";
            default:
                return null;
        }
    }
}
