package com.art_creativity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.art_creativity.app.MetierController;

@SpringBootApplication
public class Rfid3Application {
	
	public static String address = "192.168.8.120";
	public static int port = 1024;
	public static String numRfid = "FF";
	public static String password = "00000000";

	public static void main(String[] args) {
		SpringApplication.run(Rfid3Application.class, args);
	}
	@PostConstruct
	public void initPost() {
		// TODO Auto generated method stub
		try {
			
			BufferedReader in = new BufferedReader(
	                new InputStreamReader(getClass().getResourceAsStream("/config.ini")));
	        String inputLine;
	        String response = "";
	        try {
	            while((inputLine=in.readLine())!=null){
	                response += inputLine;
	            }
	        } catch (IOException ex) {
	            Logger.getLogger(Rfid3Application.class.getName()).log(Level.SEVERE, null, ex);
	        }finally{
	        	try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        numRfid = response.split(" ")[0];
	        //System.out.println("num2 = "+Integer.parseInt(numRfid, 16));
	        port = ( Integer.parseInt(response.split(" ")[1]));
	        //System.out.println("port = "+port);
	        address = (response.split(" ")[2]);
	        //System.out.println("address = "+address);
	        password = (response.split(" ")[3]);
//	        metier = new MetierController();
	        String path = getClass().getResource("/UHF/Basic.dll").getPath();
			path = java.net.URLDecoder.decode(path);
	           System.load(path);
			path = getClass().getResource("/UHF/UHF_Reader18.dll").getPath();
			path = java.net.URLDecoder.decode(path);
	           System.load(path);
	        MetierController.password = password;
	       } catch (UnsatisfiedLinkError ex) {
//	         errorDll = ex.getMessage();
	         System.err.println(""+ex);
	       }
	}
}
