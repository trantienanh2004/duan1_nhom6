/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import banaonam.model.khuyenmai;
import banaonam.untility.DB;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class khuyenmaiService {

    public ArrayList<khuyenmai> getallkhuyenmai() {
        ArrayList<khuyenmai> dskm = new ArrayList<>();
        Connection cn = DB.getConnection();
        try {
            String sql = "select * from khuyenmai";
            PreparedStatement pd = cn.prepareStatement(sql);
            ResultSet rs = pd.executeQuery();
            while (rs.next()) {
                khuyenmai km = new khuyenmai();
                km.setId(rs.getInt(1));
                km.setNoidungkhuyenmai(rs.getString(2));
                km.setMakhuyenmai(rs.getString(3));
                km.setGiamgia(rs.getInt(4));
                km.setNgaybatdau(rs.getDate(5));
                km.setNgayketthuc(rs.getDate(6));
                km.setTrangthai(rs.getString(7));
                dskm.add(km);
            }
        } catch (Exception e) {
        }
        return dskm;
    }

    public Integer them(khuyenmai km) {
        Integer row = null;
        Connection cn = DB.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateMO = sdf.format(km.getNgaybatdau());
        String datedong = sdf.format(km.getNgayketthuc());
        String sql = "INSERT INTO khuyenmai(noidungkhuyenmai,makhuyenmai,giam,ngaybatdau,ngayketthuc,trangthai)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?)";
        try {
            PreparedStatement pd = cn.prepareStatement(sql);
            pd.setNString(1, km.getNoidungkhuyenmai());
            pd.setString(2, km.getMakhuyenmai());
            pd.setInt(3, km.getGiamgia());
            pd.setString(4, dateMO);
            pd.setString(5, datedong);
            pd.setNString(6, km.getTrangthai());
            row = pd.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("lỗi ở khuyến mãi ser");
        }

        return row;
    }
public Integer sua(khuyenmai km) {
        Integer row = null;
        Connection cn = DB.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateMO = sdf.format(km.getNgaybatdau());
        String datedong = sdf.format(km.getNgayketthuc());
        String sql = "update khuyenmai "
                + "set noidungkhuyenmai = ?, makhuyenmai = ?, giam= ?, ngaybatdau = ?,ngayketthuc = ?\n" +
"			   where idKM = ?";
        try {
            PreparedStatement pd = cn.prepareStatement(sql);
            pd.setNString(1, km.getNoidungkhuyenmai());
            pd.setString(2, km.getMakhuyenmai());
            pd.setInt(3, km.getGiamgia());
            pd.setString(4, dateMO);
            pd.setString(5, datedong);
            pd.setInt(6, km.getId());
            row = pd.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("lỗi ở khuyến mãi ser");
        }

        return row;
    }
    public Integer xoa(int id) {
        Integer row = null;
        Connection cn = DB.getConnection();

        String sql = "delete from khuyenmai\n"
                + "				where idKM = ?";
        try {
            PreparedStatement pd = cn.prepareStatement(sql);
            pd.setInt(1, id);

            row = pd.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("lỗi ở khuyến mãi ser");
        }

        return row;
    }
}
