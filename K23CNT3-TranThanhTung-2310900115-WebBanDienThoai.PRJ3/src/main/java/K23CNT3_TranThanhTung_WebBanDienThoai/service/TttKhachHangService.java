package K23CNT3_TranThanhTung_WebBanDienThoai.service;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import java.util.List;
import java.util.Optional; // Cần import Optional

public interface TttKhachHangService {

    // =============================
    // Phương thức CRUD cơ bản (Giữ nguyên)
    // =============================
    List<TttKhachHang> findAll();

    // Nên sử dụng Optional cho findById để xử lý trường hợp không tìm thấy
    Optional<TttKhachHang> findById(Integer id);

    TttKhachHang save(TttKhachHang kh);

    void delete(Integer id);

    // =============================
    // Phương thức nghiệp vụ (Authentication/Authorization)
    // =============================

    Optional<TttKhachHang> login(String username, String password);

    TttKhachHang registerKhachHang(TttKhachHang khachHang);

    boolean isUsernameExist(String username);
}