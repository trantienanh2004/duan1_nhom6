/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.model;

/**
 *
 * @author NGUYEN HUU LOC
 */
public class nhanvien {
    private int maNV,maTK;
    private String tenNV,SDT,diaChi;
    private boolean gioiTinh;

    public nhanvien() {
    }

    public nhanvien(int maNV, int maTK, String tenNV, String SDT, String diaChi, boolean gioiTinh) {
        this.maNV = maNV;
        this.maTK = maTK;
        this.tenNV = tenNV;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    
    
    
}
