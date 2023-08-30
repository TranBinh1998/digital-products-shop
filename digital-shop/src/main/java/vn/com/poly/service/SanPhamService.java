package vn.com.poly.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.poly.entities.SanPham;

import java.util.List;

public interface SanPhamService {



    public Page<SanPham> findSanPhamByDanhMuc(Pageable pageable, Long danhMucId);

    public Page<SanPham> findSanPhamByHangSanXuatIdAndDanhMucId(Pageable pageable, Long maDanhMuc, Long maHangSanXuat);

    SanPham findSanPhamById(Long id);

    Page<SanPham> getProductsByCategory(String productType, String productSort, String pageIndex, int PAGE_NUMBER);

    void deleteSanPham(SanPham sanPham);

    void deleteImgProduct(String imgName);


    void saveProduct(SanPham sanPham);
}
