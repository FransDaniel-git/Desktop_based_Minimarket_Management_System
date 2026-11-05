/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class Transaksi {
    private int id_transaksi;
    private Date tanggal;
    private double total;
    private String nama_kasir;
    private String nama_pembeli;

    public Transaksi(int id_transaksi, Date tanggal, double total, String nama_kasir, String nama_pembeli) {
        this.id_transaksi = id_transaksi;
        this.tanggal = tanggal;
        this.total = total;
        this.nama_kasir = nama_kasir;
        this.nama_pembeli = nama_pembeli;
    }

    public Transaksi(Date tanggal, double total, String nama_kasir, String nama_pembeli) {
        this.tanggal = tanggal;
        this.total = total;
        this.nama_kasir = nama_kasir;
        this.nama_pembeli = nama_pembeli;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNama_kasir() {
        return nama_kasir;
    }

    public void setNama_kasir(String nama_kasir) {
        this.nama_kasir = nama_kasir;
    }

    public String getNama_pembeli() {
        return nama_pembeli;
    }

    public void setNama_pembeli(String nama_pembeli) {
        this.nama_pembeli = nama_pembeli;
    }
}
