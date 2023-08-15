package vn.com.poly.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.NguoiDung;
import vn.com.poly.repository.GioHangRepository;
import vn.com.poly.service.GioHangService;

import java.util.List;

@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    GioHangRepository gioHangRepository;


    @Override
    public GioHang getAllGioHangByIdNguoiDung(NguoiDung nguoiDung) {
        return gioHangRepository.getGioHangByNguoiDung(nguoiDung);
    }

    @Override
    public void saveGioHang(GioHang gioHang) {
        gioHangRepository.save(gioHang);
    }


}
