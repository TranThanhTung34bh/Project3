package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TttAdminAuthController {

    // Chỉ giữ lại nhiệm vụ hiển thị trang
    @GetMapping("/login")
    public String loginPage() {
        return "admin/login";
    }

    // Trang chủ sau khi đăng nhập thành công
    @GetMapping
    public String adminIndex() {
        return "admin/index";
    }

    // Lưu ý: Không viết hàm @PostMapping("/login") ở đây nữa.
    // Spring Security sẽ tự động hứng dữ liệu từ Form gửi lên.
}