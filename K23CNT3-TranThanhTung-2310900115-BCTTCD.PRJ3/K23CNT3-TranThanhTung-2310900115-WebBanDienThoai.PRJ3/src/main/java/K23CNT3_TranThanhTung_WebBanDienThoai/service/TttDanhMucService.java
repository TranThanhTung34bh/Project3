package K23CNT3_TranThanhTung_WebBanDienThoai.service;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttDanhMucSP;
import java.util.List;

public interface TttDanhMucService {
    List<TttDanhMucSP> findAll();
    TttDanhMucSP findById(Integer id);
    TttDanhMucSP save(TttDanhMucSP dm);
    void delete(Integer id);
}
