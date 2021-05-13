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

import com.example.provedor.ProvedorBL;
import com.example.utilidades.Helpers;
import com.example.utilidades.ValidateReq;
import com.example.utilidades.ValueReq;

@Controller
@RequestMapping(path = "/", produces = "application/json; charset=utf-8")


public class ProvedorController {
	@GetMapping(value = "/provedores")
	public ResponseEntity<String> index(@RequestParam Map<String,String> params) {
		try {
			return Helpers.done(ProvedorBL.index());
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@GetMapping("/provedor/{id}")
	public ResponseEntity<String> show(@PathVariable("id") Integer id) {
		try {
			return Helpers.done(ProvedorBL.show(id));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@PostMapping(value = "/provedor",consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("nombre", ValueReq.string().required());
			form.add("telefono", ValueReq.string().required());
			form.add("correo", ValueReq.string().required());
			form.add("ubicacion", ValueReq.string().required());
			form.add("id_organizacion", ValueReq.integer().required());
			form.add("id_ctlg_estados", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(ProvedorBL.create(json));
		}catch(Exception e) {
			e.getStackTrace();
			return Helpers.fail(e.getMessage());
		}

	}

	@PutMapping(value = "/provedor/{id}",consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("nombre", ValueReq.string().required());
			form.add("telefono", ValueReq.string().required());
			form.add("correo", ValueReq.string().required());
			form.add("ubicacion", ValueReq.string().required());
			form.add("id_organizacion", ValueReq.integer().required());
			form.add("id_ctlg_estados", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(ProvedorBL.update(id,json));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}
	@DeleteMapping(value = "/provedor/{id}")
	public ResponseEntity<String>delete(@PathVariable("id")Integer id){
		try {
			
			return Helpers.done(ProvedorBL.delete(id));
			}
		catch(Exception e) {
			return Helpers.fail(e.getMessage());
			}
		}
	
}
