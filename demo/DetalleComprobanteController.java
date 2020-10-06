package com.example.demo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.detalle_comprobante.Detalle_comprobanteBL;
import com.example.utilidades.Helpers;
import com.example.utilidades.ValidateReq;
import com.example.utilidades.ValueReq;

@Controller
@RequestMapping(path = "/", produces = "application/json; charset=utf-8")


public class DetalleComprobanteController {
	@GetMapping(value = "/detalle-comprobantes")
	public ResponseEntity<String> index(@RequestParam Map<String,String> params) {
		try {
			return Helpers.done(Detalle_comprobanteBL.index());
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@GetMapping("/detalle-comprobante/{id}")
	public ResponseEntity<String> show(@PathVariable("id") Integer id) {
		try {
			return Helpers.done(Detalle_comprobanteBL.show(id));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@PostMapping(value = "/detalle-comprobante",consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("cantidad", ValueReq.integer().required());
			form.add("precio", ValueReq.bigdecimal().required());
			form.add("total", ValueReq.bigdecimal().required());

			form.add("id_productos", ValueReq.integer().required());
			form.add("id_provedor", ValueReq.integer().required());
			form.add("id_encabezado_comprobante", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(Detalle_comprobanteBL.create(json));
		}catch(Exception e) {
			e.getStackTrace();
			return Helpers.fail(e.getMessage());
		}

	}

	@PutMapping(value = "/detalle-comprobante/{id}",consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("cantidad", ValueReq.integer().required());
			form.add("precio", ValueReq.bigdecimal().required());
			form.add("total", ValueReq.bigdecimal().required());

			
			form.add("id_productos", ValueReq.integer().required());
			form.add("id_provedor", ValueReq.integer().required());
			form.add("id_encabezado_comprobante", ValueReq.integer().required());



			form.validate(json);
			return Helpers.done(Detalle_comprobanteBL.update(id,json));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}

	}
}

