package com.isorest.postilion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author Quincy M.
 * This class configures the settings from the .ini file
 */
public class Api2PostilionConfig {

	public static String serverIpAddress; 
	public static String serverName;
	public static int serverPort;
	public static int readTimeOut;
	public static int expectedRespLen; 

	public static void init() {
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {	 
			input = new FileInputStream("C:/StewardBank/config/rest_postilion.ini");
	 
			// load a properties file
			prop.load(input);			
	 
			serverIpAddress = prop.getProperty("realtimeServer");
			serverPort =  Integer.parseInt( prop.getProperty("realtimeServerPort") );	
			readTimeOut = Integer.parseInt( prop.getProperty("readTimeOut") );
			expectedRespLen = Integer.parseInt( prop.getProperty("expectedRespLen") );
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
