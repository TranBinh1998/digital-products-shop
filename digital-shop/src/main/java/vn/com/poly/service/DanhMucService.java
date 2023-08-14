package vn.com.poly.service;

import vn.com.poly.entities.DanhMuc;

import java.util.List;

public interface DanhMucService {
    List<DanhMuc> getAllDanhMuc();

    List<DanhMuc> findByHangSanXuat(Long idHangSanXuat);
}
