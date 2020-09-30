-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb25.awardspace.net
-- Generation Time: Apr 12, 2020 at 06:38 PM
-- Server version: 5.7.20-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `3345320_dvdas`
--

-- --------------------------------------------------------

--
-- Table structure for table `display_veichle`
--

CREATE TABLE `display_veichle` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `registration_number` varchar(100) DEFAULT NULL,
  `veichle_id` varchar(100) DEFAULT NULL,
  `veichle_type` varchar(100) DEFAULT NULL,
  `veichle_brand` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `license_data`
--

CREATE TABLE `license_data` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `state` varchar(200) DEFAULT NULL,
  `dlno` varchar(200) DEFAULT NULL,
  `fullname` varchar(200) DEFAULT NULL,
  `sdwhof` varchar(200) DEFAULT NULL,
  `dob` varchar(200) DEFAULT NULL,
  `age` varchar(200) DEFAULT NULL,
  `dateofissue` varchar(200) DEFAULT NULL,
  `validtill` varchar(200) DEFAULT NULL,
  `bloodgroup` varchar(200) DEFAULT NULL,
  `permanentaddress` varchar(200) DEFAULT NULL,
  `currentaddress` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `license_type`
--

CREATE TABLE `license_type` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `mcwg` varchar(100) DEFAULT NULL,
  `mgv` varchar(100) DEFAULT NULL,
  `lmv` varchar(100) DEFAULT NULL,
  `hmv` varchar(100) DEFAULT NULL,
  `hgmv` varchar(100) DEFAULT NULL,
  `hpmv` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `user_password` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phoneno` varchar(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userfine`
--

CREATE TABLE `userfine` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `policename` varchar(200) DEFAULT NULL,
  `reason` varchar(5000) DEFAULT NULL,
  `fineamount` int(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userpolice`
--

CREATE TABLE `userpolice` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `user_password` varchar(200) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userreports`
--

CREATE TABLE `userreports` (
  `id` int(11) NOT NULL,
  `username` varchar(200) DEFAULT NULL,
  `reason` varchar(2000) DEFAULT NULL,
  `times` varchar(100) DEFAULT NULL,
  `dates` varchar(100) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `display_veichle`
--
ALTER TABLE `display_veichle`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `license_data`
--
ALTER TABLE `license_data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `license_type`
--
ALTER TABLE `license_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userfine`
--
ALTER TABLE `userfine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userpolice`
--
ALTER TABLE `userpolice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userreports`
--
ALTER TABLE `userreports`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `display_veichle`
--
ALTER TABLE `display_veichle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `license_data`
--
ALTER TABLE `license_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `license_type`
--
ALTER TABLE `license_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `useraccount`
--
ALTER TABLE `useraccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;
--
-- AUTO_INCREMENT for table `userfine`
--
ALTER TABLE `userfine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;
--
-- AUTO_INCREMENT for table `userpolice`
--
ALTER TABLE `userpolice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `userreports`
--
ALTER TABLE `userreports`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
