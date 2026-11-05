/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Kasir {
    private int id_kasir;
    private String nama;

    public Kasir(int id_kasir, String nama) {
        this.id_kasir = id_kasir;
        this.nama = nama;
    }

    public Kasir(String nama) {
        this.nama = nama;
    }

    public int getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(int id_kasir) {
        this.id_kasir = id_kasir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    @Override
    public String toString(){
        return "ID Kasir: " +id_kasir+ " || Nama Kasir: " +nama;
    }
}
