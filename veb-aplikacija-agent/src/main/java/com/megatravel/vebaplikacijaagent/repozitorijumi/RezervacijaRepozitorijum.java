package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Rezervacija;

@Repository
public interface RezervacijaRepozitorijum extends JpaRepository<Rezervacija, Long> {

}
