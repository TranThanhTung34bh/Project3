package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttSanPhamService;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttDanhMucService;
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
public class TttHomeController {

    @Autowired private TttSanPhamService spService;
    @Autowired private TttDanhMucService dmService;
    @Autowired private TttKhachHangService khachHangService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("products", spService.findAll());
        model.addAttribute("dms", dmService.findAll());
        return "index";
    }

    // ĐĂNG KÝ
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("khachHang", new TttKhachHang());
        return "user/register"; // CHỈNH SỬA ĐƯỜNG DẪN VIEW
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("khachHang") TttKhachHang khachHang, Model model, RedirectAttributes redirectAttributes) {
        if (khachHangService.isUsernameExist(khachHang.getUsername())) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "user/register"; // CHỈNH SỬA ĐƯỜNG DẪN VIEW
        }
        try {
            khachHangService.registerKhachHang(khachHang);
            redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            return "user/register";
        }
    }

    // ĐĂNG NHẬP
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("khachHang", new TttKhachHang());
        return "user/login"; // CHỈNH SỬA ĐƯỜNG DẪN VIEW
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("khachHang") TttKhachHang khachHang, HttpSession session, Model model) {
        Optional<TttKhachHang> authenticatedUser = khachHangService.login(khachHang.getUsername(), khachHang.getPassword());

        if (authenticatedUser.isPresent()) {
            session.setAttribute("user", authenticatedUser.get());
            session.setAttribute("hoTen", authenticatedUser.get().getHoTen());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Sai tên đăng nhập hoặc mật khẩu.");
            khachHang.setPassword(null);
            return "user/login"; // CHỈNH SỬA ĐƯỜNG DẪN VIEW
        }
    }

    // ĐĂNG XUẤT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/?logoutSuccess=true";
    }
}