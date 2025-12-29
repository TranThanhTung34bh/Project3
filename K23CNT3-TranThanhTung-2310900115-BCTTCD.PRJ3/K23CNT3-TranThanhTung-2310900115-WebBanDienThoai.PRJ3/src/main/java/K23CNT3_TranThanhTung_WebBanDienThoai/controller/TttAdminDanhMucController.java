package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttDanhMucSP;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttDanhMucSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/danhmuc")
public class TttAdminDanhMucController {

    @Autowired
    private TttDanhMucSPRepository repo;

    @GetMapping
    public String list(Model model){
        model.addAttribute("dms", repo.findAll());
        return "admin/danhmuc/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        // BẮT BUỘC: Đổi "dm" thành "dms" để tránh lỗi null trong file form.html
        model.addAttribute("dms", new TttDanhMucSP());
        return "admin/danhmuc/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("dms") TttDanhMucSP dms){
        repo.save(dms);
        return "redirect:/admin/danhmuc";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        // BẮT BUỘC: Đổi "dm" thành "dms" để khớp với th:object="${dms}"
        model.addAttribute("dms", repo.findById(id).orElse(new TttDanhMucSP()));
        return "admin/danhmuc/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        repo.deleteById(id);
        return "redirect:/admin/danhmuc";
    }
}