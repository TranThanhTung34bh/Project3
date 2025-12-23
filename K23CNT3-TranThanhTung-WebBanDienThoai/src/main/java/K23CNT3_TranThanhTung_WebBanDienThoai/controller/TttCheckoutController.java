package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.*;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class TttCheckoutController {

    @Autowired private TttKhachHangRepository khRepo;
    @Autowired private TttHoaDonRepository hdRepo;
    @Autowired private TttChiTietHoaDonRepository cthdRepo;
    @Autowired private TttSanPhamRepository spRepo;

    @PostMapping
    public String checkout(@RequestParam Integer customerId,
                           @RequestParam(defaultValue = "COD") String paymentMethod,
                           HttpSession session){
        Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("CART");
        if(cart==null || cart.isEmpty()) return "redirect:/cart";

        TttKhachHang kh = khRepo.findById(customerId).orElse(null);
        if(kh==null) return "redirect:/cart";

        TttHoaDon hd = new TttHoaDon();
        hd.setKhachHang(kh);
        hd.setNgayLap(java.sql.Date.valueOf(java.time.LocalDate.now()));

        // Gán thông tin thanh toán
        if(paymentMethod.equals("QR")) {
            hd.setPhuongThucTT("Chuyển khoản QR Code");
            hd.setTrangThaiTT("Đã thanh toán (Chờ xác nhận)");
        } else {
            hd.setPhuongThucTT("Thanh toán trực tiếp (COD)");
            hd.setTrangThaiTT("Chưa thanh toán");
        }

        hd.setTongTien(0.0);
        hd = hdRepo.save(hd);

        double total = 0;
        for(Map.Entry<Integer,Integer> e : cart.entrySet()){
            TttSanPham sp = spRepo.findById(e.getKey()).orElse(null);
            if(sp==null) continue;

            TttChiTietHoaDon ct = new TttChiTietHoaDon();
            ct.setHoaDon(hd);
            ct.setSanPham(sp);
            ct.setSoLuong(e.getValue());
            ct.setDonGia(sp.getGia());
            cthdRepo.save(ct);
            total += sp.getGia() * e.getValue();
        }
        hd.setTongTien(total);
        hdRepo.save(hd);

        session.removeAttribute("CART");
        return "redirect:/?success";
    }
}
