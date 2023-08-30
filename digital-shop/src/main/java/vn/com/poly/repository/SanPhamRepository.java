package vn.com.poly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.poly.entities.SanPham;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
   @Query("SELECT s" +
           " FROM SanPham s" +
           " JOIN FETCH s.danhMuc d" +
           " WHERE d.id = :danhMucId ")
    Page<SanPham> findSanPhamByDanhMuc(Pageable pageable, @Param("danhMucId") Long danhMucId);

    @Query("SELECT s" +
            " from SanPham s" +
            " join FETCH  s.hangSanXuat hsx" +
            " where hsx.id = :hangSanXuatId ")
     List<SanPham> findSanPhamByHangSanXuatId(@Param("hangSanXuatId") Long maHangSanXuat);

    @Query("SELECT s" +
            " FROM SanPham s" + " " +
            " join FETCH s.hangSanXuat hsx" +
            " join FETCH s.danhMuc dm" +
            " where hsx.id = :hangSanXuatId and dm.id = :danhMucId")
    Page<SanPham> findSanPhamByHangSanXuatIdAndDanhMucId(Pageable pageable,
                                                         @Param("danhMucId") Long maDanhMuc,
                                                         @Param("hangSanXuatId") Long maHangSanXuat);


 @Query("SELECT s" +
         " FROM SanPham s" + " " +
         " join FETCH s.hangSanXuat hsx" +
         " join FETCH s.danhMuc dm" +
         " where hsx.id in :hangSanXuatId and dm.id = :danhMucId")
 Page<SanPham> findSanPhamByListHangSanXuatIdAndDanhMucId(Pageable pageable,
                                                      @Param("danhMucId") Long maDanhMuc,
                                                      @Param("hangSanXuatId") List<Long> maHangSanXuat);


 @Query("SELECT s" +
         " FROM SanPham s" + " " +
         " join FETCH s.hangSanXuat hsx" +
         " join FETCH s.danhMuc dm" +
         " where dm.id = :danhMucId")
 Page<SanPham> findSanPhamByAllHangSanXuatIdAndDanhMucId(Pageable pageable,
                                                          @Param("danhMucId") Long maDanhMuc);






}
