CREATE DATABASE RapBHV

USE RapBHV

CREATE TABLE NhanVien (
    MaNhanVien VARCHAR(10) PRIMARY KEY,
    HoTen NVARCHAR(100),
    ChucVu NVARCHAR(50),
    Luong money,
    Email VARCHAR(50),
    DiaChi NVARCHAR(255),
    Sdt VARCHAR(10),
    NgayVaoLam Datetime,
	TenTaiKhoan VARCHAR(50),
	MatKhau VARCHAR(50)
);

CREATE TABLE Phim (
    MaPhim VARCHAR(10) PRIMARY KEY ,
    TenPhim NVARCHAR(100),
    DaoDien NVARCHAR(100),
    TheLoai NVARCHAR(50),
    ThoiLuong INT,
    NgayPhatHanh DATE,
    NgayKetThuc DATE,
    XepHang DECIMAL(3,1),
);
--INSERT INTO Phim VALUES('P001','Naruto','Date Hayato','Anime',210,'2007-02-15','2022-02-15',9.8);
--INSERT INTO Phim VALUES('P002','Optimus','Date Hayato','Anime',200,'2007-02-15','2022-02-15',9.0);
CREATE TABLE KhachHang (
    MaKhachHang Int PRIMARY KEY,
    HoTen NVARCHAR(100),
    Email VARCHAR(50),
    Sdt VARCHAR(10),
);

CREATE TABLE Phong (
    MaPhong VARCHAR(10) PRIMARY KEY,
    soPhong INT,
	soLuongGhe int
);

CREATE TABLE LichChieu (
    MaLichChieu VARCHAR(10) PRIMARY KEY,
    NgayChieu DATETIME,
);


CREATE TABLE SuatChieu (
	MaSuatChieu VARCHAR(10) PRIMARY KEY,
	MaLichChieu VARCHAR(10),
	MaPhong VARCHAR(10),
	MaPhim VARCHAR(10),
	SuatChieu Time,
	FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
	FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim),
	FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu)
);
--INSERT INTO SuatChieu VALUES('SC001','P001','10:15:00');
--INSERT INTO SuatChieu VALUES('SC002','P001','12:45:00');
--INSERT INTO SuatChieu VALUES('SC001','P002','10:15:00');
--INSERT INTO SuatChieu VALUES('SC002','P002','12:45:00');

CREATE TABLE Ve (
    MaVe VARCHAR(10) PRIMARY KEY,
    MaSuatChieu VARCHAR(10),
    MaKhachHang Int,
	SoGhe VARCHAR(10),
	GiaVe money,
	TrangThai int
    FOREIGN KEY (MaSuatChieu) REFERENCES SuatChieu(MaSuatChieu),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang)
);

CREATE TABLE ChiTietVe (
    MaVe VARCHAR(10),
    MaNhanVien VARCHAR(10),
    TongTien money,
	SoLuongVe int,
    FOREIGN KEY (MaVe) REFERENCES Ve(MaVe),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);


