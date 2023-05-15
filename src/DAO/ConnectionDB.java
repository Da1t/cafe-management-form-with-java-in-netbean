/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */
public class ConnectionDB {
    private Connection cn;

    public Connection getCn() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=QL_QuanCaPhe;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            
            cn = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối csdl thành công");
        }catch(ClassNotFoundException ex){
            System.out.println("Thiếu thư viện JDBC");
        }catch(SQLException ex){
            System.out.println("Lỗi kết nối SQL Server");
        }
        return cn;
    }
    
    public void close(){
        try {
            this.cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try {
            Statement stm = cn.createStatement();
            rs = stm.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public int executeUpdate(String sql){
        int i = -1;
        try {
            Statement stm = cn.createStatement();
            i = stm.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
