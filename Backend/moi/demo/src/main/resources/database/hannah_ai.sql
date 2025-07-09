-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 21, 2025 lúc 03:08 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `hannah_ai`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baidang`
--

CREATE TABLE `baidang` (
  `MaBaiDang` int(11) NOT NULL,
  `TieuDe` varchar(300) NOT NULL,
  `NoiDung` text NOT NULL,
  `MaTacGia` int(11) NOT NULL,
  `MaChuDe` int(11) DEFAULT NULL,
  `MaBaiHoc` int(11) DEFAULT NULL,
  `TrangThai` enum('DaDuyet','ChoDuyet','An') DEFAULT 'ChoDuyet',
  `SoBaoCao` int(11) DEFAULT 0,
  `LuotXem` int(11) DEFAULT 0,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `baidang`
--

INSERT INTO `baidang` (`MaBaiDang`, `TieuDe`, `NoiDung`, `MaTacGia`, `MaChuDe`, `MaBaiHoc`, `TrangThai`, `SoBaoCao`, `LuotXem`, `NgayTao`) VALUES
(1, 'Giới thiệu Java – Tầm quan trọng và ứng dụng.', 'Bài viết chia sẻ tổng quan về Java, vai trò trong ngành phát triển phần mềm hiện đại.', 2, 2, 1, 'DaDuyet', NULL, 10, '2025-05-11 12:58:07');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `baihoc`
--

CREATE TABLE `baihoc` (
  `MaBaiHoc` int(11) NOT NULL,
  `TenBaiHoc` varchar(200) NOT NULL,
  `MaKhoaHoc` int(11) NOT NULL,
  `ThuTu` int(11) NOT NULL,
  `VideoURL` varchar(255) DEFAULT NULL,
  `CapDo` enum('CoBan','TrungCap','NangCao') DEFAULT 'CoBan',
  `ThoiLuong` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `baihoc`
--

INSERT INTO `baihoc` (`MaBaiHoc`, `TenBaiHoc`, `MaKhoaHoc`, `ThuTu`, `VideoURL`, `CapDo`, `ThoiLuong`) VALUES
(1, 'Bài 1: Giới thiệu về Java', 1, 1, 'https://www.youtube.com/watch?v=3gtOAlcovoQ&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=1', 'CoBan', '10 phút'),
(2, 'Bài 2: Cài đặt môi trường Java', 1, 2, 'https://www.youtube.com/watch?v=KjMRn1YQcLc&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=2', 'CoBan', '15 phút'),
(3, 'Bài 3: Chương trình Java đầu tiên', 1, 3, 'https://www.youtube.com/watch?v=jIQmebw9VaA&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=3', 'CoBan', '10 phút'),
(4, 'Bài 4: Biến trong Java', 1, 4, 'https://www.youtube.com/watch?v=G2mCSTtBojM&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=4', 'CoBan', '15 phút'),
(5, 'Bài 5: Kiểu dữ liệu trong Java', 1, 5, 'https://www.youtube.com/watch?v=4k_5vWY2wps&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=5', 'CoBan', '10 phút'),
(6, 'Bài 6: Toán tử trong Java', 1, 6, 'https://www.youtube.com/watch?v=H9FmP010A_Q&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=6', 'CoBan', '12 phút'),
(7, 'Bài 7: Hằng trong Java', 1, 7, 'https://www.youtube.com/watch?v=dqybUkGCaVw&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=7', 'CoBan', '8 phút'),
(8, 'Bài 8: Ép kiểu trong Java', 1, 8, 'https://www.youtube.com/watch?v=kOMiIKLCK34&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=8', 'CoBan', '12 phút'),
(9, 'Bài 9: Cấu trúc rẽ nhánh trong Java', 1, 9, 'https://www.youtube.com/watch?v=vradAZcby8I&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=9', 'CoBan', '13 phút'),
(10, 'Bài 10: Vòng lặp While trong Java', 1, 10, 'https://www.youtube.com/watch?v=tDfQ33fmmvs&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=10', 'CoBan', '9 phút'),
(11, 'Bài 11: Vòng lặp For trong Java', 1, 11, 'https://www.youtube.com/watch?v=1QVfZFOt7uI&list=PL33lvabfss1yGrOutFR03OZoqm91TSsvs&index=11', 'CoBan', '10 phút'),
(12, 'Bài 1: Giới thiệu khóa học HTML-CSS và cài đặt phần mềm cần thiết', 2, 1, 'https://www.youtube.com/watch?v=G0quBPou7Z8&list=PLgk9x84DhO7X1cP-jI8UgtGmsJpXYdafe', 'CoBan', '4 phút'),
(13, 'Bài 2: Các thẻ HTML cơ bản - phần 1', 2, 2, 'https://www.youtube.com/watch?v=B51n2ucFn_I&list=PLgk9x84DhO7X1cP-jI8UgtGmsJpXYdafe&index=2', 'CoBan', '8 phút'),
(14, 'Bài 3: Các thẻ HTML cơ bản - phần 2', 2, 3, 'https://www.youtube.com/watch?v=i4XT_evGNZo&list=PLgk9x84DhO7X1cP-jI8UgtGmsJpXYdafe&index=3', 'CoBan', '10 phút'),
(15, 'Bài 4: Các cách thêm định dạng css vào file HTML', 2, 4, 'https://www.youtube.com/watch?v=zOQKvo-YD6k&list=PLgk9x84DhO7X1cP-jI8UgtGmsJpXYdafe&index=4', 'CoBan', '14 phút'),
(16, 'Bài 5: Phân biệt class và id trong html và css', 2, 5, 'https://www.youtube.com/watch?v=mFHyyABjnXg&list=PLgk9x84DhO7X1cP-jI8UgtGmsJpXYdafe&index=5', 'CoBan', '18 phút'),
(17, 'Bài 6: Một số thuộc tính cơ bản của CSS', 2, 6, 'https://www.youtube.com/watch?v=CRLl-csb2kw&list=PLgk9x84DhO7X1cP-jI8UgtGmsJpXYdafe&index=6', 'CoBan', '20 phút');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `binhluan`
--

CREATE TABLE `binhluan` (
  `MaBinhLuan` int(11) NOT NULL,
  `NoiDung` text NOT NULL,
  `MaBaiDang` int(11) NOT NULL,
  `MaNguoiDung` int(11) NOT NULL,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `binhluan`
--

INSERT INTO `binhluan` (`MaBinhLuan`, `NoiDung`, `MaBaiDang`, `MaNguoiDung`, `NgayTao`) VALUES
(1, 'Bài viết rất hữu ích, cảm ơn thầy Hùng đã chia sẻ!', 1, 3, '2025-05-12 13:01:08');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chude`
--

CREATE TABLE `chude` (
  `MaChuDe` int(11) NOT NULL,
  `TenChuDe` varchar(100) NOT NULL,
  `MoTa` text DEFAULT NULL,
  `Icon` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chude`
--

INSERT INTO `chude` (`MaChuDe`, `TenChuDe`, `MoTa`, `Icon`) VALUES
(1, 'Tất cả', 'Tất cả các khóa học', NULL),
(2, 'Phát triển Web', 'Khóa học về phát triển web', NULL),
(3, 'Khoá học dành riêng', 'Khóa học chuyên sâu dành riêng', NULL),
(4, 'Mobile App', 'Khóa học về phát triển ứng dụng di động', NULL),
(5, 'AI & Machine Learning', 'Khóa học về trí tuệ nhân tạo và học máy', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangky`
--

CREATE TABLE `dangky` (
  `MaDangKy` int(11) NOT NULL,
  `MaNguoiDung` int(11) NOT NULL,
  `MaKhoaHoc` int(11) NOT NULL,
  `NgayDangKy` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dangky`
--

INSERT INTO `dangky` (`MaDangKy`, `MaNguoiDung`, `MaKhoaHoc`, `NgayDangKy`) VALUES
(1, 3, 1, '2025-05-11 12:53:47'),
(2, 3, 2, '2025-05-12 12:53:47'),
(3, 5, 2, '2025-05-12 13:02:26'),
(4, 5, 4, '2025-05-12 13:02:26');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoahoc`
--

CREATE TABLE `khoahoc` (
  `MaKhoaHoc` int(11) NOT NULL,
  `TenKhoaHoc` varchar(200) NOT NULL,
  `MoTa` text DEFAULT NULL,
  `MaGiangVien` int(11) NOT NULL,
  `HinhAnh` varchar(255) DEFAULT NULL,
  `MienPhi` tinyint(1) DEFAULT 1,
  `TrangThai` enum('HoatDong','ChoDuyet','An') DEFAULT 'HoatDong',
  `DanhGiaTB` float DEFAULT 0,
  `SoLuongHocVien` int(11) DEFAULT 0,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khoahoc`
--

INSERT INTO `khoahoc` (`MaKhoaHoc`, `TenKhoaHoc`, `MoTa`, `MaGiangVien`, `HinhAnh`, `MienPhi`, `TrangThai`, `DanhGiaTB`, `SoLuongHocVien`, `NgayTao`) VALUES
(1, 'Lập trình Java cơ bản', 'Khóa học giúp bạn làm quen với Java từ số 0', 2, 'https://www.adm.ee/wordpress/wp-content/uploads/2023/08/JAVA.png', 1, 'HoatDong', 4.9, 100, '2025-05-08 07:45:20'),
(2, 'HTML & CSS cơ bản', 'Học cách xây dựng giao diện web', 2, 'https://ostraining.com/wp-content/uploads/coding/html5-css3-hd.jpg', 1, 'HoatDong', 4.6, 25, '2025-05-08 07:47:54'),
(3, 'Database-SQL Server', 'Tìm hiểu những kiến ​​thức cơ bản về cơ sở dữ liệu, truy vấn, tính toán và nhiều nội dung khác để bắt đầu quản lý và phân tích dữ liệu như một chuyên gia.', 2, 'https://www.netgen.co.za/wp-content/uploads/2023/05/SQL-Database.png', 1, 'HoatDong', 4.5, 15, '2025-05-11 12:41:20'),
(4, 'JavaScript cơ bản', 'Tìm hiểu các biến, vòng lặp, hàm và sự kiện để bắt đầu xây dựng các ứng dụng web tương tác bằng ngôn ngữ lập trình web – JavaScript!', 2, 'https://techvccloud.mediacdn.vn/2018/11/23/js-15429579443112042672363-crop-1542957949936317424252.png', 1, 'HoatDong', 4.9, 123, '2025-05-12 12:41:20'),
(5, 'Node.js', 'Kết hợp JavaScript với sức mạnh của Node.js—giải phóng tiềm năng toàn diện của bạn với lập trình phía máy chủ & ứng dụng web động!\r\nĐiều kiện tiên quyết: JavaScript và Dòng lệnh', 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ6Hd-Hz0vOuWyFXYO3xUrG4_bJQ0wcmd-DtA&s', 1, 'HoatDong', 4.8, 100, '2025-05-04 12:51:32');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khoahoc_chude`
--

CREATE TABLE `khoahoc_chude` (
  `MaKhoaHoc` int(11) NOT NULL,
  `MaChuDe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khoahoc_chude`
--

INSERT INTO `khoahoc_chude` (`MaKhoaHoc`, `MaChuDe`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(5, 1),
(5, 2),
(5, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaitailieu`
--

CREATE TABLE `loaitailieu` (
  `MaLoaiTaiLieu` int(11) NOT NULL,
  `TenLoai` varchar(50) NOT NULL,
  `MoTa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoidung`
--

CREATE TABLE `nguoidung` (
  `MaNguoiDung` int(11) NOT NULL,
  `TenDangNhap` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `HoTen` varchar(100) NOT NULL,
  `MaVaiTro` int(11) NOT NULL,
  `AnhDaiDien` varchar(255) DEFAULT NULL,
  `DienThoai` varchar(20) DEFAULT NULL,
  `GioiThieu` text DEFAULT NULL,
  `TrangThai` enum('Active','Inactive','Banned') DEFAULT 'Active',
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoidung`
--

INSERT INTO `nguoidung` (`MaNguoiDung`, `TenDangNhap`, `Email`, `MatKhau`, `HoTen`, `MaVaiTro`, `AnhDaiDien`, `DienThoai`, `GioiThieu`, `TrangThai`, `NgayTao`) VALUES
(1, 'hannah_ai123', 'hannah.ai@gmail.com', 'hannah.ai@12345', 'Quản Trị Viên', 1, NULL, '0343294824', NULL, 'Active', '2025-04-30 07:39:26'),
(2, 'gv_hung', 'hung.gv@gmail.com', 'hunggv123@', 'Trần Văn Hùng', 2, NULL, '0312456789', NULL, 'Active', '2025-05-08 07:39:26'),
(3, 'nvteo', 'nvteo123@gmail.com', 'nvteo@123', 'Nguyễn Văn Tèo', 3, NULL, '0343294824', NULL, 'Active', '2025-05-12 07:44:11'),
(4, 'thayquan@123', 'thayquan123@gmail.com', 'thayquan@123', 'Lê Trọng Quân', 2, NULL, '0345612789', 'Giảng viên lập trình ', 'Active', '2025-05-01 12:49:54'),
(5, 'thanhan12', 'thanhan12@gmail.com', 'thanhan12@', 'Nguyễn Thanh An', 3, NULL, '0343294824', 'Xin chàooo', 'Active', '2025-05-01 12:54:35'),
(6, 'baoan123', 'baoan123@gmail.com', 'baoan123@', 'Trần Bảo An', 3, NULL, '0312456789', 'Học viên mớiiii', 'Active', '2025-05-02 12:54:35');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phienchat`
--

CREATE TABLE `phienchat` (
  `MaPhien` int(11) NOT NULL,
  `MaNguoiDung` int(11) DEFAULT NULL,
  `MaBaiHoc` int(11) DEFAULT NULL,
  `BatDau` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tailieu`
--

CREATE TABLE `tailieu` (
  `MaTaiLieu` int(11) NOT NULL,
  `TenTaiLieu` varchar(300) NOT NULL,
  `MaLoaiTaiLieu` int(11) NOT NULL,
  `MaBaiHoc` int(11) NOT NULL,
  `MaTacGia` int(11) NOT NULL,
  `DuongDan` varchar(500) DEFAULT NULL,
  `KichThuoc` bigint(20) DEFAULT NULL,
  `LuotTai` int(11) DEFAULT 0,
  `DanhGia` float DEFAULT 0,
  `TrangThai` enum('DaDuyet','ChoDuyet','TuChoi') DEFAULT 'ChoDuyet',
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tiendo`
--

CREATE TABLE `tiendo` (
  `MaTienDo` int(11) NOT NULL,
  `MaNguoiDung` int(11) NOT NULL,
  `MaKhoaHoc` int(11) NOT NULL,
  `MaBaiHoc` int(11) NOT NULL,
  `PhanTram` decimal(5,2) DEFAULT 0.00,
  `ThoiGianHoc` int(11) DEFAULT 0,
  `HoanThanh` tinyint(1) DEFAULT 0,
  `LanCuoiHoc` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tiendo`
--

INSERT INTO `tiendo` (`MaTienDo`, `MaNguoiDung`, `MaKhoaHoc`, `MaBaiHoc`, `PhanTram`, `ThoiGianHoc`, `HoanThanh`, `LanCuoiHoc`) VALUES
(1, 3, 1, 1, 100.00, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tinnhan`
--

CREATE TABLE `tinnhan` (
  `MaTinNhan` int(11) NOT NULL,
  `MaPhien` int(11) NOT NULL,
  `NguoiGui` enum('AI','User') NOT NULL,
  `NoiDung` text DEFAULT NULL,
  `ThoiGian` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vaitro`
--

CREATE TABLE `vaitro` (
  `MaVaiTro` int(11) NOT NULL,
  `TenVaiTro` varchar(50) NOT NULL,
  `MoTa` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vaitro`
--

INSERT INTO `vaitro` (`MaVaiTro`, `TenVaiTro`, `MoTa`) VALUES
(1, 'Admin', 'Quản trị hệ thống'),
(2, 'GiangVien', 'Giảng viên tạo khóa học'),
(3, 'HocVien', 'Người học');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `baidang`
--
ALTER TABLE `baidang`
  ADD PRIMARY KEY (`MaBaiDang`),
  ADD KEY `idx_baidang_matacgia` (`MaTacGia`),
  ADD KEY `MaChuDe` (`MaChuDe`),
  ADD KEY `MaBaiHoc` (`MaBaiHoc`);

--
-- Chỉ mục cho bảng `baihoc`
--
ALTER TABLE `baihoc`
  ADD PRIMARY KEY (`MaBaiHoc`),
  ADD KEY `idx_baihoc_makhoahoc` (`MaKhoaHoc`);

--
-- Chỉ mục cho bảng `binhluan`
--
ALTER TABLE `binhluan`
  ADD PRIMARY KEY (`MaBinhLuan`),
  ADD KEY `idx_binhluan_mabaidang` (`MaBaiDang`),
  ADD KEY `MaNguoiDung` (`MaNguoiDung`);

--
-- Chỉ mục cho bảng `chude`
--
ALTER TABLE `chude`
  ADD PRIMARY KEY (`MaChuDe`);

--
-- Chỉ mục cho bảng `dangky`
--
ALTER TABLE `dangky`
  ADD PRIMARY KEY (`MaDangKy`),
  ADD UNIQUE KEY `DangKyDuyNhat` (`MaNguoiDung`,`MaKhoaHoc`),
  ADD KEY `idx_dangky_man nguoidung` (`MaNguoiDung`),
  ADD KEY `MaKhoaHoc` (`MaKhoaHoc`);

--
-- Chỉ mục cho bảng `khoahoc`
--
ALTER TABLE `khoahoc`
  ADD PRIMARY KEY (`MaKhoaHoc`),
  ADD UNIQUE KEY `TenKhoaHoc` (`TenKhoaHoc`),
  ADD KEY `idx_khoahoc_ten` (`TenKhoaHoc`),
  ADD KEY `idx_khoahoc_trangthai` (`TrangThai`),
  ADD KEY `MaGiangVien` (`MaGiangVien`);

--
-- Chỉ mục cho bảng `khoahoc_chude`
--
ALTER TABLE `khoahoc_chude`
  ADD PRIMARY KEY (`MaKhoaHoc`,`MaChuDe`),
  ADD KEY `MaChuDe` (`MaChuDe`);

--
-- Chỉ mục cho bảng `loaitailieu`
--
ALTER TABLE `loaitailieu`
  ADD PRIMARY KEY (`MaLoaiTaiLieu`),
  ADD UNIQUE KEY `TenLoai` (`TenLoai`);

--
-- Chỉ mục cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD PRIMARY KEY (`MaNguoiDung`),
  ADD UNIQUE KEY `TenDangNhap` (`TenDangNhap`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `idx_nguoidung_tendangnhap` (`TenDangNhap`),
  ADD KEY `idx_nguoidung_email` (`Email`),
  ADD KEY `MaVaiTro` (`MaVaiTro`);

--
-- Chỉ mục cho bảng `phienchat`
--
ALTER TABLE `phienchat`
  ADD PRIMARY KEY (`MaPhien`),
  ADD KEY `MaNguoiDung` (`MaNguoiDung`),
  ADD KEY `MaBaiHoc` (`MaBaiHoc`);

--
-- Chỉ mục cho bảng `tailieu`
--
ALTER TABLE `tailieu`
  ADD PRIMARY KEY (`MaTaiLieu`),
  ADD KEY `idx_tailieu_mabaihoc` (`MaBaiHoc`),
  ADD KEY `MaTacGia` (`MaTacGia`),
  ADD KEY `MaLoaiTaiLieu` (`MaLoaiTaiLieu`);

--
-- Chỉ mục cho bảng `tiendo`
--
ALTER TABLE `tiendo`
  ADD PRIMARY KEY (`MaTienDo`),
  ADD UNIQUE KEY `TienDoDuyNhat` (`MaNguoiDung`,`MaBaiHoc`),
  ADD KEY `idx_tiendo_man nguoidung` (`MaNguoiDung`),
  ADD KEY `idx_tiendo_mabaihoc` (`MaBaiHoc`),
  ADD KEY `MaKhoaHoc` (`MaKhoaHoc`);

--
-- Chỉ mục cho bảng `tinnhan`
--
ALTER TABLE `tinnhan`
  ADD PRIMARY KEY (`MaTinNhan`),
  ADD KEY `MaPhien` (`MaPhien`);

--
-- Chỉ mục cho bảng `vaitro`
--
ALTER TABLE `vaitro`
  ADD PRIMARY KEY (`MaVaiTro`),
  ADD UNIQUE KEY `TenVaiTro` (`TenVaiTro`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `baidang`
--
ALTER TABLE `baidang`
  MODIFY `MaBaiDang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `baihoc`
--
ALTER TABLE `baihoc`
  MODIFY `MaBaiHoc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `binhluan`
--
ALTER TABLE `binhluan`
  MODIFY `MaBinhLuan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `chude`
--
ALTER TABLE `chude`
  MODIFY `MaChuDe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `dangky`
--
ALTER TABLE `dangky`
  MODIFY `MaDangKy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `khoahoc`
--
ALTER TABLE `khoahoc`
  MODIFY `MaKhoaHoc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `loaitailieu`
--
ALTER TABLE `loaitailieu`
  MODIFY `MaLoaiTaiLieu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  MODIFY `MaNguoiDung` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `phienchat`
--
ALTER TABLE `phienchat`
  MODIFY `MaPhien` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tailieu`
--
ALTER TABLE `tailieu`
  MODIFY `MaTaiLieu` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tiendo`
--
ALTER TABLE `tiendo`
  MODIFY `MaTienDo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `tinnhan`
--
ALTER TABLE `tinnhan`
  MODIFY `MaTinNhan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `vaitro`
--
ALTER TABLE `vaitro`
  MODIFY `MaVaiTro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `baidang`
--
ALTER TABLE `baidang`
  ADD CONSTRAINT `baidang_ibfk_1` FOREIGN KEY (`MaTacGia`) REFERENCES `nguoidung` (`MaNguoiDung`),
  ADD CONSTRAINT `baidang_ibfk_2` FOREIGN KEY (`MaChuDe`) REFERENCES `chude` (`MaChuDe`),
  ADD CONSTRAINT `baidang_ibfk_3` FOREIGN KEY (`MaBaiHoc`) REFERENCES `baihoc` (`MaBaiHoc`);

--
-- Các ràng buộc cho bảng `baihoc`
--
ALTER TABLE `baihoc`
  ADD CONSTRAINT `baihoc_ibfk_1` FOREIGN KEY (`MaKhoaHoc`) REFERENCES `khoahoc` (`MaKhoaHoc`);

--
-- Các ràng buộc cho bảng `binhluan`
--
ALTER TABLE `binhluan`
  ADD CONSTRAINT `binhluan_ibfk_1` FOREIGN KEY (`MaBaiDang`) REFERENCES `baidang` (`MaBaiDang`),
  ADD CONSTRAINT `binhluan_ibfk_2` FOREIGN KEY (`MaNguoiDung`) REFERENCES `nguoidung` (`MaNguoiDung`);

--
-- Các ràng buộc cho bảng `dangky`
--
ALTER TABLE `dangky`
  ADD CONSTRAINT `dangky_ibfk_1` FOREIGN KEY (`MaNguoiDung`) REFERENCES `nguoidung` (`MaNguoiDung`),
  ADD CONSTRAINT `dangky_ibfk_2` FOREIGN KEY (`MaKhoaHoc`) REFERENCES `khoahoc` (`MaKhoaHoc`);

--
-- Các ràng buộc cho bảng `khoahoc`
--
ALTER TABLE `khoahoc`
  ADD CONSTRAINT `khoahoc_ibfk_1` FOREIGN KEY (`MaGiangVien`) REFERENCES `nguoidung` (`MaNguoiDung`);

--
-- Các ràng buộc cho bảng `khoahoc_chude`
--
ALTER TABLE `khoahoc_chude`
  ADD CONSTRAINT `khoahoc_chude_ibfk_1` FOREIGN KEY (`MaKhoaHoc`) REFERENCES `khoahoc` (`MaKhoaHoc`) ON DELETE CASCADE,
  ADD CONSTRAINT `khoahoc_chude_ibfk_2` FOREIGN KEY (`MaChuDe`) REFERENCES `chude` (`MaChuDe`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `nguoidung`
--
ALTER TABLE `nguoidung`
  ADD CONSTRAINT `nguoidung_ibfk_1` FOREIGN KEY (`MaVaiTro`) REFERENCES `vaitro` (`MaVaiTro`);

--
-- Các ràng buộc cho bảng `phienchat`
--
ALTER TABLE `phienchat`
  ADD CONSTRAINT `phienchat_ibfk_1` FOREIGN KEY (`MaNguoiDung`) REFERENCES `nguoidung` (`MaNguoiDung`),
  ADD CONSTRAINT `phienchat_ibfk_2` FOREIGN KEY (`MaBaiHoc`) REFERENCES `baihoc` (`MaBaiHoc`);

--
-- Các ràng buộc cho bảng `tailieu`
--
ALTER TABLE `tailieu`
  ADD CONSTRAINT `tailieu_ibfk_1` FOREIGN KEY (`MaBaiHoc`) REFERENCES `baihoc` (`MaBaiHoc`),
  ADD CONSTRAINT `tailieu_ibfk_2` FOREIGN KEY (`MaTacGia`) REFERENCES `nguoidung` (`MaNguoiDung`),
  ADD CONSTRAINT `tailieu_ibfk_3` FOREIGN KEY (`MaLoaiTaiLieu`) REFERENCES `loaitailieu` (`MaLoaiTaiLieu`);

--
-- Các ràng buộc cho bảng `tiendo`
--
ALTER TABLE `tiendo`
  ADD CONSTRAINT `tiendo_ibfk_1` FOREIGN KEY (`MaNguoiDung`) REFERENCES `nguoidung` (`MaNguoiDung`),
  ADD CONSTRAINT `tiendo_ibfk_2` FOREIGN KEY (`MaKhoaHoc`) REFERENCES `khoahoc` (`MaKhoaHoc`),
  ADD CONSTRAINT `tiendo_ibfk_3` FOREIGN KEY (`MaBaiHoc`) REFERENCES `baihoc` (`MaBaiHoc`);

--
-- Các ràng buộc cho bảng `tinnhan`
--
ALTER TABLE `tinnhan`
  ADD CONSTRAINT `tinnhan_ibfk_1` FOREIGN KEY (`MaPhien`) REFERENCES `phienchat` (`MaPhien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
