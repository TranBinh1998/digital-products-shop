package vn.com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import vn.com.poly.dto.ChiTietGioHangDto;
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

//        System.out.println("Kiem tra " +session);
//        String userName = (String) session.getAttribute("userName");
//        // Lấy ra id nguoi Dung
//        // Lấy ra gio hang
//        // lấy ra danh sách sản phẩm
//        // tính toán giá tiền tổng tiền
//        // add vào 1 dto
//        // gửi dto đến view để hiển thị (Không hiệu quả cho performent và phân trang , Bán laptop thì )
//



    @GetMapping("/list-product")
    public String getAllProductByCart(@SessionAttribute("userName") String userName, @RequestParam(defaultValue = "0") String pageIndex) {
        // Tạo ra 1 đối tượng phân trang .

        Pageable pageable;
        int page = Integer.parseInt(pageIndex);
        if (page == 1) {
            page = 0;
        }
        if (page > 1) {
            page = page - 1;
        }


        pageable = PageRequest.of(page, PAGE_NUMBER);
        // Lấy ra 1 page rồi add vào view .

        Page<ChiTietGioHangDto> chiTietGioHangDtoList = chiMucGioHangRepository.findAllByUserName(pageable,userName);

        chiTietGioHangDtoList.forEach(chiTietGioHangDto -> System.out.println(chiTietGioHangDto.toString()));



        return "views/cart/cart-list";
    }





}
