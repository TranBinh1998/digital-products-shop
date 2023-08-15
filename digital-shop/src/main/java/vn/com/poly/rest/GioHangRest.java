package vn.com.poly.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import vn.com.poly.entities.ChiMucGioHang;
import vn.com.poly.entities.GioHang;
import vn.com.poly.entities.NguoiDung;
import vn.com.poly.entities.SanPham;
import vn.com.poly.service.ChiMucGioHangService;
import vn.com.poly.service.GioHangService;
import vn.com.poly.service.NguoiDungService;
import vn.com.poly.service.SanPhamService;

@RestController
@RequestMapping("/cart-api")
public class GioHangRest {

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    GioHangService gioHangService;

    @Autowired
    ChiMucGioHangService chiMucGioHangService;

    @Autowired
    SanPhamService sanPhamService;
    // add 1 sản phẩm vào giỏ hàng == maybe

    // Khi ấn vào nút add giỏ hàng ở views thì gửi 1 request về server bằng jquery . LẤY TÊN NGƯỜI DÙNG, ID SẢN PHẨM, SỐ LƯỢNG SẢN PHẨM
    @GetMapping("/cart-products")
    @ResponseBody
    public ResponseEntity<String> addCartProduct(@RequestParam String userName,
                                                 @RequestParam String idProduct,
                                                 @RequestParam String soLuong) {

        System.out.println(userName);
        System.out.println(idProduct);
        System.out.println(soLuong);
        // Kiểm tra xem người dùng đã có trong bảng giỏ hàng chưa. Chưa thêm 1 giỏ hàng gán cho id người dùng
        NguoiDung nguoiDung = nguoiDungService.findByUserName(userName);
        if (nguoiDung == null) {
            return ResponseEntity.badRequest().body("Khong tìm thấy người dùng "+userName);
        }

        GioHang gioHang = gioHangService.getAllGioHangByIdNguoiDung(nguoiDung);

        // Tìm sản phâm trong giỏ hàng
     

        // THÊM 1 CHI TIẾT GIỎ HÀNG VỚI ID SẢN PHẨM MA GIO HANG CUA NGUOI DUNG VÀ SỐ LƯỢNG
        // Tìm xem trong chỉ mục giỏ hàng đã có sản phẩm đó chưa. Nếu có thì update nếu chưa có mới tạo
        // tìm chỉ mục theo sản phẩm. Nếu có rồi thì + thêm số lượng //
        // Nếu đã có sản phẩm trong giỏ hàng thì thành update

        ChiMucGioHang chiMucGioHang = new ChiMucGioHang();
        chiMucGioHang.setGioHang(gioHang);
        chiMucGioHang.setSoLuong(Integer.parseInt(soLuong));
        chiMucGioHang.setSanPham(sanPhamService.findSanPhamById(Long.parseLong(idProduct)));
        chiMucGioHangService.addChiTietGioHang(chiMucGioHang);

        // Trả về một chuỗi thông báo thành công hoặc lỗi
        return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thành công");
    }




}
