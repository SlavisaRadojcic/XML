package com.megatravel.mikroservissmestajnejedinice.repozitorijumi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;

@Repository
public interface SmestajRepozitorijum extends JpaRepository<Smestaj, Long> { }
