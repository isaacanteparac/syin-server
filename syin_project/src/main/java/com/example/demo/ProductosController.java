package com.example.demo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.productos.ProductosBL;
import com.example.utilidades.Helpers;
import com.example.utilidades.ValidateReq;
import com.example.utilidades.ValueReq;

@Controller
@RequestMapping(path = "/", produces = "application/json; charset=utf-8")


public class ProductosController {
	@GetMapping(value = "/productos")
	public ResponseEntity<String> index(@RequestParam Map<String,String> params) {
		try {
			return Helpers.done(ProductosBL.index());
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@GetMapping("/producto/{id}")
	public ResponseEntity<String> show(@PathVariable("id") Integer id) {
		try {
			return Helpers.done(ProductosBL.show(id));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@PostMapping(value = "/producto",consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("nombre", ValueReq.string().required());
			form.add("cantidad_min", ValueReq.bigdecimal().required());
			form.add("cantidad_maxima", ValueReq.bigdecimal().required());
			form.add("descripcion", ValueReq.string().required());
			form.add("imagen", ValueReq.string().required());
			form.add("codigo", ValueReq.string().required());
			form.add("id_organizaciones", ValueReq.integer().required());
			form.add("id_ctlg_estados", ValueReq.integer().required());
			form.add("id_ctlg_metodos_inventario", ValueReq.integer().required());
			form.add("id_ctlg_unidades_medidas", ValueReq.integer().required());
			form.add("id_ctlg_tipos_productos", ValueReq.integer().required());
			form.add("id_ctlg_iva", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(ProductosBL.create(json));
		}catch(Exception e) {
			e.getStackTrace();
			return Helpers.fail(e.getMessage());
		}

	}

	@PutMapping(value = "/producto/{id}",consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("nombre", ValueReq.string().required());
			form.add("cantidad_min", ValueReq.bigdecimal().required());
			form.add("cantidad_maxima", ValueReq.bigdecimal().required());
			form.add("descripcion", ValueReq.string().required());
			form.add("imagen", ValueReq.string().required());
			form.add("codigo", ValueReq.string().required());
			form.add("id_organizaciones", ValueReq.integer().required());
			form.add("id_ctlg_estados", ValueReq.integer().required());
			form.add("id_ctlg_metodos_inventario", ValueReq.integer().required());
			form.add("id_ctlg_unidades_medidas", ValueReq.integer().required());
			form.add("id_ctlg_tipos_productos", ValueReq.integer().required());
			form.add("id_ctlg_iva", ValueReq.integer().required());




			form.validate(json);
			return Helpers.done(ProductosBL.update(id,json));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/producto/{id}")
	public ResponseEntity<String>delete(@PathVariable("id")Integer id){
		try {
			return Helpers.done(ProductosBL.delete(id));
		}
	catch(Exception e) {
		return Helpers.fail(e.getMessage());
		}
	}

}
