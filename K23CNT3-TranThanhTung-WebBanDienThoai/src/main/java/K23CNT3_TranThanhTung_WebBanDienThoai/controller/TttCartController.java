package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttSanPham;
import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class TttCartController {

    @Autowired
    private TttSanPhamService spService;

    // ================= XEM GIỎ HÀNG =================
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        // 1. Lấy giỏ hàng từ session
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart == null) {
            cart = new HashMap<>();
        }

        List<TttSanPham> items = new ArrayList<>();
        double total = 0; // Chuyển sang double để khớp với kiểu dữ liệu của hóa đơn

        // 2. Duyệt giỏ hàng để lấy thông tin sản phẩm và tính tổng tiền
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            TttSanPham sp = spService.findById(entry.getKey());
            if (sp != null) {
                items.add(sp);
                total += sp.getGia() * entry.getValue();
            }
        }

        // 3. Đưa dữ liệu sang View
        model.addAttribute("cartMap", cart);
        model.addAttribute("items", items);
        model.addAttribute("total", total);

        // Kiểm tra user đã đăng nhập chưa để hiển thị ở view
        TttKhachHang user = (TttKhachHang) session.getAttribute("user");
        model.addAttribute("user", user);

        return "cart";
    }

    // ================= THÊM VÀO GIỎ =================
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id,
                            @RequestParam(defaultValue = "1") Integer qty,
                            HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart == null) {
            cart = new HashMap<>();
        }

        // Tối ưu: Nếu sản phẩm đã có thì cộng dồn, chưa có thì thêm mới
        cart.put(id, cart.getOrDefault(id, 0) + qty);
        session.setAttribute("CART", cart);

        return "redirect:/cart";
    }

    // ================= XÓA KHỎI GIỎ =================
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart != null) {
            cart.remove(id);
            session.setAttribute("CART", cart);
        }
        return "redirect:/cart";
    }

    // ================= CẬP NHẬT SỐ LƯỢNG (Bổ sung nếu cần) =================
    @PostMapping("/update")
    public String updateCart(@RequestParam("id") Integer id,
                             @RequestParam("qty") Integer qty,
                             HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart != null && cart.containsKey(id)) {
            if (qty <= 0) {
                cart.remove(id);
            } else {
                cart.put(id, qty);
            }
            session.setAttribute("CART", cart);
        }
        return "redirect:/cart";
    }
}