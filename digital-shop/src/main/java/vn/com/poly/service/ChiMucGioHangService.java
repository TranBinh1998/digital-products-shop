package vn.com.poly.service;

import vn.com.poly.entities.ChiMucGioHang;
import vn.com.poly.entities.GioHang;

import java.util.List;

public interface ChiMucGioHangService {

    public List<ChiMucGioHang> getAllChiMucGioHangByGioHang(GioHang gioHang);
}
