/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100413
 Source Host           : localhost:3306
 Source Schema         : syins

 Target Server Type    : MySQL
 Target Server Version : 100413
 File Encoding         : 65001

 Date: 01/10/2020 00:42:27
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ctlg_estados
-- ----------------------------
INSERT INTO `ctlg_estados` VALUES (1, 'Activo', '2020-09-30 22:23:09', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of detalle_comprobante
-- ----------------------------
INSERT INTO `detalle_comprobante` VALUES (1, 5, 10.00, 50.00, 1, 1, 1, '2020-09-30 23:46:52', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of encabezado_comprobante
-- ----------------------------
INSERT INTO `encabezado_comprobante` VALUES (1, '898989898989', 1, 1, 1, 1, '2020-09-30 23:39:37', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of kardex
-- ----------------------------
INSERT INTO `kardex` VALUES (1, 8.00, 2.00, 1, '2020-09-30 23:47:03', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organizaciones
-- ----------------------------
INSERT INTO `organizaciones` VALUES (1, 'ooooo', 'oo@email.com', '5555555', 'mmm6 7', 1, '2020-09-30 22:26:36', NULL, '2020-09-30 22:30:23');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of perfiles
-- ----------------------------
INSERT INTO `perfiles` VALUES (1, 'mm', 1, 1, '2020-09-30 22:27:44', NULL, NULL);

-- ----------------------------
-- Table structure for productos
-- ----------------------------
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cantidad_min` decimal(10, 2) NULL DEFAULT NULL,
  `cantidad_maxima` decimal(10, 2) NULL DEFAULT NULL,
  `descripcion` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productos
-- ----------------------------
INSERT INTO `productos` VALUES (1, 'papel', 22.00, 65.00, 'iujjfgkjgjkfgjkfdg', 'tyytyt', '456', 1, 1, 1, 1, 1, 1, '2020-09-30 23:46:36', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of productos_provedores
-- ----------------------------
INSERT INTO `productos_provedores` VALUES (1, 1, 1, 1, 1, '2020-09-30 23:50:16', NULL, NULL);

-- ----------------------------
-- Table structure for provedor
-- ----------------------------
DROP TABLE IF EXISTS `provedor`;
CREATE TABLE `provedor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `telefono` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of provedor
-- ----------------------------
INSERT INTO `provedor` VALUES (1, 'juan', '89566', 'gffgh@hghjg', 'jkfgkjfdg', 1, 1, '2020-09-30 23:43:48', NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usuario
-- ----------------------------
INSERT INTO `usuario` VALUES (1, 'kkkkk', 'kkkk', 'oo@email.com', 1, 1, 1, '2020-09-30 22:29:45', NULL, '2020-09-30 22:35:02');

SET FOREIGN_KEY_CHECKS = 1;