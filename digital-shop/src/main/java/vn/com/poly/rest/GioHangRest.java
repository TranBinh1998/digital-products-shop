package vn.com.poly.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.com.poly.dto.CartProductDto;
import vn.com.poly.entities.ChiMucGioHang;
import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.NguoiDung;
import vn.com.poly.entities.SanPham;
import vn.com.poly.service.ChiMucGioHangService;
import vn.com.poly.service.GioHangService;
import vn.com.poly.service.NguoiDungService;
import vn.com.poly.service.SanPhamService;

import java.net.URI;

@RestController
@RequestMapping(path = "/cart-api")
public class GioHangRest {

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    GioHangService gioHangService;

    @Autowired
    ChiMucGioHangService chiMucGioHangService;

    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/cart-products")
    public ResponseEntity<ChiMucGioHang> addCartProductGet(@RequestParam String userName, String idProduct, String soLuong) {


        System.out.println("Đã đến đây");

        NguoiDung nguoiDung = nguoiDungService.findByUserName(userName);
        System.out.println(nguoiDung);
        GioHang gioHang = gioHangService.getAllGioHangByIdNguoiDung(nguoiDung);
        // Sai logic. TÌM SẢN PHẨM TRONG
        SanPham sanPham = sanPhamService.findSanPhamById(Long.parseLong(idProduct));
        if (sanPham == null || nguoiDung == null  || gioHang == null )  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ChiMucGioHang chiMucGioHang = chiMucGioHangService.findChiMucGioHangByGioHangAndSanPham(gioHang, sanPham);
        if (chiMucGioHang == null) {
            chiMucGioHang = new ChiMucGioHang();
            chiMucGioHang.setGioHang(gioHang);
            chiMucGioHang.setSoLuong(Integer.parseInt(soLuong));
            chiMucGioHang.setSanPham(sanPhamService.findSanPhamById(Long.parseLong(idProduct)));
            System.out.println("Vo ly");
            //
            chiMucGioHangService.addChiTietGioHang(chiMucGioHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(chiMucGioHang);
        }
        int oldQuantity = chiMucGioHang.getSoLuong();
        chiMucGioHang.setGioHang(gioHang);
        chiMucGioHang.setSoLuong(Integer.parseInt(soLuong) + oldQuantity);
        chiMucGioHang.setSanPham(sanPhamService.findSanPhamById(Long.parseLong(idProduct)));
        chiMucGioHangService.addChiTietGioHang(chiMucGioHang);

        return ResponseEntity.status(HttpStatus.CREATED).body(chiMucGioHang);
    }
}




