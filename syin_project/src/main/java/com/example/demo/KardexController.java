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

import com.example.kardex.KardexBL;
import com.example.utilidades.Helpers;
import com.example.utilidades.ValidateReq;
import com.example.utilidades.ValueReq;



@Controller
@RequestMapping(path = "/", produces = "application/json; charset=utf-8")


public class KardexController {
	@GetMapping(value = "/kardexs")
	public ResponseEntity<String> index(@RequestParam Map<String,String> params) {
		try {
			return Helpers.done(KardexBL.index());
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@GetMapping("/kardex/{id}")
	public ResponseEntity<String> show(@PathVariable("id") Integer id) {
		try {
			return Helpers.done(KardexBL.show(id));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@PostMapping(value = "/kardex",consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("cantidad", ValueReq.bigdecimal().required());
			form.add("precio_unitario", ValueReq.bigdecimal().required());
			form.add("id_detalle_comprobante", ValueReq.integer().required());
			form.validate(json);
			return Helpers.done(KardexBL.create(json));
		}catch(Exception e) {
			e.getStackTrace();
			return Helpers.fail(e.getMessage());
		}

	}

	@PutMapping(value = "/kardex/{id}",consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("cantidad", ValueReq.bigdecimal().required());
			form.add("precio_unitario", ValueReq.bigdecimal().required());
			form.add("id_detalle_comprobante", ValueReq.integer().required());
			form.validate(json);
			return Helpers.done(KardexBL.update(id,json));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/kardex/{id}")
	public ResponseEntity<String>delete(@PathVariable("id")Integer id){
		try {
			return Helpers.done(KardexBL.delete(id));
			}
		catch(Exception e) {
			return Helpers.fail(e.getMessage());
			}
		}
}
