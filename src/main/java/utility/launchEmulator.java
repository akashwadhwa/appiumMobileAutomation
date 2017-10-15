package utility;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class launchEmulator {
	
	private static String sdkPath = "/Users/akashwadhwa89/Library/Android/sdk/";
	private static String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
	private static String emulatorPath = sdkPath + "tools" + File.separator + "emulator";
	
	/**
	 * Starts an emulator for the provided AVD name
	 * 
	 * @param nameOfAVD
	 */
	public static void launchAndroidEmulator(String nameOfAVD) {
	 System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
	 String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
	 try {
	  Process process = new ProcessBuilder(aCommand).start();
	  process.waitFor(30, TimeUnit.SECONDS);
	  System.out.println("Emulator launched successfully!");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	}
	
	/**
	 * Kills all running emulators
	 */
	public static void closeAndroidEmulator() {
	 System.out.println("Killing emulator...");
	 String[] aCommand = new String[] { adbPath, "emu", "kill" };
	 try {
	  Process process = new ProcessBuilder(aCommand).start();
	  process.waitFor(5, TimeUnit.SECONDS);
	  System.out.println("Emulator closed successfully!");
	 } catch (Exception e) {
	  e.printStackTrace();
	 }
	}
	
}
