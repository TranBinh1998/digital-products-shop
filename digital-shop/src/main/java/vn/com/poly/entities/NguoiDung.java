package vn.com.poly.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class NguoiDung {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;


		@Column(name = "user_name", unique = true)
		private String userName;

		@Column(name = "email", unique = true)
		private String email;

		@JsonIgnore
		private String password;

		@Column(name = "trang_thai", columnDefinition = "boolean default true")
		private boolean trangThai;

		@CreatedDate
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
		private Date ngayTao;

		@CreatedDate
		@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
		private Date ngayCapNhat;

		@Column(name = "ho_ten")
		private String hoTen;


		@Column(name = "so_dien_thoai", unique = true)
		private String soDienThoai;



		private String diaChi;

		public String getAvata() {
			return avata;
		}

		public void setAvata(String avata) {
			this.avata = avata;
		}

		private String avata;

		@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
		@JoinTable(name="nguoidung_vaitro",
				   joinColumns=@JoinColumn(name="ma_nguoi_dung"),
				   inverseJoinColumns=@JoinColumn(name="ma_vai_tro"))
		private Set<VaiTro> vaiTro;

		@Transient
		@JsonIgnore
		private List<DonHang> listDonHang;

		public List<DonHang> getListDonHang() {
			return listDonHang;
		}

		public void setListDonHang(List<DonHang> listDonHang) {
			this.listDonHang = listDonHang;
		}


	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayTao() {
		return ngayTao;
	}

	public void setNgayTao(Date ngayTao) {
		this.ngayTao = ngayTao;
	}

	public Date getNgayCapNhat() {
		return ngayCapNhat;
	}

	public void setNgayCapNhat(Date ngayCapNhat) {
		this.ngayCapNhat = ngayCapNhat;
	}

	public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getHoTen() {
			return hoTen;
		}

		public void setHoTen(String hoTen) {
			this.hoTen = hoTen;
		}

		public String getSoDienThoai() {
			return soDienThoai;
		}

		public void setSoDienThoai(String soDienThoai) {
			this.soDienThoai = soDienThoai;
		}

		public String getDiaChi() {
			return diaChi;
		}

		public void setDiaChi(String diaChi) {
			this.diaChi = diaChi;
		}

		public Set<VaiTro> getVaiTro() {
			return vaiTro;
		}

		public void setVaiTro(Set<VaiTro> vaiTro) {
			this.vaiTro = vaiTro;
		}


		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

	public NguoiDung(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public NguoiDung() {
			// TODO Auto-generated constructor stub
		}
	

}
