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

import com.example.usuario.UsuarioBL;
import com.example.utilidades.Helpers;
import com.example.utilidades.ValidateReq;
import com.example.utilidades.ValueReq;

@Controller
@RequestMapping(path = "/", produces = "application/json; charset=utf-8")


public class UsuarioController {
	
	@GetMapping(value = "/usuario/{id}",  params = {  "id_organizaciones" })
	public ResponseEntity<String> usuario_perfiles(@PathVariable("id") Integer id, @RequestParam("id_organizaciones") Integer id_organizaciones) {
		try {
			return Helpers.done(UsuarioBL.usuario_perfil(id,id_organizaciones));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	} 

	
	
	@GetMapping(value = "/usuarios")
	public ResponseEntity<String> index(@RequestParam Map<String,String> params) {
		try {
			return Helpers.done(UsuarioBL.index());
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<String> show(@PathVariable("id") Integer id) {
		try {
			return Helpers.done(UsuarioBL.show(id));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}
	
	

	@PostMapping(value = "/usuario",consumes = "application/json")
	public ResponseEntity<String> create(@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("nombres", ValueReq.string().required());
			form.add("apellidos", ValueReq.string().required());
			form.add("correo", ValueReq.string().required());
			form.add("id_perfiles", ValueReq.integer().required());
			form.add("id_organizaciones", ValueReq.integer().required());
			form.add("id_ctlg_estados", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(UsuarioBL.create(json));
		}catch(Exception e) {
			e.getStackTrace();
			return Helpers.fail(e.getMessage());
		}

	}

	@PutMapping(value = "/usuario/{id}",consumes = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Integer id,@RequestBody String json) {
		try {
			ValidateReq form = new ValidateReq();
			form.add("nombres", ValueReq.string().required());
			form.add("apellidos", ValueReq.string().required());
			form.add("correo", ValueReq.string().required());
			form.add("id_perfiles", ValueReq.integer().required());
			form.add("id_organizaciones", ValueReq.integer().required());
			form.add("id_ctlg_estados", ValueReq.integer().required());


			form.validate(json);
			return Helpers.done(UsuarioBL.update(id,json));
		}catch(Exception e) {
			return Helpers.fail(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/usuario/{id}")
	public ResponseEntity<String>delete(@PathVariable("id")Integer id){
		try {
			return Helpers.done(UsuarioBL.delete(id));
			}
		catch(Exception e) {
			return Helpers.fail(e.getMessage());
			}
		}
	
}
