/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import service.PengembalianService;

/**
 *
 * @author LENOVO
 */
public class PengembalianControl {
    PengembalianService pc = new PengembalianService();
    
    public double melakukanPengembalian(int id_detail){
        return pc.pengembalianProduk(id_detail);
    }
}
