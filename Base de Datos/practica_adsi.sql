-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-01-2013 a las 14:35:46
-- Versión del servidor: 5.5.27
-- Versión de PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `practica_adsi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casilla`
--

CREATE TABLE IF NOT EXISTS `casilla` (
  `numero` int(10) NOT NULL,
  `posX` int(10) DEFAULT '0',
  `posY` int(10) DEFAULT '0',
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `casilla`
--

INSERT INTO `casilla` (`numero`, `posX`, `posY`) VALUES
(1, 0, 0),
(2, 0, 0),
(3, 0, 0),
(4, 0, 0),
(5, 0, 0),
(6, 0, 0),
(7, 0, 0),
(8, 0, 0),
(9, 0, 0),
(10, 0, 0),
(11, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casillapregunta`
--

CREATE TABLE IF NOT EXISTS `casillapregunta` (
  `numero` int(10) NOT NULL,
  `categoria` int(10) NOT NULL,
  PRIMARY KEY (`numero`),
  KEY `categoria` (`categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `casillapregunta`
--

INSERT INTO `casillapregunta` (`numero`, `categoria`) VALUES
(1, 1),
(7, 1),
(10, 1),
(3, 2),
(9, 2),
(11, 2),
(5, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casillaretencion`
--

CREATE TABLE IF NOT EXISTS `casillaretencion` (
  `numero` int(10) NOT NULL,
  `TurnoRetenido` int(10) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `casillaretencion`
--

INSERT INTO `casillaretencion` (`numero`, `TurnoRetenido`) VALUES
(2, 2),
(6, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casillasalto`
--

CREATE TABLE IF NOT EXISTS `casillasalto` (
  `numero` int(10) NOT NULL,
  `CasillaDestino` int(10) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `casillasalto`
--

INSERT INTO `casillasalto` (`numero`, `CasillaDestino`) VALUES
(4, 8),
(8, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `idCategoria` int(10) NOT NULL,
  `nombre` varchar(30) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `nombre`) VALUES
(1, 'Cine'),
(2, 'Videojuegos'),
(3, 'Series'),
(4, 'Manga');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infopartidas`
--

CREATE TABLE IF NOT EXISTS `infopartidas` (
  `idPartida` int(10) NOT NULL,
  `nombreU` varchar(30) CHARACTER SET latin1 NOT NULL,
  `numeroC` int(10) NOT NULL,
  `LeToca` int(1) NOT NULL,
  `Color` varchar(30) CHARACTER SET latin1 NOT NULL,
  `TurnosBloqueado` int(10) NOT NULL,
  `Posicion` int(10) NOT NULL,
  PRIMARY KEY (`idPartida`,`nombreU`),
  KEY `nombreU` (`nombreU`),
  KEY `numeroC` (`numeroC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `infopartidas`
--

INSERT INTO `infopartidas` (`idPartida`, `nombreU`, `numeroC`, `LeToca`, `Color`, `TurnosBloqueado`, `Posicion`) VALUES
(1, 'Aaron', 1, 0, 'rojo', 0, 1),
(1, 'David', 2, 0, 'azul', 2, 3),
(1, 'Sara', 4, 1, 'verde', 0, 2),
(2, 'Endika', 3, 1, 'amarillo', 0, 1),
(2, 'Helen', 9, 0, 'rojo', 0, 2),
(3, 'Helen', 7, 0, 'verde', 0, 2),
(3, 'Jon Ander', 6, 1, 'azul', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida`
--

CREATE TABLE IF NOT EXISTS `partida` (
  `idPartida` int(10) NOT NULL AUTO_INCREMENT,
  `Fecha` date NOT NULL,
  PRIMARY KEY (`idPartida`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `partida`
--

INSERT INTO `partida` (`idPartida`, `Fecha`) VALUES
(1, '2012-12-01'),
(2, '2012-12-01'),
(3, '2012-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregcat`
--

CREATE TABLE IF NOT EXISTS `pregcat` (
  `idPregunta` int(10) NOT NULL,
  `idCategoria` int(10) NOT NULL,
  PRIMARY KEY (`idPregunta`,`idCategoria`),
  KEY `idCategoria` (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pregcat`
--

INSERT INTO `pregcat` (`idPregunta`, `idCategoria`) VALUES
(2, 1),
(4, 1),
(1, 2),
(3, 3),
(18, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

CREATE TABLE IF NOT EXISTS `pregunta` (
  `idPregunta` int(10) NOT NULL,
  `texto` varchar(60) CHARACTER SET latin1 NOT NULL,
  `Resp1` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Resp2` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Resp3` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Resp4` varchar(30) CHARACTER SET latin1 NOT NULL,
  `RespCorrecta` varchar(30) CHARACTER SET latin1 NOT NULL,
  `VecesPreguntada` int(10) NOT NULL DEFAULT '0',
  `VecesAcertada` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idPregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pregunta`
--

INSERT INTO `pregunta` (`idPregunta`, `texto`, `Resp1`, `Resp2`, `Resp3`, `Resp4`, `RespCorrecta`, `VecesPreguntada`, `VecesAcertada`) VALUES
(1, 'Pokemon: Nombre de la rata amarilla', 'Charmander', 'Pikachu', 'Squirtel', 'Ekans', 'Pikachu', 0, 0),
(2, 'Nombre del doctor Jones', 'Paco', 'Francisco', 'Indiana', 'Missisipi', 'Indiana', 0, 0),
(3, 'Sobrenatural: Apellido de Sam y Dean', 'Winchester', 'Beretta', 'Kate', 'Colt', 'Winchester', 0, 0),
(4, 'Alicia en el pais de las maravillas: nombre del gato', 'Triston', 'Rison', 'Cabron', 'Chiston', 'Rison', 0, 0),
(18, '¿Como se llama la tecnica especial de Rina en "Slayers"?', 'Droga de esclavos', 'Matadragones', 'Kame-Hame-Ha', 'Hadouken', 'Matadragones', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE IF NOT EXISTS `respuestas` (
  `NombreU` varchar(30) CHARACTER SET latin1 NOT NULL,
  `idPregunta` int(10) NOT NULL,
  `Fecha` date NOT NULL,
  `Acertada` int(1) NOT NULL,
  PRIMARY KEY (`NombreU`,`idPregunta`,`Fecha`),
  KEY `idPregunta` (`idPregunta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `nombre` varchar(30) CHARACTER SET latin1 NOT NULL,
  `passw` varchar(30) CHARACTER SET latin1 NOT NULL,
  `email` varchar(30) CHARACTER SET latin1 NOT NULL,
  `administrador` int(1) NOT NULL DEFAULT '0',
  `numPregAcert` int(10) NOT NULL DEFAULT '0',
  `numPregTotal` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`nombre`, `passw`, `email`, `administrador`, `numPregAcert`, `numPregTotal`) VALUES
('Aaron', 'noraA', 'aaron@ehu.es', 0, 1, 1),
('Admin', 'Admin', 'adm@ovbial.es', 1, 0, 0),
('David', 'divaD', 'david@ehu.es', 0, 1, 1),
('Egoitz', 'ztiogE', 'egoitz@ehu.es', 0, 0, 0),
('Endika', 'akidnE', 'endika@ehu.es', 0, 0, 1),
('Helen', 'neleH', 'helen@ehu.es', 0, 1, 1),
('Jon Ander', 'rednA noJ', 'jonander@ehu.es', 0, 0, 1),
('Oscar', 'oscar', 'os@hotmail.com', 0, 0, 0),
('Sara', 'araS', 'sara@ehu.es', 0, 10, 40);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `casillapregunta`
--
ALTER TABLE `casillapregunta`
  ADD CONSTRAINT `casillapregunta_ibfk_1` FOREIGN KEY (`numero`) REFERENCES `casilla` (`numero`),
  ADD CONSTRAINT `casillapregunta_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`idCategoria`);

--
-- Filtros para la tabla `casillaretencion`
--
ALTER TABLE `casillaretencion`
  ADD CONSTRAINT `casillaretencion_ibfk_1` FOREIGN KEY (`numero`) REFERENCES `casilla` (`numero`);

--
-- Filtros para la tabla `casillasalto`
--
ALTER TABLE `casillasalto`
  ADD CONSTRAINT `casillasalto_ibfk_1` FOREIGN KEY (`numero`) REFERENCES `casilla` (`numero`),
  ADD CONSTRAINT `casillasalto_ibfk_2` FOREIGN KEY (`numero`) REFERENCES `casilla` (`numero`);

--
-- Filtros para la tabla `infopartidas`
--
ALTER TABLE `infopartidas`
  ADD CONSTRAINT `infopartidas_ibfk_1` FOREIGN KEY (`idPartida`) REFERENCES `partida` (`idPartida`),
  ADD CONSTRAINT `infopartidas_ibfk_2` FOREIGN KEY (`nombreU`) REFERENCES `usuarios` (`nombre`),
  ADD CONSTRAINT `infopartidas_ibfk_3` FOREIGN KEY (`numeroC`) REFERENCES `casilla` (`numero`);

--
-- Filtros para la tabla `pregcat`
--
ALTER TABLE `pregcat`
  ADD CONSTRAINT `pregcat_ibfk_1` FOREIGN KEY (`idPregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE CASCADE,
  ADD CONSTRAINT `pregcat_ibfk_2` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`);

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `respuestas_ibfk_1` FOREIGN KEY (`NombreU`) REFERENCES `usuarios` (`nombre`) ON DELETE CASCADE,
  ADD CONSTRAINT `respuestas_ibfk_2` FOREIGN KEY (`idPregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
