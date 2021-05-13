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

import com.example.encabezado_comprobante.Encabezado_comprobanteBL;
import com.example.utilidades.Helpers;
import com.example.utilidades.ValidateReq;
import com.example.utilidades.ValueReq;

@Controller
@RequestMapping(path = "/", produces = "application/json; charset=utf-8")


public class EncabezadocomprobanteController {
	@GetMapping(value = "/encabezado-comprobantes")
	public ResponseEntity<String> index(@RequestParam Map<String,String> params) {
		try {
			return Helpers.done(Encabezado_comprobanteBL.index());
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@GetMapping("/encabezado-comprobante/{id}")
	public ResponseEntity<String> show(@PathVariable("id") Integer id) {
		try {
			return Helpers.done(Encabezado_comprobanteBL.show(id));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@PostMapping(value = "/encabezado-comprobante",consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("num_comprovante", ValueReq.string().required());
			form.add("id_organizacion", ValueReq.integer().required());
			form.add("id_usuario", ValueReq.integer().required());
			form.add("id_ctlg_tipos_comprobante", ValueReq.integer().required());
			form.add("id_ctlg_tipos_transacion", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(Encabezado_comprobanteBL.create(json));
		}catch(Exception e) {
			e.getStackTrace();
			return Helpers.fail(e.getMessage());
		}

	}

	@PutMapping(value = "/encabezado-comprobante/{id}",consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("num_comprovante", ValueReq.string().required());
			form.add("id_organizacion", ValueReq.integer().required());
			form.add("id_usuario", ValueReq.integer().required());
			form.add("id_ctlg_tipos_comprobante", ValueReq.integer().required());
			form.add("id_ctlg_tipos_transacion", ValueReq.integer().required());



			form.validate(json);
			return Helpers.done(Encabezado_comprobanteBL.update(id,json));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}

	}
}
