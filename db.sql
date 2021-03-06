USE [master]
GO
/****** Object:  Database [PRJ_Assignment]    Script Date: 8/13/2020 12:49:26 PM ******/
CREATE DATABASE [PRJ_Assignment]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PRJ_Assignment', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS01\MSSQL\DATA\PRJ_Assignment.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PRJ_Assignment_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS01\MSSQL\DATA\PRJ_Assignment_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [PRJ_Assignment] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PRJ_Assignment].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PRJ_Assignment] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET ARITHABORT OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [PRJ_Assignment] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PRJ_Assignment] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PRJ_Assignment] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PRJ_Assignment] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PRJ_Assignment] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PRJ_Assignment] SET  MULTI_USER 
GO
ALTER DATABASE [PRJ_Assignment] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PRJ_Assignment] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PRJ_Assignment] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PRJ_Assignment] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PRJ_Assignment] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PRJ_Assignment] SET QUERY_STORE = OFF
GO
USE [PRJ_Assignment]
GO
/****** Object:  Table [dbo].[tblEquipment]    Script Date: 8/13/2020 12:49:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblEquipment](
	[EquipmentID] [varchar](50) NOT NULL,
	[EquipmentName] [varchar](50) NULL,
	[EquipmentStatus] [bit] NULL,
	[RoomID] [varchar](50) NULL,
 CONSTRAINT [PK_tblEquipment] PRIMARY KEY CLUSTERED 
(
	[EquipmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblFeedback]    Script Date: 8/13/2020 12:49:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblFeedback](
	[Username] [varchar](50) NOT NULL,
	[RoomID] [varchar](50) NOT NULL,
	[EquipmentID] [varchar](50) NOT NULL,
	[Status] [varchar](50) NULL,
	[Date] [datetime] NOT NULL,
	[ContentFeedback] [varchar](50) NULL,
	[Reply] [varchar](50) NULL,
	[ID] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tblFeedback] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblLogin]    Script Date: 8/13/2020 12:49:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblLogin](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Role] [varchar](50) NULL,
 CONSTRAINT [PK_Login] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblOccupied]    Script Date: 8/13/2020 12:49:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblOccupied](
	[Username] [varchar](50) NOT NULL,
	[RoomID] [varchar](50) NOT NULL,
	[Job] [varchar](50) NULL,
 CONSTRAINT [PK_tblOccupied] PRIMARY KEY CLUSTERED 
(
	[Username] ASC,
	[RoomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRoom]    Script Date: 8/13/2020 12:49:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRoom](
	[RoomID] [varchar](50) NOT NULL,
	[Room_Description] [varchar](50) NULL,
 CONSTRAINT [PK_tblRoom] PRIMARY KEY CLUSTERED 
(
	[RoomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001B1', N'Board1', 0, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001B2', N'Board2', 0, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001C1', N'Chair1', 0, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001C2', N'Chair2', 1, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001L1', N'Lamp1', 0, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001L2', N'Lamp2', 1, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001T1', N'Table1', 1, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001T2', N'Table2', 0, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'001TV', N'TV', 0, N'001')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002B1', N'Board1', 0, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002B2', N'Board2', 0, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002C1', N'Chair1', 0, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002C2', N'Chair2', 1, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002L1', N'Lamp1', 1, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002L2', N'Lamp2', 0, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002T1', N'Table1', 1, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002T2', N'Table2', 1, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'002TV', N'TV', 0, N'002')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003B1', N'Board1', 0, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003B2', N'Board2', 1, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003C1', N'Chair1', 1, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003C2', N'Chair2', 0, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003L1', N'Lamp1', 1, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003L2', N'Lamp2', 1, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003T1', N'Table1', 0, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003T2', N'Table2', 0, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'003TV', N'TV', 0, N'003')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101B1', N'Board1', 0, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101B2', N'Board2', 1, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101C1', N'Chair1', 0, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101C2', N'Chair2', 0, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101L1', N'Lamp1', 0, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101L2', N'Lamp2', 0, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101T1', N'Table1', 1, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101T2', N'Table2', 1, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'101TV', N'TV', 0, N'101')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102B1', N'Board1', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102B2', N'Board2', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102C1', N'Chair1', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102C2', N'Chair2', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102L1', N'Lamp1', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102L2', N'Lamp2', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102T1', N'Table1', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102T2', N'Table2', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'102TV', N'TV', 0, N'102')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103B1', N'Board1', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103B2', N'Board2', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103C1', N'Chair1', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103C2', N'Chair2', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103L1', N'Lamp1', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103L2', N'Lamp2', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103T1', N'Table1', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103T2', N'Table2', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'103TV', N'TV', 1, N'103')
INSERT [dbo].[tblEquipment] ([EquipmentID], [EquipmentName], [EquipmentStatus], [RoomID]) VALUES (N'testAbc', N'ds', 0, N'001')
INSERT [dbo].[tblFeedback] ([Username], [RoomID], [EquipmentID], [Status], [Date], [ContentFeedback], [Reply], [ID]) VALUES (N'teacher1', N'001', N'001TV', N'Accepted', CAST(N'2012-12-12T00:00:00.000' AS DateTime), NULL, N'Request expired!zxc ', N'35131adw53s1')
INSERT [dbo].[tblFeedback] ([Username], [RoomID], [EquipmentID], [Status], [Date], [ContentFeedback], [Reply], [ID]) VALUES (N'student1', N'001', N'testAbc', N'Accepted', CAST(N'2019-12-09T09:42:41.373' AS DateTime), N'121313', N' done', N'5cf0ec3d0fc6462f99a2a534130ef464')
INSERT [dbo].[tblFeedback] ([Username], [RoomID], [EquipmentID], [Status], [Date], [ContentFeedback], [Reply], [ID]) VALUES (N'testStudent', N'001', N'testAbc', N'Accepted', CAST(N'2019-12-09T09:42:16.800' AS DateTime), N'hjaskfhjdczlkjalskjc', N' 951', N'9ba064ba76e34a5f8004a4c5e0ebd696')
INSERT [dbo].[tblFeedback] ([Username], [RoomID], [EquipmentID], [Status], [Date], [ContentFeedback], [Reply], [ID]) VALUES (N'student1', N'001', N'001C2', N'Declined', CAST(N'2019-12-04T09:51:50.133' AS DateTime), N'Broken', N'Ya ya ya ya ', N'a73b7645f7904abab508e9f2cec9dc2c')
INSERT [dbo].[tblFeedback] ([Username], [RoomID], [EquipmentID], [Status], [Date], [ContentFeedback], [Reply], [ID]) VALUES (N'student1', N'101', N'101B2', N'Declined', CAST(N'2019-12-04T15:27:19.210' AS DateTime), N'This room is broke now ', N'Request expired!', N'dca0cc1c46754d95b00a99cf8fb472d8')
INSERT [dbo].[tblFeedback] ([Username], [RoomID], [EquipmentID], [Status], [Date], [ContentFeedback], [Reply], [ID]) VALUES (N'student1', N'001', N'testAbc', N'Declined', CAST(N'2019-12-09T09:46:10.107' AS DateTime), N'kjhjkkjhkjhjk', N'Request expired!', N'eeb71919f3c743b58484b3afa8c48483')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'admin', N'admin', N'Employer')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'student1', N'student1', N'Student')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'student2', N'student2', N'Student')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'student3', N'student3', N'Student')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'teacher1', N'teacher1', N'Teacher')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'teacher2', N'teacher2', N'Student')
INSERT [dbo].[tblLogin] ([Username], [Password], [Role]) VALUES (N'testStudent', N'123', N'Student')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student1', N'001', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student1', N'101', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student2', N'002', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student2', N'103', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student3', N'001', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student3', N'003', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'student3', N'102', N'Study')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'teacher1', N'001', N'Teach')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'teacher1', N'002', N'Teach')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'teacher1', N'003', N'Teach')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'teacher2', N'101', N'Teach')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'teacher2', N'102', N'Teach')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'teacher2', N'103', N'Teach')
INSERT [dbo].[tblOccupied] ([Username], [RoomID], [Job]) VALUES (N'testStudent', N'001', N'Study')
INSERT [dbo].[tblRoom] ([RoomID], [Room_Description]) VALUES (N'001', N'Classroom')
INSERT [dbo].[tblRoom] ([RoomID], [Room_Description]) VALUES (N'002', N'Classroom')
INSERT [dbo].[tblRoom] ([RoomID], [Room_Description]) VALUES (N'003', N'Classroom')
INSERT [dbo].[tblRoom] ([RoomID], [Room_Description]) VALUES (N'101', N'Classroom')
INSERT [dbo].[tblRoom] ([RoomID], [Room_Description]) VALUES (N'102', N'IT support')
INSERT [dbo].[tblRoom] ([RoomID], [Room_Description]) VALUES (N'103', N'Library')
ALTER TABLE [dbo].[tblEquipment]  WITH CHECK ADD  CONSTRAINT [FK_tblEquipment_tblRoom] FOREIGN KEY([RoomID])
REFERENCES [dbo].[tblRoom] ([RoomID])
GO
ALTER TABLE [dbo].[tblEquipment] CHECK CONSTRAINT [FK_tblEquipment_tblRoom]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblEquipment] FOREIGN KEY([EquipmentID])
REFERENCES [dbo].[tblEquipment] ([EquipmentID])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblEquipment]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblLogin] FOREIGN KEY([Username])
REFERENCES [dbo].[tblLogin] ([Username])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblLogin]
GO
ALTER TABLE [dbo].[tblFeedback]  WITH CHECK ADD  CONSTRAINT [FK_tblFeedback_tblRoom] FOREIGN KEY([RoomID])
REFERENCES [dbo].[tblRoom] ([RoomID])
GO
ALTER TABLE [dbo].[tblFeedback] CHECK CONSTRAINT [FK_tblFeedback_tblRoom]
GO
ALTER TABLE [dbo].[tblOccupied]  WITH CHECK ADD  CONSTRAINT [FK_tblOccupied_tblLogin] FOREIGN KEY([Username])
REFERENCES [dbo].[tblLogin] ([Username])
GO
ALTER TABLE [dbo].[tblOccupied] CHECK CONSTRAINT [FK_tblOccupied_tblLogin]
GO
ALTER TABLE [dbo].[tblOccupied]  WITH CHECK ADD  CONSTRAINT [FK_tblOccupied_tblRoom] FOREIGN KEY([RoomID])
REFERENCES [dbo].[tblRoom] ([RoomID])
GO
ALTER TABLE [dbo].[tblOccupied] CHECK CONSTRAINT [FK_tblOccupied_tblRoom]
GO
USE [master]
GO
ALTER DATABASE [PRJ_Assignment] SET  READ_WRITE 
GO
