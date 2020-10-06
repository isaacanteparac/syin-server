/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100414
 Source Host           : localhost:3306
 Source Schema         : syin

 Target Server Type    : MySQL
 Target Server Version : 100414
 File Encoding         : 65001

 Date: 06/10/2020 02:06:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ctlg_estados
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_estados`;
CREATE TABLE `ctlg_estados`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_estados
-- ----------------------------
INSERT INTO `ctlg_estados` VALUES (1, 'Activo', '2020-09-30 22:23:09', NULL, NULL);
INSERT INTO `ctlg_estados` VALUES (2, 'Inactico', '2020-10-03 17:57:29', NULL, NULL);

-- ----------------------------
-- Table structure for ctlg_iva
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_iva`;
CREATE TABLE `ctlg_iva`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `porcentaje` decimal(10, 2) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_iva
-- ----------------------------
INSERT INTO `ctlg_iva` VALUES (1, 'IVA 12%', 0.12, '2020-09-30 22:39:53', NULL, NULL);
INSERT INTO `ctlg_iva` VALUES (2, 'IVA 0%', 0.00, '2020-09-30 22:40:31', NULL, NULL);

-- ----------------------------
-- Table structure for ctlg_metodos_inventario
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_metodos_inventario`;
CREATE TABLE `ctlg_metodos_inventario`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_ctlg_estados`(`id_ctlg_estados`) USING BTREE,
  CONSTRAINT `ctlg_metodos_inventario_ibfk_1` FOREIGN KEY (`id_ctlg_estados`) REFERENCES `ctlg_estados` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_metodos_inventario
-- ----------------------------
INSERT INTO `ctlg_metodos_inventario` VALUES (1, 'FIFO - PEP', 1, '2020-09-30 22:42:22', NULL, NULL);
INSERT INTO `ctlg_metodos_inventario` VALUES (2, 'PONDERADO - PROMEDIO', 1, '2020-09-30 22:42:54', NULL, NULL);

-- ----------------------------
-- Table structure for ctlg_tipos_comprobante
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_tipos_comprobante`;
CREATE TABLE `ctlg_tipos_comprobante`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_tipos_comprobante
-- ----------------------------
INSERT INTO `ctlg_tipos_comprobante` VALUES (1, 'Factura', '2020-09-30 22:49:16', NULL, NULL);
INSERT INTO `ctlg_tipos_comprobante` VALUES (2, 'Nota de venta', '2020-09-30 22:49:31', NULL, NULL);
INSERT INTO `ctlg_tipos_comprobante` VALUES (3, 'Liquidacion de comprobante', '2020-09-30 22:49:43', NULL, '2020-09-30 22:50:33');
INSERT INTO `ctlg_tipos_comprobante` VALUES (4, 'Nota de credito', '2020-09-30 22:50:51', NULL, NULL);
INSERT INTO `ctlg_tipos_comprobante` VALUES (5, 'Nota de debito', '2020-09-30 22:51:02', NULL, NULL);

-- ----------------------------
-- Table structure for ctlg_tipos_productos
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_tipos_productos`;
CREATE TABLE `ctlg_tipos_productos`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_tipos_productos
-- ----------------------------
INSERT INTO `ctlg_tipos_productos` VALUES (1, 'Peresible', '2020-09-30 22:52:33', NULL, NULL);
INSERT INTO `ctlg_tipos_productos` VALUES (2, 'No peresible', '2020-09-30 22:52:46', NULL, NULL);

-- ----------------------------
-- Table structure for ctlg_tipos_transacion
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_tipos_transacion`;
CREATE TABLE `ctlg_tipos_transacion`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_tipos_transacion
-- ----------------------------
INSERT INTO `ctlg_tipos_transacion` VALUES (1, 'Compras', '2020-09-30 22:56:53', NULL, NULL);
INSERT INTO `ctlg_tipos_transacion` VALUES (2, 'Ventas', '2020-09-30 22:57:06', NULL, NULL);
INSERT INTO `ctlg_tipos_transacion` VALUES (3, 'Devolucion en compras', '2020-09-30 22:57:44', NULL, NULL);
INSERT INTO `ctlg_tipos_transacion` VALUES (4, 'Devolucion en ventas', '2020-09-30 22:57:58', NULL, NULL);

-- ----------------------------
-- Table structure for ctlg_unidades_medidas
-- ----------------------------
DROP TABLE IF EXISTS `ctlg_unidades_medidas`;
CREATE TABLE `ctlg_unidades_medidas`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `simbolo` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_unidades_medidas
-- ----------------------------
INSERT INTO `ctlg_unidades_medidas` VALUES (1, 'kilogramo ', 'Kg', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (2, 'Litro', 'L', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (3, 'Kilometro', 'Km', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (4, 'Metro', 'm', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (5, 'Centímetro', 'cm', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (6, 'Milímetro', 'mm', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (7, 'Gramo', 'g', '2020-09-30 23:34:59', NULL, NULL);
INSERT INTO `ctlg_unidades_medidas` VALUES (8, 'Mililitro', 'ml', '2020-09-30 23:34:59', NULL, NULL);

-- ----------------------------
-- Table structure for detalle_comprobante
-- ----------------------------
DROP TABLE IF EXISTS `detalle_comprobante`;
CREATE TABLE `detalle_comprobante`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) NULL DEFAULT NULL,
  `precio` decimal(10, 2) NULL DEFAULT NULL,
  `total` decimal(10, 2) NULL DEFAULT NULL,
  `id_productos` int(11) NULL DEFAULT NULL,
  `id_provedor` int(11) NULL DEFAULT NULL,
  `id_encabezado_comprobante` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_productos`(`id_productos`) USING BTREE,
  INDEX `id_provedor`(`id_provedor`) USING BTREE,
  INDEX `id_encabezado_comprobante`(`id_encabezado_comprobante`) USING BTREE,
  CONSTRAINT `detalle_comprobante_ibfk_1` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `detalle_comprobante_ibfk_2` FOREIGN KEY (`id_provedor`) REFERENCES `provedor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `detalle_comprobante_ibfk_3` FOREIGN KEY (`id_encabezado_comprobante`) REFERENCES `encabezado_comprobante` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detalle_comprobante
-- ----------------------------
INSERT INTO `detalle_comprobante` VALUES (1, 50, 90.00, 60.00, 1, 1, 1, '2020-09-30 23:46:52', NULL, '2020-10-03 23:38:52');
INSERT INTO `detalle_comprobante` VALUES (2, 10, 54.00, 45.00, 1, 1, 1, '2020-10-01 18:58:13', NULL, '2020-10-03 23:38:56');
INSERT INTO `detalle_comprobante` VALUES (3, 25, 930.00, 630.00, 1, 1, 1, '2020-10-02 22:31:24', NULL, '2020-10-03 23:39:32');
INSERT INTO `detalle_comprobante` VALUES (4, 15, 1000.00, 1000.00, 1, 1, 1, '2020-10-03 00:57:08', NULL, '2020-10-03 23:39:28');
INSERT INTO `detalle_comprobante` VALUES (5, 2, 10.00, 20.00, 1, NULL, NULL, '2020-10-03 23:12:53', NULL, '2020-10-03 23:13:20');
INSERT INTO `detalle_comprobante` VALUES (6, 10, 5.00, 15.00, 1, 2, 5, '2020-10-03 23:41:55', NULL, NULL);
INSERT INTO `detalle_comprobante` VALUES (7, 80, 6.00, 480.00, 1, 1, 6, '2020-10-04 00:02:49', NULL, NULL);
INSERT INTO `detalle_comprobante` VALUES (8, 2, 2.00, 4.00, 1, 1, 7, '2020-10-04 00:11:02', NULL, NULL);

-- ----------------------------
-- Table structure for encabezado_comprobante
-- ----------------------------
DROP TABLE IF EXISTS `encabezado_comprobante`;
CREATE TABLE `encabezado_comprobante`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num_comprovante` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_organizacion` int(11) NULL DEFAULT NULL,
  `id_usuario` int(11) NULL DEFAULT NULL,
  `id_ctlg_tipos_comprobante` int(11) NULL DEFAULT NULL,
  `id_ctlg_tipos_transacion` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_organizacion`(`id_organizacion`) USING BTREE,
  INDEX `id_usuario`(`id_usuario`) USING BTREE,
  INDEX `id_ctlg_tipos_comprobante`(`id_ctlg_tipos_comprobante`) USING BTREE,
  INDEX `id_ctlg_tipos_transacion`(`id_ctlg_tipos_transacion`) USING BTREE,
  CONSTRAINT `encabezado_comprobante_ibfk_1` FOREIGN KEY (`id_organizacion`) REFERENCES `organizaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `encabezado_comprobante_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `encabezado_comprobante_ibfk_3` FOREIGN KEY (`id_ctlg_tipos_comprobante`) REFERENCES `ctlg_tipos_comprobante` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `encabezado_comprobante_ibfk_4` FOREIGN KEY (`id_ctlg_tipos_transacion`) REFERENCES `ctlg_tipos_transacion` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of encabezado_comprobante
-- ----------------------------
INSERT INTO `encabezado_comprobante` VALUES (1, '5555', 2, 1, 1, 1, '2020-09-30 23:39:37', NULL, '2020-10-06 01:18:23');
INSERT INTO `encabezado_comprobante` VALUES (2, 'gokg343', 2, 1, 1, 1, '2020-10-02 22:30:34', NULL, '2020-10-06 01:18:28');
INSERT INTO `encabezado_comprobante` VALUES (3, '123456', 2, 1, 1, 1, '2020-10-03 00:56:19', NULL, '2020-10-06 01:18:52');
INSERT INTO `encabezado_comprobante` VALUES (4, '547896', 1, 1, 1, 1, '2020-10-03 23:11:19', NULL, NULL);
INSERT INTO `encabezado_comprobante` VALUES (5, '555', 1, 1, 1, 1, '2020-10-03 23:41:20', NULL, NULL);
INSERT INTO `encabezado_comprobante` VALUES (6, '666', 1, 1, 1, 2, '2020-10-04 00:01:18', NULL, NULL);
INSERT INTO `encabezado_comprobante` VALUES (7, '777', 1, 1, 1, 2, '2020-10-04 00:10:28', NULL, '2020-10-04 00:10:40');

-- ----------------------------
-- Table structure for kardex
-- ----------------------------
DROP TABLE IF EXISTS `kardex`;
CREATE TABLE `kardex`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` decimal(10, 2) NULL DEFAULT NULL,
  `precio_unitario` decimal(10, 2) NULL DEFAULT NULL,
  `id_detalle_comprobante` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_detalle_comprobante`(`id_detalle_comprobante`) USING BTREE,
  CONSTRAINT `kardex_ibfk_1` FOREIGN KEY (`id_detalle_comprobante`) REFERENCES `detalle_comprobante` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kardex
-- ----------------------------
INSERT INTO `kardex` VALUES (1, 99.00, 99.00, 1, '2020-09-30 23:47:03', NULL, '2020-10-03 00:42:41');
INSERT INTO `kardex` VALUES (2, 33.00, 33.00, 2, '2020-10-03 00:43:02', NULL, NULL);
INSERT INTO `kardex` VALUES (3, 123.00, 456.00, 2, '2020-10-03 00:58:32', NULL, NULL);

-- ----------------------------
-- Table structure for opciones
-- ----------------------------
DROP TABLE IF EXISTS `opciones`;
CREATE TABLE `opciones`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icono` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ruta` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opciones
-- ----------------------------
INSERT INTO `opciones` VALUES (1, 'home', 'hfhf.icon', 'www.hddh.com', '2020-09-30 23:48:26', NULL, NULL);

-- ----------------------------
-- Table structure for organizaciones
-- ----------------------------
DROP TABLE IF EXISTS `organizaciones`;
CREATE TABLE `organizaciones`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telefono` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ubicacion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_ctlg_estados`(`id_ctlg_estados`) USING BTREE,
  CONSTRAINT `organizaciones_ibfk_1` FOREIGN KEY (`id_ctlg_estados`) REFERENCES `ctlg_estados` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organizaciones
-- ----------------------------
INSERT INTO `organizaciones` VALUES (1, 'pe', 'prp@email.com', '89', 'jj 7657', 1, '2020-09-30 22:26:36', NULL, '2020-10-01 19:33:59');
INSERT INTO `organizaciones` VALUES (2, 'asus', 'prp@email.com', '89', 'jj 7657', 1, '2020-10-02 22:32:59', NULL, NULL);
INSERT INTO `organizaciones` VALUES (3, 'ert', 'ert@email.com', '8945687', 'jj 7657 yuytf', 1, '2020-10-03 00:54:50', NULL, NULL);

-- ----------------------------
-- Table structure for perfil_opciones
-- ----------------------------
DROP TABLE IF EXISTS `perfil_opciones`;
CREATE TABLE `perfil_opciones`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_perfiles` int(11) NULL DEFAULT NULL,
  `id_opciones` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_perfiles`(`id_perfiles`) USING BTREE,
  INDEX `id_opciones`(`id_opciones`) USING BTREE,
  CONSTRAINT `perfil_opciones_ibfk_1` FOREIGN KEY (`id_perfiles`) REFERENCES `perfiles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `perfil_opciones_ibfk_2` FOREIGN KEY (`id_opciones`) REFERENCES `opciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perfil_opciones
-- ----------------------------
INSERT INTO `perfil_opciones` VALUES (1, 1, 1, '2020-09-30 23:49:20', NULL, NULL);

-- ----------------------------
-- Table structure for perfiles
-- ----------------------------
DROP TABLE IF EXISTS `perfiles`;
CREATE TABLE `perfiles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_organizaciones` int(11) NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_organizaciones`(`id_organizaciones`) USING BTREE,
  INDEX `id_ctlg_estados`(`id_ctlg_estados`) USING BTREE,
  CONSTRAINT `perfiles_ibfk_1` FOREIGN KEY (`id_organizaciones`) REFERENCES `organizaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `perfiles_ibfk_2` FOREIGN KEY (`id_ctlg_estados`) REFERENCES `ctlg_estados` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perfiles
-- ----------------------------
INSERT INTO `perfiles` VALUES (1, 'nomo', 1, 1, '2020-09-30 22:27:44', NULL, '2020-10-01 19:34:54');
INSERT INTO `perfiles` VALUES (4, 'pepe', 1, 1, '2020-10-02 23:41:10', NULL, NULL);
INSERT INTO `perfiles` VALUES (6, 'jere', 1, 1, '2020-10-03 00:53:58', NULL, NULL);
INSERT INTO `perfiles` VALUES (7, 'uue', 1, 1, '2020-10-03 17:16:40', NULL, NULL);

-- ----------------------------
-- Table structure for productos
-- ----------------------------
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cantidad_min` decimal(10, 2) NULL DEFAULT NULL,
  `cantidad_maxima` decimal(10, 2) NULL DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `imagen` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `codigo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_organizaciones` int(11) NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `id_ctlg_metodos_inventario` int(11) NULL DEFAULT NULL,
  `id_ctlg_unidades_medidas` int(11) NULL DEFAULT NULL,
  `id_ctlg_iva` int(11) NULL DEFAULT NULL,
  `id_ctlg_tipos_productos` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_organizaciones`(`id_organizaciones`) USING BTREE,
  INDEX `id_ctlg_metodos_inventario`(`id_ctlg_metodos_inventario`) USING BTREE,
  INDEX `id_ctlg_unidades_medidas`(`id_ctlg_unidades_medidas`) USING BTREE,
  INDEX `id_ctlg_iva`(`id_ctlg_iva`) USING BTREE,
  INDEX `id_ctlg_tipos_productos`(`id_ctlg_tipos_productos`) USING BTREE,
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_organizaciones`) REFERENCES `organizaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_ctlg_metodos_inventario`) REFERENCES `ctlg_metodos_inventario` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_ibfk_3` FOREIGN KEY (`id_ctlg_unidades_medidas`) REFERENCES `ctlg_unidades_medidas` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_ibfk_4` FOREIGN KEY (`id_ctlg_iva`) REFERENCES `ctlg_iva` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_ibfk_5` FOREIGN KEY (`id_ctlg_tipos_productos`) REFERENCES `ctlg_tipos_productos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productos
-- ----------------------------
INSERT INTO `productos` VALUES (1, 'limon', 60.00, 60.00, 'limon helado', 'limon.jpg', 'l9382', 2, 1, 2, 2, 1, 1, '2020-09-30 23:46:36', NULL, '2020-10-06 02:03:36');
INSERT INTO `productos` VALUES (8, 'banana', 64.00, 64.00, 'nmnmo helado', 'jpg', 'l9382', 1, 1, 2, 2, 2, 2, '2020-10-02 23:40:30', NULL, '2020-10-06 01:37:49');
INSERT INTO `productos` VALUES (9, 'coco', 5.00, 2.00, 'cocohelado', '45', 'l95454382', 1, 1, 2, 2, 2, 2, '2020-10-03 00:52:34', NULL, NULL);

-- ----------------------------
-- Table structure for productos_provedores
-- ----------------------------
DROP TABLE IF EXISTS `productos_provedores`;
CREATE TABLE `productos_provedores`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_organizacion` int(11) NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `id_provedor` int(11) NULL DEFAULT NULL,
  `id_productos` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_organizacion`(`id_organizacion`) USING BTREE,
  INDEX `id_ctlg_estados`(`id_ctlg_estados`) USING BTREE,
  INDEX `id_provedor`(`id_provedor`) USING BTREE,
  INDEX `id_productos`(`id_productos`) USING BTREE,
  CONSTRAINT `productos_provedores_ibfk_1` FOREIGN KEY (`id_organizacion`) REFERENCES `organizaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_provedores_ibfk_2` FOREIGN KEY (`id_ctlg_estados`) REFERENCES `ctlg_estados` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_provedores_ibfk_3` FOREIGN KEY (`id_provedor`) REFERENCES `provedor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `productos_provedores_ibfk_4` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productos_provedores
-- ----------------------------
INSERT INTO `productos_provedores` VALUES (1, 1, 1, 1, 1, '2020-09-30 23:50:16', NULL, NULL);
INSERT INTO `productos_provedores` VALUES (2, 1, 1, 1, 8, '2020-10-03 22:47:33', NULL, '2020-10-03 22:48:46');
INSERT INTO `productos_provedores` VALUES (3, 1, 1, 1, 9, '2020-10-03 22:48:43', NULL, '2020-10-03 22:49:00');
INSERT INTO `productos_provedores` VALUES (5, 1, 1, 2, 1, '2020-10-03 23:40:24', NULL, '2020-10-03 23:40:36');

-- ----------------------------
-- Table structure for provedor
-- ----------------------------
DROP TABLE IF EXISTS `provedor`;
CREATE TABLE `provedor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telefono` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ubicacion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_organizacion` int(11) NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `update` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_organizacion`(`id_organizacion`) USING BTREE,
  INDEX `id_ctlg_estados`(`id_ctlg_estados`) USING BTREE,
  CONSTRAINT `provedor_ibfk_1` FOREIGN KEY (`id_organizacion`) REFERENCES `organizaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `provedor_ibfk_2` FOREIGN KEY (`id_ctlg_estados`) REFERENCES `ctlg_estados` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provedor
-- ----------------------------
INSERT INTO `provedor` VALUES (1, 'pppp', '888888', '6666666@hghjg', 'oklkñ0', 1, 1, '2020-09-30 23:43:48', NULL, '2020-10-02 22:28:16');
INSERT INTO `provedor` VALUES (2, 'poujh', '888888', '6666666@hghjg', 'oklkñ0', 1, 1, '2020-10-02 22:30:00', NULL, NULL);
INSERT INTO `provedor` VALUES (3, 'poujh', '888888', '6666666@hghjg', 'oklkñ0', 1, 1, '2020-10-02 23:30:10', NULL, NULL);
INSERT INTO `provedor` VALUES (4, 'cesar', '8887888', 'cesar@hghjg', 'saman', 1, 1, '2020-10-03 00:55:39', NULL, NULL);

-- ----------------------------
-- Table structure for usuario
-- ----------------------------
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `apellidos` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `correo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_organizaciones` int(11) NULL DEFAULT NULL,
  `id_ctlg_estados` int(11) NULL DEFAULT NULL,
  `id_perfiles` int(11) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT current_timestamp,
  `deleted` timestamp(0) NULL DEFAULT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_organizaciones`(`id_organizaciones`) USING BTREE,
  INDEX `id_ctlg_estados`(`id_ctlg_estados`) USING BTREE,
  INDEX `id_perfiles`(`id_perfiles`) USING BTREE,
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_organizaciones`) REFERENCES `organizaciones` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_ctlg_estados`) REFERENCES `ctlg_estados` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`id_perfiles`) REFERENCES `perfiles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES (1, 'ic', 'aara', 'hnjgii@email.com', 1, 1, 1, '2020-09-30 22:29:45', '2020-10-03 17:27:02', '2020-10-03 17:27:02');
INSERT INTO `usuario` VALUES (4, 'isaav', 'pppp', 'idjd', 1, 1, 4, '2020-10-03 17:13:01', NULL, '2020-10-03 17:28:41');
INSERT INTO `usuario` VALUES (5, 'miguek', 'rodpe', 'pedro@email.com', 2, 1, 1, '2020-10-03 23:50:51', NULL, NULL);
INSERT INTO `usuario` VALUES (6, 'ber', 'rodpe', 'pedro@email.com', 2, 1, 1, '2020-10-03 23:51:04', NULL, NULL);
INSERT INTO `usuario` VALUES (7, 'tujty', 'ytutyu', 'pedro@email.com', 1, 2, 1, '2020-10-04 00:26:22', NULL, '2020-10-04 01:23:54');

SET FOREIGN_KEY_CHECKS = 1;
