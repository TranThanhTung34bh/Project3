package K23CNT3_TranThanhTung_WebBanDienThoai.service;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttHoaDon;
import java.util.List;

public interface TttHoaDonService {
    List<TttHoaDon> findAll();
    TttHoaDon findById(Integer id);
    TttHoaDon save(TttHoaDon hd);
}
