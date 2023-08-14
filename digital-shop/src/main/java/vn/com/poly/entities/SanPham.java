package vn.com.poly.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tenSanPham;
    private long donGia;
    private int donViKho;
    private int donViBan; // Lấy số lượng sản phẩm bán chạy nhất
    private String thongTinBaoHanh;
    private String thongTinChung;
    private String manHinh;
    private String heDieuHanh;
    private String cpu;
    private String ram;
    private String thietKe;
    private String dungLuongPin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date namRaMat;

    private String photo;

    @Transient
    @JsonIgnore
    private MultipartFile hinhAnh;

    @ManyToOne
    @JoinColumn(name = "ma_danh_muc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "ma_hang_sx")
    private HangSanXuat hangSanXuat;

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public int getDonViKho() {
        return donViKho;
    }

    public void setDonViKho(int donViKho) {
        this.donViKho = donViKho;
    }

    public int getDonViBan() {
        return donViBan;
    }

    public void setDonViBan(int donViBan) {
        this.donViBan = donViBan;
    }

    public String getThongTinBaoHanh() {
        return thongTinBaoHanh;
    }

    public void setThongTinBaoHanh(String thongTinBaoHanh) {
        this.thongTinBaoHanh = thongTinBaoHanh;
    }

    public String getThongTinChung() {
        return thongTinChung;
    }

    public void setThongTinChung(String thongTinChung) {
        this.thongTinChung = thongTinChung;
    }

    public String getManHinh() {
        return manHinh;
    }

    public void setManHinh(String manHinh) {
        this.manHinh = manHinh;
    }

    public String getHeDieuHanh() {
        return heDieuHanh;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHeDieuHanh(String heDieuHanh) {
        this.heDieuHanh = heDieuHanh;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getThietKe() {
        return thietKe;
    }

    public void setThietKe(String thietKe) {
        this.thietKe = thietKe;
    }

    public String getDungLuongPin() {
        return dungLuongPin;
    }

    public void setDungLuongPin(String dungLuongPin) {
        this.dungLuongPin = dungLuongPin;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public HangSanXuat getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(HangSanXuat hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }


    public MultipartFile getHinhAnh() {
        return hinhAnh;
    }

    public Date getNamRaMat() {
        return namRaMat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setNamRaMat(Date namRaMat) {
        // Nếu namRaMat chưa được gán, gán nó với ngày hiện tại
        if (this.namRaMat == null) {
            this.namRaMat = new Date();
        }
        // Nếu không, gán nó với giá trị được truyền vào
        else {
            this.namRaMat = namRaMat;
        }
    }

    public void setHinhAnh(MultipartFile hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

}
