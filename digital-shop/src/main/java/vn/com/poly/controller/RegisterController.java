package vn.com.poly.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.poly.dto.RegisterUser;
import vn.com.poly.entities.HangSanXuat;
import vn.com.poly.entities.NguoiDung;
import vn.com.poly.entities.VaiTro;
import vn.com.poly.repository.VaiTroRepository;
import vn.com.poly.service.HangSanXuatService;
import vn.com.poly.service.NguoiDungService;
import vn.com.poly.service.SanPhamService;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    NguoiDungService nguoiDungService;


    @Autowired
    HangSanXuatService hangSanXuatService;

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    HttpServletRequest request;


    @GetMapping("/showRegisterForm")
    public String showRegisterForm(Model model) {
        RegisterUser ru = new RegisterUser();


        sendSession();

        model.addAttribute("registerUser", ru);

        List<HangSanXuat> hangSanXuatList = hangSanXuatService.getAllHangSanXuatList();
        model.addAttribute("hangSanXuats", hangSanXuatList);



        return "views/register/register-form";
    }

    @InitBinder
    public void initBinder(WebDataBinder data) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("registerUser") RegisterUser registerUser,
                          BindingResult result,
                          Model model,
                          HttpSession session
    ) {


        // form validation
        if (result.hasErrors()) {
            return "views/register/register-form";
        }

        if (!nguoiDungService.confirmPassWord(registerUser.getPassword(), registerUser.getRePassword())) {
            model.addAttribute("confirmPass", "Mật khẩu nhập lại không khớp");

            return "views/register/register-form";
        }

        String userName = registerUser.getUserName();

        // kiểm tra user đã tồn tại?
        NguoiDung userExisting = nguoiDungService.findByUserName(userName);
        if (userExisting != null) {
            // nếu người dùng đã tồn tại
            model.addAttribute("registerUser", new RegisterUser());
            model.addAttribute("my_error", "Tài khoản đã tồn tại!");
            return "views/register/register-form";
        }

        // Nếu chưa ton tai thi lưu vào database
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        NguoiDung user = new NguoiDung();
        user.setEmail(registerUser.getEmail());
        user.setPassword(bcrypt.encode(registerUser.getPassword()));
        user.setNgayTao(new Date());
        user.setDiaChi(registerUser.getDiaChi());
        user.setUserName(registerUser.getUserName());
//        user.setAvata(); nếu có avata sẽ là ten của img lưu ở server
        user.setHoTen(registerUser.getHoVaTen());
        user.setEmail(registerUser.getEmail());
        user.setSoDienThoai(registerUser.getSoDienThoai());
        VaiTro defaultRole = vaiTroRepository.findVaiTroByTenVaiTro("ROLE_USER");
        Set<VaiTro> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setVaiTro(roles);

        sendSession();

        // thông báo thành công
        session.setAttribute("myuser", user);

        return "views/register/confirmation";
    }

    private void sendSession() {
        StringBuffer previousUrl = request.getRequestURL();
        String query = request.getQueryString();

        String fullUrl = previousUrl.append("?").append(query).toString();

        HttpSession session = request.getSession();

        session.setAttribute("previousUrl", fullUrl);
    }

}
