package vn.com.poly.rest;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import java.util.Date;

@RestController
@RequestMapping(path = "/cart-api")
public class GioHangRest {

    @Autowired
    HttpServletRequest request;

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
        NguoiDung nguoiDung = nguoiDungService.findByUserName(userName);
        System.out.println(nguoiDung);
        GioHang gioHang = gioHangService.getAllGioHangByIdNguoiDung(nguoiDung);
        SanPham sanPham = sanPhamService.findSanPhamById(Long.parseLong(idProduct));
        if (sanPham == null || nguoiDung == null || gioHang == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ChiMucGioHang chiMucGioHang = chiMucGioHangService.findChiMucGioHangByGioHangAndSanPham(gioHang, sanPham);
        if (chiMucGioHang == null) {
            // nếu chỉ mục giỏ hàng chưa tồn tại thì thêm mới sản phẩm vào giỏ hàng
            chiMucGioHang = new ChiMucGioHang();
            chiMucGioHang.setGioHang(gioHang);
            chiMucGioHang.setNgayThemVaoGioHang(new Date());
            chiMucGioHang.setSoLuong(Integer.parseInt(soLuong));
            chiMucGioHang.setSanPham(sanPhamService.findSanPhamById(Long.parseLong(idProduct)));

            chiMucGioHangService.addChiTietGioHang(chiMucGioHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(chiMucGioHang);
        }
        // Hàm có thể nhận api liên tục

        // Nếu đã đã tồn tại thì thêm số lượng
        int oldQuantity = chiMucGioHang.getSoLuong(); // vd = 5;
        chiMucGioHang.setGioHang(gioHang);
        chiMucGioHang.setNgayThemVaoGioHang(new Date()); // Nếu giảm số lượng mà lên sau hiển thị lại mà lên đầu giỏ hàng thì hơi kì
        chiMucGioHang.setSoLuong(Integer.parseInt(soLuong) + oldQuantity);  // ví dụ trước đó là 5 nếu  soLuong = 1 thì + 1
        chiMucGioHang.setSanPham(sanPhamService.findSanPhamById(Long.parseLong(idProduct)));
        chiMucGioHangService.addChiTietGioHang(chiMucGioHang);
        return ResponseEntity.status(HttpStatus.CREATED).body(chiMucGioHang);
    }

    @GetMapping("/del-cart-products/{idProduct}/{userName}")
    public ResponseEntity<Void> deleteProductByCart(@PathVariable Long idProduct, @PathVariable String userName) {
        System.out.println("delete");
        // Gọi service để xóa sản phẩm trong giỏ hàng
        System.out.println("đã vào đây");
        System.out.println(idProduct);
        SanPham sanPham = sanPhamService.findSanPhamById(idProduct);
        if (sanPham == null) {
            return ResponseEntity.badRequest().build();
        }
        NguoiDung nguoiDung = nguoiDungService.findByUserName(userName);

        GioHang gioHang = gioHangService.getAllGioHangByIdNguoiDung(nguoiDung);


        ChiMucGioHang chiMucGioHang = chiMucGioHangService.findChiMucGioHangByGioHangAndSanPham(gioHang, sanPham);
        if (chiMucGioHang == null) {
            return ResponseEntity.badRequest().build();
        }

        chiMucGioHangService.deleteChiMucGioHang(chiMucGioHang);
        // Trả về status code 204 (NO_CONTENT) để biểu thị xóa thành công
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}




