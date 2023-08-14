package vn.com.poly.dto;



import java.util.Objects;

public class ChiTietGioHangDto {

    private String tenSanPham;
    private int soLuong;
    private Long donGia;

    public ChiTietGioHangDto() {
    }

    public ChiTietGioHangDto(String tenSanPham, int soLuong, Long donGia) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Long getDonGia() {
        return donGia;
    }

    public void setDonGia(Long donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietGioHangDto{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                '}';
    }
}
