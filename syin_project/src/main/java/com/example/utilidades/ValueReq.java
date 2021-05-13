package com.example.utilidades;
import com.google.gson.JsonObject;

public class ValueReq {
	private Boolean isRequired = false;
	private String is = "string";

	public ValueReq() {

	}

	public static ValueReq string() {
		ValueReq v = new ValueReq();
		v.is = "string";
		return v;
	}

	public static ValueReq integer() {
		ValueReq v = new ValueReq();
		v.is = "integer";
		return v;
	}

	public static ValueReq bigdecimal() {
		ValueReq v = new ValueReq();
		v.is = "bigdecimal";
		return v;
	}

	public static ValueReq bool() {
		ValueReq v = new ValueReq();
		v.is = "bool";
		return v;
	}

	public ValueReq required() {
		this.isRequired = true;
		return this;
	}

	public void validate(String key, JsonObject obj, String parent) throws Exception {
		try {
			Boolean ec = obj.has(key);

			if (this.isRequired && !ec) {
				throw new Exception("required");
			}

			if (ec) {

				switch (this.is) {
				case "string":
					String s = obj.get(key).getAsString();
					if (this.isRequired && s.isEmpty()) {
						throw new Exception("required");
					}
					break;
				case "integer":
					obj.get(key).getAsInt();
					break;
				case "bigdecimal":
					obj.get(key).getAsBigDecimal();
					break;

				case "bool":
					obj.get(key).getAsBoolean();
					break;
				}

			}

		} catch (Exception e) {
			parent = (parent.equals("") ? "" : parent + ".");
			if (e.getMessage().equals("required")) {
				throw new Exception(parent + key + " es requerido");
			} else {
				throw new Exception(parent + key + " no es un " + this.is);
			}
		}

	}
}
