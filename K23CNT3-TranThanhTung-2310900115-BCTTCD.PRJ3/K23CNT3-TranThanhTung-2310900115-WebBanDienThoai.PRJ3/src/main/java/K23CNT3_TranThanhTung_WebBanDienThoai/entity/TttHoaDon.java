package K23CNT3_TranThanhTung_WebBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hoadon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TttHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaHD")
    private Integer maHD;

    @ManyToOne
    @JoinColumn(name = "MaKH")
    private TttKhachHang khachHang;

    @Column(name = "NgayLap")
    private java.sql.Date ngayLap;

    @Column(name = "TongTien")
    private Double tongTien;

    // Thêm 2 trường mới này
    @Column(name = "phuong_thuc_tt")
    private String phuongThucTT;

    @Column(name = "trang_thai_tt")
    private String trangThaiTT;
}