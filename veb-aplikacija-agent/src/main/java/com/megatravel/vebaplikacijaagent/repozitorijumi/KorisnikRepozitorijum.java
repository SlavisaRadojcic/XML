package com.megatravel.vebaplikacijaagent.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.vebaplikacijaagent.model.Korisnik;

@Repository
public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> {

}
