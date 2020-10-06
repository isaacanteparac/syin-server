#organizaciones por usuario
/*SELECT
	organizaciones.nombre as organizacion,
	CONCAT(usuario.nombres," ",usuario.apellidos) as nombres_apellidos,
	ctlg_estados.nombre  as estados,
	perfiles.nombre as trabajo
FROM organizaciones
	INNER JOIN usuario on usuario.id_organizaciones = organizaciones.id 
	INNER JOIN ctlg_estados on usuario.id_ctlg_estados = ctlg_estados.id and organizaciones.id_ctlg_estados = ctlg_estados.id
	INNER JOIN perfiles on usuario.id_perfiles = perfiles.id and organizaciones.id = perfiles.id_organizaciones and perfiles.id_ctlg_estados = ctlg_estados.id
WHERE organizaciones.id = 1 and usuario.id = 1;*/




#productos
/*SELECT
	organizaciones.nombre as organizacion,
	productos.nombre as productos,
	productos.codigo as codigo,
	productos.imagen as imagen,
	productos.cantidad_min as cantidad_min,
	productos.cantidad_maxima as cantidad_max,
	productos.descripcion as descripcion,
	ctlg_tipos_productos.nombre as tipos,
	ctlg_unidades_medidas.nombre as simbolo,
	ctlg_metodos_inventario.nombre as metodos_inventario,
	ctlg_iva.nombre as iva,
	ctlg_estados.nombre as estado
FROM productos
	INNER JOIN organizaciones on productos.id_organizaciones = organizaciones.id
	INNER JOIN ctlg_tipos_productos on productos.id_ctlg_tipos_productos = ctlg_tipos_productos.id
	INNER JOIN ctlg_unidades_medidas on productos.id_ctlg_unidades_medidas = ctlg_unidades_medidas.id
	INNER JOIN ctlg_metodos_inventario on productos.id_ctlg_metodos_inventario = ctlg_metodos_inventario.id
	INNER JOIN ctlg_iva on productos.id_ctlg_iva = ctlg_iva.id
	INNER JOIN ctlg_estados on productos.id_ctlg_estados = ctlg_estados.id
WHERE organizaciones.id = 1 and provedor.id = 1;*/





#metodo inventario
/*SELECT
	organizaciones.nombre as organizacion,
	ctlg_metodos_inventario.nombre as metdodo_inventario,
	productos.nombre as producto,
	productos.codigo as codigo

FROM ctlg_metodos_inventario
	INNER JOIN productos on ctlg_metodos_inventario.id = productos.id_ctlg_metodos_inventario
	INNER JOIN organizaciones on organizaciones.id = productos.id_organizaciones
WHERE organizaciones.id = 2;*/




#producto por organizacion
/*SELECT
	organizaciones.nombre as organizacion,
	productos.nombre as productos,
	productos.codigo as codigo,
	productos.descripcion as descripcion,
	productos.cantidad_min as cantidad_min,
	productos.cantidad_maxima as cantidad_max,
	ctlg_metodos_inventario.nombre as metodos_inventario
FROM organizaciones
	INNER JOIN productos on organizaciones.id = productos.id_organizaciones
	INNER JOIN ctlg_metodos_inventario on ctlg_metodos_inventario.id = productos.id_ctlg_metodos_inventario
WHERE organizaciones.id = 2;*/



#TRANSACion
/*SELECT
	organizaciones.nombre as nombre,
	ctlg_tipos_transacion.nombre as transacion,
	encabezado_comprobante.num_comprovante as num_comprobante
FROM ctlg_tipos_transacion
	INNER JOIN encabezado_comprobante on ctlg_tipos_transacion.id = encabezado_comprobante.id_ctlg_tipos_transacion
	INNER JOIN organizaciones on organizaciones.id = encabezado_comprobante.id_organizacion
WHERE organizaciones.id = 1 and ctlg_tipos_transacion.id=2;*/




#tipo producto
/*SELECT
	organizaciones.nombre as organizacion,
	ctlg_tipos_productos.nombre as tipo,
	productos.nombre as producto,
	productos.codigo as codigo

FROM ctlg_tipos_productos
	INNER JOIN productos on ctlg_tipos_productos.id = productos.id_ctlg_tipos_productos
	INNER JOIN organizaciones on organizaciones.id = productos.id_organizaciones
WHERE organizaciones.id = 1 and id_ctlg_tipos_productos = 2 ;*/



#usuarios activos desact
/*SELECT
	organizaciones.nombre as org,
	ctlg_estados.nombre as estados,
	CONCAT(usuario.nombres," ",usuario.apellidos) as nombres
FROM ctlg_estados 
	INNER JOIN usuario on ctlg_estados.id = usuario.id_ctlg_estados
	INNER JOIN organizaciones on organizaciones.id = usuario.id_organizaciones
WHERE organizaciones.id = 1  and ctlg_estados.id = 2;*/



#busqueda x iva
/*SELECT
	organizaciones.nombre as org,
	ctlg_iva.nombre as iva,
	productos.nombre as productos
FROM ctlg_iva
	INNER JOIN  productos on ctlg_iva.id = productos.id_ctlg_iva
	INNER JOIN organizaciones on organizaciones.id = productos.id_organizaciones
WHERE organizaciones.id = 2 and ctlg_iva.id = 1;*/















