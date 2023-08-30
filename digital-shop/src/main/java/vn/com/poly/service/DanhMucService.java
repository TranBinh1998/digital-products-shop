package vn.com.poly.service;

import org.springframework.ui.Model;
import vn.com.poly.entities.DanhMuc;

import java.util.List;

public interface DanhMucService {
    List<DanhMuc> getAllDanhMuc();

    List<DanhMuc> findByHangSanXuat(Long idHangSanXuat);

    void listCategoryToView(Model model);
}
