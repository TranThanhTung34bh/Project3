package K23CNT3_TranThanhTung_WebBanDienThoai.repository;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TttSanPhamRepository extends JpaRepository<TttSanPham, Integer> {
    List<TttSanPham> findByDanhMucMaDMSP(Integer maDMSP);
    List<TttSanPham> findByTenSPContainingIgnoreCase(String q);
}
