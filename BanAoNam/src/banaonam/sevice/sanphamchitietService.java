/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import banaonam.model.sanpham;
import banaonam.model.sanphamchitiet;
import banaonam.untility.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class sanphamchitietService {

    public ArrayList<sanphamchitiet> getallsanphamchitiet() {
        ArrayList<sanphamchitiet> dsspct = new ArrayList<>();

        Connection cn = DB.getConnection();
        String sql = "SELECT sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,gia,chitietsanpham.SOLUONG,MACTSP,CHITIETSANPHAM.HINHANH,trangthai FROM SANPHAM \n"
                + "join CHITIETSANPHAM on sanpham.MASP = CHITIETSANPHAM.masp \n"
                + "join MAUSAC on MAUSAC.MAMS = CHITIETSANPHAM.MAMS \n"
                + "join CHATLIEU on CHATLIEU.MACL = CHITIETSANPHAM.MACL \n"
                + "join SIZE on SIZE.MASIZE = CHITIETSANPHAM.MASIZE";
        try {
            PreparedStatement pd = cn.prepareStatement(sql);
            ResultSet rs = pd.executeQuery();
            while (rs.next()) {
                sanphamchitiet spct = new sanphamchitiet();

                spct.setMASP(rs.getString(1));
                spct.setTensp(rs.getString(2));
                spct.setSIZE(rs.getString(3));
                spct.setMau(rs.getString(4));
                spct.setChatlieu(rs.getString(5));
                spct.setGIA(rs.getInt(6));
                spct.setSOLUONG(rs.getInt(7));
                spct.setMaspct(rs.getInt(8));
                spct.setLinkAnh(rs.getString(9));
                spct.setThanhtoan(rs.getString(10));
                dsspct.add(spct);
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return dsspct;
    }

    public Integer addSPCT(sanphamchitiet SPCT) {
        Integer row = null;
        Connection cn = DB.getConnection();
        String sql = "INSERT INTO CHITIETSANPHAM (MASP, MAMS, MACL, MASIZE, HINHANH, SOLUONG, GIA, MOTA, thanhtoan)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pd = cn.prepareStatement(sql);

            pd.setString(1, SPCT.getMASP());
            pd.setInt(2, Integer.valueOf(SPCT.getMau()));
            pd.setInt(3, Integer.valueOf(SPCT.getChatlieu()));
            pd.setInt(4, Integer.valueOf(SPCT.getSIZE()));
            pd.setNString(5, SPCT.getLinkAnh());
            pd.setInt(6, SPCT.getSOLUONG());
            pd.setDouble(7, SPCT.getGIA());
            pd.setNString(8, "null");
            pd.setNString(9, "chưa thanh toán");
            row = pd.executeUpdate();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return row;

    }

    public Integer UPDATESPCT(sanphamchitiet SPCT) {
        Integer row = null;
        Connection cn = DB.getConnection();
        String sql = "update CHITIETSANPHAM\n"
                + "set MAMS = ? ,MACL = ? , MASIZE = ? ,HINHANH = ? ,SOLUONG = ? , GIA =? ,MOTA = ?, Thanhtoan = ?\n"
                + "where MACTSP = ?";
        try {
            PreparedStatement pd = cn.prepareStatement(sql);

            
            pd.setInt(1, Integer.valueOf(SPCT.getMau()));
            pd.setInt(2, Integer.valueOf(SPCT.getChatlieu()));
            pd.setInt(3, Integer.valueOf(SPCT.getSIZE()));
            pd.setNString(4, SPCT.getLinkAnh());
            pd.setInt(5, SPCT.getSOLUONG());
            pd.setDouble(6, SPCT.getGIA());
            pd.setNString(7, "null");
            pd.setNString(8, "chưa thanh toán");
            pd.setInt(9, SPCT.getMaspct());
            row = pd.executeUpdate();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return row;

    }
}
