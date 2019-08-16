package com.megatravel.mikroservissmestajnejedinice.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;

@Repository
public interface RezervacijaRepozitorijum extends JpaRepository<Rezervacija, Long> { }
