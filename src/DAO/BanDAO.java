/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import POJO.Ban;

/**
 *
 * @author Administrator
 */
public class BanDAO {
    public static ArrayList<Ban> getDSB(){
        ArrayList<Ban> dsb = new ArrayList<Ban>();
        try {
            String sql = "select * from Ban";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                Ban b = new Ban(rs.getString("MaBan"),rs.getString("TenBan"),rs.getString("TrangThai"));
                dsb.add(b);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return dsb;
    }
    public static ArrayList<String> XuatMaBan(){
        ArrayList<String> dsmb = new ArrayList<String>();
        try {
            String sql = "select MaBan from Ban";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 dsmb.add(rs.getString("MaBan"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return dsmb;
    }
    public static String XuatTrangThaiBan(String MaBan){
        String kq="";
        try {
            String sql = "select TrangThai from Ban where MaBan = '"+MaBan+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                kq=rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return kq;
    }
}
