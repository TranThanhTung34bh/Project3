package K23CNT3_TranThanhTung_WebBanDienThoai.repository;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttKhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TttKhachHangRepository extends JpaRepository<TttKhachHang, Integer> {
    Optional<TttKhachHang> findByUsername(String username);
}
