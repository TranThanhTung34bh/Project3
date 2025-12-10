package K23CNT3_TranThanhTung_WebBanDienThoai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "khachhang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TttKhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaKH")
    private Integer maKH;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;
}