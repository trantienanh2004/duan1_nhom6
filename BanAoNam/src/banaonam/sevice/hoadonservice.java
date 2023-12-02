/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import banaonam.model.hoadon;
import banaonam.untility.DB;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author NGUYEN HUU LOC
 */
public class hoadonservice {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";

    public List<hoadon> getAllHD() {
        sql = "select MAHD, MANV, TRANGTHAI,NGAYTAO from HOADON where TRANGTHAI = N'Chờ thanh toán'";
        List<hoadon> list = new ArrayList<>();
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setMaHD(rs.getInt("MAHD"));
                hd.setMaNV(rs.getInt("MANV"));
                hd.setTrangThai(rs.getString("TRANGTHAI"));
                hd.setNgayTao(rs.getString("NGAYTAO"));

                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public List<hoadon> getAllHDByQLHD() {
        sql = "select MAHD,NGAYTAO,MANV,MAKH,TRANGTHAI from HOADON ";
        List<hoadon> list = new ArrayList<>();
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hoadon hd = new hoadon();
                hd.setMaHD(rs.getInt("MAHD"));
                hd.setMaNV(rs.getInt("MANV"));
                hd.setTrangThai(rs.getString("TRANGTHAI"));
                hd.setNgayTao(rs.getString("NGAYTAO"));
                hd.setMaKH(rs.getInt("MAKH"));

                list.add(hd);

            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int addHD(hoadon hd) {
        sql = "insert into HOADON values (?,?,?,?)";
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd.getMaNV());
            ps.setObject(2, hd.getMaKH());
            ps.setObject(3, hd.getTrangThai());
            ps.setObject(4, hd.getNgayTao());

            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateHD(int maKH, int maHD) {
        sql = "update HOADON set TRANGTHAI = N' Đã thanh toán',MAKH = ? where MAHD=?";
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);

            ps.setObject(1, maKH);
            ps.setObject(2, maHD);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
