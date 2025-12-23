package K23CNT3_TranThanhTung_WebBanDienThoai.controller;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import K23CNT3_TranThanhTung_WebBanDienThoai.service.TttKhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/customers")
public class TttCustomerController {

    @Autowired
    private TttKhachHangService tttKhachHangService;

    // 1. HIỂN THỊ DANH SÁCH
    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", tttKhachHangService.findAll());
        model.addAttribute("pageTitle", "Quản lý Khách hàng");
        return "admin/customers/customerlist";
    }

    // 2. HIỂN THỊ FORM SỬA
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        return tttKhachHangService.findById(id).map(customer -> {
            // SỬA TẠI ĐÂY: Đổi "customers" thành "customer"
            model.addAttribute("customer", customer);
            model.addAttribute("pageTitle", "Sửa Khách hàng");
            return "admin/customers/customerform";
        }).orElseGet(() -> {
            ra.addFlashAttribute("error", "Không tìm thấy khách hàng.");
            return "redirect:/admin/customers";
        });
    }

    // 3. LƯU THÔNG TIN
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") TttKhachHang customer, RedirectAttributes ra) {
        // Spring Boot sẽ tự hiểu @ModelAttribute("customer") khớp với th:object="${customer}"
        tttKhachHangService.save(customer);
        ra.addFlashAttribute("success", "Lưu thông tin thành công!");
        return "redirect:/admin/customers";
    }

    // 4. XÓA KHÁCH HÀNG
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            tttKhachHangService.delete(id);
            ra.addFlashAttribute("success", "Xóa thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Lỗi: Khách hàng này có thể đang có đơn hàng, không thể xóa!");
        }
        return "redirect:/admin/customers";
    }
}