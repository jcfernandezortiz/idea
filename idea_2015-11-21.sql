# ************************************************************
# Sequel Pro SQL dump
# Versión 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: idea.cgdidjsi5aiz.eu-central-1.rds.amazonaws.com (MySQL 5.6.23-log)
# Base de datos: idea
# Tiempo de Generación: 2015-11-21 00:06:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla clasificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `clasificacion`;

CREATE TABLE `clasificacion` (
  `idclasificacion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text,
  `descripcion` text,
  PRIMARY KEY (`idclasificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

LOCK TABLES `clasificacion` WRITE;
/*!40000 ALTER TABLE `clasificacion` DISABLE KEYS */;

INSERT INTO `clasificacion` (`idclasificacion`, `nombre`, `descripcion`)
VALUES
	(1,'deportes','ideas relacionadas al area de deportes'),
	(2,'salud','ideas relacionadas al area de salud'),
	(3,'tecnologia',NULL),
	(4,'educacion',NULL);

/*!40000 ALTER TABLE `clasificacion` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla comentario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comentario`;

CREATE TABLE `comentario` (
  `idcomentario` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `ididea` int(11) DEFAULT NULL,
  `comentario` text,
  `fecha_registro` timestamp NULL DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idcomentario`),
  KEY `idusuario_comentario_idx` (`idusuario`),
  KEY `ididea_comentario_idx` (`ididea`),
  CONSTRAINT `ididea_comentario` FOREIGN KEY (`ididea`) REFERENCES `idea` (`ididea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idusuario_comentario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Volcado de tabla estado_idea
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estado_idea`;

CREATE TABLE `estado_idea` (
  `idestado_idea` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  PRIMARY KEY (`idestado_idea`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

LOCK TABLES `estado_idea` WRITE;
/*!40000 ALTER TABLE `estado_idea` DISABLE KEYS */;

INSERT INTO `estado_idea` (`idestado_idea`, `descripcion`)
VALUES
	(1,'creada'),
	(2,'en subasta'),
	(3,'vendida'),
	(4,'en stand by');

/*!40000 ALTER TABLE `estado_idea` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla estado_subasta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `estado_subasta`;

CREATE TABLE `estado_subasta` (
  `idestado_subasta` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  PRIMARY KEY (`idestado_subasta`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

LOCK TABLES `estado_subasta` WRITE;
/*!40000 ALTER TABLE `estado_subasta` DISABLE KEYS */;

INSERT INTO `estado_subasta` (`idestado_subasta`, `descripcion`)
VALUES
	(1,'por iniciar'),
	(2,'en proceso'),
	(3,'finalizada'),
	(4,'venta');

/*!40000 ALTER TABLE `estado_subasta` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla idea
# ------------------------------------------------------------

DROP TABLE IF EXISTS `idea`;

CREATE TABLE `idea` (
  `ididea` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `idestado_idea` int(11) DEFAULT NULL,
  `titulo` text,
  `descripcion` text,
  `fecha_registro` timestamp NULL DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ididea`),
  KEY `idusuario_idea_idx` (`idusuario`),
  KEY `idestado_idea_idea_idx` (`idestado_idea`),
  CONSTRAINT `idestado_idea_idea` FOREIGN KEY (`idestado_idea`) REFERENCES `estado_idea` (`idestado_idea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idusuario_idea` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Volcado de tabla idea_x_clasificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `idea_x_clasificacion`;

CREATE TABLE `idea_x_clasificacion` (
  `ididea` int(11) DEFAULT NULL,
  `idclasificacion` int(11) DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT NULL,
  KEY `ididea_idea_x_clasificacion_idx` (`ididea`),
  KEY `idclasificacion_idea_x_clasificacion_idx` (`idclasificacion`),
  CONSTRAINT `idclasificacion_idea_x_clasificacion` FOREIGN KEY (`idclasificacion`) REFERENCES `clasificacion` (`idclasificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ididea_idea_x_clasificacion` FOREIGN KEY (`ididea`) REFERENCES `idea` (`ididea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Volcado de tabla oferta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oferta`;

CREATE TABLE `oferta` (
  `idoferta` int(11) NOT NULL AUTO_INCREMENT,
  `idventa` int(11) DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `fecha_registro` timestamp NULL DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idoferta`),
  KEY `idventa_oferta_idx` (`idventa`),
  KEY `idusuario_oferta_idx` (`idusuario`),
  CONSTRAINT `idusuario_oferta` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idventa_oferta` FOREIGN KEY (`idventa`) REFERENCES `venta` (`idventa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Volcado de tabla perfil
# ------------------------------------------------------------

DROP TABLE IF EXISTS `perfil`;

CREATE TABLE `perfil` (
  `idperfil` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` text,
  PRIMARY KEY (`idperfil`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;

INSERT INTO `perfil` (`idperfil`, `descripcion`)
VALUES
	(1,'administrador'),
	(2,'emprendedor');

/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla usuario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `idperfil` int(11) DEFAULT NULL,
  `nombre` text,
  `correo` text,
  `password` text,
  `fecha_registro` timestamp NULL DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `idperfil_usuario_idx` (`idperfil`),
  CONSTRAINT `idperfil_usuario` FOREIGN KEY (`idperfil`) REFERENCES `perfil` (`idperfil`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;

INSERT INTO `usuario` (`idusuario`, `idperfil`, `nombre`, `correo`, `password`, `fecha_registro`, `activo`)
VALUES
	(1,1,'Usuario Demo','correo@prueba.com','userdemo','0000-00-00 00:00:00',1);

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla venta
# ------------------------------------------------------------

DROP TABLE IF EXISTS `venta`;

CREATE TABLE `venta` (
  `idventa` int(11) NOT NULL AUTO_INCREMENT,
  `ididea` int(11) DEFAULT NULL,
  `idestado_subasta` int(11) DEFAULT NULL,
  `fecha_inicio` timestamp NULL DEFAULT NULL,
  `fecha_fin` timestamp NULL DEFAULT NULL,
  `activo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idventa`),
  KEY `ididea_venta_idx` (`ididea`),
  KEY `idestado_subasta_venta_idx` (`idestado_subasta`),
  CONSTRAINT `idestado_subasta_venta` FOREIGN KEY (`idestado_subasta`) REFERENCES `estado_subasta` (`idestado_subasta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ididea_venta` FOREIGN KEY (`ididea`) REFERENCES `idea` (`ididea`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
