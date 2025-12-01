package K23CNT3_TranThanhTung_WebBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chitiethoadon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TttChiTietHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaCTHD")
    private Integer maCTHD;

    @ManyToOne
    @JoinColumn(name = "MaHD")
    private TttHoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "MaSP")
    private TttSanPham sanPham;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private Double donGia;
}