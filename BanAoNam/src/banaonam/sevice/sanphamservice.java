/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import banaonam.model.dangnhap;
import banaonam.model.sanpham;
import banaonam.untility.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class sanphamservice {
    public List<sanpham> getallsanpham(){
        ArrayList<sanpham> dssp = new ArrayList<>();
       
        Connection cn = DB.getConnection();
        String sql = "SELECT * FROM SANPHAM";
        try {
           PreparedStatement pd = cn.prepareStatement(sql);
           ResultSet rs = pd.executeQuery();
            while (rs.next()) {       
              sanpham sp = new sanpham();   
                sp.setMasp(rs.getString(1));
                sp.setTensp(rs.getString(2));
                sp.setNgaytao(rs.getString(3));
                sp.setTrangthai(rs.getString(4));
                dssp.add(sp);
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return dssp;
    }
   
    
    public Integer addSanPham(sanpham sp){
        Integer row =null;
        Connection cn = DB.getConnection();
        String sql = "INSERT INTO SANPHAM (MASP,TENSP,ngaytao,trangthai) VALUES(?,?,?,?)";
        try {
           PreparedStatement pd = cn.prepareStatement(sql);
           pd.setString(1, sp.getMasp());
           pd.setString(2, sp.getTensp());
           pd.setString(3, sp.getNgaytao());
           pd.setString(4, sp.getTrangthai());
          
           row = pd.executeUpdate();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return row;
    }
    
     public Integer UpdateSanPham(sanpham sp){
        Integer row =null;
        Connection cn = DB.getConnection();
        String sql = "update sanpham\n" +
"set TENSP = ?,ngaytao = ?\n" +
"where Masp = ?";
        try {
           PreparedStatement pd = cn.prepareStatement(sql);
           pd.setString(1, sp.getTensp());
           pd.setString(2, sp.getTrangthai());
           pd.setString(3, sp.getMasp());
           
           row = pd.executeUpdate();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return row;
    }
}
