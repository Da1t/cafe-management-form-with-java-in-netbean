/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class SanPhamDAO {
    public static ArrayList<String> XuatTenSP(String tenlsp){
        ArrayList<String> dstensp = new ArrayList<String>();
        try {
            String sql = "select TenSP from SanPham sp, LoaiSanPham lsp \n" +
                        "where sp.MaLSP = lsp.MaLSP and TenLSP = N'"+tenlsp+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 dstensp.add(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return dstensp;
    }
    public static String XuatMaSP(String TenSP){
        String masp = "";
        try {
            String sql = "select MaSP from SanPham where TenSP = N'"+TenSP+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                masp = rs.getString(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return masp;
    }
}
