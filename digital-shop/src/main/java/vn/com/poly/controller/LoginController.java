package vn.com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        return "views/login";
    }

    @GetMapping("/showPage403")
    public String showPage403(){
        return "error/403";
    }

}
