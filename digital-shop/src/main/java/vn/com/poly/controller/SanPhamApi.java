package vn.com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.com.poly.entities.SanPham;
import vn.com.poly.service.SanPhamService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SanPhamApi {

    @Autowired
    SanPhamService sanPhamService;


    public static int PAGE_NUMBER = 16;

    @GetMapping("/get-products")
    public ModelAndView getProducts(@RequestParam String productType,
                                    @RequestParam String productSort,
                                    @RequestParam(defaultValue = "0") String pageIndex) {
        Page<SanPham> sanPhams;

        sanPhams = sanPhamService.getProductsByCategory(productType,productSort,pageIndex,PAGE_NUMBER);
        ModelAndView mav = new ModelAndView("views/fragment/product-fragment :: productsList");
        mav.addObject("sanPhams", sanPhams);
        mav.addObject("page", sanPhams.getNumber());
        return mav;
    }

    @GetMapping("/get-products-brand")
    public ModelAndView getProductsByThuongHieu(@RequestParam String productType,
                                                @RequestParam String productSort,
                                                @RequestParam String productBrand,
                                                @RequestParam(defaultValue = "0") String pageIndex) {

        Pageable pageable;

        int page = Integer.parseInt(pageIndex);
        if (page == 1) {
            page = 0;
        }
        if (page > 1) {
            page = page - 1;
        }
        int numbersort = Integer.parseInt(productSort);
        Page<SanPham> sanPhams;
        if (numbersort == 2) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").descending());
        } else if (numbersort == 3) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").ascending());
        } else {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").descending());
        }
        sanPhams = sanPhamService.findSanPhamByHangSanXuatIdAndDanhMucId(pageable, Long.parseLong(productType), Long.parseLong(productBrand));
        ModelAndView mav = new ModelAndView("views/fragment/product-fragment :: productsList");
        mav.addObject("sanPhams", sanPhams);

        mav.addObject("page", page);
        return mav;
    }


}
