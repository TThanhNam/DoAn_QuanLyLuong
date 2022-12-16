create Database ForgotPassWord
go
use  ForgotPassWord
go
CREATE TABLE Forgotpassjava(
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[tenhienthi] [nvarchar](50) NOT NULL,
	[loaiTK] [nvarchar](50) NOT NULL
) ON [PRIMARY]
insert into Forgotpassjava values(N'nhatminhminh57@gmail.com', N'Minh@123', N'Minh', N'QL')
insert into Forgotpassjava values(N'nhatlinhlinh14@gmail.com', N'Minh@456', N'Minh', N'KT')
insert into Forgotpassjava values(N'vhong10092001@gmail.com', N'Vu2001', N'Vũ', N'QL')
insert into Forgotpassjava values(N'huyvu10092001@gmail.com', N'Vu1009', N'Minh', N'KT')
insert into Forgotpassjava values(N'nam01697826435nam@gmail.com
', N'Nam0169', N'Nam', N'QL')
insert into Forgotpassjava values(N'nam0355034827nam@gmail.com', N'nam@123', N'Nam', N'KT')
insert into Forgotpassjava values(N'nlap741@gmail.com', N'Lapnguyen', N'Lập', N'QL')
insert into Forgotpassjava values(N'lapn6104@gmail.com', N'Lapnguyen', N'Lập', N'KT')