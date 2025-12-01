package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttSanPhamService;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttDanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TttHomeController {

    @Autowired
    private TttSanPhamService spService;

    @Autowired
    private TttDanhMucService dmService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("products", spService.findAll());
        model.addAttribute("dms", dmService.findAll());
        return "index";
    }
}
