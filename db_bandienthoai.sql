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

-- ========================
-- Dữ liệu bảng danh mục sản phẩm
INSERT INTO danhmucsp (MaDMSP, TenDMSP, MoTa) VALUES
(1,'iPhone', 'Các dòng điện thoại Apple'),
(2,'Samsung', 'Điện thoại Samsung Galaxy'),
(3,'Xiaomi', 'Điện thoại Xiaomi Redmi, Mi'),
(4,'OPPO', 'Điện thoại thương hiệu OPPO'),
(5,'Phụ kiện', 'Các loại phụ kiện điện thoại');

-- Dữ liệu bảng sản phẩm
INSERT INTO sanpham (MaSP, TenSP, Gia, SoLuong, HinhAnh, MaDMSP) VALUES
(1,'iPhone 15 Pro Max', 32990000, 10, 'iphone15promax.jpg', 1),
(2,'iPhone 14', 19990000, 15, 'iphone14.jpg', 1),
(3,'Samsung S24 Ultra', 28990000, 12, 's24ultra.jpg', 2),
(4,'Samsung A54', 8990000, 20, 'a54.jpg', 2),
(5,'Xiaomi 13T Pro', 16990000, 18, '13tpro.jpg', 3),
(6,'Xiaomi Redmi Note 12', 4990000, 35, 'note12.jpg', 3),
(7,'OPPO Reno10', 10990000, 22, 'reno10.jpg', 4),
(8,'OPPO A38', 3990000, 40, 'a38.jpg', 4),
(9,'Cáp Lightning', 250000, 100, 'caplightning.jpg', 5),
(10,'Ốp lưng iPhone', 150000, 80, 'oplung.jpg', 5);

-- Dữ liệu bảng khách hàng (có username + password)
INSERT INTO khachhang (MaKH, HoTen, SDT, DiaChi, Email, Username, Password) VALUES
(1,'Nguyễn Văn A', '0912345678', 'Hà Nội', 'vana@gmail.com','user1','123456'),
(2,'Trần Thị B', '0987654321', 'Hồ Chí Minh', 'thib@gmail.com','user2','123456'),
(3,'Phạm Văn C', '0933555777', 'Đà Nẵng', 'vanc@gmail.com','user3','123456'),
(4,'Lê Thị D', '0977888999', 'Hải Phòng', 'thid@gmail.com','user4','123456'),
(5,'Hoàng Văn E', '0909001122', 'Cần Thơ', 'vane@gmail.com','user5','123456');

-- Dữ liệu bảng hóa đơn
INSERT INTO hoadon (MaHD, MaKH, NgayLap, TongTien) VALUES
(1,1,'2025-11-20',61980000),
(2,2,'2025-11-21',8990000),
(3,3,'2025-11-22',3990000);

-- Dữ liệu bảng chi tiết hóa đơn
INSERT INTO chitiethoadon (MaCTHD, MaHD, MaSP, SoLuong, DonGia) VALUES
(1,1,1,1,32990000),
(2,1,3,1,28990000),
(3,1,10,1,150000),
(4,2,4,1,8990000),
(5,3,8,1,3990000);

-- Dữ liệu bảng admin
INSERT INTO admin (MaAdmin, Username, Password, HoTen) VALUES
(1,'admin','123456','Quản trị viên');
