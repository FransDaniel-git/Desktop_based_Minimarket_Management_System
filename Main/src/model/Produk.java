/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Produk {
    private int id_produk;
    private String nama;
    private String tipe;
    private double harga;
    private int stok;

    public Produk(int id_produk, String nama, String tipe, double harga, int stok) {
        this.id_produk = id_produk;
        this.nama = nama;
        this.tipe = tipe;
        this.harga = harga;
        this.stok = stok;
    }

    public Produk(String nama, String tipe, double harga, int stok) {
        this.nama = nama;
        this.tipe = tipe;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    @Override
    public String toString(){
        return nama;
    }
    
    public boolean isEqual(Produk other) {
        if (other == null) {
            return false;
        }
        if (this.id_produk != other.id_produk) {
            return false;
        }
        return true;
    }
}
