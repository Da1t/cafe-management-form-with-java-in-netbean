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
public class LoaiSPDAO {
    public static ArrayList<String> XuatTenLSP(){
        ArrayList<String> dstenlsp = new ArrayList<String>();
        try {
            String sql = "select TenLSP from LoaiSanPham";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                 dstenlsp.add(rs.getString("TenLSP"));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return dstenlsp;
    }
}
