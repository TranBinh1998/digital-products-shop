package vn.com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import org.springframework.web.servlet.ModelAndView;
import vn.com.poly.dto.ChiTietGioHangDto;
import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.NguoiDung;
import vn.com.poly.repository.ChiMucGioHangRepository;
import vn.com.poly.repository.NguoiDungRepository;
import vn.com.poly.service.ChiMucGioHangService;
import vn.com.poly.service.GioHangService;
import vn.com.poly.service.NguoiDungService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class GioHangController {
        // Lấy userName từ sesstion
    // Gọi hàm để lấy toàn bộ giỏ hàng của userName đó
    // Hiển thị lên 1 talbe

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    GioHangService gioHangService;

    @Autowired
    ChiMucGioHangRepository chiMucGioHangRepository;

    @Autowired
    ChiMucGioHangService chiMucGioHangService;

    public static int PAGE_NUMBER = 32;
    @GetMapping("/list-product")
    public String getAllProductByCart(@SessionAttribute("userName") String userName,
                                      @RequestParam(defaultValue = "0") String pageIndex,
                                      Model model) {
        Pageable pageable;
        int page = Integer.parseInt(pageIndex);
        if (page == 1) {
            page = 0;
        }
        if (page > 1) {
            page = page - 1;
        }

        pageable = PageRequest.of(page, PAGE_NUMBER);

        Page<ChiTietGioHangDto> chiTietGioHangDtoList = chiMucGioHangRepository.findAllByUserName(pageable,userName);

        chiTietGioHangDtoList.forEach(chiTietGioHangDto -> {
            System.out.println(chiTietGioHangDto.toString());
        });
        model.addAttribute("listSanPhamByGioHang", chiTietGioHangDtoList);
        return "views/cart/cart-list";
    }


}
