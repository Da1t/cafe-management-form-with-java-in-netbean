/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import POJO.ChiTietHD;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class HoaDonDAO {
    public static ArrayList<ChiTietHD> getDSCTHD(String mahd){
//        public ChiTietHD(String masp, String tensp, int soluong, int giatien, int thanhtien)
        ArrayList<ChiTietHD> dscthd = new ArrayList<ChiTietHD>();
        try {
            String sql = "select cthd.MaSP,TenSP, SoLuong, GiaTien, ThanhTien \n" +
                        "from ChiTietHoaDon cthd, SanPham sp\n" +
                        "where cthd.MaSP = sp.MaSP and MaHD = '"+mahd+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                ChiTietHD cthd = new ChiTietHD(rs.getString("MaSP"),rs.getString("TenSP"),rs.getInt("SoLuong"),
                        rs.getInt("GiaTien"),rs.getInt("ThanhTien"));
                dscthd.add(cthd);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return dscthd;
    }
    public static int ThemXoaSua(String sql){
        int i=0;
        try {
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
             i = cn.executeUpdate(sql);
            cn.close();
            System.out.println("Thêm/xóa/sửa thành công");
            
        } catch (Exception e) {
            System.out.println("Thêm/xóa/sửa không thành công");
           
        }
        return i;
    }
    public static String XuatGG_TongTien(String MaHD){
        String kq1="",kq2="";
        try {
            String sql = "select GiamGia,TongTien from HoaDon where MaHD = '"+MaHD+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                kq1=rs.getString(1);
                kq2=rs.getString(2);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return kq1+" "+kq2;
    }
    public static String XuatMaBan(String MaHD){
        String kq="";
        try {
            String sql = "select MaBan from HoaDon where MaHD = '"+MaHD+"'";
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
    public static int XacNhanHD(String MaHD){
        int kq=0;
        try {
            String sql = "select count(MaHD) from HoaDon where MaHD = '"+MaHD+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                kq=rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return kq;
    }
    public static int DemSLMonHD(String MaHD){
        int kq=0;
        try {
            String sql = "select count(*) from ChiTietHoaDon where MaHD = '"+MaHD+"'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                kq=rs.getInt(1);
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return kq;
    }
    public static ArrayList<String> XuatHD_ChuaThanhToan(){
        ArrayList<String> dsmahd = new ArrayList<String>();
        try {
            String sql = "select MaHD from HoaDon where TinhTrang = N'Chưa thanh toán'";
            ConnectionDB cn = new ConnectionDB();
            cn.getCn();
            ResultSet rs = cn.executeQuery(sql);
            while(rs.next()){
                dsmahd.add(rs.getString(1));
            }
            cn.close();
        } catch (Exception e) {
            System.out.println("Lỗi không thể lấy dữ liệu");
        }
        return dsmahd;
    }
}
