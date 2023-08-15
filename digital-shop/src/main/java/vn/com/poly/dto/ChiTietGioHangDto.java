package vn.com.poly.dto;



import java.util.Objects;

public class ChiTietGioHangDto {

    private String tenSanPham;
    private int soLuong;
    private long donGia;
    private String photo;
    private long totalPrice;

    public ChiTietGioHangDto() {
    }

    public ChiTietGioHangDto(String tenSanPham, int soLuong, long donGia, String photo, long totalPrice) {
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.photo = photo;
        this.totalPrice = totalPrice;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietGioHangDto{" +
                "tenSanPham='" + tenSanPham + '\'' +
                ", soLuong=" + soLuong +
                ", donGia=" + donGia +
                ", photo='" + photo + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
