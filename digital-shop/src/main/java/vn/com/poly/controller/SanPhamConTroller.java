package vn.com.poly.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.com.poly.entities.DanhMuc;
import vn.com.poly.entities.HangSanXuat;
import vn.com.poly.entities.SanPham;
import vn.com.poly.service.DanhMucService;
import vn.com.poly.service.HangSanXuatService;
import vn.com.poly.service.SanPhamService;
import vn.com.poly.service.SessionService;

import java.security.PublicKey;
import java.util.List;

@Controller
@RequestMapping("/san-phams")
public class SanPhamConTroller {

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucService danhMucService;

    @Autowired
    HangSanXuatService hangSanXuatService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    SessionService sendSessionService;

    @GetMapping("/brands")
    public String getProductByThuongHieu(@RequestParam("id") String id, Model model) {
        hangSanXuatService.manufacturerToView(model);
        danhMucService.listCategoryToView(model);
        sendSessionService.sendSession(request);
        model.addAttribute("idHangSanXuat", id);
        return "views/user/brand-san-phams";
    }

    @GetMapping("/all")
    public String getAllSanPham(Model model) {
        danhMucService.listCategoryToView(model);
        hangSanXuatService.manufacturerToView(model);
        sendSessionService.sendSession(request);
        return "views/user/listSanPham";
    }

    @GetMapping("/detail-product")
    public String detailSanPham(@RequestParam("id") String id, Model model) {
        SanPham sanPham = sanPhamService.findSanPhamById(Long.parseLong(id));
        model.addAttribute("sanPham", sanPham);
        sendSessionService.sendSession(request);
        hangSanXuatService.manufacturerToView(model);
        return "views/user/detail-product";
    }


}
