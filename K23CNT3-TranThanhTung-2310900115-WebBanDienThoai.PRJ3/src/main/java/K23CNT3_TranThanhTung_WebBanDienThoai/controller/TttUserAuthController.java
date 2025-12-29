package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttKhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TttUserAuthController {

    @Autowired
    private TttKhachHangService khachHangService;

    // HIỂN THỊ FORM ĐĂNG NHẬP
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("khachHang", new TttKhachHang());
        return "user/login";
    }

    // XỬ LÝ ĐĂNG NHẬP
    @PostMapping("/login")
    public String Tttlogin(@ModelAttribute("khachHang") TttKhachHang khachHang, HttpSession session, Model model) {
        Optional<TttKhachHang> authenticatedUser = khachHangService.login(khachHang.getUsername(), khachHang.getPassword());
        if (authenticatedUser.isPresent()) {
            // Lưu đối tượng vào session với tên "user"
            session.setAttribute("user", authenticatedUser.get());
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            return "user/login";
        }
    }

    // HIỂN THỊ FORM ĐĂNG KÝ
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("khachHang", new TttKhachHang());
        return "user/register";
    }

    // XỬ LÝ ĐĂNG KÝ
    @PostMapping("/register")
    public String register(@ModelAttribute("khachHang") TttKhachHang khachHang, Model model, RedirectAttributes redirectAttributes) {
        khachHangService.registerKhachHang(khachHang);
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công!");
        return "redirect:/login";
    }

    // ĐĂNG XUẤT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa sạch session
        return "redirect:/index";
    }
}