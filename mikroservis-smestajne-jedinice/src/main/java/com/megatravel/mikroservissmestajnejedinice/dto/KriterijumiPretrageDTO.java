package com.megatravel.mikroservissmestajnejedinice.dto;

import java.util.Date;

public class KriterijumiPretrageDTO {

	private String zemlja;
	private String grad;
	private int brojLjudi;
	private Date pocetak;
	private Date kraj;
	public String getZemlja() {
		return zemlja;
	}
	public void setZemlja(String zemlja) {
		this.zemlja = zemlja;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public int getBrojLjudi() {
		return brojLjudi;
	}
	public void setBrojLjudi(int brojLjudi) {
		this.brojLjudi = brojLjudi;
	}
	public Date getPocetak() {
		return pocetak;
	}
	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}
	public Date getKraj() {
		return kraj;
	}
	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
	
	
}
