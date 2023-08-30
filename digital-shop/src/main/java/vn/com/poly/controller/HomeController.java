package vn.com.poly.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.poly.entities.DanhMuc;
import vn.com.poly.entities.HangSanXuat;
import vn.com.poly.entities.SanPham;
import vn.com.poly.service.DanhMucService;
import vn.com.poly.service.HangSanXuatService;
import vn.com.poly.service.SanPhamService;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucService danhMucService;

    @Autowired
    HangSanXuatService hangSanXuatService;

    @Autowired
    HttpServletRequest request;

    @GetMapping
    public String home(Model model) {
        Pageable pageableSanPhamByNamRaMat = PageRequest.of(0, 4, Sort.by("namRaMat").descending());
        Pageable pageableSanPhamByBanChay = PageRequest.of(0, 4, Sort.by("donViBan").descending());


        List<DanhMuc> danhMucList = danhMucService.getAllDanhMuc();

        Long laptopId = danhMucList.get(0).getId();
        Long pcId = danhMucList.get(1).getId();
        Long thietBiNgheNhinId = danhMucList.get(2).getId();
        Long linhKienMayTinh = danhMucList.get(3).getId();
        Long thietBiLuuTru = danhMucList.get(4).getId();
        Long thietBiMang = danhMucList.get(5).getId();
        Long cameraQuanSat = danhMucList.get(6).getId();
        Long phuKienCacLoai = danhMucList.get(7).getId();
        Long thietBiVanPhong = danhMucList.get(8).getId();


        Page<SanPham> sanPhams = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByNamRaMat, laptopId);
        Page<SanPham> sanPhamBanChay = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, laptopId);
        model.addAttribute("sanPhamBanChay", sanPhamBanChay);
        model.addAttribute("sanPhams", sanPhams);

        Page<SanPham> pcList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByNamRaMat, pcId);
        Page<SanPham> pcListBanChay = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, pcId);
        model.addAttribute("pcList", pcList);
        model.addAttribute("pcListBanChay", pcListBanChay);
        //Thiết bị nghe nhìn
        Page<SanPham> thietBiNgheNhinBanChayList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, thietBiNgheNhinId);
        model.addAttribute("thietBiNgheNhin", thietBiNgheNhinBanChayList);

        // Linh Kiện máy tính
        Page<SanPham> linhKienList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, linhKienMayTinh);
        model.addAttribute("linhKienList", linhKienList);

        // Thiết bị lưu trữ
        Page<SanPham> thietBiLuTruList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, thietBiLuuTru);
        model.addAttribute("thietBiLuuTru", thietBiLuTruList);

        // thiết bị mạng
        Page<SanPham> thietBiMangList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, thietBiMang);
        model.addAttribute("thietBiMang", thietBiMangList);

        // Camera quan sát
        Page<SanPham> cameraQuanSatList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, cameraQuanSat);
        model.addAttribute("cameraQuanSat", cameraQuanSatList);

        // Phụ kiện các loại
        Page<SanPham> phuKienCacLoaiList = sanPhamService.findSanPhamByDanhMuc(pageableSanPhamByBanChay, phuKienCacLoai);
        model.addAttribute("phuKienCacLoai", phuKienCacLoaiList);

        List<HangSanXuat> hangSanXuatList = hangSanXuatService.getAllHangSanXuatList();


        model.addAttribute("hangSanXuats", hangSanXuatList);

        StringBuffer previousUrl = request.getRequestURL();
        String query = request.getQueryString();

        String fullUrl = previousUrl.append("?").append(query).toString();

        HttpSession session = request.getSession();


        session.setAttribute("previousUrl", fullUrl);

        return "views/user/home";
    }

    // Khi ấn vào nút usus thì sẽ hiển thị ra 1 list danh sách sản phẩm
    // Làm để biết được thương hiệu đó có những loại sản phẩm nào


}
