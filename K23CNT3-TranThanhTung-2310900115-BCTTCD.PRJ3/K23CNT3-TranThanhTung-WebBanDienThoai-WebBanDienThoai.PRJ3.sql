-- Tạo database
CREATE DATABASE IF NOT EXISTS bandienthoai;
USE bandienthoai;

-- Bảng danh mục sản phẩm
CREATE TABLE danhmucsp (
    MaDMSP INT AUTO_INCREMENT PRIMARY KEY,
    TenDMSP VARCHAR(100) NOT NULL,
    MoTa TEXT
);

-- Bảng sản phẩm
CREATE TABLE sanpham (
    MaSP INT AUTO_INCREMENT PRIMARY KEY,
    TenSP VARCHAR(150) NOT NULL,
    Gia DECIMAL(15,2) NOT NULL,
    SoLuong INT DEFAULT 0,
    HinhAnh VARCHAR(255),
    MaDMSP INT,
    FOREIGN KEY (MaDMSP) REFERENCES danhmucsp(MaDMSP)
);

-- Bảng khách hàng + tài khoản user
CREATE TABLE khachhang (
    MaKH INT AUTO_INCREMENT PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    SDT VARCHAR(20) NOT NULL,
    DiaChi VARCHAR(255),
    Email VARCHAR(100),
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL
);

-- Bảng hóa đơn
CREATE TABLE hoadon (
    MaHD INT AUTO_INCREMENT PRIMARY KEY,
    MaKH INT,
    NgayLap DATE NOT NULL,
    TongTien DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (MaKH) REFERENCES khachhang(MaKH)
);

-- Bảng chi tiết hóa đơn
CREATE TABLE chitiethoadon (
    MaCTHD INT AUTO_INCREMENT PRIMARY KEY,
    MaHD INT,
    MaSP INT,
    SoLuong INT NOT NULL,
    DonGia DECIMAL(15,2) NOT NULL,
    FOREIGN KEY (MaHD) REFERENCES hoadon(MaHD),
    FOREIGN KEY (MaSP) REFERENCES sanpham(MaSP)
);

-- Bảng admin
CREATE TABLE admin (
    MaAdmin INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    HoTen VARCHAR(100)
);
 
 drop database bandienthoai;
-- ========================
-- Dữ liệu bảng danh mục sản phẩm
INSERT INTO danhmucsp (madmsp, mo_ta, tendmsp) VALUES
(1, 'Các dòng điện thoại Apple', 'iPhone'),
(2, 'Điện thoại Samsung Galaxy', 'Samsung'),
(3, 'Điện thoại Xiaomi Redmi, Mi', 'Xiaomi'),
(4, 'Điện thoại thương hiệu OPPO', 'OPPO'),
(5, 'Các loại phụ kiện điện thoại', 'Phụ kiện');

-- Dữ liệu bảng sản phẩm
INSERT INTO sanpham (masp, gia, hinh_anh, so_luong, tensp, madmsp) VALUES
(1, 32990000, 'iphone15promax.jpg', 10, 'iPhone 15 Pro Max', 1),
(2, 19990000, 'iphone14.jpg', 15, 'iPhone 14', 1),
(3, 28990000, 's24ultra.jpg', 12, 'Samsung S24 Ultra', 2),
(4, 8990000, 'a54.jpg', 20, 'Samsung A54', 2),
(5, 16990000, '13tpro.jpg', 18, 'Xiaomi 13T Pro', 3),
(6, 4990000, 'note12.jpg', 35, 'Xiaomi Redmi Note 12', 3),
(7, 10990000, 'reno10.jpg', 22, 'OPPO Reno10', 4),
(8, 3990000, 'a38.jpg', 40, 'OPPO A38', 4),
(9, 250000, 'caplightning.jpg', 100, 'Cáp Lightning', 5),
(10, 150000, 'oplung.jpg', 80, 'Ốp lưng iPhone', 5);

-- Dữ liệu bảng khách hàng (có username + password)
INSERT INTO khachhang (makh, dia_chi, email, ho_ten, password, sdt, username) VALUES
(1, 'Hà Nội', 'vana@gmail.com', 'Nguyễn Văn A', '123456', '0912345678', 'user1'),
(2, 'Hồ Chí Minh', 'thib@gmail.com', 'Trần Thị B', '123456', '0987654321', 'user2'),
(3, 'Đà Nẵng', 'vanc@gmail.com', 'Phạm Văn C', '123456', '0933555777', 'user3'),
(4, 'Hải Phòng', 'thid@gmail.com', 'Lê Thị D', '123456', '0977888999', 'user4'),
(5, 'Cần Thơ', 'vane@gmail.com', 'Hoàng Văn E', '123456', '0909001122', 'user5');

-- Dữ liệu bảng hóa đơn
INSERT INTO hoadon (mahd, ngay_lap, tong_tien, makh) VALUES
(1, '2025-11-20', 61980000, 1),
(2, '2025-11-21', 8990000, 2),
(3, '2025-11-22', 3990000, 3);
-- Dữ liệu bảng chi tiết hóa đơn
INSERT INTO chitiethoadon (macthd, don_gia, so_luong, mahd, masp) VALUES
(1, 32990000, 1, 1, 1),    
(2, 28990000, 1, 1, 3),   
(3, 150000, 10, 1, 10),  
(4, 8990000, 4, 2, 4),    
(5, 3990000, 8, 3, 8);     

-- Dữ liệu bảng admin
INSERT INTO admin (ma_admin, ho_ten, password, username) VALUES
(2,'Trần Thanh Tùng','123456','tung34bh'),
(1,'Quản trị viên','123456','admin');
