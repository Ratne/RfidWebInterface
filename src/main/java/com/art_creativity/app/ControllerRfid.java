package com.art_creativity.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art_creativity.Rfid3Application;

@RestController
public class ControllerRfid {

	@Autowired
	private IMetierController metierController;
	
	@RequestMapping("/")
	public DataRFid read(){
		return metierController.readerRfid(Integer.parseInt(Rfid3Application.numRfid, 16), Rfid3Application.port, Rfid3Application.address);
	}
}
