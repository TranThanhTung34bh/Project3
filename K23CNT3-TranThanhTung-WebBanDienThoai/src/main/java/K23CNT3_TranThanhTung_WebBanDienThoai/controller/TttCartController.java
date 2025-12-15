package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttSanPham;
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

        // Lấy giỏ hàng từ session
        Map<Integer, Integer> cart =
                (Map<Integer, Integer>) session.getAttribute("CART");

        if (cart == null) {
            cart = new HashMap<>();
        }

        List<TttSanPham> items = new ArrayList<>();
        long total = 0;

        // Lấy sản phẩm + tính tổng tiền
        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            Integer maSP = entry.getKey();
            Integer soLuong = entry.getValue();

            TttSanPham sp = spService.findById(maSP);
            if (sp != null) {
                items.add(sp);
                total += sp.getGia() * soLuong;
            }
        }

        // Đưa dữ liệu sang View
        model.addAttribute("cartMap", cart);
        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "cart";
    }

    // ================= THÊM VÀO GIỎ =================
    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id,
                            @RequestParam(defaultValue = "1") Integer qty,
                            HttpSession session) {

        Map<Integer, Integer> cart =
                (Map<Integer, Integer>) session.getAttribute("CART");

        if (cart == null) {
            cart = new HashMap<>();
        }

        cart.put(id, cart.getOrDefault(id, 0) + qty);
        session.setAttribute("CART", cart);

        return "redirect:/cart";
    }

    // ================= XÓA KHỎI GIỎ =================
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id,
                         HttpSession session) {

        Map<Integer, Integer> cart =
                (Map<Integer, Integer>) session.getAttribute("CART");

        if (cart != null) {
            cart.remove(id);
            session.setAttribute("CART", cart);
        }

        return "redirect:/cart";
    }
}
