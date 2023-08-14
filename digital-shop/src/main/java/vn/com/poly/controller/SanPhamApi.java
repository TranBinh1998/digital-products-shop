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

    // Mặc định là sắp cếp giảm dần theo giá

    // Hàm để lấy danh sách sản phẩm theo loại và sắp xếp
    @GetMapping("/get-products")
    public ModelAndView getProducts(@RequestParam String productType,
                                    @RequestParam String productSort,
                                    @RequestParam(defaultValue = "0") String pageIndex) {
        // Khởi tạo các biến cần thiết
        Pageable pageable; // Đối tượng để phân trang
        long totalPages; // Số trang tối đa
        int page = Integer.parseInt(pageIndex); // Số trang hiện tại
        int numbersort = Integer.parseInt(productSort); // Số để xác định tiêu chí sắp xếp
        long productTypeId = Long.parseLong(productType); // Mã loại sản phẩm
        Page<SanPham> sanPhams; // Danh sách sản phẩm theo loại

        // Điều chỉnh số trang hiện tại nếu cần
        if (page == 1) {
            page = 0;
        }
        if (page > 1) {
            page = page - 1;
        }

        // Tạo đối tượng pageable với số lượng sản phẩm mỗi trang là PAGE_NUMBER
        switch (numbersort) {
            case 2: // Sắp xếp theo giá giảm dần
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").descending());
                break;
            case 3: // Sắp xếp theo giá tăng dần
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").ascending());
                break;
            default: // Sắp xếp theo năm ra mắt giảm dần
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").descending());
                break;
        }

        // Lấy danh sách sản phẩm theo loại và tiêu chí sắp xếp
        sanPhams = sanPhamService.findSanPhamByDanhMuc(pageable, productTypeId);

        // Lấy số trang tối đa
        totalPages = sanPhams.getTotalPages();

        // Tạo ModelAndView với tên của fragment html và model attribute
        ModelAndView mav = new ModelAndView("views/fragment/product-fragment :: productsList");
        mav.addObject("sanPhams", sanPhams);
        mav.addObject("page", page);

        // Trả về ModelAndView
        return mav;
    }

    @GetMapping("/get-products-brand")
    public ModelAndView getProductsByThuongHieu(@RequestParam String productType,
                                                @RequestParam String productSort,
                                                @RequestParam String productBrand,
                                                @RequestParam(defaultValue = "0") String pageIndex) {
        Pageable pageable;
        long totalPages;
        int page = Integer.parseInt(pageIndex);
        if (page==1) {
            page = 0;
        }
        if (page>1){
            page = page-1;
        }
        // tính tổng số trang cần để hiển thị
        // Lấy list sanPhams theo productType
        int numbersort = Integer.parseInt(productSort);
        Page<SanPham> sanPhams;
        if (numbersort == 2) {

            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").descending()); // Nhận vào request có số trang 0+1
            sanPhams = sanPhamService.findSanPhamByHangSanXuatIdAndDanhMucId(pageable, Long.parseLong(productType), Long.parseLong(productBrand));

        } else if (numbersort == 3) {
            pageable = PageRequest.of(page, PAGE_NUMBER,Sort.by("donGia").ascending());
            sanPhams = sanPhamService.findSanPhamByHangSanXuatIdAndDanhMucId(pageable, Long.parseLong(productType), Long.parseLong(productBrand));
            totalPages = sanPhams.getTotalPages();
            System.out.println(totalPages);
        } else {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").descending());
            sanPhams = sanPhamService.findSanPhamByHangSanXuatIdAndDanhMucId(pageable, Long.parseLong(productType), Long.parseLong(productBrand));
            totalPages = sanPhams.getTotalPages();
            System.out.println(totalPages);
        }
        // Tạo ModelAndView với tên của fragment html và model attribute
        ModelAndView mav = new ModelAndView("views/fragment/product-fragment :: productsList");
        mav.addObject("sanPhams", sanPhams);
        // Gửi về server sẽ trang 1 page = 0 còn trang 2 từ page = 1
        mav.addObject("page", page);
        // Trả về ModelAndView
        return mav;
    }


}
