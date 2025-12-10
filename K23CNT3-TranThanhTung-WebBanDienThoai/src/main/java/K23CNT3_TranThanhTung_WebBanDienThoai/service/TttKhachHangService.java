package K23CNT3_TranThanhTung_WebBanDienThoai.service;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import java.util.List;

public interface TttKhachHangService {
    List<TttKhachHang> findAll();
    TttKhachHang findById(Integer id);
    TttKhachHang save(TttKhachHang kh);
    void delete(Integer id);
}
