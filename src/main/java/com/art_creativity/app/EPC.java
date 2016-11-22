package com.art_creativity.app;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EPC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4880909440148140829L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tag;
	private String dataRead;
	private String tampered;

	public EPC() {
		// TODO Auto-generated constructor stub
	}


	public EPC(String tag, String dataRead, String tampered) {
		super();
		this.tag = tag;
		this.dataRead = dataRead;
		this.tampered = tampered;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTampered() {
		return tampered;
	}

	public void setTampered(String tampered) {
		this.tampered = tampered;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDataRead() {
		return dataRead;
	}

	public void setDataRead(String dataRead) {
		this.dataRead = dataRead;
	}

}
