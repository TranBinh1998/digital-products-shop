package vn.com.poly.rest;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.poly.entities.ChiMucGioHang;
import vn.com.poly.entities.SanPham;
import vn.com.poly.repository.ChiMucGioHangRepository;
import vn.com.poly.repository.SanPhamRepository;
import vn.com.poly.service.ChiMucGioHangService;
import vn.com.poly.service.SanPhamService;

@RestController
@RequestMapping("/api-admin")
public class ProductRest {


    @Autowired
    SanPhamService sanPhamService;


    @Autowired
    ChiMucGioHangService chiMucGioHangService;


    @GetMapping("/delete-product/{idProduct}")
    @Transactional
    ResponseEntity<Void> deleteProductAdmin(@PathVariable Long idProduct) {
        System.out.println("đã ở đây");
        SanPham sanPham = sanPhamService.findSanPhamById(idProduct);
        if (sanPham == null) {
            return ResponseEntity.notFound().build();
        }
        ChiMucGioHang chiMucGioHang = chiMucGioHangService.findChiMucGioHangBySanPham(sanPham);
        if (chiMucGioHang != null) {
            chiMucGioHangService.deleteChiMucGioHang(chiMucGioHang);
        }
        sanPhamService.deleteSanPham(sanPham);
        return ResponseEntity.noContent().build();
    }

}
