package K23CNT3_TranThanhTung_WebBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sanpham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TttSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaSP")
    private Integer maSP;

    @Column(name = "TenSP", nullable = false, length = 150)
    private String tenSP;

    // THUỘC TÍNH MỚI CHO MÔ TẢ CHI TIẾT
    @Column(name = "mo_ta_chi_tiet", columnDefinition = "TEXT")
    private String moTaChiTiet;

    @Column(name = "Gia", nullable = false)
    private Double gia;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "HinhAnh", length = 255)
    private String hinhAnh;

    @ManyToOne
    @JoinColumn(name = "MaDMSP")
    private TttDanhMucSP danhMuc;
}