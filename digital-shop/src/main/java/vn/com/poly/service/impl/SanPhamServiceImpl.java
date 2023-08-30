package vn.com.poly.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.com.poly.entities.SanPham;
import vn.com.poly.repository.SanPhamRepository;
import vn.com.poly.service.SanPhamService;
import vn.com.poly.util.Constants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRespository;

    @Override
    public Page<SanPham> findSanPhamByDanhMuc(Pageable pageable, Long danhMucId) {
        return sanPhamRespository.findSanPhamByDanhMuc(pageable, danhMucId);
    }

    @Override
    public Page<SanPham> findSanPhamByHangSanXuatIdAndDanhMucId(Pageable pageable, Long maDanhMuc, Long maHangSanXuat) {
        return sanPhamRespository.findSanPhamByHangSanXuatIdAndDanhMucId(pageable, maDanhMuc, maHangSanXuat);
    }

    @Override
    public SanPham findSanPhamById(Long id) {
        List<Long> longList = new ArrayList<>();
        longList.add(id);

        List<SanPham> sanPhamList = sanPhamRespository.findAllById(longList);
        return sanPhamList.get(0);
    }

    public Page<SanPham> getProductsByCategory(String productType, String productSort, String pageIndex, int PAGE_NUMBER) {

        int page = Integer.parseInt(pageIndex);
        int numbersort = Integer.parseInt(productSort);
        long productTypeId = Long.parseLong(productType);
        if (page == 1) {
            page = 0;
        }
        if (page > 1) {
            page = page - 1;
        }

        System.out.println("Trang hiện tại ở service" + page);


        Pageable pageable = createPageable(numbersort, page, PAGE_NUMBER);

        Page<SanPham> sanPhams = findSanPhamByDanhMuc(pageable, productTypeId);

        return sanPhams;
    }

    @Override
    @Transactional
    public void deleteSanPham(SanPham sanPham) {
        if (!sanPham.getPhoto().equals(Constants.DEFAULT_PICTURE_LAPTOP)) {
           deleteImgProduct(sanPham.getPhoto());
        }
        sanPhamRespository.delete(sanPham);
    }

    @Override
    public void deleteImgProduct(String imgName) {
        try {
            File img = new File("uploads//" + imgName);
            boolean result = img.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void saveProduct(SanPham sanPham) {
        sanPhamRespository.saveAndFlush(sanPham);
    }

    private Pageable createPageable(int numbersort, int page, int PAGE_NUMBER) {
        Pageable pageable;
        switch (numbersort) {
            case Constants.SORT_BY_PRICE_DESC:
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").descending());
                break;
            case Constants.SORT_BY_PRICE_ASC: // Sắp xếp theo giá tăng dần
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("donGia").ascending());
                break;
            case Constants.SORT_BY_YEAR_DESC: // Sắp xếp theo năm ra mắt giảm dần
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").descending());
                break;
            default:
                pageable = PageRequest.of(page, PAGE_NUMBER, Sort.by("namRaMat").descending());
        }
        return pageable;
    }


}


