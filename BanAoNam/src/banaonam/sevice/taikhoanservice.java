/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

/**
 *
 * @author NGUYEN HUU LOC
 */
import banaonam.model.taikhoan;
import banaonam.untility.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class taikhoanservice {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = "";
    
    public int getMaTK(String tenTK){
        sql = "select MATK from TAIKHOAN where TENTK = ?";
        taikhoan tk = null;
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenTK);
            
            rs = ps.executeQuery();
            while(rs.next()){
                tk = new taikhoan();
                tk.setMaTK(rs.getInt("MATK"));
            }
            return tk.getMaTK();
        } catch (Exception e) {
            return 0;
        }
    }
}
