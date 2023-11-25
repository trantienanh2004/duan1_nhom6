/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import banaonam.model.hoadonchitiet;
import banaonam.untility.DB;
import java.sql.*;
/**
 *
 * @author NGUYEN HUU LOC
 */
public class hoadonchitietservice {
    public int addHDCT(hoadonchitiet hdct){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        
        sql = "insert into HOADONCHITIET values (?,?,?,?,?)";
        try {
            con =  DB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hdct.getMaCTSP());
            ps.setObject(2, hdct.getMaHD());
            ps.setObject(3, hdct.getTongTien());
            ps.setObject(4, hdct.getSoLuong());
            ps.setObject(5, hdct.getTrangThai());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
