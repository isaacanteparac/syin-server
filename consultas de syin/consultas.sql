/*SELECT 
usuario.id,ctlg_tipos_transacion.CONCAT(usuario.nombres," ",usuario.apellidos)as nombres, 
usuario.correo,
organizaciones.nombre as organizaciones,
perfiles.nombre as perfiles
FROM usuario
INNER JOIN organizaciones ON organizaciones.id = usuario.id_organizaciones
INNER JOIN perfiles ON perfiles.id = usuario.id_perfiles
INNER JOIN ctlg_estados ON ctlg_estados.id = usuario.id_ctlg_estados
WHERE organizaciones.id =  AND perfiles.id=1 and usuario.deleted IS NULL;*/

/*	UPDATE usuario set 
	updated= NULL
	where id= 3 ;*/
	
	/*DELETE from usuario where id=3;*/
	
/*SELECT 
	provedor.nombre,
	productos.nombre as productos
FROM provedor
	INNER JOIN productos_provedores on productos_provedores.id_provedor = provedor.id
	INNER JOIN productos on productos.id = productos_provedores.id_productos
WHERE provedor.id = 1 */


/*
SELECT 
productos.id,
	productos.nombre as productos,
	provedor.nombre as provedor,
	provedor.id as id_pro,
	ctlg_tipos_transacion.nombre as transaccion,
	

	SUM(detalle_comprobante.cantidad) as cantidad_total
	
	
	
FROM productos
	INNER JOIN productos_provedores 
		on productos_provedores.id_productos = productos.id
		
	INNER JOIN provedor 
		on provedor.id = productos_provedores.id_provedor
	inner join detalle_comprobante on detalle_comprobante.id_productos= productos.id and provedor.id = detalle_comprobante.id_provedor
	inner JOIN  encabezado_comprobante on detalle_comprobante.id_encabezado_comprobante = encabezado_comprobante.id
	INNER JOIN ctlg_tipos_transacion on ctlg_tipos_transacion.id= encabezado_comprobante.id_ctlg_tipos_transacion
	
WHERE productos.id  = 1 GROUP BY provedor.id , ctlg_tipos_transacion.id  
 ;

*/

 


