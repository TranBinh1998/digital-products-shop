package vn.com.poly.service;

import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.NguoiDung;

import java.util.List;

public interface GioHangService {
    List<GioHang> getAllGioHangByIdNguoiDung(NguoiDung nguoiDung);
}
