package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttDanhMucSP;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttDanhMucSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/categories")
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
        model.addAttribute("dm", new TttDanhMucSP());
        return "admin/danhmuc/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TttDanhMucSP dm){
        repo.save(dm);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("dm", repo.findById(id).orElse(new TttDanhMucSP()));
        return "admin/danhmuc/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        repo.deleteById(id);
        return "redirect:/admin/categories";
    }
}
