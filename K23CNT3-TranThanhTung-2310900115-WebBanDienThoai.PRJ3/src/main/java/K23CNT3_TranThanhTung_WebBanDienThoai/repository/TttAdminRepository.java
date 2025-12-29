package K23CNT3_TranThanhTung_WebBanDienThoai.repository;

import K23CNT3_TranThanhTung_WebBanDienThoai.entity.TttAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TttAdminRepository extends JpaRepository<TttAdmin, Integer> {
    Optional<TttAdmin> findByUsername(String username);
}
