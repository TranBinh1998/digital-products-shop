package vn.com.poly.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.com.poly.entities.HangSanXuat;
import vn.com.poly.service.HangSanXuatService;

@Controller
@RequestMapping("/admin")
public class HangSanXuatAdminController {
    @Autowired
    HangSanXuatService hangSanXuatService;

    @GetMapping("/view-add-hsx")
    public String addHangSanXuat(Model model) {
        HangSanXuat hsx = new HangSanXuat();
        model.addAttribute("hsx", hsx);
        return "/views/admin/view-add-hsx";
    }

    @PostMapping("/save-hsx}")
    public String saveHangSanXuat(@ModelAttribute HangSanXuat hangSanXuat, RedirectAttributes redirectAttributes) {
        hangSanXuatService.saveHangSanXuat(hangSanXuat);
        redirectAttributes.addFlashAttribute("message", "Lưu thành công!");
        redirectAttributes.addFlashAttribute("alertClass", "success");
        return "redirect:/admin";
    }

}
