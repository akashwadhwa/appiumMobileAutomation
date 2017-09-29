package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils extends Properties{

	private Properties prop = null;

	public PropertyUtils(){
		InputStream is = null;
		try {
			this.prop = new Properties();
			is = this.getClass().getResourceAsStream("/config/config.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getPropertyValue(String key){
		return this.prop.getProperty(key);
	}
	
	public static void main(String a[]){
        
		PropertyUtils mpc = new PropertyUtils();
        System.out.println("db.host: "+mpc.getPropertyValue("devices"));
    }

}
