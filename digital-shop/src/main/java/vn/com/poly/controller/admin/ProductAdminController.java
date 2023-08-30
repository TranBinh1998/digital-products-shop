package vn.com.poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.com.poly.entities.SanPham;
import vn.com.poly.repository.HangSanXuatRepository;
import vn.com.poly.repository.SanPhamRepository;
import vn.com.poly.service.DanhMucService;
import vn.com.poly.service.HangSanXuatService;
import vn.com.poly.service.SanPhamService;
import vn.com.poly.util.Constants;
import vn.com.poly.util.FileUploadUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {
    private static final int PAGE_NUMBER = 16;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    DanhMucService danhMucService;

    @Autowired
    HangSanXuatService hangSanXuatService;

    @Autowired
    SanPhamRepository sanPhamRepository;


    // Trang chủ home admin
    @GetMapping("")
    public String productManagement(Model model) {
        // Lấy ra tất cả những sản phẩm là laptop trước
        Page<SanPham> sanPhams;

        sanPhams = sanPhamService.getProductsByCategory("1", "1", "0", PAGE_NUMBER);

        danhMucService.listCategoryToView(model);
        hangSanXuatService.manufacturerToView(model);
        model.addAttribute("sanPhams", sanPhams);

        // Tra ra 1 model enad view
        return "views/admin/admin";
    }


    @GetMapping("/view-product")
    public ModelAndView getProductsByThuongHieu(@RequestParam String productType,
                                                @RequestParam String productSort,
                                                @RequestParam(required = false) List<Long> productBrand,
                                                @RequestParam(defaultValue = "0") String pageIndex) {

        Pageable pageable;
        System.out.println(productBrand);
        int page = Integer.parseInt(pageIndex);
        if (page == 1) {
            page = 0;
        }
        if (page > 1) {
            page = page - 1;
        }
        int numbersort = Integer.parseInt(productSort);
        Page<SanPham> sanPhams = null;
        if (numbersort == Constants.SORT_BY_PRICE_DESC) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").descending());
        } else if (numbersort == Constants.SORT_BY_PRICE_ASC) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").ascending());
        } else if (numbersort == Constants.SORT_BY_YEAR_ASC) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").ascending());
        } else if (numbersort == Constants.SORT_BY_INVENTORY_ASC) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donViKho").ascending());
        } else if (numbersort == Constants.SORT_BY_INVENTORY_DESC) {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donViKho").descending());
        } else {
            pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").descending());
        }

        if (productBrand.isEmpty()) {
            sanPhams = sanPhamRepository.findSanPhamByAllHangSanXuatIdAndDanhMucId(pageable, Long.parseLong(productType));
        }
        if (!productBrand.isEmpty()) {
            sanPhams = sanPhamRepository.findSanPhamByListHangSanXuatIdAndDanhMucId(pageable, Long.parseLong(productType), productBrand);
        }
        ModelAndView mav = new ModelAndView("views/fragment/admin-product-fragment :: productsAdminList");
        mav.addObject("sanPhams", sanPhams);

        mav.addObject("page", page);
        return mav;
    }


    @GetMapping("/update-product")
    public String updateSanPham(@RequestParam Long id, Model model) {
        SanPham sanPham = sanPhamService.findSanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        danhMucService.listCategoryToView(model);
        hangSanXuatService.manufacturerToView(model);
        return "views/admin/view-add-product";
    }

    @GetMapping("/add-product")
    public String addSanPham(Model model) {
        SanPham sanPham = new SanPham();
        model.addAttribute("sanPham", sanPham);
        danhMucService.listCategoryToView(model);
        hangSanXuatService.manufacturerToView(model);
        return "views/admin/view-add-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("sanPham") SanPham sanPham, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if (fileName.equals("")){
            sanPham.setNamRaMat(new Date());
            sanPhamService.saveProduct(sanPham);
            return "redirect:/admin";
        }
        String uploadDir = "uploads/";
        FileUploadUtil.savaFile(uploadDir, fileName, multipartFile);
        if (!fileName.equals(Constants.DEFAULT_PICTURE_LAPTOP)) { // nếu mà không trùng thì xóa ảnh đó database và server trước rồi mới lưu vào database
            Path path = Paths.get(uploadDir + sanPham.getPhoto());
            if (Files.exists(path)) {
                sanPhamService.deleteImgProduct(sanPham.getPhoto());
            }
            sanPhamService.deleteImgProduct(sanPham.getPhoto());
        }
        sanPham.setPhoto(fileName);
        sanPham.setNamRaMat(new Date());
        sanPhamService.saveProduct(sanPham);
        return "redirect:/admin";
    }
}


