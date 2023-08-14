package vn.com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.poly.entities.HangSanXuat;
import vn.com.poly.repository.HangSanXuatRepository;
import vn.com.poly.service.HangSanXuatService;

import java.util.List;

@Service
public class HangSanXuatServiceImpl implements HangSanXuatService {
    @Autowired
    HangSanXuatRepository hangSanXuatRespository;
    @Override
    public List<HangSanXuat> getAllHangSanXuatList() {
        return hangSanXuatRespository.findAll();
    }
}
