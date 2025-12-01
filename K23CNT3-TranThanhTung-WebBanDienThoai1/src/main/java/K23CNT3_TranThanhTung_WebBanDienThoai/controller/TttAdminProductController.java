package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttSanPham;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttSanPhamRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.repository.TttDanhMucSPRepository;
import K23CNT3_TranThanhTung_WebBanDienThoai.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/products")
public class TttAdminProductController {

    @Autowired private TttSanPhamRepository spRepo;
    @Autowired private TttDanhMucSPRepository dmRepo;
    @Autowired private FileStorageService fileService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("products", spRepo.findAll());
        return "admin/products/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("product", new TttSanPham());
        model.addAttribute("dms", dmRepo.findAll());
        return "admin/products/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TttSanPham product, @RequestParam("imageFile") MultipartFile file){
        try {
            if(file!=null && !file.isEmpty()){
                String filename = fileService.store(file);
                product.setHinhAnh(filename);
            }
            spRepo.save(product);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", spRepo.findById(id).orElse(new TttSanPham()));
        model.addAttribute("dms", dmRepo.findAll());
        return "admin/products/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        spRepo.deleteById(id);
        return "redirect:/admin/products";
    }
}
