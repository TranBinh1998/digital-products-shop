package vn.com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.poly.entities.DanhMuc;

import java.util.List;

@Repository
public interface DanhMucRepository extends JpaRepository<DanhMuc, Long> {

    // Tìm Danh muc sản phẩm theo bảng sản xuất
    @Query("SELECT dm " +
            " FROM DanhMuc dm " +
            " JOIN SanPham sp ON dm.id = sp.hangSanXuat.id " +
            " WHERE sp.hangSanXuat.id = :idHangSanXuat GROUP BY dm.id, dm.tenDanhMuc")
    List<DanhMuc> findByHangSanXuat(@Param("idHangSanXuat") Long idHangSanXuat);
}
