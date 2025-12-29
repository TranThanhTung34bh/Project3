package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttSanPhamService;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttDanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TttHomeController {

    @Autowired
    private TttSanPhamService spService;

    @Autowired
    private TttDanhMucService dmService;

    @GetMapping({"/", "/index"})
    public String index(@RequestParam(required = false) Integer cat, Model model) {
        if (cat != null) {
            model.addAttribute("products", spService.findByDanhMuc(cat));
        } else {
            model.addAttribute("products", spService.findAll());
        }
        model.addAttribute("dms", dmService.findAll());
        model.addAttribute("hotSales", spService.findAll());
        model.addAttribute("cat", cat);

        // Lưu ý: Dòng này có thể không cần thiết nữa nếu bạn dùng Session để kiểm tra user
        // Nhưng nếu form login nằm ngay trên trang chủ thì hãy giữ lại
        model.addAttribute("khachHang", new TttKhachHang());

        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        model.addAttribute("products", spService.search(q));
        model.addAttribute("dms", dmService.findAll());
        model.addAttribute("q", q);
        return "index";
    }
}