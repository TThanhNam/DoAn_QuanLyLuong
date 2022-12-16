create Database QuanLyLuong
go
use QuanLyLuong
go

create table DonVi(
	maDonVi varchar(255) primary key not null,
	tenDonVi nvarchar(255)
);

insert into DonVi values('DV1', N'Phòng hành chánh')
insert into DonVi values('DV2', N'Phong nhân sự')
insert into DonVi values('DV3', N'Phong kỹ thuật')
insert into DonVi values('DV4', N'Phòng Quản Lý')
insert into DonVi values('DV5', N'Phân xưởng 2')
insert into DonVi values('DV6', N'Phân xưởng 3')


create table NhanVienHanhChanh(
	maNhanVien varchar(255) primary key,
	tenNhanVien nvarchar(255),
	soDienThoai nvarchar(255),
	ngayCT date,
	maDonVi varchar(255),
	soTaiKhoan nvarchar(255),
	tenNganHang nvarchar(255),
	trinhDo nvarchar(255),
	bangCap nvarchar(255),
	luongNgayCoBan real,
	constraint maDonViNVHC foreign key(maDonVi) references DonVi(maDonVi)
);

ALTER TABLE NhanVienHanhChanh
ADD CONSTRAINT check_luong_ngay_co_ban CHECK (luongNgayCoBan >= 0);

insert into NhanVienHanhChanh values('NV1', N'Nguyễn Văn Long', '0131941114', '2021-07-21', 'DV1', '0194981511', 'Agribank', N'Đại học', N'Kỹ sư', 206000)
insert into NhanVienHanhChanh values('NV2', N'Trần Thành Nam', '0131151526', '2020-01-25', 'DV2', '0157538293', 'Vietcombank', N'12/12', N'Chưa có', 200000)
insert into NhanVienHanhChanh values('NV3', N'Phan Gia Hân', '0114151515', '2020-05-11', 'DV2', '0918571751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV4', N'Hồ Thị Hoài Trinh', '0344972681', '2020-05-8', 'DV3', '0919171751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV5', N'Phan Tuấn Tài', '0195351515', '2020-03-6', 'DV4', '0918538751', 'Vietcombank', N'Đại học', N'Kỹ sư', 207000)
insert into NhanVienHanhChanh values('NV6', N'Trần Quang Anh', '0126351515', '2019-03-9', 'DV4', '0918538751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV7', N'Phạm Ngọc	Anh', '0126356215', '2020-08-6', 'DV4', '0918558751', 'Sacombank', N'Đại học', N'Kỹ sư', 203000)
insert into NhanVienHanhChanh values('NV8', N'Trần Thành Nam', '0185351511', '2021-05-6', 'DV4', '0628538751', 'Agribank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV9', N'Lê Nguyễn Thành Đạt', '0195351546', '2020-07-4', 'DV4', '0918582751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV10', N'Trương Tấn Duy', '0195481515', '2020-03-1', 'DV4', '0919138751', 'Agribank', N'Đại học', N'Kỹ sư', 200500)
insert into NhanVienHanhChanh values('NV11', N'Đặng Thị Mỹ Duyên', '0131941114', '2021-07-29', 'DV1', '0194981511', 'Agribank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV12', N'Nguyễn Thị Thu Hiền', '0131151526', '2020-01-21', 'DV2', '0157538293', 'Vietcombank', N'12/12', N'Chưa có', 200000)
insert into NhanVienHanhChanh values('NV13', N'Trần Thành Nam', '0114151515', '2020-09-11', 'DV2', '0918571751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV14', N'Trần Thị Thu Hường', '0344972681', '2020-05-8', 'DV3', '0919171751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200600)
insert into NhanVienHanhChanh values('NV15', N'Nguyễn Kiều Minh Huy', '0195351515', '2020-01-6', 'DV4', '0918538751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV16', N'Nguyễn Nhĩ Khang', '0126351515', '2019-06-9', 'DV4', '0918538751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV17', N'Đặng Thị Hồng Loan', '0126356215', '2020-07-6', 'DV4', '0918558751', 'Sacombank', N'Đại học', N'Kỹ sư', 200040)
insert into NhanVienHanhChanh values('NV18', N'Lê Thị Mỹ Ngọc', '0185351511', '2021-01-6', 'DV4', '0628538751', 'Agribank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV19', N'Ngô Thị Ánh Ngọc', '0195351546', '2020-09-4', 'DV4', '0918582751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV20', N'Nguyễn Thị Quỳnh Như', '0195481515', '2020-03-1', 'DV4', '0919138751', 'Agribank', N'Đại học', N'Kỹ sư', 203000)
insert into NhanVienHanhChanh values('NV21', N'Văn Khang Phan', '0131941114', '2021-07-21', 'DV1', '0194981511', 'Agribank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV22', N'Nguyễn Ngọc Phú', '0131151526', '2020-01-25', 'DV2', '0157538293', 'Vietcombank', N'12/12', N'Chưa có', 200000)
insert into NhanVienHanhChanh values('NV23', N'Dương Thị Thục Quyên', '0114151515', '2020-05-11', 'DV2', '0918571751', 'Vietcombank', N'Đại học', N'Kỹ sư', 209000)
insert into NhanVienHanhChanh values('NV24', N'Đào Văn Quyền', '0344972681', '2020-05-8', 'DV3', '0919171751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200000)
insert into NhanVienHanhChanh values('NV25', N'Bùi Đức Thắng', '0195351515', '2020-03-6', 'DV4', '0918538751', 'Vietcombank', N'Đại học', N'Kỹ sư', 200700)

create table CongNhan(
	maNhanVien varchar(255) primary key,
	tenNhanVien nvarchar(255),
	soDienThoai nvarchar(255),
	ngayCT date,
	maDonVi varchar(255),
	soTaiKhoan nvarchar(255),
	tenNganHang nvarchar(255),
	soNamKN int,
	constraint maDonViCN foreign key(maDonVi) references DonVi(maDonVi),
);

ALTER TABLE CongNhan
ADD CONSTRAINT check_so_nam_kn CHECK (soNamKN >= 0);

insert into CongNhan values('NV26', N'Nguyễn Văn Minh', '0194194815', '2021-02-12', 'DV5', '1958185115', 'Agribank', 1)
insert into CongNhan values('NV27', N'Trương Thành Long', '0185181511', '2020-05-25', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV28', N'Trần Lệ Quyên', '0149195151', '2020-09-03', 'DV5', '10519591', 'ACB', 2)
insert into CongNhan values('NV29', N'Phạm Đức Thắng', '0185181511', '2020-02-25', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV30', N'TNguyễn Tấn Thịnh', '0149195151', '2020-01-03', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV31', N'Nguyễn Quốc Thịnh', '0149195151', '2020-09-06', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV32', N'Phạm Thị Anh Thư', '0194194815', '2021-02-28', 'DV5', '1958185115', 'Agribank', 1)
insert into CongNhan values('NV33', N'Lê Thị Thủy Tiên', '0185181511', '2020-05-24', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV34', N'An Thị Thùy Trang', '0149195151', '2020-06-03', 'DV5', '10519591', 'ACB', 2)
insert into CongNhan values('NV35', N'Phạm Lê Minh Tri', '0185181511', '2020-02-01', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV36', N'Đặng Huỳnh Kiều Trinh', '0149195151', '2020-01-09', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV37', N'Trần Anh Tuấn', '0149195151', '2020-04-06', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV38', N'Nguyễn Thanh Tùng', '0194194815', '2021-02-12', 'DV5', '1958185115', 'Agribank', 1)
insert into CongNhan values('NV39', N'Nguyễn Quang Việt', '0185181511', '2020-05-25', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV40', N'Lê Tú Tường Vy', '0149195151', '2020-09-03', 'DV5', '10519591', 'ACB', 2)
insert into CongNhan values('NV41', N'Nguyễn Nữ Ánh Tuyết', '0185181511', '2020-02-25', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV42', N'Lê Văn Việt', '0149195151', '2020-01-03', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV43', N'Lâm Thị Như Ý', '0149195151', '2020-09-06', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV44', N'Nguyễn Duy Long', '0194194815', '2021-02-12', 'DV5', '1958185115', 'Agribank', 1)
insert into CongNhan values('NV45', N'Cao Thị Hồng Mai', '0185181511', '2020-05-25', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV46', N'Trương Khắc Anh Tiến', '0149195151', '2020-09-03', 'DV5', '10519591', 'ACB', 2)
insert into CongNhan values('NV47', N'Nguyễn Phi Hoàng', '0185181511', '2020-02-25', 'DV5', '105915817', 'ACB', 3)
insert into CongNhan values('NV48', N'Nguyễn Viết Học', '0149195151', '2020-01-03', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV49', N'Nguyễn Phạm Hoàng	Long', '0149195151', '2020-09-06', 'DV6', '10519591', 'ACB', 2)
insert into CongNhan values('NV50', N'Nguyễn Vương Ngọc Hải', '0194194815', '2021-02-12', 'DV5', '1958185115', 'Agribank', 1)

create table SanPham(
	maSanPham varchar(255) primary key,
	tenSanPham nvarchar(255),
	donGiaSP real
);

ALTER TABLE SanPham
ADD CONSTRAINT check_don_gia_sp CHECK (donGiaSP >= 0);

insert into SanPham values('SP1', N'Áo khoác nam',100000)
insert into SanPham values('SP2', N'Áo thun nam',200000)
insert into SanPham values('SP3', N'Áo sơ mi nam',100000)
insert into SanPham values('SP4', N'Áo phông nam',100000)
insert into SanPham values('SP5', N'Áo thun cổ tròn nam',100000)
insert into SanPham values('SP6', N'Áo thun unixes mi',30000)
insert into SanPham values('SP7', N'Áo xuông dài tay',50000)
insert into SanPham values('SP8', N'Áo thun SWEATER',200000)
insert into SanPham values('SP9', N'Áo bèo nhún mi',300000)
insert into SanPham values('SP10', N'Áo viền trắng chữ cổ tim',250000)
insert into SanPham values('SP11', N'Áo khoác rộng',100000)
insert into SanPham values('SP12', N'Áo thun trắng nam',200000)
insert into SanPham values('SP13', N'Áo sơ mi form rộng',300000)
insert into SanPham values('SP14', N'Áo khoác nỉ rộng',100000)
insert into SanPham values('SP15', N'Áo thun cổ tròn',200000)
insert into SanPham values('SP16', N'Áo khoác unixes',300000)
insert into SanPham values('SP17', N'Áo tiểu thư cổ bèo tay dài',100000)
insert into SanPham values('SP18', N'Áo thun đen',200000)
insert into SanPham values('SP19', N'Áo Hoodie nỉ',300000)
insert into SanPham values('SP20', N'Áo sơ mi học sinh',300000)
insert into SanPham values('SP21', N'Áo Hoodie nỉ rộng unixes',100000)
insert into SanPham values('SP22', N'Áo at one toboy',200000)
insert into SanPham values('SP23', N'Áo sơ mi freesize',300000)
insert into SanPham values('SP24', N'Áo khoác nữ form rộng',100000)
insert into SanPham values('SP25', N'Áo thun tay dài',200000)
insert into SanPham values('SP26', N'Áo sơ mi tay ngắn',300000)
insert into SanPham values('SP27', N'Áo khoác thu động nam',100000)
insert into SanPham values('SP28', N'Áo thun mùa hè',200000)
insert into SanPham values('SP29', N'Quần âu nam',300000)
insert into SanPham values('SP30', N'Quần dài nam',300000)
insert into SanPham values('SP31', N'Quần Gym nam',100000)
insert into SanPham values('SP32', N'Áo khoắc bomber',200000)
insert into SanPham values('SP33', N'Áo tank top gym nam',300000)
insert into SanPham values('SP34', N'Áo ghi lê dệt kim',100000)
insert into SanPham values('SP35', N'Áo thun nỉ nam',200000)
insert into SanPham values('SP36', N'Áo sơ mi form rộng nam',300000)
insert into SanPham values('SP37', N'Áo ba lỗ nam',100000)
insert into SanPham values('SP38', N'Áo khoác kaki ',200000)
insert into SanPham values('SP39', N'Áo love color Tee',300000)
insert into SanPham values('SP40', N'Quần kaki nam',300000)
insert into SanPham values('SP41', N'Quần jean nam',100000)
insert into SanPham values('SP42', N'Quần giảm mỡ bụng eo thon',200000)
insert into SanPham values('SP43', N'Quần tập gym, yoga nam nữ',300000)
insert into SanPham values('SP44', N'Quần dài trơn',100000)
insert into SanPham values('SP45', N'Quần ống rộng suông khóa trước',200000)
insert into SanPham values('SP46', N'Quần vải jean unixes',300000)
insert into SanPham values('SP47', N'Quần jogger nam',100000)
insert into SanPham values('SP48', N'Quần bó nữ',200000)
insert into SanPham values('SP49', N'Áo sơ mi',100000)
insert into SanPham values('SP50', N'Quần vải jean bò baggy',200000)

create table CongDoanSanPham(
	maCongDoan varchar(255) primary key,
	tenCongDoan nvarchar(255),
	maSanPham varchar(255),
	donGiaCD real,
	constraint maSanPhamCD foreign key(maSanPham) references SanPham(maSanPham),
);

ALTER TABLE CongDoanSanPham
ADD CONSTRAINT check_don_gia_cd CHECK (donGiaCD >= 0);


insert into CongDoanSanPham values('CD1', N'May Thân áo', 'SP1',5000)
insert into CongDoanSanPham values('CD2', N'Tay áo', 'SP2',5000)
insert into CongDoanSanPham values('CD3', N'Cổ ao', 'SP3', 7000)
insert into CongDoanSanPham values('CD4', N'Ủi là','SP4', 9000)
insert into CongDoanSanPham values('CD5', N'Cắt chỉ thừa','SP5', 10000)
insert into CongDoanSanPham values('CD6', N'Xấy khô','SP6', 10000)
insert into CongDoanSanPham values('CD7', N'Đóng gói','SP7', 10000)
insert into CongDoanSanPham values('CD8', N'Kiểm tra sản phẩm','SP8', 10000)
insert into CongDoanSanPham values('CD9', N'Xếp kho','SP9', 10000)
insert into CongDoanSanPham values('CD10', N'Xếp kho','SP10', 10000)

create table BangChamCongThangNhanVienHanhChanh(
	maChamCongThang varchar(255) primary key,
	maNhanVien varchar(255),
	soNgayDiLam int,
	soBuoiLamThem int,
	thang int,
	nam int,
	constraint maNhanVienBCCTNVHC foreign key(maNhanVien) references NhanVienHanhChanh(maNhanVien),
);

ALTER TABLE BangChamCongThangNhanVienHanhChanh
ADD CONSTRAINT check_so_ngay_di_lam CHECK (soNgayDiLam >= 0);

ALTER TABLE BangChamCongThangNhanVienHanhChanh
ADD CONSTRAINT check_so_buoi_lam_them CHECK (soBuoiLamThem >= 0);

ALTER TABLE BangChamCongThangNhanVienHanhChanh
ADD CONSTRAINT check_thang CHECK (thang between 1 and 12);

ALTER TABLE BangChamCongThangNhanVienHanhChanh
ADD CONSTRAINT check_nam CHECK (nam >= 0);

insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV1112021', 'NV1', 2, 0, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV2112021', 'NV2', 2, 1, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV3112021', 'NV3', 2, 0, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV4112021', 'NV4', 2, 1, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV5112021', 'NV5', 2, 1, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV6112021', 'NV6', 2, 1, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV7112021', 'NV7', 2, 1, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV8112021', 'NV8', 2, 0, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV9112021', 'NV9', 2, 2, 11, 2021)
insert into BangChamCongThangNhanVienHanhChanh values('BCCTNVHCNV10112021', 'NV10', 2, 0, 11, 2021)

create table BangChamCongNgayNhanVienHanhChanh(
	maChamCongNgay varchar(255) primary key,
	maNhanVien varchar(255),
	maChamCongThang varchar(255),
	ngayChamCong date,
	coDiLam bit,
	coLamThem bit,
	constraint maNhanVienBCCNVHC foreign key(maNhanVien) references NhanVienHanhChanh(maNhanVien),
	constraint maChamCongThangBCCN foreign key(maChamCongThang) references BangChamCongThangNhanVienHanhChanh(maChamCongThang)
);



insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN1', 'NV1', 'BCCTNVHCNV1112021', '2021-11-01', 1, 0)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN2', 'NV1', 'BCCTNVHCNV1112021', '2021-11-02', 1, 0)

insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN3', 'NV2', 'BCCTNVHCNV2112021', '2021-11-01', 1, 1)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN4', 'NV2', 'BCCTNVHCNV2112021', '2021-11-02', 1, 0)

insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN5', 'NV3', 'BCCTNVHCNV3112021', '2021-11-01', 1, 0)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN6', 'NV3', 'BCCTNVHCNV3112021', '2021-11-02', 1, 0)

insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN7', 'NV4', 'BCCTNVHCNV4112021', '2021-11-01', 1, 1)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN8', 'NV4', 'BCCTNVHCNV4112021', '2021-11-02', 1, 0)


insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN9', 'NV5', 'BCCTNVHCNV5112021', '2021-11-01', 1, 1)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN10', 'NV5', 'BCCTNVHCNV5112021', '2021-11-02', 1, 0)

insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN11', 'NV6', 'BCCTNVHCNV6112021', '2021-11-01', 1, 0)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN12', 'NV6', 'BCCTNVHCNV6112021', '2021-11-02', 1, 1)

insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN13', 'NV7', 'BCCTNVHCNV7112021', '2021-11-01', 1, 0)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN14', 'NV7', 'BCCTNVHCNV7112021', '2021-11-02', 1, 1)


insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN15', 'NV8', 'BCCTNVHCNV8112021', '2021-11-01', 1, 0)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN16', 'NV8', 'BCCTNVHCNV8112021', '2021-11-02', 1, 0)


insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN17', 'NV9', 'BCCTNVHCNV9112021', '2021-11-01', 1, 1)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN18', 'NV9', 'BCCTNVHCNV9112021', '2021-11-01', 1, 1)


insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN19', 'NV10', 'BCCTNVHCNV10112021', '2021-11-01', 1, 0)
insert into BangChamCongNgayNhanVienHanhChanh values('CCNVHCN20', 'NV10', 'BCCTNVHCNV10112021', '2021-11-01', 1, 0)

create table BangChamCongNgayCongNhan(
	maChamCongNgay varchar(255) primary key,
	maNhanVien varchar(255),
	ngayChamCong date,
	constraint maNhanVienBCCCN foreign key(maNhanVien) references CongNhan(maNhanVien)
);

insert into BangChamCongNgayCongNhan values('BCCNCN1', 'NV26','2021-11-01')
insert into BangChamCongNgayCongNhan values('BCCNCN2', 'NV26','2021-11-02')

insert into BangChamCongNgayCongNhan values('BCCNCN3', 'NV27','2021-11-01')
insert into BangChamCongNgayCongNhan values('BCCNCN4', 'NV27','2021-11-09')

insert into BangChamCongNgayCongNhan values('BCCNCN5', 'NV28','2021-11-01')

insert into BangChamCongNgayCongNhan values('BCCNCN6', 'NV29','2021-11-08')
insert into BangChamCongNgayCongNhan values('BCCNCN7', 'NV29','2021-11-10')

insert into BangChamCongNgayCongNhan values('BCCNCN8', 'NV30','2021-11-10')
insert into BangChamCongNgayCongNhan values('BCCNCN9', 'NV30','2021-11-14')

insert into BangChamCongNgayCongNhan values('BCCNCN10', 'NV31','2021-11-16')
insert into BangChamCongNgayCongNhan values('BCCNCN11', 'NV32','2021-11-06')

insert into BangChamCongNgayCongNhan values('BCCNCN12', 'NV33','2021-11-04')
insert into BangChamCongNgayCongNhan values('BCCNCN13', 'NV34','2021-11-06')

insert into BangChamCongNgayCongNhan values('BCCNCN14', 'NV35','2021-11-26')
insert into BangChamCongNgayCongNhan values('BCCNCN15', 'NV35','2021-11-15')

insert into BangChamCongNgayCongNhan values('BCCNCN16', 'NV37','2021-11-14')
insert into BangChamCongNgayCongNhan values('BCCNCN17', 'NV37','2021-11-12')

insert into BangChamCongNgayCongNhan values('BCCNCN18', 'NV38','2021-11-12')
insert into BangChamCongNgayCongNhan values('BCCNCN19', 'NV38','2021-11-09')

insert into BangChamCongNgayCongNhan values('BCCNCN20', 'NV39','2021-11-09')
insert into BangChamCongNgayCongNhan values('BCCNCN21', 'NV39','2021-11-08')

insert into BangChamCongNgayCongNhan values('BCCNCN22', 'NV40','2021-11-17')
insert into BangChamCongNgayCongNhan values('BCCNCN23', 'NV40','2021-11-01')

insert into BangChamCongNgayCongNhan values('BCCNCN24', 'NV41','2021-11-09')
insert into BangChamCongNgayCongNhan values('BCCNCN25', 'NV41','2021-11-01')

insert into BangChamCongNgayCongNhan values('BCCNCN26', 'NV42','2021-11-08')
insert into BangChamCongNgayCongNhan values('BCCNCN27', 'NV42','2021-11-10')

insert into BangChamCongNgayCongNhan values('BCCNCN28', 'NV43','2021-11-10')
insert into BangChamCongNgayCongNhan values('BCCNCN29', 'NV43','2021-11-10')

insert into BangChamCongNgayCongNhan values('BCCNCN30', 'NV44','2021-11-10')

create table ChiTietChamCongCongNhan(
	maChiTietCC varchar(255) primary key,
	maCongDoan varchar(255),
	caLamViec int,
	soLuongSP int,
	maChamCong varchar(255),
	constraint maCongDoanCTCCCN foreign key(maCongDoan) references CongDoanSanPham(maCongDoan),
	constraint maChamCongCTCCCN foreign key(maChamCong) references BangChamCongNgayCongNhan(maChamCongNgay)
);

ALTER TABLE ChiTietChamCongCongNhan
ADD CONSTRAINT check_ca_lam_viec CHECK (caLamViec between 1 and 3);

ALTER TABLE ChiTietChamCongCongNhan
ADD CONSTRAINT check_so_luong_sp CHECK (soLuongSP >= 0);

insert into ChiTietChamCongCongNhan values('CTCCCN1', 'CD5', 1, 100, 'BCCNCN1')
insert into ChiTietChamCongCongNhan values('CTCCCN2', 'CD2', 3, 200, 'BCCNCN2')

insert into ChiTietChamCongCongNhan values('CTCCCN3', 'CD5', 2, 89, 'BCCNCN3')
insert into ChiTietChamCongCongNhan values('CTCCCN4', 'CD7', 1, 107, 'BCCNCN4')

insert into ChiTietChamCongCongNhan values('CTCCCN5', 'CD3', 3, 100, 'BCCNCN5')
insert into ChiTietChamCongCongNhan values('CTCCCN6', 'CD9', 2, 200, 'BCCNCN6')

insert into ChiTietChamCongCongNhan values('CTCCCN7', 'CD10', 1, 100, 'BCCNCN7')
insert into ChiTietChamCongCongNhan values('CTCCCN8', 'CD1', 2, 200, 'BCCNCN8')

insert into ChiTietChamCongCongNhan values('CTCCCN9', 'CD1', 3, 100, 'BCCNCN9')
insert into ChiTietChamCongCongNhan values('CTCCCN10', 'CD2', 1, 200, 'BCCNCN10')

insert into ChiTietChamCongCongNhan values('CTCCCN11', 'CD1', 3, 100, 'BCCNCN11')
insert into ChiTietChamCongCongNhan values('CTCCCN12', 'CD5', 2, 200, 'BCCNCN12')

insert into ChiTietChamCongCongNhan values('CTCCCN13', 'CD6', 2, 100, 'BCCNCN13')
insert into ChiTietChamCongCongNhan values('CTCCCN14', 'CD7', 1, 200, 'BCCNCN14')

insert into ChiTietChamCongCongNhan values('CTCCCN15', 'CD8', 3, 100, 'BCCNCN15')
insert into ChiTietChamCongCongNhan values('CTCCCN16', 'CD3', 1, 200, 'BCCNCN16')

insert into ChiTietChamCongCongNhan values('CTCCCN17', 'CD4', 1, 100, 'BCCNCN17')
insert into ChiTietChamCongCongNhan values('CTCCCN18', 'CD6', 3, 200, 'BCCNCN18')

insert into ChiTietChamCongCongNhan values('CTCCCN19', 'CD4', 1, 100, 'BCCNCN19')
insert into ChiTietChamCongCongNhan values('CTCCCN20', 'CD7', 3, 200, 'BCCNCN20')

insert into ChiTietChamCongCongNhan values('CTCCCN21', 'CD8', 2, 100, 'BCCNCN21')
insert into ChiTietChamCongCongNhan values('CTCCCN22', 'CD3', 3, 200, 'BCCNCN22')

insert into ChiTietChamCongCongNhan values('CTCCCN23', 'CD5', 2, 100, 'BCCNCN23')
insert into ChiTietChamCongCongNhan values('CTCCCN24', 'CD8', 3, 200, 'BCCNCN24')

insert into ChiTietChamCongCongNhan values('CTCCCN25', 'CD1', 1, 100, 'BCCNCN25')
insert into ChiTietChamCongCongNhan values('CTCCCN26', 'CD8', 1, 200, 'BCCNCN26')

create table TienBHXHNVHC(
	maTienBHXH varchar(255) primary key not null,
	tienBHXH real,
	maNhanVien varchar(255),
	thang int,
	nam int,
	constraint maNhanVienNVHCBHXH foreign key(maNhanVien) references NhanVienHanhChanh(maNhanVien)
);

ALTER TABLE TienBHXHNVHC
ADD CONSTRAINT check_tien_BHXH CHECK (tienBHXH >= 0);

ALTER TABLE TienBHXHNVHC
ADD CONSTRAINT check_thang_BHXH CHECK (thang between 1 and 12);

ALTER TABLE TienBHXHNVHC
ADD CONSTRAINT check_nam_BHXH CHECK (nam >= 0);


insert into TienBHXHNVHC values('BHXH1', 105000, 'NV1', 11, 2021)
insert into TienBHXHNVHC values('BHXH2', 200000, 'NV2', 11, 2021)
insert into TienBHXHNVHC values('BHXH3', 500000, 'NV3', 11, 2021)
insert into TienBHXHNVHC values('BHXH4', 400000, 'NV4', 11, 2021)
insert into TienBHXHNVHC values('BHXH5', 300000, 'NV5', 11, 2021)

insert into TienBHXHNVHC values('BHXH6', 100000, 'NV6', 11, 2021)
insert into TienBHXHNVHC values('BHXH7', 200000, 'NV7', 11, 2021)
insert into TienBHXHNVHC values('BHXH8', 505000, 'NV8', 11, 2021)
insert into TienBHXHNVHC values('BHXH9', 400000, 'NV9', 11, 2021)
insert into TienBHXHNVHC values('BHXH10', 300000, 'NV10', 11, 2021)

insert into TienBHXHNVHC values('BHXH11', 105000, 'NV11', 11, 2021)
insert into TienBHXHNVHC values('BHXH12', 200000, 'NV12', 11, 2021)
insert into TienBHXHNVHC values('BHXH13', 500000, 'NV13', 11, 2021)
insert into TienBHXHNVHC values('BHXH14', 405000, 'NV14', 11, 2021)
insert into TienBHXHNVHC values('BHXH15', 300000, 'NV15', 11, 2021)

insert into TienBHXHNVHC values('BHXH16', 100000, 'NV16', 11, 2021)
insert into TienBHXHNVHC values('BHXH17', 205000, 'NV17', 11, 2021)
insert into TienBHXHNVHC values('BHXH18', 500000, 'NV18', 11, 2021)
insert into TienBHXHNVHC values('BHXH19', 400000, 'NV19', 11, 2021)
insert into TienBHXHNVHC values('BHXH20', 305000, 'NV20', 11, 2021)

insert into TienBHXHNVHC values('BHXH25', 100000, 'NV21', 11, 2021)
insert into TienBHXHNVHC values('BHXH26', 200000, 'NV22', 11, 2021)

create table PhuCapNVHC(
	maPhuCap varchar(255) primary key not null,
	tienPhuCap real,
	maNhanVien varchar(255),
	thang int,
	nam int,
	constraint maNhanVienNVHCPC foreign key(maNhanVien) references NhanVienHanhChanh(maNhanVien)
);

ALTER TABLE PhuCapNVHC
ADD CONSTRAINT check_tien_phu_cap_nvhc CHECK (tienPhuCap >= 0);

ALTER TABLE PhuCapNVHC
ADD CONSTRAINT check_thang_phu_cap_nvhc CHECK (thang between 1 and 12);

ALTER TABLE PhuCapNVHC
ADD CONSTRAINT check_nam_phu_cap_nvhc CHECK (nam >= 0);

insert into PhuCapNVHC values('PC1', 400000, 'NV1', 11, 2021)
insert into PhuCapNVHC values('PC2', 350000, 'NV2', 11, 2021)
insert into PhuCapNVHC values('PC3', 100000, 'NV3', 11, 2021)
insert into PhuCapNVHC values('PC4', 350000, 'NV4', 11, 2021)
insert into PhuCapNVHC values('PC5', 100000, 'NV5', 11, 2021)

insert into PhuCapNVHC values('PC6', 400000, 'NV6', 11, 2021)
insert into PhuCapNVHC values('PC7', 350000, 'NV7', 11, 2021)
insert into PhuCapNVHC values('PC8', 100000, 'NV8', 11, 2021)
insert into PhuCapNVHC values('PC9', 350000, 'NV9', 11, 2021)
insert into PhuCapNVHC values('PC10', 100000, 'NV10', 11, 2021)

insert into PhuCapNVHC values('PC11', 400000, 'NV11', 11, 2021)
insert into PhuCapNVHC values('PC12', 350000, 'NV12', 11, 2021)
insert into PhuCapNVHC values('PC13', 100000, 'NV13', 11, 2021)
insert into PhuCapNVHC values('PC14', 350000, 'NV14', 11, 2021)
insert into PhuCapNVHC values('PC15', 100000, 'NV15', 11, 2021)

insert into PhuCapNVHC values('PC16', 400000, 'NV16', 11, 2021)
insert into PhuCapNVHC values('PC17', 350000, 'NV17', 11, 2021)
insert into PhuCapNVHC values('PC18', 100000, 'NV18', 11, 2021)
insert into PhuCapNVHC values('PC19', 350000, 'NV19', 11, 2021)
insert into PhuCapNVHC values('PC20', 100000, 'NV20', 11, 2021)

insert into PhuCapNVHC values('PC21', 400000, 'NV21', 11, 2021)
insert into PhuCapNVHC values('PC22', 350000, 'NV22', 11, 2021)
insert into PhuCapNVHC values('PC23', 100000, 'NV23', 11, 2021)
insert into PhuCapNVHC values('PC24', 350000, 'NV24', 11, 2021)
insert into PhuCapNVHC values('PC25', 100000, 'NV25', 11, 2021)


create table TienKyLuatNVHC(
	maTienKyLuat varchar(255) primary key not null,
	tienKyLuat real,
	maNhanVien varchar(255),
	thang int,
	nam int,
	constraint maNhanVienNVHCTKL foreign key(maNhanVien) references NhanVienHanhChanh(maNhanVien)
);

ALTER TABLE TienKyLuatNVHC
ADD CONSTRAINT check_tien_ky_luat_nvhc CHECK (tienKyLuat >= 0);

ALTER TABLE TienKyLuatNVHC
ADD CONSTRAINT check_thang_ky_luat_nvhc CHECK (thang between 1 and 12);

ALTER TABLE TienKyLuatNVHC
ADD CONSTRAINT check_nam_ky_luat_nvhc CHECK (nam >= 0);

insert into TienKyLuatNVHC values('KL1', 100000, 'NV1', 11, 2021)
insert into TienKyLuatNVHC values('KL2', 130000, 'NV3', 11, 2021)
insert into TienKyLuatNVHC values('KL3', 230000, 'NV4', 11, 2021)
insert into TienKyLuatNVHC values('KL4', 130000, 'NV6', 11, 2021)
insert into TienKyLuatNVHC values('KL5', 230000, 'NV8', 11, 2021)

insert into TienKyLuatNVHC values('KL6', 100000, 'NV10', 11, 2021)
insert into TienKyLuatNVHC values('KL7', 130000, 'NV12', 11, 2021)
insert into TienKyLuatNVHC values('KL8', 230000, 'NV14', 11, 2021)
insert into TienKyLuatNVHC values('KL9', 130000, 'NV20', 11, 2021)
insert into TienKyLuatNVHC values('KL10', 230000, 'NV24', 11, 2021)

create table TienBHXHCN(
	maTienBHXH varchar(255) primary key not null,
	tienBHXH real,
	maNhanVien varchar(255),
	thang int,
	nam int,
	constraint maNhanVienCNBHXH foreign key(maNhanVien) references CongNhan(maNhanVien)
);

ALTER TABLE TienBHXHCN
ADD CONSTRAINT check_tien_bhxh_cn CHECK (tienBHXH >= 0);

ALTER TABLE TienBHXHCN
ADD CONSTRAINT check_thang_bhxh_cn CHECK (thang between 1 and 12);

ALTER TABLE TienBHXHCN
ADD CONSTRAINT check_nam_bhxh_cn CHECK (nam >= 0);

insert into TienBHXHCN values('BHXH1', 100000, 'NV26', 11, 2021)
insert into TienBHXHCN values('BHXH2', 500000, 'NV27', 11, 2021)
insert into TienBHXHCN values('BHXH3', 300000, 'NV28', 11, 2021)
insert into TienBHXHCN values('BHXH4', 500000, 'NV29', 11, 2021)
insert into TienBHXHCN values('BHXH5', 200000, 'NV30', 11, 2021)

insert into TienBHXHCN values('BHXH6', 500000, 'NV31', 11, 2021)
insert into TienBHXHCN values('BHXH7', 406000, 'NV32', 11, 2021)
insert into TienBHXHCN values('BHXH8', 500000, 'NV33', 11, 2021)
insert into TienBHXHCN values('BHXH9', 700000, 'NV34', 11, 2021)
insert into TienBHXHCN values('BHXH10', 505000, 'NV35', 11, 2021)

insert into TienBHXHCN values('BHXH11', 600000, 'NV36', 11, 2021)
insert into TienBHXHCN values('BHXH12', 800000, 'NV37', 11, 2021)
insert into TienBHXHCN values('BHXH13', 200000, 'NV38', 11, 2021)
insert into TienBHXHCN values('BHXH14', 400000, 'NV39', 11, 2021)
insert into TienBHXHCN values('BHXH15', 500000, 'NV40', 11, 2021)

insert into TienBHXHCN values('BHXH16', 500000, 'NV41', 11, 2021)
insert into TienBHXHCN values('BHXH17', 100000, 'NV42', 11, 2021)
insert into TienBHXHCN values('BHXH18', 500000, 'NV43', 11, 2021)
insert into TienBHXHCN values('BHXH19', 600000, 'NV44', 11, 2021)
insert into TienBHXHCN values('BHXH20', 500000, 'NV45', 11, 2021)

create table PhuCapCN(
	maPhuCap varchar(255) primary key not null,
	tienPhuCap real,
	maNhanVien varchar(255),
	thang int,
	nam int,
	constraint maNhanVienCNPC foreign key(maNhanVien) references CongNhan(maNhanVien)
);

ALTER TABLE PhuCapCN
ADD CONSTRAINT check_tien_phu_cap_cn CHECK (tienPhuCap >= 0);

ALTER TABLE PhuCapCN
ADD CONSTRAINT check_thang_phu_cap_cn CHECK (thang between 1 and 12);

ALTER TABLE PhuCapCN
ADD CONSTRAINT check_nam_phu_cap_cn CHECK (nam >= 0);

insert into PhuCapCN values('PC1', 500000, 'NV26', 11, 2021)
insert into PhuCapCN values('PC2', 600000, 'NV27', 11, 2021)
insert into PhuCapCN values('PC3', 1000000, 'NV28', 11, 2021)
insert into PhuCapCN values('PC4', 600000, 'NV29', 11, 2021)
insert into PhuCapCN values('PC5', 1000000, 'NV30', 11, 2021)

insert into PhuCapCN values('PC6', 500000, 'NV31', 11, 2021)
insert into PhuCapCN values('PC7', 600000, 'NV32', 11, 2021)
insert into PhuCapCN values('PC8', 1000000, 'NV33', 11, 2021)
insert into PhuCapCN values('PC9', 600000, 'NV34', 11, 2021)
insert into PhuCapCN values('PC10', 1000000, 'NV35', 11, 2021)

insert into PhuCapCN values('PC11', 500000, 'NV36', 11, 2021)
insert into PhuCapCN values('PC12', 600000, 'NV37', 11, 2021)
insert into PhuCapCN values('PC13', 1000000, 'NV38', 11, 2021)
insert into PhuCapCN values('PC14', 600000, 'NV39', 11, 2021)
insert into PhuCapCN values('PC15', 1000000, 'NV40', 11, 2021)

insert into PhuCapCN values('PC16', 500000, 'NV41', 11, 2021)
insert into PhuCapCN values('PC17', 600000, 'NV42', 11, 2021)
insert into PhuCapCN values('PC18', 1000000, 'NV43', 11, 2021)
insert into PhuCapCN values('PC19', 600000, 'NV44', 11, 2021)
insert into PhuCapCN values('PC20', 1000000, 'NV45', 11, 2021)

insert into PhuCapCN values('PC21', 500000, 'NV46', 11, 2021)
insert into PhuCapCN values('PC22', 600000, 'NV47', 11, 2021)
insert into PhuCapCN values('PC23', 1000000, 'NV48', 11, 2021)
insert into PhuCapCN values('PC24', 600000, 'NV49', 11, 2021)
insert into PhuCapCN values('PC25', 1000000, 'NV50', 11, 2021)

create table TienKyLuatCN(
	maTienKyLuat varchar(255) primary key not null,
	tienKyLuat real,
	maNhanVien varchar(255),
	thang int,
	nam int,
	constraint maNhanVienCNTKL foreign key(maNhanVien) references CongNhan(maNhanVien)
);

ALTER TABLE TienKyLuatCN
ADD CONSTRAINT check_tien_ky_luat_cn CHECK (tienKyLuat >= 0);

ALTER TABLE TienKyLuatCN
ADD CONSTRAINT check_thang_ky_luat_cn CHECK (thang between 1 and 12);

ALTER TABLE TienKyLuatCN
ADD CONSTRAINT check_nam_ky_luat_cn CHECK (nam >= 0);

insert into TienKyLuatCN values('KL11', 50000, 'NV26', 11, 2021)
insert into TienKyLuatCN values('KL12', 100000, 'NV49', 11, 2021)
insert into TienKyLuatCN values('KL13', 50000, 'NV30', 11, 2021)
insert into TienKyLuatCN values('KL14', 100000, 'NV35', 11, 2021)
insert into TienKyLuatCN values('KL15', 50000, 'NV36', 11, 2021)

insert into TienKyLuatCN values('KL16', 50000, 'NV38', 11, 2021)
insert into TienKyLuatCN values('KL17', 100000, 'NV40', 11, 2021)
insert into TienKyLuatCN values('KL18', 50000, 'NV41', 11, 2021)
insert into TienKyLuatCN values('KL19', 100000, 'NV43', 11, 2021)
insert into TienKyLuatCN values('KL20', 50000, 'NV50', 11, 2021)


create table BangLuongThangNhanVienHanhChanh(
	maLuongThang varchar(255) primary key,
	thang int,
	nam int,
	maChamCongThang varchar(255),
	maPhuCap varchar(255),
	maTienBHXH varchar(255),
	maTienKyLuat varchar(255),
	luongThang real,
	thucLinh real,
	constraint maBangChamCongNVHC foreign key (maChamCongThang) references BangChamCongThangNhanVienHanhChanh(maChamCongThang),
	constraint maPhuCapBLTNVHC foreign key (maPhuCap) references PhuCapNVHC(maPhuCap),
	constraint maTienBHXHBLTNVHC foreign key (maTienBHXH) references TienBHXHNVHC(maTienBHXH),
	constraint maTienKyLuatBLTNVHC foreign key (maTienKyLuat) references TienKyLuatNVHC(maTienKyLuat)
);

ALTER TABLE BangLuongThangNhanVienHanhChanh
ADD CONSTRAINT check_luong_thang_nvhc CHECK (luongThang >= 0);

ALTER TABLE BangLuongThangNhanVienHanhChanh
ADD CONSTRAINT check_thang_luong_nvhc CHECK (thang between 1 and 12);

ALTER TABLE BangLuongThangNhanVienHanhChanh
ADD CONSTRAINT check_nam_luong_nvhc CHECK (nam >= 0);

create table BangLuongThangCongNhan(
	maLuongThang varchar(255) primary key,
	thang int,
	nam int,
	maCongNhan varchar(255),
	maPhuCap varchar(255),
	maTienBHXH varchar(255),
	maTienKyLuat varchar(255),
	luongThang real,
	thucLinh real,
	constraint maPhuCapBLTCN foreign key (maPhuCap) references PhuCapCN(maPhuCap),
	constraint maTienBHXHBLTCN foreign key (maTienBHXH) references TienBHXHCN(maTienBHXH),
	constraint maTienKyLuatBLTCN foreign key (maTienKyLuat) references TienKyLuatCN(maTienKyLuat),
	constraint maCongNhanBLTCN foreign key (maCongNhan) references CongNhan(maNhanVien)
);

ALTER TABLE BangLuongThangCongNhan
ADD CONSTRAINT check_luong_thang_cn CHECK (luongThang >= 0);

ALTER TABLE BangLuongThangCongNhan
ADD CONSTRAINT check_thang_luong_cn CHECK (thang between 1 and 12);

ALTER TABLE BangLuongThangCongNhan
ADD CONSTRAINT check_nam_luong_cn CHECK (nam >= 0);

create table BangLuongNgayCongNhan (
	maLuongNgay varchar(255) primary key,
	maChamCongNgay varchar(255),
	maLuongThang varchar(255),
	luongNgay real,
	constraint maChamCongBLNCN foreign key (maChamCongNgay) references BangChamCongNgayCongNhan(maChamCongNgay),
	constraint maLuongThangBLNCN foreign key (maLuongThang) references BangLuongThangCongNhan(maLuongThang)
);

ALTER TABLE BangLuongNgayCongNhan
ADD CONSTRAINT check_luong_ngay_cn CHECK (luongNgay >= 0);
