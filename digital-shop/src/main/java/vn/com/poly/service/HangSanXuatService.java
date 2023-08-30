package vn.com.poly.service;

import org.springframework.ui.Model;
import vn.com.poly.entities.HangSanXuat;

import java.util.List;

public interface HangSanXuatService {

    List<HangSanXuat> getAllHangSanXuatList();

    void manufacturerToView(Model model);


    void saveHangSanXuat(HangSanXuat hangSanXuat);
}