package K23CNT3_TranThanhTung_WebBanDienThoai.service;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttSanPham;
import java.util.List;

public interface TttSanPhamService {
    List<TttSanPham> findAll();
    TttSanPham findById(Integer id);
    TttSanPham save(TttSanPham sp);
    void deleteById(Integer id);
    List<TttSanPham> findByDanhMuc(Integer maDMSP);
    List<TttSanPham> search(String q);
}
