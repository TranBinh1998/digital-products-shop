package vn.com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.com.poly.entities.SanPham;
import vn.com.poly.repository.SanPhamRepository;
import vn.com.poly.service.SanPhamService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRespository;

    @Override
    public Page<SanPham> findSanPhamByDanhMuc(Pageable pageable, Long danhMucId) {
        return sanPhamRespository.findSanPhamByDanhMuc(pageable, danhMucId);
    }

    @Override
    public Page<SanPham> findSanPhamByHangSanXuatIdAndDanhMucId(Pageable pageable, Long maDanhMuc, Long maHangSanXuat) {
        return sanPhamRespository.findSanPhamByHangSanXuatIdAndDanhMucId(pageable, maDanhMuc, maHangSanXuat);
    }

    @Override
    public SanPham findSanPhamById(Long id) {
        List<Long> longList = new ArrayList<>();
        longList.add(id);

        List<SanPham> sanPhamList = sanPhamRespository.findAllById(longList);
        return sanPhamList.get(0);
    }


//    @Override
//    public List<SanPham> getSanPhamByType(Pageable pageable, Long danhMucId) {
//        return sanPhamRespository
//    }
}
