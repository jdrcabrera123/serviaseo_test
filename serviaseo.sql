-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-10-2024 a las 09:42:38
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `serviaseo`
--
CREATE DATABASE IF NOT EXISTS `serviaseo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `serviaseo`;

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE `clientes` (
  `id_cliente` int(9) NOT NULL COMMENT 'Autoincremental',
  `numero_documento` int(15) NOT NULL COMMENT 'Numero de Documento del Cliente',
  `tipo_documento` int(2) NOT NULL COMMENT 'Tipo de Documento del Cliente',
  `nombre_cliente` varchar(255) NOT NULL COMMENT 'Nombre Completo del Cliente',
  `tipo_persona` varchar(2) NOT NULL COMMENT 'Tipo de Persona, si es Natural o Juridico',
  `email` varchar(255) NOT NULL COMMENT 'Correo Electronico',
  `celular` int(20) DEFAULT NULL COMMENT 'Celular del cliente',
  `estado` varchar(1) NOT NULL DEFAULT 'A' COMMENT 'A - Activo\r\nI - Inactivo',
  `fecha_ingreso` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de Ingreso del Cliente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `numero_documento`, `tipo_documento`, `nombre_cliente`, `tipo_persona`, `email`, `celular`, `estado`, `fecha_ingreso`) VALUES
(1, 1102559489, 13, 'LAURA VALENTINA GARCIA', 'PN', 'lauvalen@gmail.co', 3012055, 'A', '2024-10-04 03:11:16'),
(5, 1014269694, 13, 'DANIELA MARTINEZ ', 'PN', 'danielamartinez@gmail.com', 3104565, 'A', '2024-10-04 04:37:11'),
(6, 900154878, 31, 'DISTRIBUIDORA DE LIMPIEZA', 'PJ', 'dislimpiezabogota@gmail.com', 3202145, 'A', '2024-10-04 04:37:56');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

DROP TABLE IF EXISTS `facturas`;
CREATE TABLE `facturas` (
  `id_factura` int(10) NOT NULL COMMENT 'Consecutivo Factura',
  `id_cliente` int(9) NOT NULL COMMENT 'ID Del cliente',
  `id_limpieza` int(9) NOT NULL COMMENT 'ID Tipo de Limpieza',
  `fecha_limpieza` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Fecha de la Limpieza'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id_factura`, `id_cliente`, `id_limpieza`, `fecha_limpieza`) VALUES
(1, 5, 1, '2024-10-04 05:41:47'),
(2, 1, 2, '2024-10-04 05:43:05'),
(3, 5, 3, '2024-10-04 05:46:01'),
(4, 1, 4, '2024-10-04 07:26:55'),
(5, 1, 3, '2024-10-04 07:30:36'),
(6, 1, 1, '2024-10-04 07:39:15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_producto`
--

DROP TABLE IF EXISTS `factura_producto`;
CREATE TABLE `factura_producto` (
  `id_factura` int(10) NOT NULL,
  `id_producto` int(9) NOT NULL,
  `id_factura_producto` int(10) NOT NULL,
  `fecha_ingreso` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `factura_producto`
--

INSERT INTO `factura_producto` (`id_factura`, `id_producto`, `id_factura_producto`, `fecha_ingreso`) VALUES
(1, 1, 1, '2024-10-04 05:41:47'),
(1, 2, 2, '2024-10-04 05:41:47'),
(1, 3, 3, '2024-10-04 05:41:47'),
(1, 4, 4, '2024-10-04 05:41:47'),
(1, 5, 5, '2024-10-04 05:41:48'),
(2, 6, 6, '2024-10-04 05:43:05'),
(2, 7, 7, '2024-10-04 05:43:05'),
(2, 8, 8, '2024-10-04 05:43:05'),
(2, 9, 9, '2024-10-04 05:43:05'),
(2, 10, 10, '2024-10-04 05:43:05'),
(3, 11, 11, '2024-10-04 05:46:01'),
(3, 12, 12, '2024-10-04 05:46:01'),
(4, 18, 13, '2024-10-04 07:26:55'),
(5, 1, 14, '2024-10-04 07:30:36'),
(5, 4, 15, '2024-10-04 07:30:36'),
(5, 13, 16, '2024-10-04 07:30:36'),
(5, 20, 17, '2024-10-04 07:30:36'),
(6, 1, 18, '2024-10-04 07:39:15'),
(6, 4, 19, '2024-10-04 07:39:15'),
(6, 13, 20, '2024-10-04 07:39:15'),
(6, 20, 21, '2024-10-04 07:39:15'),
(6, 12, 22, '2024-10-04 07:39:15'),
(6, 14, 23, '2024-10-04 07:39:15');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `limpiezas`
--

DROP TABLE IF EXISTS `limpiezas`;
CREATE TABLE `limpiezas` (
  `id_limpieza` int(9) NOT NULL COMMENT 'Consecutivo Limpiezas',
  `nombre_limpieza` varchar(255) NOT NULL COMMENT 'Nombre de Limpieza',
  `estado` varchar(2) NOT NULL DEFAULT 'A' COMMENT 'A - Activo\r\nI - Inactivo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `limpiezas`
--

INSERT INTO `limpiezas` (`id_limpieza`, `nombre_limpieza`, `estado`) VALUES
(1, 'Completa', 'A'),
(2, 'Parcial', 'A'),
(3, 'Baños', 'A'),
(4, 'Ropa', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id_producto` int(9) NOT NULL COMMENT 'Consecutivo Producto',
  `nombre_producto` varchar(255) NOT NULL COMMENT 'Nombre del Producto',
  `cantidad` int(10) NOT NULL COMMENT 'Cantidad en Stock',
  `estado` varchar(2) NOT NULL DEFAULT 'A' COMMENT 'A - Activo\r\nI - Inactivo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `nombre_producto`, `cantidad`, `estado`) VALUES
(1, 'Clorox', 100, 'A'),
(2, 'Desinfectante', 100, 'A'),
(3, 'Detergentes', 100, 'A'),
(4, 'Desengrasante', 100, 'A'),
(5, 'Trapero', 100, 'A'),
(6, 'Escoba', 100, 'A'),
(7, 'Limpiadores multiusos\r\n', 100, 'A'),
(8, 'Limpiavidrios', 100, 'A'),
(9, 'Jabón líquido\r\n', 100, 'A'),
(10, 'Aspiradoras', 100, 'A'),
(11, 'recogedores', 100, 'A'),
(12, 'Cepillos de diferentes tamaños', 100, 'A'),
(13, 'Plumeros', 100, 'A'),
(14, 'Paños de microfibra', 100, 'A'),
(15, 'Esponjas ', 100, 'A'),
(16, 'estropajos', 100, 'A'),
(17, 'Bolsas de basura', 100, 'A'),
(18, 'Toallas de papel', 100, 'A'),
(19, 'Hidrolavadoras', 100, 'A'),
(20, 'Detergente líquido', 100, 'A'),
(21, 'Suavizante de telas', 100, 'A'),
(22, 'Quitamanchas', 100, 'A'),
(23, 'Blanqueador ', 100, 'A'),
(24, 'Acondicionador de telas\r\n', 100, 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumentos`
--

DROP TABLE IF EXISTS `tipodocumentos`;
CREATE TABLE `tipodocumentos` (
  `idTipo` int(2) NOT NULL COMMENT 'Codigo DIAN Tipo de Documento',
  `tipoDocumento` varchar(255) NOT NULL COMMENT 'Nombre de Tipo de Documento',
  `estado` varchar(2) NOT NULL DEFAULT 'A' COMMENT 'A - Activo\r\nI - Inactivo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `tipodocumentos`
--

INSERT INTO `tipodocumentos` (`idTipo`, `tipoDocumento`, `estado`) VALUES
(11, 'REGISTRO CIVIL DE NACIMIENTO', 'A'),
(12, 'TARJETA DE IDENTIDAD', 'A'),
(13, 'CEDULA DE CIUDADANIA', 'A'),
(21, 'TARJETA DE EXTRANJERIA', 'A'),
(22, 'CEDULA DE EXTRANJERIA', 'A'),
(31, 'NIT', 'A'),
(41, 'PASAPORTE', 'A'),
(42, 'TIPO DE DOCUMENTO EXTRANJERO', 'A'),
(43, 'SIN ID DEL EXTERIOR O USO DIAN', 'A'),
(47, 'PERMISO ESPECIAL DE PERMANENCIA', 'A'),
(48, 'PERMISO DE PROTECCION TEMPORAL', 'A'),
(50, 'NIT DE OTROS PAISES', 'A');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`) USING BTREE,
  ADD UNIQUE KEY `UK_Clientes` (`numero_documento`,`tipo_documento`) USING BTREE,
  ADD KEY `EMAIL` (`email`) USING BTREE,
  ADD KEY `tipoDocumento_fk` (`tipo_documento`);

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id_factura`) USING BTREE,
  ADD KEY `limpieza_fk` (`id_limpieza`),
  ADD KEY `cliente_fk` (`id_cliente`);

--
-- Indices de la tabla `factura_producto`
--
ALTER TABLE `factura_producto`
  ADD PRIMARY KEY (`id_factura_producto`) USING BTREE,
  ADD KEY `producto_fk` (`id_producto`),
  ADD KEY `factura_fk` (`id_factura`);

--
-- Indices de la tabla `limpiezas`
--
ALTER TABLE `limpiezas`
  ADD PRIMARY KEY (`id_limpieza`) USING BTREE;

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`) USING BTREE;

--
-- Indices de la tabla `tipodocumentos`
--
ALTER TABLE `tipodocumentos`
  ADD PRIMARY KEY (`idTipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(9) NOT NULL AUTO_INCREMENT COMMENT 'Autoincremental', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id_factura` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Consecutivo Factura', AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `factura_producto`
--
ALTER TABLE `factura_producto`
  MODIFY `id_factura_producto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `limpiezas`
--
ALTER TABLE `limpiezas`
  MODIFY `id_limpieza` int(9) NOT NULL AUTO_INCREMENT COMMENT 'Consecutivo Limpiezas', AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(9) NOT NULL AUTO_INCREMENT COMMENT 'Consecutivo Producto', AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `tipoDocumento_fk` FOREIGN KEY (`tipo_documento`) REFERENCES `tipodocumentos` (`idTipo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD CONSTRAINT `cliente_fk` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `limpieza_fk` FOREIGN KEY (`id_limpieza`) REFERENCES `limpiezas` (`id_limpieza`);

--
-- Filtros para la tabla `factura_producto`
--
ALTER TABLE `factura_producto`
  ADD CONSTRAINT `factura_fk` FOREIGN KEY (`id_factura`) REFERENCES `facturas` (`id_factura`),
  ADD CONSTRAINT `producto_fk` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`);
COMMIT;


DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `crear_factura`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `crear_factura` (IN `p_idCliente` INT, IN `p_idLimpieza` INT, IN `p_productos` JSON)   BEGIN
    DECLARE p_idFactura INT;
    DECLARE i INT DEFAULT 0;
    DECLARE totalProductos INT;

    SET totalProductos = JSON_LENGTH(p_productos);

    INSERT INTO facturas (id_cliente, id_limpieza)
    VALUES (p_idCliente, p_idLimpieza);

    SET p_idFactura = LAST_INSERT_ID();

    WHILE i < totalProductos DO
        INSERT INTO factura_producto (id_factura, id_producto)
        VALUES (p_idFactura, JSON_UNQUOTE(JSON_EXTRACT(p_productos, CONCAT('$[', i, ']'))));
        SET i = i + 1;
    END WHILE;

    SELECT p_idFactura AS idNuevaFactura;
END$$

DROP PROCEDURE IF EXISTS `registrar_cliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `registrar_cliente` (IN `p_tipoDocumento` INT, IN `p_numeroDocumento` INT, IN `p_nombreCliente` VARCHAR(255), IN `p_email` VARCHAR(255), IN `p_celular` BIGINT, IN `p_estado` VARCHAR(2))   BEGIN
    DECLARE p_tipoPersona VARCHAR(2);

    IF p_tipoDocumento IN (31, 50) THEN
        SET p_tipoPersona = 'PJ'; 
    ELSE
        SET p_tipoPersona = 'PN';
    END IF;

    INSERT INTO clientes (tipo_documento, numero_documento, nombre_cliente, tipo_persona, email, celular, estado)
    VALUES (p_tipoDocumento, p_numeroDocumento, p_nombreCliente, p_tipoPersona, p_email, p_celular, p_estado);

    SELECT LAST_INSERT_ID() AS idNuevoCliente;
END$$

DROP PROCEDURE IF EXISTS `verificar_cliente`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `verificar_cliente` (IN `p_tipoDocumento` INT, IN `p_numeroDocumento` INT)   BEGIN
    DECLARE cliente_existente INT;

    SELECT COUNT(*) INTO cliente_existente
    FROM clientes
    WHERE tipo_documento = p_tipoDocumento
    AND numero_documento = p_numeroDocumento;

    IF cliente_existente > 0 THEN
        SELECT 'Cliente existe' AS resultado;
    ELSE
        SELECT 'Cliente no existe' AS resultado;
    END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
