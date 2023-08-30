package vn.com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.com.poly.entities.HangSanXuat;
import vn.com.poly.repository.HangSanXuatRepository;
import vn.com.poly.service.HangSanXuatService;

import java.util.List;

@Service

public class HangSanXuatServiceImpl implements HangSanXuatService {

    @Autowired
    HangSanXuatRepository hangSanXuatRespository;

    @Override
    @ExceptionHandler
    public List<HangSanXuat> getAllHangSanXuatList() {
        return hangSanXuatRespository.findAll();
    }

    @Override
    @ExceptionHandler
    public void manufacturerToView(Model model) {
        List<HangSanXuat> hangSanXuatList = getAllHangSanXuatList();
        model.addAttribute("hangSanXuats", hangSanXuatList);
    }

    @Override
    public void saveHangSanXuat(HangSanXuat hangSanXuat) {
        hangSanXuatRespository.saveAndFlush(hangSanXuat);
    }


}
