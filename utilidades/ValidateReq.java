package com.example.utilidades;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class ValidateReq {

	private HashMap<String, Object> struct = new HashMap<String, Object>();
	private Boolean isRequired = false;

	public ValidateReq() {

	}

	public ValidateReq required() {
		this.isRequired = true;
		return this;
	}

	public Boolean isRequired() {
		return this.isRequired;
	}

	public ValidateReq add(String key, ValueReq value) {
		struct.put(key, value);
		return this;

	}

	public ValidateReq add(String key, ValidateReq value) {
		struct.put(key, value);
		return this;

	}

	private void resolver(JsonObject obj, String parent) throws Exception {
		for (Map.Entry<String, Object> entry : this.struct.entrySet()) {
			if (entry.getValue() instanceof ValueReq) {
				((ValueReq) entry.getValue()).validate(entry.getKey(), obj, parent);
			} else if (entry.getValue() instanceof ValidateReq) {

				Boolean ec = obj.has(entry.getKey());

				if (!ec && ((ValidateReq) entry.getValue()).isRequired()) {
					throw new Exception(entry.getKey() + " es requerido");
				}

				if (ec) {
					((ValidateReq) entry.getValue()).validateObj(obj.get(entry.getKey()).getAsJsonObject(),
							entry.getKey());
				}

			}
		}
	}

	private void validateObj(JsonObject obj, String parent) throws Exception {
		this.resolver(obj, parent);
	}

	public void validate(String s) throws Exception {
		Gson g = new Gson();
		JsonObject obj = g.fromJson(s, JsonObject.class);
		this.resolver(obj, "");

	}

}
