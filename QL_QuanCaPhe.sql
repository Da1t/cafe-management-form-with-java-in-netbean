create database QL_QuanCaPhe
go
use QL_QuanCaPhe
go
set dateformat dmy
create table LoaiSanPham
(
	MaLSP char(10) primary key not null,
	TenLSP nvarchar(50),
)

select TenLSP from LoaiSanPham

create table SanPham
(
	MaSP char(10) primary key not null,
	TenSP nvarchar(50),
	GiaTien bigint,
	DVT nvarchar(20),
	MaLSP char(10),
	constraint fk_sp_lsp foreign key (MaLSP) references LoaiSanPham(MaLSP)
)

select TenSP from SanPham

select MaSP from SanPham where TenSP = N''


create table Ban
(
	MaBan char(10) primary key not null,
	TenBan nvarchar(50),
	TrangThai nvarchar(50) default N'Còn trống',
)

select * from Ban

update Ban set TrangThai = N'Còn trống' where MaBan = 'B4'

select TrangThai from Ban where MaBan = 'B2'

create table NhanVien
(
	MaNV char(10) primary key not null,
	TenNV nvarchar(50),
	NgaySinh date,
	GioiTinh nvarchar(10),
	DiaChi nvarchar(100),
	SDT char(11),
	Email char(100),
	HinhAnh char(100),
	TaiKhoan char(100),
	MatKhau char(100),
	Quyen char(30),
)

create table HoaDon
(
	MaHD char(10) primary key not null,
	MaNV char(10),
	MaBan char(10),
	NgayLapHD date,
	TongTien bigint default 0,
	GiamGia int default 0,
	TinhTrang nvarchar(50) default N'Chưa thanh toán',
	constraint fk_hd_nv foreign key (MaNV) references NhanVien(MaNV),
	constraint fk_hd_maban foreign key (MaBan) references Ban(MaBan),
)

select top(1) MaHD from HoaDon where TinhTrang = N'Chưa thanh toán'

update HoaDon set MaBan = '' where MaHD = ''

update HoaDon set GiamGia = where MaHD = ''

select GiamGia,TongTien from HoaDon where MaHD = ''

select MaBan from HoaDon where MaHD = ''

insert into HoaDon(MaHD,MaNV,MaBan) values('HD1','NV001')

select * from HoaDon

select count(*) from ChiTietHoaDon where MaHD = 'HD7'

select MaHD from HoaDon

create table ChiTietHoaDon
(
	MaHD char(10) not null,
	MaSP char(10) not null,
	SoLuong int default 1,
	ThanhTien bigint default 0,
	primary key(MaHD, MaSP),
	constraint fk_cthd_hd foreign key (MaHD) references HoaDon(MaHD),
	constraint fk_cthd_sp foreign key (MaSP) references SanPham(MaSP),
)

delete ChiTietHoaDon where MaHD = 'HD2'

delete HoaDon where MaHD = 'HD2'

select * from ChiTietHoaDon

update ChiTietHoaDon set SoLuong = where MaHD = '' and MaSP=''

delete from ChiTietHoaDon where MaHD = '' and MaSP = ''


insert into ChiTietHoaDon(MaHD,MaSP) values('','')

exec TinhThanhTien 'HD1', 'SP1'

select MaHD from HoaDon where TinhTrang = N'Chưa thanh toán'

update ChiTietHoaDon set SoLuong =  where MaHD = '' and MaSP = ''

select cthd.MaSP,TenSP, SoLuong, GiaTien, ThanhTien 
from ChiTietHoaDon cthd, SanPham sp
where cthd.MaSP = sp.MaSP and MaHD = 'HD1'

create proc TinhThanhTien @MaHD char(10), @MaSP char(10)
as
begin
	update ChiTietHoaDon
	set ThanhTien = SoLuong * GiaTien
	from ChiTietHoaDon cthd,SanPham sp
	where cthd.MaSP = sp.MaSP
	and MaHD = @MaHD and cthd.MaSP = @MaSP
end

insert into ChiTietHoaDon(MaHD,MaSP,SoLuong) values ('','',)

insert into LoaiSanPham(MaLSP, TenLSP) values ('LSP1', N'Cà phê')
insert into LoaiSanPham(MaLSP, TenLSP) values ('LSP2', N'Nước trái cây')
insert into LoaiSanPham(MaLSP, TenLSP) values ('LSP3', N'Nước ngọt')
insert into LoaiSanPham(MaLSP, TenLSP) values ('LSP4', N'Nước khoáng')

insert into SanPham(MaSP, TenSP, GiaTien, DVT, MaLSP) values ('SP1', N'Cà phê đá', 20000, N'Ly', 'LSP1')
insert into SanPham(MaSP, TenSP, GiaTien, DVT, MaLSP) values ('SP2', N'Cà phê sữa', 25000, N'Ly', 'LSP1')
insert into SanPham(MaSP, TenSP, GiaTien, DVT, MaLSP) values ('SP3', N'Nước cam', 15000, N'Ly', 'LSP2')
insert into SanPham(MaSP, TenSP, GiaTien, DVT, MaLSP) values ('SP4', N'Sting', 10000, N'Lon', 'LSP3')
insert into SanPham(MaSP, TenSP, GiaTien, DVT, MaLSP) values ('SP5', N'Aquafina', 7000, N'Chai', 'LSP4')

insert into Ban(MaBan, TenBan) values ('B1', N'Bàn 1')
insert into Ban(MaBan, TenBan) values ('B2', N'Bàn 2')
insert into Ban(MaBan, TenBan) values ('B3', N'Bàn 3')
insert into Ban(MaBan, TenBan) values ('B4', N'Bàn 4')
insert into Ban(MaBan, TenBan) values ('B5', N'Bàn 5')

set dateformat dmy
insert into NhanVien(MaNV, TenNV, NgaySinh, GioiTinh, DiaChi, SDT, Email,TaiKhoan, MatKhau,Quyen) 
values ('NV1', N'Nguyễn Lê Huỳnh', '24/04/1999', N'Nữ', N'Hồ Chí Minh', '0123465789', 'huynh@gmail.com', 'nguyenhuynh', 'nguyenhuynh','Quan ly')
insert into NhanVien(MaNV, TenNV, NgaySinh, GioiTinh, DiaChi, SDT, Email,TaiKhoan, MatKhau,Quyen) 
values ('NV2', N'Nguyễn Hải Nam', '13/03/1997', N'Nam', N'Hồ Chí Minh', '0987654321', 'nam@gmail.com', 'nguyennam', 'nguyennam','Nhan vien')
insert into NhanVien(MaNV, TenNV, NgaySinh, GioiTinh, DiaChi, SDT, Email,TaiKhoan, MatKhau,Quyen) 
values ('NV3', N'Trần Trung Đại', '04/02/2000', N'Nam', N'Hồ Chí Minh', '0436823862', 'dai@gmail.com', 'trandai', 'trandai','Nhan vien')

select MaNV from NhanVien

select TenSP from SanPham sp, LoaiSanPham lsp 
where sp.MaLSP = lsp.MaLSP and TenLSP = N'Cà phê'

go

create procedure tongtien_hoadon @mahd char(10)
as
	update HoaDon
	set TongTien = (select sum(CTHD.thanhtien) from ChiTietHoaDon CTHD where CTHD.MaHD = @mahd)-GiamGia
	where MaHD = @mahd
go


create procedure doiban @mahd char(10), @maban char(10), @mabancandoi char(10)
as
	update HoaDon
	set MaBan = @mabancandoi
	where MaHD = @mahd

	update Ban
	set TrangThai = N'Còn trống'
	where MaBan = @maban

	update Ban
	set TrangThai = N'Đang phục vụ'
	where MaBan = @mabancandoi
go

exec doiban '','',''