/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.DbConnection;
import interfaceDAO.InterfaceCreate;
import interfaceDAO.InterfaceDelete;
import interfaceDAO.InterfaceRead;
import interfaceDAO.InterfaceReadByNama;
import interfaceDAO.InterfaceSearchById;
import java.util.ArrayList;
import java.util.List;
import model.Kasir;
import interfaceDAO.InterfaceUpdate;

/**
 *
 * @author LENOVO
 */
public class KasirDAO implements InterfaceUpdate <Kasir, Integer>, InterfaceDelete<Integer>, InterfaceCreate<Kasir>, InterfaceRead<Kasir>, InterfaceReadByNama<Kasir>, InterfaceSearchById<Kasir, Integer>{

    @Override
    public void create(Kasir k) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "INSERT INTO `kasir`(`nama`) VALUES (?);";
        System.out.println("Creating kasir....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setString(1, k.getNama());
            int result = statement.executeUpdate();
            System.out.println("Success creating " + result + " kasir....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error creating kasir....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
        }
    }

    @Override
    public List<Kasir> read() {
        int i = 0;
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `kasir`;";
        System.out.println("Reading kasir....");
        List<Kasir> listKasir = new ArrayList<Kasir>();

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i++;
                    listKasir.add(new Kasir(
                            rs.getInt("id_kasir"),
                            rs.getString("nama")));
                }
                System.out.println("Found " + i + " kasir....");
            } else {
                System.out.println("Not Found Any Kasir....");
            }
            System.out.println("Success Reading kasir....");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error reading kasir....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
            return listKasir;
        }
    }

    @Override
    public void update(Kasir k, Integer id_kasir) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "UPDATE `kasir` SET `nama`= ? WHERE id_kasir = ?;";
        System.out.println("Updating kasir....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setString(1, k.getNama());
            statement.setInt(2, id_kasir);
            int result = statement.executeUpdate();
            System.out.println("Success Updating " + result + " Kasir....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Updating Kasir....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
        }
    }

    @Override
    public void delete(Integer id_kasir) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "DELETE FROM `kasir` WHERE id_kasir = ?;";
        System.out.println("Deleting kasir....");

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_kasir);
            int result = statement.executeUpdate();
            System.out.println("Success Deleting " + result + " Kasir....");
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Deleting Kasir....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
        }
    }

    @Override
    public List<Kasir> readByNama(String nama) {
        int i = 0;
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `kasir` WHERE nama LIKE ?;";
        System.out.println("Reading kasir by nama....");
        List<Kasir> listKasir = new ArrayList<Kasir>();

        try {
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setString(1, "%" + nama + "%");
            ResultSet rs = statement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    i++;
                    listKasir.add(new Kasir(
                            rs.getInt("id_kasir"),
                            rs.getString("nama")));
                }
                System.out.println("Found " + i + " kasir....");
            } else {
                System.out.println("Not Found Any Kasir....");
            }
            System.out.println("Success Reading kasir by nama....");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error reading kasir by nama....");
            System.out.println(e.toString());
        } finally {
            DbConnection.closeConnection();
            return listKasir;
        }
    }

    @Override
    public Kasir searchById(Integer id_kasir) {
        DbConnection.makeConnection();
        DbConnection.setAutoCommit(true);
        String sql = "SELECT * FROM `kasir` WHERE id_kasir = ?;";
        System.out.println("Searching kasir by id....");
        Kasir k = null;
        try{
            PreparedStatement statement = DbConnection.CON.prepareStatement(sql);
            statement.setInt(1, id_kasir);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                k = new Kasir(
                    rs.getInt("id_kasir"),
                    rs.getString("nama")
                );
                System.out.println("Found kasir....");
            } else {
                System.out.println("Not Found Kasir....");
            }
            System.out.println("Success Searching kasir by id....");
            rs.close();
            statement.close();
        }catch (Exception e) {
            System.out.println("Error Searching kasir by id....");
            System.out.println(e.toString());
        }finally {
            DbConnection.closeConnection();
            return k;
        }
    }

    public Kasir searchByIdWithParameterConnection(Connection CON, Integer id_kasir) {
        String sql = "SELECT * FROM `kasir` WHERE id_kasir = ?;";
        System.out.println("Searching kasir by id....");
        Kasir k = null;
        try {
            PreparedStatement statement = CON.prepareStatement(sql);
            statement.setInt(1, id_kasir);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
                k = new Kasir(
                        rs.getInt("id_kasir"),
                        rs.getString("nama")
                );
                System.out.println("Found kasir....");
            } else {
                System.out.println("Not Found Kasir....");
            }
            System.out.println("Success Searching kasir by id....");
            rs.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error Searching kasir by id....");
            System.out.println(e.toString());
        } finally {
            return k;
        }
    }

}
