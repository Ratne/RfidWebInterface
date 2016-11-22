package com.art_creativity.app;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.art_creativity.Rfid3Application;

@Controller
public class ParameterController {

	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String param(Model model){
		model.addAttribute("address", Rfid3Application.numRfid);
		model.addAttribute("port", Rfid3Application.port);
		model.addAttribute("addressIp", Rfid3Application.address);
		model.addAttribute("password", Rfid3Application.password);
		return "param";
	}
	
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String save(Model model, @RequestParam(value="address", required=true) String address,
			@RequestParam(value="port", required=true) int port,
			@RequestParam(value="addressIp", required=true) String addressIp,
			@RequestParam(value="password", required=true) String password){
		model.addAttribute("address", address);
		model.addAttribute("port", port);
		model.addAttribute("addressIp", addressIp);
		model.addAttribute("password", password);
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(getClass().getResource("/config.ini").getPath())));
			out.write((address + " "+port + " "+addressIp+ " "+password).getBytes());
			out.flush();
			Rfid3Application.address = addressIp;
			Rfid3Application.numRfid = address;
			Rfid3Application.port = port;
			Rfid3Application.password = password;
			MetierController.password = password;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "param";
	}
}
