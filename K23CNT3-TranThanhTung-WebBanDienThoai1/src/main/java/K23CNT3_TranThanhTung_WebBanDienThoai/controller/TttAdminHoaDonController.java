package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttHoaDonRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttChiTietHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
@RequestMapping("/admin/orders")
public class TttAdminHoaDonController {

    @Autowired private TttHoaDonRepository hdRepo;
    @Autowired private TttChiTietHoaDonRepository cthdRepo;

    @GetMapping
    public String list(Model model){
        model.addAttribute("orders", hdRepo.findAll());
        return "admin/orders/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model){
        model.addAttribute("order", hdRepo.findById(id).orElse(null));
        model.addAttribute("details", cthdRepo.findAll().stream().filter(d-> d.getHoaDon()!=null && d.getHoaDon().getMaHD().equals(id)).toList());
        return "admin/orders/view";
    }
}
