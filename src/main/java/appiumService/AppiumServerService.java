package appiumService;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServerService {
	
	private static AppiumDriverLocalService service;
	
	public static void startAppium(String ipAddress, int portNumber) {
		String osName = System.getProperty("os.name").toLowerCase();

		String nodePath = null;
		String appiumPath = null;

		if (osName.contains("mac")) {
			nodePath = "/usr/local/bin/node";
			appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
		} else if (osName.contains("linux")) {
			nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
			appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
		}else if (osName.contains("windows")) {
			nodePath = "C:/Program Files/Appium/node.exe";
			appiumPath = "C:/Program Files/Appium/node_modules/appium/bin/appium.js";
		}
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(nodePath))
				.withAppiumJS(new File(appiumPath))
				.withIPAddress(ipAddress)
				.usingPort(portNumber));
		System.out.println("Starting Appium server"); 
		service.start();
	}
	
	public static void stopAppium() {
		service.stop();
	}

}
