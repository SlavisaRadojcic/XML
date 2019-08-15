package com.megatravel.mikroserviskorisnici.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroserviskorisnici.model.Korisnik;

@Repository
public interface KorisnikRepozitorijum extends JpaRepository<Korisnik, Long> { }
