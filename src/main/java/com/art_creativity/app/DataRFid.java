package com.art_creativity.app;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

@Entity
public class DataRFid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4638260019253023491L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany
	private List<EPC>epcs;
	private String alarm;
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date date;
	
	public List<EPC> getEpcs() {
		return epcs;
	}
	public void setEpcs(List<EPC> epcs) {
		this.epcs = epcs;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public DataRFid() {
		// TODO Auto-generated constructor stub
	}

}
