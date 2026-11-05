/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Detail_transaksi {
    private int id_detail;
    private int id_transaksi;
    private int id_produk;
    private int id_kasir;
    private int jumlah;
    private double sub_total;
    private Transaksi transaksi;
    private Produk produk;
    private Kasir kasir;

    public Detail_transaksi(int id_detail, int id_transaksi, int id_produk, int id_kasir, int jumlah, double sub_total) {
        this.id_detail = id_detail;
        this.id_transaksi = id_transaksi;
        this.id_produk = id_produk;
        this.id_kasir = id_kasir;
        this.jumlah = jumlah;
        this.sub_total = sub_total;
    }

    public Detail_transaksi(int id_transaksi, int id_produk, int id_kasir, int jumlah, double sub_total) {
        this.id_transaksi = id_transaksi;
        this.id_produk = id_produk;
        this.id_kasir = id_kasir;
        this.jumlah = jumlah;
        this.sub_total = sub_total;
    } 

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public int getId_kasir() {
        return id_kasir;
    }

    public void setId_kasir(int id_kasir) {
        this.id_kasir = id_kasir;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }
    
    
}
