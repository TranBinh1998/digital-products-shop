package vn.com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.poly.entities.ChiMucGioHang;
import vn.com.poly.entities.GioHang;
import vn.com.poly.repository.ChiMucGioHangRepository;
import vn.com.poly.service.ChiMucGioHangService;

import java.util.List;

@Service
public class ChiMucGioHangServiceImpl implements ChiMucGioHangService {

    @Autowired
    ChiMucGioHangRepository chiMucGioHangRepository;


    @Override
    public List<ChiMucGioHang> getAllChiMucGioHangByGioHang(GioHang gioHang) {
        return chiMucGioHangRepository.getAllByGioHang(gioHang);
    }
}
