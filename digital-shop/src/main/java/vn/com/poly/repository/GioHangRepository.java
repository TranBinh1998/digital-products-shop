package vn.com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.NguoiDung;

import java.util.List;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {
    public List<GioHang> getGioHangByNguoiDung(NguoiDung nguoiDung);
}
