/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import model.Kasir;
import model.ProdukDibeli;
import service.PembelianService;

/**
 *
 * @author LENOVO
 */
public class PembelianControl {
    PembelianService ps = new PembelianService();
    
    public double melakukanPembelian(List<ProdukDibeli> listProdukDibeli, Kasir kasir, String nama_pembeli){
        return ps.pembelianProduk(listProdukDibeli, kasir, nama_pembeli);
    }
}
