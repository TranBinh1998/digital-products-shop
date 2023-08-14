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


    // Lấy ra id của hãng sản xuất
    @GetMapping("/brands")
    public String getProductByThuongHieu(@RequestParam("id") String id, Model model) {
        System.out.println("id danh mục = " + id);
        List<DanhMuc> danhMucList = danhMucService.getAllDanhMuc();
//        List<DanhMuc> danhMucList = danhMucService.findByHangSanXuat(Long.parseLong(id));
        //   Bài toán tương Lấy danh sách danh mục theo thương hiệu thông qua bảng sản phẩm
        //   sau đó

        sendSession();

        List<HangSanXuat> hangSanXuatList = hangSanXuatService.getAllHangSanXuatList();
        System.out.println("Size hang " + hangSanXuatList.size());
        model.addAttribute("hangSanXuats", hangSanXuatList);
        model.addAttribute("danhMucs", danhMucList);
        model.addAttribute("idHangSanXuat", id);
        return "views/user/brand-san-phams";
    }

    @GetMapping("/all")
    public String getAllSanPham(Model model) {
        List<DanhMuc> danhMucList = danhMucService.getAllDanhMuc();

        List<HangSanXuat> hangSanXuatList = hangSanXuatService.getAllHangSanXuatList();
        System.out.println("Size hang " + hangSanXuatList.size());

        model.addAttribute("hangSanXuats", hangSanXuatList);

        model.addAttribute("danhMucs", danhMucList);

        sendSession();

        return "views/user/listSanPham";
    }

    // Detail sản phẩm
    // Lấy id sản phẩm từ link trong mỗi sản phẩm


    @GetMapping("/detail-product")
    public String detailSanPham(@RequestParam("id") String id, Model model) {
        SanPham sanPham = sanPhamService.findSanPhamById(Long.parseLong(id));

        model.addAttribute("sanPham", sanPham);

        sendSession();
        List<HangSanXuat> hangSanXuatList = hangSanXuatService.getAllHangSanXuatList();
        model.addAttribute("hangSanXuats", hangSanXuatList);

        return "views/user/detail-product";
    }

    private void sendSession() {
        StringBuffer previousUrl = request.getRequestURL();
        String query = request.getQueryString();

        String fullUrl = previousUrl.append("?").append(query).toString();

        HttpSession session = request.getSession();

        session.setAttribute("previousUrl", fullUrl);
    }


}
