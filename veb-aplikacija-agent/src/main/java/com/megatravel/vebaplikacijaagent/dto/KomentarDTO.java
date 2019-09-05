package com.megatravel.vebaplikacijaagent.dto;

import com.megatravel.vebaplikacijaagent.model.Komentar;

public class KomentarDTO {

    private Long id;
    private String tekst;
    private boolean odobren;
	
    public KomentarDTO(Komentar komentar) {
    	this.id = komentar.getId();
    	this.tekst = komentar.getTekst();
    	this.odobren = komentar.isOdobren();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

}
