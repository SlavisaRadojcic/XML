package com.megatravel.mikroservissmestajnejedinice.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservissmestajnejedinice.model.Adresa;

@Repository
public interface AdresaRepozitorijum extends JpaRepository<Adresa, Long> {

}
