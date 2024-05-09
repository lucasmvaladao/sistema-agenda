-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Tempo de geração: 09/05/2024 às 16:23
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `agenda`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_cadastro`
--

CREATE TABLE `tbl_cadastro` (
  `id_cadastro` int(11) NOT NULL,
  `nome_cadastro` varchar(60) NOT NULL,
  `email_cadastro` varchar(50) NOT NULL,
  `ddd_cadastro` int(2) NOT NULL,
  `celular_cadastro` int(9) NOT NULL,
  `mes_cadastro` int(2) NOT NULL,
  `uf_cadastro` varchar(2) NOT NULL,
  `cid_cadastro` varchar(50) NOT NULL,
  `end_cadastro` varchar(100) NOT NULL,
  `cpf_cadastro` char(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbl_cadastro`
--

INSERT INTO `tbl_cadastro` (`id_cadastro`, `nome_cadastro`, `email_cadastro`, `ddd_cadastro`, `celular_cadastro`, `mes_cadastro`, `uf_cadastro`, `cid_cadastro`, `end_cadastro`, `cpf_cadastro`) VALUES
(10, 'Lucas Maciel Valadão', 'contato.valadao14@gmail.com', 11, 913389999, 6, 'SP', 'São Paulo', 'Av. João Dias', '79699435003'),
(11, 'Kamila Marinho', 'kamimarin@hotmail.com', 11, 983385673, 4, 'SP', 'São Paulo', 'Rua Toledo Barbosa', '59682850444'),
(12, 'Lucas Maciel Valadão', 'contato.valadao14@gmail.com', 11, 913389399, 6, 'SP', 'São Paulo', 'Rua Gomes De Azurara', '54671519836');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tbl_login`
--

CREATE TABLE `tbl_login` (
  `id_login` int(11) NOT NULL,
  `email_login` varchar(50) NOT NULL,
  `senha_login` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tbl_login`
--

INSERT INTO `tbl_login` (`id_login`, `email_login`, `senha_login`) VALUES
(1, 'oi@gmail.com', 'oi');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tbl_cadastro`
--
ALTER TABLE `tbl_cadastro`
  ADD PRIMARY KEY (`id_cadastro`);

--
-- Índices de tabela `tbl_login`
--
ALTER TABLE `tbl_login`
  ADD PRIMARY KEY (`id_login`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbl_cadastro`
--
ALTER TABLE `tbl_cadastro`
  MODIFY `id_cadastro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `tbl_login`
--
ALTER TABLE `tbl_login`
  MODIFY `id_login` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
