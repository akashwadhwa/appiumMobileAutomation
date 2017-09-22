package config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class deviceConfig {

	public final Map<String, Object> deviceMap;
	 public final String url;

	public deviceConfig() {
		
	    // todo: will need to uniform url for selenium/appium
	    this.url = System.getProperty("appium", "http://0.0.0.0:4723/wd/hub");
	    
		// changes device in use
		String deviceName = System.getProperty("deviceName", "iPhone 6s");
		this.deviceMap = getDevice(deviceName);
	}

	private HashMap<String, Object> getDevice(String device) {

		Type hashType = new TypeToken<HashMap<String, Object>>() {}.getType();
		Gson gson = new Gson();

		InputStream deviceFile = getClass().getResourceAsStream("/devices/devices.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(deviceFile));

		JsonParser parser = new JsonParser();

		JsonElement jsonElement = parser.parse(reader).getAsJsonObject().get(device);

		return gson.fromJson(jsonElement, hashType);
	}
	

}
