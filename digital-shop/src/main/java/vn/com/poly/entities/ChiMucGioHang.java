package vn.com.poly.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class ChiMucGioHang {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne (cascade = CascadeType.REMOVE)
	@JoinColumn(name = "ma_san_pham")
	private SanPham sanPham;

	@Column(name = "so_luong")
	private int soLuong;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
	private Date ngayThemVaoGioHang;


	
	@ManyToOne
	@JoinColumn(name = "ma_gio_hang")
	private GioHang gioHang;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int so_luong) {
		this.soLuong = so_luong;
	}

	public GioHang getGioHang() {
		return gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
	}


	public Date getNgayThemVaoGioHang() {
		return ngayThemVaoGioHang;
	}

	public void setNgayThemVaoGioHang(Date ngayThemVaoGioHang) {
		this.ngayThemVaoGioHang = ngayThemVaoGioHang;
	}
}
