/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import banaonam.model.nhanvien;
import banaonam.untility.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NGUYEN HUU LOC
 */
public class nhanvienservice {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public int getMaNV(int maTK) {
        sql = "select MANV from NHANVIEN where MATK = ?";
        nhanvien nv = null;

        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maTK);
            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new nhanvien();
                nv.setMaNV(rs.getInt("MANV"));
            }
            return nv.getMaNV();
        } catch (Exception e) {
            return 0;
        }
    }

    public String tenNV(int maNV) {
        sql = "select TENNV from NHANVIEN where MANV = ?";
        nhanvien nv = null;
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, maNV);
            rs = ps.executeQuery();
            while (rs.next()) {
                nv = new nhanvien();
                nv.setTenNV(rs.getString("TENNV"));
            }
            return nv.getTenNV();
        } catch (Exception e) {
            return null;
        }
    }

}
