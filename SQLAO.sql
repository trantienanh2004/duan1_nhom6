﻿create DATABASE SQL_BAN_AO
GO
USE SQL_BAN_AO
GO


IF OBJECT_ID('TAIKHOAN') IS NOT NULL
DROP TABLE TAIKHOAN
GO
CREATE TABLE TAIKHOAN
(
MATK VARCHAR(10) NOT NULL,
TENTK VARCHAR(15) NOT NULL,
MATKHAU VARCHAR(15) NOT NULL,
CHUCVU VARCHAR(20) NOT NULL,
CONSTRAINT PK_TAIKHOAN PRIMARY KEY (MATK)
)
GO

IF OBJECT_ID('SANPHAM') IS NOT NULL
DROP TABLE SANPHAM
GO
CREATE TABLE SANPHAM
(
MASP VARCHAR(10) NOT NULL,
TENSP VARCHAR(15) NOT NULL,
ngaytao date not null,
trangthai varchar(50) null,


CONSTRAINT PK_SANPHAM PRIMARY KEY (MASP)
)
GO
IF OBJECT_ID('MAUSAC') IS NOT NULL
DROP TABLE MAUSAC
GO
CREATE TABLE MAUSAC
(
MAMS INT IDENTITY(1,1) NOT NULL,
TENMAU NVARCHAR(15) NOT NULL,
CONSTRAINT PK_MAUSAC PRIMARY KEY (MAMS)
)
GO
IF OBJECT_ID('CHATLIEU') IS NOT NULL
DROP TABLE CHATLIEU
GO
CREATE TABLE CHATLIEU
(
MACL INT IDENTITY(1,1) NOT NULL,
TENCHATLIEU NVARCHAR(15) NOT NULL,
CONSTRAINT PK_CHATLIEU PRIMARY KEY (MACL)
)
GO
IF OBJECT_ID('SIZE') IS NOT NULL
DROP TABLE SIZE
GO
CREATE TABLE SIZE
(
MASIZE int identity(1,1) ,
TENSIZE NVARCHAR(15) NOT NULL,
CONSTRAINT PK_SIZE PRIMARY KEY (MASIZE)
)
GO
IF OBJECT_ID('CHITIETSANPHAM') IS NOT NULL
DROP TABLE CHITIETSANPHAM
GO
CREATE TABLE CHITIETSANPHAM
(
MACTSP INT IDENTITY(1,10) NOT NULL,
MASP VARCHAR(10) NOT NULL,
MAMS INT  NOT NULL,
MACL INT NOT NULL,
MASIZE int NOT NULL,
HINHANH VARCHAR(100) NULL,
SOLUONG INT NOT NULL,
GIA MONEY NOT NULL,
MOTA NVARCHAR(255)  NULL,
thanhtoan nvarchar(50) null,
CONSTRAINT PK_CHITIETSANPHAM PRIMARY KEY (MACTSP),
CONSTRAINT FK_CHITIETSANPHAM_SANPHAM FOREIGN KEY (MASP) REFERENCES SANPHAM,
CONSTRAINT FK_CHITIETSANPHAM_MAUSAC FOREIGN KEY (MAMS) REFERENCES MAUSAC,
CONSTRAINT FK_CHITIETSANPHAM_SIZE FOREIGN KEY (MASIZE) REFERENCES SIZE,
CONSTRAINT FK_CHITIETSANPHAM_CHATLIEU FOREIGN KEY (MACL) REFERENCES CHATLIEU,
)
GO
IF OBJECT_ID('KHACHHANG') IS NOT NULL
DROP TABLE KHACHHANG
GO
CREATE TABLE KHACHHANG
(
MAKH INT IDENTITY(1,1),
TENKH NVARCHAR(50)  NULL,
SDT VARCHAR(15)  NULL,
GIOITINH BIT NOT NULL,
DIACHI NVARCHAR(50) NOT NULL,
CONSTRAINT PK_KHACHHANG PRIMARY KEY (MAKH)
)
GO
IF OBJECT_ID('NHANVIEN') IS NOT NULL
DROP TABLE NHANVIEN
GO
CREATE TABLE NHANVIEN
(
MANV INT IDENTITY(1,1),
MATK VARCHAR(10) NOT NULL,
TENNV NVARCHAR(50) NOT NULL,
SDT VARCHAR(15) NOT NULL,
GIOITINH BIT NOT NULL,
DIACHI NVARCHAR(50) NOT NULL,
CONSTRAINT PK_NHANVIEN PRIMARY KEY (MANV),
CONSTRAINT FK_NHANVIEN_TAIKHOAN FOREIGN KEY (MATK) REFERENCES TAIKHOAN 
)
GO
IF OBJECT_ID('HOADON') IS NOT NULL
DROP TABLE HOADON
GO
CREATE TABLE HOADON
(
MAHD INT IDENTITY(100000,1),
MANV INT NOT NULL,
MAKH INT NOT NULL,
TRANGTHAI NVARCHAR(255)  NULL,
NGAYTAO DATETIME NOT NULL,
CONSTRAINT PK_HOADON PRIMARY KEY (MAHD),
CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN,
CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG

)
GO
IF OBJECT_ID('HOADONCHITIET') IS NOT NULL
DROP TABLE HOADONCHITIET
GO
CREATE TABLE HOADONCHITIET
(
MAHDCT INT IDENTITY (100000,1),
MACTSP INT NOT NULL,
MAHD INT NOT NULL,
TONGTIEN MONEY NOT NULL,
SOLUONG INT NOT NULL,
TRANGTHAI NVARCHAR(255)  NULL,
CONSTRAINT PK_HOADONCHITIET PRIMARY KEY (MAHDCT),
CONSTRAINT PK_HOADONCHITIET_CHITIETSANPHAM FOREIGN KEY(MACTSP) REFERENCES CHITIETSANPHAM ,
CONSTRAINT PK_HOADONCHITIET_HOADON FOREIGN KEY(MAHD) REFERENCES HOADON 
)
GO
IF OBJECT_ID('DOITRA') IS NOT NULL
DROP TABLE DOITRA
GO
CREATE TABLE DOITRA
(
MADT INT IDENTITY (100000,1),
MAHDCT INT NOT NULL,
MAKH INT NOT NULL,
MANV INT NOT NULL,
NGAYDOI DATE NOT NULL,
LYDO NVARCHAR(255) NOT NULL,
CONSTRAINT PK_DOITRA PRIMARY KEY (MADT),
CONSTRAINT PK_DOITRA_HOADONCHITIET FOREIGN KEY(MAHDCT) REFERENCES HOADONCHITIET ,
CONSTRAINT PK_DOITRA_KHACHHANG FOREIGN KEY(MAKH) REFERENCES KHACHHANG ,
CONSTRAINT PK_DOITRA_NHANVIEN FOREIGN KEY(MANV) REFERENCES NHANVIEN ,
)


SELECT * FROM TAIKHOAN
SELECT sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,gia,chitietsanpham.SOLUONG FROM SANPHAM join CHITIETSANPHAM on sanpham.MASP = CHITIETSANPHAM.masp join MAUSAC on MAUSAC.MAMS = CHITIETSANPHAM.MAMS join CHATLIEU on CHATLIEU.MACL = CHITIETSANPHAM.MACL join SIZE on SIZE.MASIZE = CHITIETSANPHAM.MASIZE
group by sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,gia,chitietsanpham.SOLUONG
SELECT * FROM MauSAC
SELECT * FROM CHatlieu
SELECT * FROM SIZE
SELECT * FROM HOADON
SELECT * FROM KHACHHANG
SELECT * FROM NHANVIEN
SELECT * FROM HOADONCHITIET
select * from DOITRA
SELECT * FROM CHITIETSANPHAM 
select * from SANPHAM




INSERT INTO TAIKHOAN(MATK,TENTK,MATKHAU,CHUCVU) VALUES
('3','NHANVIENDZ03','1234','NHANVIEN'),
('1','NHANVIENDZ01','1234','NHANVIEN'),
('2','NHANVIENDZ02','1234','NHANVIEN')

INSERT INTO SANPHAM (MASP,TENSP,ngaytao,trangthai) VALUES
('SP01',N'Áo sơ mi','11/21/2023',null),
('SP02',N'Áo PHÔNG','11/21/2023',null),
('SP03',N'Áo GIÓ','11/21/2023',null)

INSERT INTO MAUSAC(TENMAU) VALUES
(N'Trắng'),
(N'Đen'),
(N'Xanh dương')


INSERT INTO CHATLIEU(TENCHATLIEU) VALUES
(N'Cotton'),
(N'Len'),
(N'Kaki')
INSERT INTO SIZE ( TENSIZE)
VALUES
( N'Size S'),
( N'Size M'),
( N'Size L'),
( N'Size XL')
INSERT INTO CHITIETSANPHAM (MASP, MAMS, MACL, MASIZE, HINHANH, SOLUONG, GIA, MOTA,thanhtoan)
VALUES
('SP01', 1, 1, 1, '/images/ao-so-mi-trang.jpg', 10, 200000, 'Áo sơ mi nam màu trắng, chất liệu cotton', 'chưa thanh toán'),
('SP02', 2, 2, 2, '/images/ao-phong-den.jpg', 5, 150000, 'Áo phông nam màu đen, chất liệu cotton', 'chưa thanh toán'),
('SP03', 3, 1, 3, '/images/ao-gio-xanh-duong.jpg', 20, 300000, 'Áo gió nam màu xanh dương, chất liệu kaki' ,'chưa thanh toán')
INSERT INTO KHACHHANG (TENKH, SDT, GIOITINH, DIACHI)
VALUES
(N'Nguyễn Văn A', '0123456789', 1, N'Số 1, đường Nguyễn Trãi, Hà Nội'),
(N'Trần Thị B', '0987654321', 0, N'Số 2, đường Lê Duẩn, TP. Hồ Chí Minh');
INSERT INTO NHANVIEN (MATK, TENNV, SDT, GIOITINH, DIACHI)
VALUES
('1', N'Nhân viên 1', '0123456789', 1, N'Số 3, đường Trần Hưng Đạo, Đà Nẵng'),
('2', N'Nhân viên 2', '0987654321', 0, N'Số 4, đường Bà Triệu, TP. Hải Phòng');
INSERT INTO HOADON (MANV, MAKH, NGAYTAO,TRANGTHAI)
VALUES
(1, 1, '2023-11-10','Chờ thanh toán'),
(2, 2, '2023-11-11','Chờ thanh toán');
INSERT INTO HOADONCHITIET (MACTSP, MAHD, TONGTIEN, SOLUONG, TRANGTHAI)
VALUES
(1, 100000, 200000, 1, 'đã thanh toán'),
(11, 100000, 150000, 2, 'đã thanh toán'),
(21, 100001, 300000, 3, 'đã thanh toán');
INSERT INTO DOITRA (MAHDCT, MAKH, MANV, NGAYDOI, LYDO)
VALUES
(100001, 1, 1, '2023-11-12', N'Đổi size');


-- khu vục test

SELECT sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,gia,chitietsanpham.SOLUONG,MACTSP FROM SANPHAM join CHITIETSANPHAM
on sanpham.MASP = CHITIETSANPHAM.masp join MAUSAC on MAUSAC.MAMS = CHITIETSANPHAM.MAMS join CHATLIEU on CHATLIEU.MACL = CHITIETSANPHAM.MACL 
join SIZE on SIZE.MASIZE = CHITIETSANPHAM.MASIZE
group by sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,gia,chitietsanpham.SOLUONG,MACTSP

SELECT SANPHAM.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,gia,chitietsanpham.SOLUONG,MACTSP,CHITIETSANPHAM.HINHANH,trangthai FROM SANPHAM 
               join CHITIETSANPHAM on sanpham.MASP = CHITIETSANPHAM.masp 
               join MAUSAC on MAUSAC.MAMS = CHITIETSANPHAM.MAMS 
               join CHATLIEU on CHATLIEU.MACL = CHITIETSANPHAM.MACL 
               join SIZE on SIZE.MASIZE = CHITIETSANPHAM.MASIZE
			 
			   order by sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,FORMAT(gia,'N0','de-DE'),chitietsanpham.SOLUONG,MACTSP,CHITIETSANPHAM.HINHANH,trangthai
---
			   update sanpham
			   set tensp ='áo thun',ngaytao = '11/22/2023'
			   where MASP = 'sp04'
			   SELECT sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,FORMAT(gia,'N0','de-DE'),chitietsanpham.SOLUONG,MACTSP,CHITIETSANPHAM.HINHANH,trangthai FROM SANPHAM 
                join CHITIETSANPHAM on sanpham.MASP = CHITIETSANPHAM.masp 
                join MAUSAC on MAUSAC.MAMS = CHITIETSANPHAM.MAMS 
                join CHATLIEU on CHATLIEU.MACL = CHITIETSANPHAM.MACL 
                join SIZE on SIZE.MASIZE = CHITIETSANPHAM.MASIZE order by sanpham.masp,TENSP,TENSIZE,TENMAU,TENCHATLIEU,FORMAT(gia,'N0','de-DE'),chitietsanpham.SOLUONG,MACTSP,CHITIETSANPHAM.HINHANH,trangthai


				delete HOADON where TRANGTHAI = N'Chờ thanh toán'