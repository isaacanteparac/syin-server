package com.example.utilidades;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Helpers {

	public static ResponseEntity<String> done(Object object) {
		Gson gson = new Gson();
		return new ResponseEntity<String>(gson.toJson(object), HttpStatus.OK);
	}


	public static ResponseEntity<String> fail(String message) {
		JsonObject json=new JsonObject();
		json.addProperty("error", true);
		json.addProperty("message", message);
		return new ResponseEntity<String>(json.toString(), HttpStatus.BAD_REQUEST);
	}


	public static <T> Object JsonToObject(String json,Class<T> object)  {
		Gson googleJson = new Gson();
		return googleJson.fromJson(json, object);
	}




}
