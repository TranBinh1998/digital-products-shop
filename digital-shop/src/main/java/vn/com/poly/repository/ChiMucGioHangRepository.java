package vn.com.poly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.poly.dto.ChiTietGioHangDto;
import vn.com.poly.entities.ChiMucGioHang;
import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.SanPham;

import java.util.List;

@Repository
public interface ChiMucGioHangRepository extends JpaRepository<ChiMucGioHang, Long> {

    List<ChiMucGioHang> getAllByGioHang(GioHang gioHang);
    // Lây danh sách sản phẩm theo mã người dùng

    @Query("SELECT new vn.com.poly.dto.ChiTietGioHangDto (s.tenSanPham , cmgh.soLuong, s.donGia, s.photo, s.donGia*cmgh.soLuong)" + // Chọn các cột cần thiết và tạo một đối tượng dto mới từ chúng
            " FROM NguoiDung nd  " + // Bắt đầu từ bảng NguoiDung
            " LEFT JOIN GioHang gh ON nd.id = gh.nguoiDung.id " + // Nối trái với bảng GioHang theo quan hệ nguoiDung
            " left JOIN ChiMucGioHang cmgh on gh.id = cmgh.gioHang.id " + // Nối trái với bảng ChiMucGioHang theo quan hệ gioHang
            " left JOIN SanPham s ON s.id = cmgh.sanPham.id" + // Nối trái với bảng SanPham theo quan hệ gioHang
            " WHERE nd.userName = :userName " + // Điều kiện lọc theo userName của người dùng
            " ORDER BY cmgh.ngayThemVaoGioHang asc")
        // Sắp xếp theo ngày thêm vào giỏ hàng tăng dần
    Page<ChiTietGioHangDto> findAllByUserName(Pageable pageable, @Param("userName") String userName);


    ChiMucGioHang findChiMucGioHangByGioHangAndSanPham(GioHang gioHang, SanPham sanPham);


}
