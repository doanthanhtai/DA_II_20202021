USE [master]
GO

/****** Object:  Database [dbQLShopTT]    Script Date: 5/9/2021 2:08:37 AM ******/
CREATE DATABASE [dbQLShopTT]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'dbQLShopTT', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\dbQLShopTT.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'dbQLShopTT_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\dbQLShopTT_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [dbQLShopTT].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [dbQLShopTT] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [dbQLShopTT] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [dbQLShopTT] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [dbQLShopTT] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [dbQLShopTT] SET ARITHABORT OFF 
GO

ALTER DATABASE [dbQLShopTT] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [dbQLShopTT] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [dbQLShopTT] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [dbQLShopTT] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [dbQLShopTT] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [dbQLShopTT] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [dbQLShopTT] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [dbQLShopTT] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [dbQLShopTT] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [dbQLShopTT] SET  DISABLE_BROKER 
GO

ALTER DATABASE [dbQLShopTT] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [dbQLShopTT] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [dbQLShopTT] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [dbQLShopTT] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [dbQLShopTT] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [dbQLShopTT] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [dbQLShopTT] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [dbQLShopTT] SET RECOVERY FULL 
GO

ALTER DATABASE [dbQLShopTT] SET  MULTI_USER 
GO

ALTER DATABASE [dbQLShopTT] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [dbQLShopTT] SET DB_CHAINING OFF 
GO

ALTER DATABASE [dbQLShopTT] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [dbQLShopTT] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [dbQLShopTT] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [dbQLShopTT] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO

ALTER DATABASE [dbQLShopTT] SET QUERY_STORE = OFF
GO

ALTER DATABASE [dbQLShopTT] SET  READ_WRITE 
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[ChiTietDonHang]    Script Date: 5/9/2021 2:09:05 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ChiTietDonHang](
	[maDonHang] [nchar](10) NULL,
	[maSanPham] [nchar](10) NULL,
	[soluong] [int] NULL,
	[giatri] [money] NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[ChiTietKho]    Script Date: 5/9/2021 2:09:29 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ChiTietKho](
	[maKho] [nchar](10) NULL,
	[maSanPham] [nchar](10) NULL,
	[soluong] [float] NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[DonHang]    Script Date: 5/9/2021 2:09:41 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[DonHang](
	[maDonHang] [nchar](10) NULL,
	[maNhanVien] [nchar](10) NULL,
	[maKhachHang] [nchar](10) NULL,
	[ngaytao] [date] NULL,
	[trangthaidonhang] [int] NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[KhachHang]    Script Date: 5/9/2021 2:09:57 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nchar](10) NULL,
	[tenkhachhang] [nvarchar](50) NULL,
	[sodienthoai] [nchar](10) NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[Kho]    Script Date: 5/9/2021 2:10:15 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Kho](
	[maKho] [nchar](10) NULL,
	[tenKho] [nvarchar](50) NULL,
	[diachi] [nvarchar](50) NULL,
	[trangthaikho] [int] NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[NhanVien]    Script Date: 5/9/2021 2:10:28 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nchar](10) NULL,
	[tenNhanVien] [nvarchar](50) NULL,
	[sodienthoai] [nchar](10) NULL,
	[ngaysinh] [date] NULL,
	[cmnd] [nvarchar](12) NULL,
	[diachi] [nvarchar](50) NULL,
	[vitri] [nvarchar](50) NULL,
	[mucluong] [float] NULL,
	[ngaynhanviec] [date] NULL,
	[hinhanh] [nvarchar](50) NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[SanPham]    Script Date: 5/9/2021 2:11:00 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[SanPham](
	[maSanPham] [nchar](10) NULL,
	[tenSanPham] [nvarchar](50) NULL,
	[mausac] [nvarchar](50) NULL,
	[kichthuoc] [int] NULL,
	[nguoncungcap] [nvarchar](50) NULL,
	[giaban] [float] NULL,
	[hinhanh] [nvarchar](200) NULL
) ON [PRIMARY]
GO


USE [dbQLShopTT]
GO

/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 5/9/2021 2:11:11 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TaiKhoan](
	[username] [nchar](10) NULL,
	[password] [nvarchar](50) NULL,
	[vaitro] [nvarchar](50) NULL,
	[manhanvien] [nchar](10) NULL
) ON [PRIMARY]
GO


