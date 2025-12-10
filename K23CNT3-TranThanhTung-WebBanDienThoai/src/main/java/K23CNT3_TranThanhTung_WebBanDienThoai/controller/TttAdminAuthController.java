package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttAdmin;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class TttAdminAuthController {

    @Autowired
    private TttAdminRepository adminRepo;

    @GetMapping("/login")
    public String loginPage(){ return "admin/login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, HttpSession session, Model model){
        TttAdmin admin = adminRepo.findByUsername(username).orElse(null);
        if(admin!=null && admin.getPassword().equals(password)){
            session.setAttribute("ADMIN", admin);
            return "redirect:/admin";
        }
        model.addAttribute("error", "Sai username hoặc password");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("ADMIN");
        return "redirect:/admin/login";
    }

    @GetMapping
    public String adminIndex(){ return "admin/index"; }
}
